package ar.com.mercadolibre.planets.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** 
 * Class to support custom Math operations.
 * @author malico
 */
public class MathUtils {

	/** The BigDecimal precision used.*/
	public static final int DECIMAL_PRESICION = 16;

	/** A Infinitesimal delta to check equality. */
	private static final double DELTA = 1e-8;

	/**
	 * Indicates if two numbers are equals.
	 * @param x the first number.
	 * @param y the second number
	 * @return true if are equals, false otherwise.
	 */
	public static boolean equals(BigDecimal x, BigDecimal y) {
		BigDecimal diff = x.subtract(y);
		return (diff.abs().compareTo(new BigDecimal(DELTA)) < 1);
	}

	/**
	 * Calculates the triangle area using Heron's formula
	 *
	 * @param x1 the axis-x for the first triangle point.
	 * @param y1 the axis-y for the first triangle point.
	 * @param x2 the axis-x for the second triangle point.
	 * @param y2 the axis-y for the second triangle point.
	 * @param x3 the axis-x for the third triangle point.
	 * @param y3 the axis-y for the third triangle point.
	 * @return the triangle area.
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

	/**
	 * Calculates the triangle perimeter.
	 *
	 * @param x1 the axis-x for the first triangle point.
	 * @param y1 the axis-y for the first triangle point.
	 * @param x2 the axis-x for the second triangle point.
	 * @param y2 the axis-y for the second triangle point.
	 * @param x3 the axis-x for the third triangle point.
	 * @param y3 the axis-y for the third triangle point.
	 * @return the triangle perimeter.
	 */
	public static BigDecimal calculateTrianglePerimeter(BigDecimal x1,
			BigDecimal y1, BigDecimal x2, BigDecimal y2, BigDecimal x3,
			BigDecimal y3) {
		BigDecimal sideA = distance(x1, y1, x2, y2);
		BigDecimal sideB = distance(x1, y1, x3, y3);
		BigDecimal sideC = distance(x2, y2, x3, y3);
		BigDecimal perimeter = sideA.add(sideB).add(sideC);
		
		BigDecimal semiPerimeter = perimeter.divide(new BigDecimal(2));
		double area = Math.sqrt(semiPerimeter.doubleValue()
				* (semiPerimeter.doubleValue() - sideA.doubleValue())
				* (semiPerimeter.doubleValue() - sideB.doubleValue())
				* (semiPerimeter.doubleValue() - sideC.doubleValue()));
		if (area == 0) {
			return BigDecimal.ZERO;
		}
		return perimeter;
	}

	/**
	 * Gets the distance between two points.
	 * @param x1 the axis-x for the first point.
	 * @param y1 the axis-y for the first point.
	 * @param x2 the axis-x for the second point.
	 * @param y2 the axis-y for the second point.
	 * @return the distance.
	 */
	private static BigDecimal distance(BigDecimal x1, BigDecimal y1,
			BigDecimal x2, BigDecimal y2) {
		BigDecimal deltaX = x2.subtract(x1);
		BigDecimal deltaY = y2.subtract(y1);
		double distance = Math.sqrt(Math.pow(deltaX.doubleValue(), 2)
				+ (Math.pow(deltaY.doubleValue(), 2)));
		return new BigDecimal(distance).setScale(MathUtils.DECIMAL_PRESICION,
				RoundingMode.HALF_UP);
	}
}
