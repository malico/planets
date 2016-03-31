package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

public class Betasoide extends Planet {

private static BigDecimal DISTANCE = new BigDecimal(2000);
	
	private static BigDecimal SPEED = new BigDecimal(3);

	@Override
	public BigDecimal getDistanceToSun() {
		return DISTANCE;
	}

	@Override
	public BigDecimal getTranslationSpeed() {
		return SPEED;
	}

}
