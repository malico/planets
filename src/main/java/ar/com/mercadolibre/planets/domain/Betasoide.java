package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

/** The Betasoide planet. */
public class Betasoide extends Planet {

	/** The distance to the Sun, in kilometers.*/
	private static BigDecimal DISTANCE = new BigDecimal(2000);

	/** The angle speed, in degrees.*/
	private static BigDecimal SPEED = new BigDecimal(3);

	/** The singleton instance. */
	private static Betasoide instance = new Betasoide();

	/** private constructor.*/
	private Betasoide() {
		super();
	}
	
	/**
	 * Gets the Betasoide planet.
	 * @return a Betasoide instance, never null.
	 */
	public static Betasoide getInstance() {
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
