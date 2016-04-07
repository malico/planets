package ar.com.mercadolibre.planets.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercadolibre.planets.domain.Betasoide;
import ar.com.mercadolibre.planets.domain.Ferengi;
import ar.com.mercadolibre.planets.domain.ForecastSummary;
import ar.com.mercadolibre.planets.domain.Planet;
import ar.com.mercadolibre.planets.domain.TheSun;
import ar.com.mercadolibre.planets.domain.Vulcano;
import ar.com.mercadolibre.planets.domain.WeatherCondition;
import ar.com.mercadolibre.planets.domain.Forecast;
import ar.com.mercadolibre.planets.domain.ForecastRepository;

/**
 * Service for the weather forecast.
 * @author malico
 */
@Service("forecastService")
@Transactional
public class ForecastService {

	/** The weather registers repository.*/
	@Autowired
	private ForecastRepository repository;
	
	  private static final Logger LOG = getLogger(ForecastRepository.class);

	/**
	 * Predicts the weather for the given day.
	 * @param day The day which is going to forecast the weather.
	 * @return the Forecast result, never null.
	 */
	public Forecast forecast(final int day) {
		
		LOG.info("Starting forecast for day {}.", day);
		Forecast result;

		result = repository.byDay(day);
		if (result != null) {
			LOG.info("Leaving forecast for day {}. Found on Database", day);
			return result;
		}

		Ferengi ferengi = Ferengi.getInstance();
		ferengi.move(day);
		Vulcano vulcano = Vulcano.getInstance();
		vulcano.move(day);
		Betasoide betasoide = Betasoide.getInstance();
		betasoide.move(day);
		WeatherCondition weather = forecast(ferengi, vulcano, betasoide);
		
		BigDecimal mmRained = BigDecimal.ZERO;
		if (WeatherCondition.RAIN.equals(weather)) {
			// If rains, the amount of millimeters must be calculated.
			mmRained = MathUtils.calculateTrianglePerimeter(ferengi.getX(),
					ferengi.getY(), vulcano.getX(), vulcano.getY(), betasoide.getX(), betasoide.getY());
		}
		
		result = new Forecast(day, ferengi.getX(), ferengi.getY(), vulcano.getX(), vulcano.getY(),
				betasoide.getX(), betasoide.getY(), weather, mmRained);
		repository.save(result);
		LOG.info("Leaving forecast for day {}. New forecast", day);
		return result;
	}

	/**
	 * Predicts the weather condition based on the three planets positions.
	 * 
	 * NOTES:
	 * 
	 * <li>If the triangle (p1, p2, p3) is equals to 0, then the planets are
	 * aligned. This will be a DROUGHT day</li>
	 * <li>If the planets are aligned and the the triangle (p1, p2, sun) is
	 * equals to 0, then the planets are also aligned with the Sun. This will be
	 * a OPTIMAL day</li>
	 * <li>If the triangle (p1, p2, p3) contains the point (0,0) -The Sun-, the
	 * day will be RAINY. Otherwise, the day will be CLEAR.</li>
	 * 
	 * @param p1
	 *            the first Planet, cannot be null.
	 * @param p2
	 *            the second Planet, cannot be null.
	 * @param p3
	 *            the last Planet, cannot be null.
	 * @return a WeatherCondition, never null.
	 */
	WeatherCondition forecast(final Planet p1, final Planet p2, final Planet p3) {
		Validate.notNull(p1, "The first planet cannot be null.");
		Validate.notNull(p2, "The second planet cannot be null.");
		Validate.notNull(p3, "The third planet cannot be null.");

		if (MathUtils.equals(
				MathUtils.calculateTriangleArea(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY()),
				BigDecimal.ZERO)) {
			if (MathUtils.equals(MathUtils.calculateTriangleArea(p1.getX(), p1.getY(), p2.getX(), p2.getY(),
					TheSun.getInstance().getX(), TheSun.getInstance().getY()), BigDecimal.ZERO)) {
				return WeatherCondition.DROUGHT;
			} else {
				return WeatherCondition.OPTIMAL;
			}
		}
		if (willRain(p1, p2, p3)) {
			return WeatherCondition.RAIN;
		} else {
			return WeatherCondition.CLEAR;
		}
	}

	/**
	 * Predicts if the day will be rainy.
	 * 
	 * IMPLEMENTATION NOTE: It will rain if the triangle(p1, p2, p3) ==
	 * (triangle(p1, p2, Sun)+triangle(p1, Sun, p3)+triangle(Sun, p2, p3))
	 * 
	 * @param p1
	 *            the first Planet, cannot be null.
	 * @param p2
	 *            the second Planet, cannot be null.
	 * @param p3
	 *            the last Planet, cannot be null.
	 * @return true if rains, false otherwise.
	 */
	private boolean willRain(Planet p1, Planet p2, Planet p3) {
		BigDecimal totalArea = MathUtils.calculateTriangleArea(p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(),
				p3.getY());
		BigDecimal area1 = MathUtils.calculateTriangleArea(p1.getX(), p1.getY(), p2.getX(), p2.getY(),
				TheSun.getInstance().getX(), TheSun.getInstance().getY());
		BigDecimal area2 = MathUtils.calculateTriangleArea(p1.getX(), p1.getY(), p3.getX(), p3.getY(),
				TheSun.getInstance().getX(), TheSun.getInstance().getY());
		BigDecimal area3 = MathUtils.calculateTriangleArea(p2.getX(), p2.getY(), p3.getX(), p3.getY(),
				TheSun.getInstance().getX(), TheSun.getInstance().getY());

		return (MathUtils.equals(totalArea, area1.add(area2).add(area3)));
	}

	/**
	 * Calculates a forecast summary for the given period.
	 * @param fromDay the period from day.
	 * @param toDay the period to day.
	 * @return a ForecastSummary with all the info.
	 */
	public ForecastSummary summary(int fromDay, int toDay) {
		long clearDays = repository.getDaysWithCondition(WeatherCondition.CLEAR, fromDay, toDay);
		long rainyDays = repository.getDaysWithCondition(WeatherCondition.RAIN, fromDay, toDay);
		long droughtDays = repository.getDaysWithCondition(WeatherCondition.DROUGHT, fromDay, toDay);
		long optimalDays = repository.getDaysWithCondition(WeatherCondition.OPTIMAL, fromDay, toDay);
		List<Forecast> maximumRainDays = repository.getMostRainyDays(fromDay, toDay);
		List<Long> days = new ArrayList<>();
		for (Forecast forecast : maximumRainDays) {
			days.add(new Long(forecast.getDay()));
		}
		return new ForecastSummary(clearDays, rainyDays, droughtDays, optimalDays, days);
	}

}
