package ar.com.mercadolibre.planets.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercadolibre.planets.domain.Betasoide;
import ar.com.mercadolibre.planets.domain.Ferengi;
import ar.com.mercadolibre.planets.domain.ForecastSummary;
import ar.com.mercadolibre.planets.domain.Planet;
import ar.com.mercadolibre.planets.domain.TheSun;
import ar.com.mercadolibre.planets.domain.Vulcano;
import ar.com.mercadolibre.planets.domain.WeatherRegistry;
import ar.com.mercadolibre.planets.domain.WeatherRegistryRepository;
 
@Service("forecastService")
@Transactional
public class ForecastService {
	
	@Autowired
	private WeatherRegistryRepository repository;

	public enum Weather {
		CLEAR, RAIN, OPTIMAL, DROUGHT;
	}

	public WeatherRegistry forecast(final int aDay) {
		
		WeatherRegistry registry;
		
		registry = repository.byDay(aDay);
		if (registry != null) {
			return registry;
		}
		
		Ferengi p1 = new Ferengi(aDay);
		Vulcano p2 = new Vulcano(aDay);
		Betasoide p3 = new Betasoide(aDay);
		Weather weather = forecast(p1, p2, p3);

		registry = new WeatherRegistry(aDay,
				p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(),
				p3.getY(), weather, MathUtils.calculateTrianglePerimeter(p1.getX(),
				p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY()));
		repository.save(registry);
		return registry;
	}
	
	public Weather forecast(final Planet p1, final Planet p2, final Planet p3) {

		if (MathUtils.equals(MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY()),
				BigDecimal.ZERO)) {
			if (MathUtils.equals(MathUtils.calculateTriangleArea(p1.getX(), p1
					.getY(), p2.getX(), p2.getY(), TheSun.getInstance().getX(),
					TheSun.getInstance().getY()), BigDecimal.ZERO)) {
				return Weather.DROUGHT;
			} else {
				return Weather.OPTIMAL;
			}
		}
		if (willRain(p1, p2, p3)) {
			return Weather.RAIN;
		} else {
			return Weather.CLEAR;
		}
	}

	public ForecastSummary summary(int fromDay, int toDay) {
		long clearDays = repository.getDaysWithCondition(Weather.CLEAR, fromDay, toDay);
		long rainyDays = repository.getDaysWithCondition(Weather.RAIN, fromDay, toDay);
		long droughtDays = repository.getDaysWithCondition(Weather.DROUGHT, fromDay, toDay);
		long optimalDays = repository.getDaysWithCondition(Weather.OPTIMAL, fromDay, toDay);
		long maximumRain = repository.getMaximumRain(fromDay, toDay);
		return new ForecastSummary(clearDays, rainyDays, droughtDays, optimalDays, maximumRain);
	}
	
	private boolean willRain(Planet p1, Planet p2, Planet p3) {
		BigDecimal totalArea = MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
		BigDecimal area1 = MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p2.getX(), p2.getY(), TheSun.getInstance().getX(),
				TheSun.getInstance().getY());
		BigDecimal area2 = MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p3.getX(), p3.getY(), TheSun.getInstance().getX(),
				TheSun.getInstance().getY());
		BigDecimal area3 = MathUtils.calculateTriangleArea(p2.getX(),
				p2.getY(), p3.getX(), p3.getY(), TheSun.getInstance().getX(),
				TheSun.getInstance().getY());

		return (MathUtils.equals(totalArea, area1.add(area2).add(area3)));
	}

}
