package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PlanetTest {
	
	private static final double DELTA = 1e-7;

	@Test
	public void getXPositionForDay() {
		
		Planet ferengi = new Ferengi();
		BigDecimal radius = ferengi.getDistanceToSun();
		
		assertEquals(ferengi.getXPositionForDay(0), 0, DELTA);
		assertEquals(ferengi.getXPositionForDay(90), radius.doubleValue(), DELTA);
		assertEquals(ferengi.getXPositionForDay(180), 0, DELTA);
		assertEquals(ferengi.getXPositionForDay(270), radius.doubleValue() * -1, DELTA);

	}
	
	@Test
	public void getYPositionForDay() {
		
		Planet ferengi = new Ferengi();
		BigDecimal radius = ferengi.getDistanceToSun();
		
		assertEquals(radius.doubleValue(), ferengi.getYPositionForDay(0), DELTA);
		assertEquals(0, ferengi.getYPositionForDay(90), DELTA);
		assertEquals(radius.doubleValue() * -1, ferengi.getYPositionForDay(180), DELTA);
		assertEquals(0, ferengi.getYPositionForDay(270), DELTA);

	}
}