package ar.com.mercadolibre.planets.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import ar.com.mercadolibre.planets.configuration.HibernateTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { HibernateTestConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
public class ForecastRepositoryTest extends DatabaseTest {

	private static final double DELTA = 1e-8;

	@Autowired
	ForecastRepository repository;

	@Test
	public void saveAndRetrieve() {
		Forecast registry = new Forecast(1234,
				new BigDecimal(10), new BigDecimal(22), new BigDecimal(41),
				new BigDecimal(6), new BigDecimal(74), new BigDecimal(0.8),
				WeatherCondition.RAIN, new BigDecimal(1605.15));
		repository.save(registry);

		Forecast byDay = repository.byDay(1234);

		assertNotNull(byDay);
		assertEquals(1234, byDay.getDay());
		assertEquals(10, byDay.getFerengiX().doubleValue(), DELTA);
		assertEquals(22, byDay.getFerengiY().doubleValue(), DELTA);
		assertEquals(41, byDay.getVulcanoX().doubleValue(), DELTA);
		assertEquals(6, byDay.getVulcanoY().doubleValue(), DELTA);
		assertEquals(74, byDay.getBetasoideX().doubleValue(), DELTA);
		assertEquals(0.8, byDay.getBetasoideY().doubleValue(), DELTA);
		assertEquals(1605.15, byDay.getPlanetsDistance().doubleValue(), DELTA);
		assertEquals(WeatherCondition.RAIN, byDay.getCondition());
	}

	@Test
	public void getDaysWithCondition() {
		// in InitialDB.xml are configured 7 rainy days, 2 sunny days, 1 drought
		// and any optimal.
		long rainyDays = repository.getDaysWithCondition(WeatherCondition.RAIN, 1, 10);
		long droughtDays = repository.getDaysWithCondition(WeatherCondition.DROUGHT, 1,
				10);
		long sunnyDays = repository.getDaysWithCondition(WeatherCondition.CLEAR, 1, 10);
		long optimalDays = repository.getDaysWithCondition(WeatherCondition.OPTIMAL, 1,
				10);
		assertEquals(7, rainyDays);
		assertEquals(1, droughtDays);
		assertEquals(2, sunnyDays);
		assertEquals(0, optimalDays);
	}
	
	@Test
	public void getMaximumRainDay() {
		// in InitialDB.xml the max rains day is number 4.
		Forecast maxRainyDay = repository.getMaximumRainDay(1, 10);
		assertEquals(4, maxRainyDay.getDay());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
				.getResourceAsStream("weather.xml"));
		return dataSet;
	}

}
