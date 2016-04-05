package ar.com.mercadolibre.planets;

import java.math.BigDecimal;

import org.junit.Test;

public class ForecastServiceTest {
	
	@Test
	public void forecast() {
		DummyPlanet p1 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(1)));
		DummyPlanet p2 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(-1)));
		DummyPlanet p3 = new DummyPlanet(new BigDecimal(-1), (new BigDecimal(0)));
		new ForecastService().forecast(p1, p2, p3);
	}

	class DummyPlanet extends Planet {
		
		private BigDecimal dummyX;
		private BigDecimal dummyY;
		
		public DummyPlanet(BigDecimal x, BigDecimal y) {
			dummyX = x;
			dummyY = y;
		}

		@Override
		public BigDecimal getX() {
			return dummyX;
		}
		
		@Override
		public BigDecimal getY() {
			return dummyY;
		}

		@Override
		public BigDecimal getDistanceToSun() {
			return BigDecimal.ONE;
		}

		@Override
		public BigDecimal getTranslationSpeed() {
			return BigDecimal.ONE;
		}
		
	}
}
