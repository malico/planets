package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

/** The Sun.*/
public class TheSun extends Planet {
	
	/** The singleton instance. */
	private static TheSun instance = new TheSun();

	@Override
	public BigDecimal getDistanceToSun() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getTranslationSpeed() {
		return BigDecimal.ZERO;
	}
	
	/**
	 * Gets the Sun.
	 * @return a Sun instance, never null.
	 */
	public static TheSun getInstance() {
		return instance;
	}
	
	/** private constructor.*/
	private TheSun() {}

}
