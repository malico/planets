package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ar.com.mercadolibre.planets.domain.Ferengi;
import ar.com.mercadolibre.planets.domain.Planet;

public class PlanetTest {

	private static final double DELTA = 1e-7;

	@Test
	public void getXPositionForDay() {

		Planet ferengi = new Ferengi();
		BigDecimal radius = ferengi.getDistanceToSun();

		assertEquals(ferengi.getXPositionForDay(0).doubleValue(), 0, DELTA);
		assertEquals(ferengi.getXPositionForDay(90).doubleValue(),
				radius.doubleValue(), DELTA);
		assertEquals(ferengi.getXPositionForDay(180).doubleValue(), 0, DELTA);
		assertEquals(ferengi.getXPositionForDay(270).doubleValue(),
				radius.doubleValue() * -1, DELTA);

	}

	@Test
	public void getYPositionForDay() {

		Planet ferengi = new Ferengi();
		BigDecimal radius = ferengi.getDistanceToSun();

		assertEquals(radius.doubleValue(), ferengi.getYPositionForDay(0)
				.doubleValue(), DELTA);
		assertEquals(0, ferengi.getYPositionForDay(90).doubleValue(), DELTA);
		assertEquals(radius.doubleValue() * -1, ferengi.getYPositionForDay(180)
				.doubleValue(), DELTA);
		assertEquals(0, ferengi.getYPositionForDay(270).doubleValue(), DELTA);

	}
}
