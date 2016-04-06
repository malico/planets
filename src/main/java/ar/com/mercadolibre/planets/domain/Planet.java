package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ar.com.mercadolibre.planets.service.MathUtils;

public abstract class Planet {

	private BigDecimal x;

	private BigDecimal y;

	public abstract BigDecimal getDistanceToSun();

	public abstract BigDecimal getTranslationSpeed();

	public Planet() {
		x = BigDecimal.ZERO;
		y = getDistanceToSun();
	}
	
	public Planet(long days) {
		this();
		move(days);
	}

	public BigDecimal getX() {
		return x;
	}

	public BigDecimal getY() {
		return y;
	}
	
	public void move(final long days) {
		x = getXPositionForDay(days);
		y = getYPositionForDay(days);
	}

	public BigDecimal getXPositionForDay(long aDay) {
		BigDecimal degrees = getTranslationSpeed().multiply(new BigDecimal(aDay));
		long relativeDegrees = degrees.longValue() % 360;
		if (degrees.compareTo(BigDecimal.ZERO) < 0) {
			relativeDegrees = 360 - (degrees.abs().longValue() % 360);
		}
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
		BigDecimal degrees = getTranslationSpeed().multiply(new BigDecimal(aDay));
		long relativeDegrees = degrees.longValue() % 360;
		if (degrees.compareTo(BigDecimal.ZERO) < 0) {
			relativeDegrees = 360 - (degrees.abs().longValue() % 360);
		}
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
