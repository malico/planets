package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

public class TheSun extends Planet {
	
	private static TheSun instance = new TheSun();

	@Override
	public BigDecimal getDistanceToSun() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getTranslationSpeed() {
		return BigDecimal.ZERO;
	}
	
	public static TheSun getInstance() {
		return instance;
	}
	
	private TheSun() {}

}
