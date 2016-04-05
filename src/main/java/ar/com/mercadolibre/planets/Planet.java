package ar.com.mercadolibre.planets;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Planet {

	private BigDecimal x;

	private BigDecimal y;

	public abstract BigDecimal getDistanceToSun();

	public abstract BigDecimal getTranslationSpeed();

	public Planet() {
		x = BigDecimal.ZERO;
		y = getDistanceToSun();
	}

	public BigDecimal getX() {
		return x;
	}

	public BigDecimal getY() {
		return y;
	}

	public BigDecimal getXPositionForDay(long aDay) {
		long relativeDegrees = aDay % 360;
		double position;

		if (relativeDegrees >= 0 && relativeDegrees <= 90) {
			position = Math.sin(Math.toRadians(relativeDegrees))
					* getDistanceToSun().doubleValue();
		} else if (relativeDegrees > 90 && relativeDegrees <= 180) {
			position = Math.cos(Math.toRadians(relativeDegrees - 90))
					* getDistanceToSun().doubleValue();
		} else if (relativeDegrees > 180 && relativeDegrees <= 270) {
			position = Math.sin(Math.toRadians(relativeDegrees - 180))
					* getDistanceToSun().doubleValue() * -1;
		} else {
			position = Math.cos(Math.toRadians(relativeDegrees - 270))
					* getDistanceToSun().doubleValue() * -1;
		}
		return new BigDecimal(position).setScale(MathUtils.DECIMAL_PRESICION,
				RoundingMode.HALF_UP);
	}

	public BigDecimal getYPositionForDay(long aDay) {
		long relativeDegrees = aDay % 360;
		double position;

		if (relativeDegrees >= 0 && relativeDegrees <= 90) {
			position = Math.cos(Math.toRadians(relativeDegrees))
					* getDistanceToSun().doubleValue();
		} else if (relativeDegrees > 90 && relativeDegrees <= 180) {
			position = Math.sin(Math.toRadians(relativeDegrees - 90))
					* getDistanceToSun().doubleValue() * -1;
		} else if (relativeDegrees > 180 && relativeDegrees <= 270) {
			position = Math.cos(Math.toRadians(relativeDegrees - 180))
					* getDistanceToSun().doubleValue() * -1;
		} else {
			position = Math.sin(Math.toRadians(relativeDegrees - 270))
					* getDistanceToSun().doubleValue();
		}
		return new BigDecimal(position).setScale(MathUtils.DECIMAL_PRESICION,
				RoundingMode.HALF_UP);
	}
}
