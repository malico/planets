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
import ar.com.mercadolibre.planets.service.ForecastService.Weather;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { HibernateTestConfiguration.class }, loader = AnnotationConfigWebContextLoader.class)
public class WeatherRegistryRepositoryTest extends DatabaseTest {

	private static final double DELTA = 1e-8;

	@Autowired
	WeatherRegistryRepository repository;

	@Test
	public void saveAndRetrieve() {
		WeatherRegistry registry = new WeatherRegistry(1234,
				new BigDecimal(10), new BigDecimal(22), new BigDecimal(41),
				new BigDecimal(6), new BigDecimal(74), new BigDecimal(0.8),
				Weather.RAIN, new BigDecimal(1605.15));
		repository.save(registry);

		WeatherRegistry byDay = repository.byDay(1234);

		assertNotNull(byDay);
		assertEquals(1234, byDay.getDay());
		assertEquals(10, byDay.getFerengiX().doubleValue(), DELTA);
		assertEquals(22, byDay.getFerengiY().doubleValue(), DELTA);
		assertEquals(41, byDay.getVulcanoX().doubleValue(), DELTA);
		assertEquals(6, byDay.getVulcanoY().doubleValue(), DELTA);
		assertEquals(74, byDay.getBetasoideX().doubleValue(), DELTA);
		assertEquals(0.8, byDay.getBetasoideY().doubleValue(), DELTA);
		assertEquals(1605.15, byDay.getPlanetsDistance().doubleValue(), DELTA);
		assertEquals(Weather.RAIN, byDay.getCondition());
	}

	@Test
	public void getDaysWithCondition() {
		// in InitialDB.xml are configured 7 rainy days, 2 sunny days, 1 drought
		// and any optimal.
		long rainyDays = repository.getDaysWithCondition(Weather.RAIN, 1, 10);
		long droughtDays = repository.getDaysWithCondition(Weather.DROUGHT, 1,
				10);
		long sunnyDays = repository.getDaysWithCondition(Weather.CLEAR, 1, 10);
		long optimalDays = repository.getDaysWithCondition(Weather.OPTIMAL, 1,
				10);
		assertEquals(7, rainyDays);
		assertEquals(1, droughtDays);
		assertEquals(2, sunnyDays);
		assertEquals(0, optimalDays);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader()
				.getResourceAsStream("initialDB.xml"));
		return dataSet;
	}

}
