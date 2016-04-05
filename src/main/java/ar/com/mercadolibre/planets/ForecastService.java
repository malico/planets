package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

public class ForecastService {

	public enum Weather {
		CLEAR, RAIN, OPTIMAL, DROUGHT;
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
