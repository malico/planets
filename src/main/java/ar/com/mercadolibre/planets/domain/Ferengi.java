package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

public class Ferengi extends Planet {
	
	private static BigDecimal DISTANCE = new BigDecimal(500);
	
	private static BigDecimal SPEED = new BigDecimal(1);
	
	public Ferengi() {
		super();
	}
	
	public Ferengi(long days) {
		super(days);
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
