package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

public abstract class Planet {

	public abstract BigDecimal getDistanceToSun();

	public abstract BigDecimal getTranslationSpeed();

	public double getXPositionForDay(long aDay) {
		long relativeDegrees = aDay % 360;
		if (relativeDegrees >= 0 && relativeDegrees <= 90) {
			return Math.sin(Math.toRadians(relativeDegrees)) * getDistanceToSun().floatValue();
		} else if (relativeDegrees > 90 && relativeDegrees <= 180) {
			return Math.cos(Math.toRadians(relativeDegrees-90)) * getDistanceToSun().floatValue();
		} else if (relativeDegrees > 180 && relativeDegrees <= 270) {
			return Math.sin(Math.toRadians(relativeDegrees-180)) * getDistanceToSun().floatValue() * -1;
		} else {
			return Math.cos(Math.toRadians(relativeDegrees-270)) * getDistanceToSun().floatValue() * -1;
		}
	}

	public double getYPositionForDay(long aDay) {
		long relativeDegrees = aDay % 360;
		if (relativeDegrees >= 0 && relativeDegrees <= 90) {
			return Math.cos(Math.toRadians(relativeDegrees)) * getDistanceToSun().floatValue();
		} else if (relativeDegrees > 90 && relativeDegrees <= 180) {
			return Math.sin(Math.toRadians(relativeDegrees-90)) * getDistanceToSun().floatValue() * -1;
		} else if (relativeDegrees > 180 && relativeDegrees <= 270) {
			return Math.cos(Math.toRadians(relativeDegrees-180)) * getDistanceToSun().floatValue() * -1;
		} else {
			return Math.sin(Math.toRadians(relativeDegrees-270)) * getDistanceToSun().floatValue();
		}
	}
}
