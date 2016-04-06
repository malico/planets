package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

public class Vulcano extends Planet {

	private static BigDecimal DISTANCE = new BigDecimal(1000);
	
	private static BigDecimal SPEED = new BigDecimal(-5);

	@Override
	public BigDecimal getDistanceToSun() {
		return DISTANCE;
	}

	@Override
	public BigDecimal getTranslationSpeed() {
		return SPEED;
	}
		
}
