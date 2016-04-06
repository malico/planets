package ar.com.mercadolibre.planets.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.planets.domain.WeatherRegistry;
import ar.com.mercadolibre.planets.service.ForecastService;
import ar.com.mercadolibre.planets.service.ForecastService.Weather;

@RestController
@RequestMapping("/")
public class ForecastController {

	@Autowired
	private ForecastService service;

	@RequestMapping(value = {"/forecast/{day}" }, method = RequestMethod.GET)
	public ResponseEntity<WeatherRegistry> forecast(@PathVariable("day") int day) {

		System.out.println("HOLAAAAAAAAA");
		WeatherRegistry registry = new WeatherRegistry(100050l, 14, new BigDecimal(16), new BigDecimal(516), new BigDecimal(7),
				new BigDecimal(-1), new BigDecimal(0), new BigDecimal(16), Weather.DROUGHT, new BigDecimal(1605.15));
		return new ResponseEntity<WeatherRegistry>(registry, HttpStatus.OK);
	}

}
