package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

/** The Ferengi planet.*/
public class Ferengi extends Planet {
	
	/** The distance to the Sun, in kilometers.*/
	private static BigDecimal DISTANCE = new BigDecimal(500);
	
	/** The angle speed, in degrees.*/
	private static BigDecimal SPEED = new BigDecimal(1);
	
	/** The singleton instance. */
	private static Ferengi instance = new Ferengi();

	/** private constructor.*/
	private Ferengi() {
		super();
	}
	
	/**
	 * Gets the Ferengi planet.
	 * @return a Ferengi instance, never null.
	 */
	public static Ferengi getInstance() {
		return instance;
	}
	
	@Override
	public BigDecimal getDistanceToSun() {
		return DISTANCE;
	}

	@Override
	public BigDecimal getTranslationSpeed() {
		return SPEED;
	}

}
