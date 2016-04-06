package ar.com.mercadolibre.planets.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

	public static final int DECIMAL_PRESICION = 16;

	private static final double DELTA = 1e-8;

	public static boolean equals(BigDecimal x, BigDecimal y) {
		BigDecimal diff = x.subtract(y);
		return (diff.abs().compareTo(new BigDecimal(DELTA)) < 1);
	}

	/**
	 * Using Heron's formula
	 * 
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return
	 */
	public static BigDecimal calculateTriangleArea(BigDecimal x1,
			BigDecimal y1, BigDecimal x2, BigDecimal y2, BigDecimal x3,
			BigDecimal y3) {
		BigDecimal sideA = distance(x1, y1, x2, y2);
		BigDecimal sideB = distance(x1, y1, x3, y3);
		BigDecimal sideC = distance(x2, y2, x3, y3);
		BigDecimal semiPerimeter = sideA.add(sideB).add(sideC)
				.divide(new BigDecimal(2));
		double area = Math.sqrt(semiPerimeter.doubleValue()
				* (semiPerimeter.doubleValue() - sideA.doubleValue())
				* (semiPerimeter.doubleValue() - sideB.doubleValue())
				* (semiPerimeter.doubleValue() - sideC.doubleValue()));
		return new BigDecimal(area).setScale(MathUtils.DECIMAL_PRESICION,
				RoundingMode.HALF_UP);
	}

	public static BigDecimal calculateTrianglePerimeter(BigDecimal x1,
			BigDecimal y1, BigDecimal x2, BigDecimal y2, BigDecimal x3,
			BigDecimal y3) {
		BigDecimal sideA = distance(x1, y1, x2, y2);
		BigDecimal sideB = distance(x1, y1, x3, y3);
		BigDecimal sideC = distance(x2, y2, x3, y3);
		return sideA.add(sideB).add(sideC);
	}

	public static BigDecimal distance(BigDecimal x1, BigDecimal y1,
			BigDecimal x2, BigDecimal y2) {
		BigDecimal deltaX = x2.subtract(x1);
		BigDecimal deltaY = y2.subtract(y1);
		double distance = Math.sqrt(Math.pow(deltaX.doubleValue(), 2)
				+ (Math.pow(deltaY.doubleValue(), 2)));
		return new BigDecimal(distance).setScale(MathUtils.DECIMAL_PRESICION,
				RoundingMode.HALF_UP);
	}
}
