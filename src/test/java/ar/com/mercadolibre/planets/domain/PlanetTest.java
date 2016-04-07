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

		Planet ferengi = Ferengi.getInstance();
		BigDecimal radius = ferengi.getDistanceToSun();

		assertEquals(0, ferengi.move(0).getX().doubleValue(), DELTA);
		assertEquals(radius.doubleValue(), ferengi.move(90).getX().doubleValue(), DELTA);
		assertEquals(0, ferengi.move(180).getX().doubleValue(), DELTA);
		assertEquals(radius.doubleValue() * -1, ferengi.move(270).getX().doubleValue(), DELTA);

	}

	@Test
	public void getYPositionForDay() {

		Planet ferengi = Ferengi.getInstance();
		BigDecimal radius = ferengi.getDistanceToSun();

		assertEquals(radius.doubleValue(), ferengi.move(0).getY().doubleValue(), DELTA);
		assertEquals(0, ferengi.move(90).getY().doubleValue(), DELTA);
		assertEquals(radius.doubleValue() * -1, ferengi.move(180).getY().doubleValue(), DELTA);
		assertEquals(0, ferengi.move(270).getY().doubleValue(), DELTA);

	}
}
