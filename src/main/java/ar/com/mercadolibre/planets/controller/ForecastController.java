package ar.com.mercadolibre.planets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.planets.domain.ForecastSummary;
import ar.com.mercadolibre.planets.domain.WeatherRegistry;
import ar.com.mercadolibre.planets.service.ForecastService;

@RestController
@RequestMapping("/")
public class ForecastController {

	@Autowired
	private ForecastService service;

	@RequestMapping(value = { "/forecast/{day}" }, method = RequestMethod.GET)
	public ResponseEntity<WeatherRegistry> forecast(@PathVariable("day") int day) {

		WeatherRegistry registry = service.forecast(day);
		return new ResponseEntity<WeatherRegistry>(registry, HttpStatus.OK);
	}

	@RequestMapping(value = { "/run/{years}" }, method = RequestMethod.GET)
	public ResponseEntity<ForecastSummary> runCron(
			@PathVariable("years") int years) {

		int totalDays = years * 365;
		for (int day = 1; day <= totalDays; day++) {
			service.forecast(day);
		}
		return new ResponseEntity<ForecastSummary>(
				service.summary(0, totalDays), HttpStatus.OK);
	}

}
