package ar.com.mercadolibre.planets.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercadolibre.planets.domain.Betasoide;
import ar.com.mercadolibre.planets.domain.Ferengi;
import ar.com.mercadolibre.planets.domain.Planet;
import ar.com.mercadolibre.planets.domain.TheSun;
import ar.com.mercadolibre.planets.domain.Vulcano;
import ar.com.mercadolibre.planets.domain.WeatherRegistry;
 
@Service("forecastService")
@Transactional
public class ForecastService {

	public enum Weather {
		CLEAR, RAIN, OPTIMAL, DROUGHT;
	}

	public WeatherRegistry forecast(final int aDay) {
		Ferengi p1 = new Ferengi(aDay);
		Vulcano p2 = new Vulcano(aDay);
		Betasoide p3 = new Betasoide(aDay);
		Weather weather = forecast(p1, p2, p3);
		 
		 WeatherRegistry registry = new WeatherRegistry(100050l, aDay, p1.getX(), p1.getY(), p2.getX(),
				 p2.getY(), p3.getX(), p3.getY(), weather, new BigDecimal(1605.15));
		 return registry;
	}
	
	public Weather forecast(final Planet p1, final Planet p2, final Planet p3) {
		System.out.println("P1 - (" + p1.getX().toString() + " ," + p1.getY().toString() + ")"); 
		System.out.println("P2 - (" + p2.getX().toString() + " ," + p2.getY().toString() + ")"); 
		System.out.println("P3 - (" + p3.getX().toString() + " ," + p3.getY().toString() + ")"); 

		if (MathUtils.equals(MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY()),
				BigDecimal.ZERO)) {
			if (MathUtils.equals(MathUtils.calculateTriangleArea(p1.getX(), p1
					.getY(), p2.getX(), p2.getY(), TheSun.getInstance().getX(),
					TheSun.getInstance().getY()), BigDecimal.ZERO)) {
				return Weather.DROUGHT;
			} else {
				return Weather.OPTIMAL;
			}
		}
		if (willRain(p1, p2, p3)) {
			return Weather.RAIN;
		} else {
			return Weather.CLEAR;
		}
	}

	private boolean willRain(Planet p1, Planet p2, Planet p3) {
		BigDecimal totalArea = MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());
		BigDecimal area1 = MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p2.getX(), p2.getY(), TheSun.getInstance().getX(),
				TheSun.getInstance().getY());
		BigDecimal area2 = MathUtils.calculateTriangleArea(p1.getX(),
				p1.getY(), p3.getX(), p3.getY(), TheSun.getInstance().getX(),
				TheSun.getInstance().getY());
		BigDecimal area3 = MathUtils.calculateTriangleArea(p2.getX(),
				p2.getY(), p3.getX(), p3.getY(), TheSun.getInstance().getX(),
				TheSun.getInstance().getY());

		return (MathUtils.equals(totalArea, area1.add(area2).add(area3)));
	}

}
