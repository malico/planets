package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

//TODO: Make it singleton
public class TheSun extends Planet {

	@Override
	public BigDecimal getDistanceToSun() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getTranslationSpeed() {
		return BigDecimal.ZERO;
	}

}
