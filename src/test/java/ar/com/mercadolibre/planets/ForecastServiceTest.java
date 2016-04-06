package ar.com.mercadolibre.planets;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import ar.com.mercadolibre.planets.domain.Planet;
import ar.com.mercadolibre.planets.service.ForecastService;
import ar.com.mercadolibre.planets.service.ForecastService.Weather;

public class ForecastServiceTest {
	
	@Test
	public void forecast_rain() {
		DummyPlanet p1 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(1)));
		DummyPlanet p2 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(-1)));
		DummyPlanet p3 = new DummyPlanet(new BigDecimal(-1), (new BigDecimal(0)));
		Weather forecast = new ForecastService().forecast(p1, p2, p3);
		assertEquals(Weather.RAIN, forecast);
	}
	
	@Test
	public void forecast_clear() {
		DummyPlanet p1 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(1)));
		DummyPlanet p2 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(-1)));
		DummyPlanet p3 = new DummyPlanet(new BigDecimal(2), (new BigDecimal(2)));
		Weather forecast = new ForecastService().forecast(p1, p2, p3);
		assertEquals(Weather.CLEAR, forecast);
	}
	
	@Test
	public void forecast_drought() {
		DummyPlanet p1 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(1)));
		DummyPlanet p2 = new DummyPlanet(new BigDecimal(-1), (new BigDecimal(-1)));
		DummyPlanet p3 = new DummyPlanet(new BigDecimal(-5), (new BigDecimal(-5)));
		Weather forecast = new ForecastService().forecast(p1, p2, p3);
		assertEquals(Weather.DROUGHT, forecast);
	}
	
	@Test
	public void forecast_optimal() {
		DummyPlanet p1 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(1)));
		DummyPlanet p2 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(2)));
		DummyPlanet p3 = new DummyPlanet(new BigDecimal(1), (new BigDecimal(-5)));
		Weather forecast = new ForecastService().forecast(p1, p2, p3);
		assertEquals(Weather.OPTIMAL, forecast);
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
