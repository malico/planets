package ar.com.mercadolibre.planets.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class MathUtilsTest {
	
	private static final double DELTA = 1e-7;
	
	private static final BigDecimal SQRT_2 = new BigDecimal("1.4142135623730950488016887");

	@Test
	public void perimeter() {
		BigDecimal perimeter = MathUtils.calculateTrianglePerimeter(
				new BigDecimal(0), new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(1),
				new BigDecimal(1), new BigDecimal(0));
		BigDecimal expected = BigDecimal.ONE.add(BigDecimal.ONE).add(SQRT_2);
		assertEquals(expected.doubleValue(), perimeter.doubleValue(), DELTA);
	}
	
	@Test
	public void area() {
		BigDecimal area = MathUtils.calculateTriangleArea(
				new BigDecimal(0), new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(1),
				new BigDecimal(1), new BigDecimal(0));
		assertEquals(0.5, area.doubleValue(), DELTA);
	}
	
	@Test
	public void perimeter_alignedDots() {
		BigDecimal perimeter = MathUtils.calculateTrianglePerimeter(
				new BigDecimal(0), new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(1),
				new BigDecimal(0), new BigDecimal(2));
		assertEquals(0, perimeter.doubleValue(), DELTA);
	}
	
	@Test
	public void area_alignedDots() {
		BigDecimal area = MathUtils.calculateTriangleArea(
				new BigDecimal(0), new BigDecimal(0),
				new BigDecimal(0), new BigDecimal(1),
				new BigDecimal(0), new BigDecimal(2));
		assertEquals(0, area.doubleValue(), DELTA);
	}

}
