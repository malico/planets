package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

public class ForecastService {

	public enum Weather {
		CLEAR, RAIN, OPTIMAL, DROUGHT;
	}
	
	public Weather forecast(final Planet p1, final Planet p2, final Planet p3) {
		if (calculateTriangleArea(p1, p2, p3).compareTo(BigDecimal.ZERO) == 0) {
			if (calculateTriangleArea(p1, p2, new TheSun()).compareTo(BigDecimal.ZERO) == 0) {
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

	private boolean willRain(Planet p1, Planet p2, Planet p3) {
		BigDecimal totalArea = calculateTriangleArea(p1, p2, p3);
		BigDecimal area1 = calculateTriangleArea(p1, p2, new TheSun());
		BigDecimal area2 = calculateTriangleArea(p1, p3, new TheSun());
		BigDecimal area3 = calculateTriangleArea(p2, p3, new TheSun());

		return (totalArea.compareTo(area1.add(area2).add(area3)) == 0);
	}

	/**
	 * Using Heron's formula
	 * @param p1
	 * @param p2
	 * @param p3
	 * @return
	 */
	private BigDecimal calculateTriangleArea(Planet p1, Planet p2, Planet p3) {
		BigDecimal sideA = distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		BigDecimal sideB = distance(p1.getX(), p1.getY(), p3.getX(), p3.getY());
		BigDecimal sideC = distance(p2.getX(), p2.getY(), p3.getX(), p3.getY());
		BigDecimal semiPerimeter = sideA.add(sideB).add(sideC).divide(new BigDecimal(2));
		double area = Math.sqrt(semiPerimeter.doubleValue()
				* (semiPerimeter.doubleValue() - sideA.doubleValue())
				* (semiPerimeter.doubleValue() - sideB.doubleValue())
				* (semiPerimeter.doubleValue() - sideC.doubleValue()));
		return new BigDecimal(area);
	}
	
	private BigDecimal calculateTrianglePerimeter(Planet p1, Planet p2, Planet p3) {
		BigDecimal sideA = distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		BigDecimal sideB = distance(p1.getX(), p1.getY(), p3.getX(), p3.getY());
		BigDecimal sideC = distance(p2.getX(), p2.getY(), p3.getX(), p3.getY());
		return sideA.add(sideB).add(sideC);
	}

	private BigDecimal distance(BigDecimal x1, 
			BigDecimal y1, BigDecimal x2, BigDecimal y2) {
		BigDecimal deltaX = x2.subtract(x1);
		BigDecimal deltaY = y2.subtract(y1);
		double distance = Math.sqrt(Math.pow(deltaX.doubleValue(), 2)
				+ (Math.pow(deltaY.doubleValue(), 2)));
		return new BigDecimal(distance);
	}
}
