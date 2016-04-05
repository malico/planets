package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

public class MathUtils {

	public static final int DECIMAL_PRESICION = 16;
	
	private static final double DELTA = 1e-8;

	public static boolean equals(BigDecimal x, BigDecimal y) {
		BigDecimal diff = x.subtract(y);
		return (diff.abs().compareTo(new BigDecimal(DELTA)) < 1);
	}
}
