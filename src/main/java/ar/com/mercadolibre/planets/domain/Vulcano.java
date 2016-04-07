package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

/** The Vulcano planet.*/
public class Vulcano extends Planet {

	/** The distance to the Sun, in kilometers.*/
	private static BigDecimal DISTANCE = new BigDecimal(1000);
	
	/** The angle speed, in degrees.*/
	private static BigDecimal SPEED = new BigDecimal(-5);
	
	/** The singleton instance. */
	private static Vulcano instance = new Vulcano();

	/** private constructor.*/
	private Vulcano() {
		super();
	}
	
	/**
	 * Gets the Vulcano planet.
	 * @return a Vulcano instance, never null.
	 */
	public static Vulcano getInstance() {
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
