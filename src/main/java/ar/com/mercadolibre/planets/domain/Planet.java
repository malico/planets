package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ar.com.mercadolibre.planets.service.MathUtils;

/** 
 * Represents a celestial body like a Planet or a Satellite.
 * 
 * All planets have a perfect-circle orbit.
 * 
 * On zero day, the planet is located in the position (0, distance).
 * 
 * @author malico
 */
public abstract class Planet {

	/** The axis-x position.*/
	private BigDecimal x;

	/** The axis-y position.*/
	private BigDecimal y;

	/**
	 * Returns the distance to the Sun.
	 * @return the distance, in kilometers.
	 */
	public abstract BigDecimal getDistanceToSun();

	/**
	 * Returns the angle that the planet moves every day.
	 * @return the angle, in degrees.
	 */
	public abstract BigDecimal getTranslationSpeed();

	/**
	 * Default constructor.
	 */
	public Planet() {
		x = BigDecimal.ZERO;
		y = getDistanceToSun();
	}
	
	/**
	 * Gets the axis-x position.
	 * @return the position in the axis-x, in kilometers.
	 */
	public BigDecimal getX() {
		return x;
	}

	/**
	 * Gets the axis-y position.
	 * @return the position in the axis-y, in kilometers.
	 */
	public BigDecimal getY() {
		return y;
	}
	
	/**
	 * Moves the planet nDays from the origin.
	 * @param nDays the number of days (from 0) the planet will move.
	 */
	public Planet move(final long nDays) {
		x = calculateX(nDays);
		y = calculateY(nDays);
		return this;
	}

	/**
	 * Calculates the axis-x position of the planet in the passed day.
	 * @param aDay the day number.
	 * @return  the position in the axis-x, in kilometers.
	 */
	private BigDecimal calculateX(long aDay) {
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

	/**
	 * Calculates the axis-y position of the planet in the passed day.
	 * @param aDay the day number.
	 * @return  the position in the axis-y, in kilometers.
	 */
	private BigDecimal calculateY(long aDay) {
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
