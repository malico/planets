package ar.com.mercadolibre.planets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.planets.domain.ForecastSummary;
import ar.com.mercadolibre.planets.domain.Forecast;
import ar.com.mercadolibre.planets.service.ForecastService;

/**
 * Controller to handle request.
 * @author malico
 */
@RestController
@RequestMapping("/")
public class ForecastController {

	/** The forecast service, never null.*/
	@Autowired
	private ForecastService service;

	/**
	 * Forecasts the weather for the given day.
	 * @param day the day which the weather will be forecasted.
	 * @return a JSON response with the forecast summary.
	 */
	@RequestMapping(value = { "/forecast/{day}" }, method = RequestMethod.GET)
	public ResponseEntity<Forecast> forecast(@PathVariable("day") int day) {

		Forecast registry = service.forecast(day);
		return new ResponseEntity<Forecast>(registry, HttpStatus.OK);
	}

	/**
	 * Calculates the weather for the given number of years.
	 * @param years the years which the weather will be forecasted.
	 * @return a JSON response with the forecast summary.
	 */
	@RequestMapping(value = { "/calculate/{years}" }, method = RequestMethod.GET)
	public ResponseEntity<ForecastSummary> calculateWeather(
			@PathVariable("years") int years) {

		int totalDays = years * 365;
		for (int day = 1; day <= totalDays; day++) {
			service.forecast(day);
		}
		return new ResponseEntity<ForecastSummary>(
				service.summary(1, totalDays), HttpStatus.OK);
	}

}
