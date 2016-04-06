package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

import ar.com.mercadolibre.planets.service.ForecastService.Weather;

public class WeatherRegistry {

	private Long id;

	private int day;
	
	private BigDecimal ferengiX;

	private BigDecimal ferengiY;

	private BigDecimal vulcanoX;
	
	private BigDecimal vulcanoY;
	
	private BigDecimal betasoideX;
	
	private BigDecimal betasoideY;
	
	private Weather condition;
	
	private BigDecimal planetsDistance;
	
	public WeatherRegistry(final Long theId, final int aDay, final BigDecimal ferengiXPos, final BigDecimal ferengiYPos,
			final BigDecimal vulcanoXPos, final BigDecimal vulcanoYPos, final BigDecimal betasoideXPos,
			final BigDecimal betasoideYPos, final Weather theWeahter, final BigDecimal theDistance) {
		setId(theId);
		setDay(aDay);
		setFerengiX(ferengiXPos);
		setFerengiY(ferengiYPos);
		setVulcanoX(vulcanoXPos);
		setVulcanoY(vulcanoYPos);
		setBetasoideX(betasoideXPos);
		setBetasoideY(betasoideYPos);
		setCondition(theWeahter);
		setPlanetsDistance(theDistance);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public BigDecimal getFerengiX() {
		return ferengiX;
	}

	public void setFerengiX(BigDecimal ferengiX) {
		this.ferengiX = ferengiX;
	}

	public BigDecimal getFerengiY() {
		return ferengiY;
	}

	public void setFerengiY(BigDecimal ferengiY) {
		this.ferengiY = ferengiY;
	}

	public BigDecimal getVulcanoX() {
		return vulcanoX;
	}

	public void setVulcanoX(BigDecimal vulcanoX) {
		this.vulcanoX = vulcanoX;
	}

	public BigDecimal getVulcanoY() {
		return vulcanoY;
	}

	public void setVulcanoY(BigDecimal vulcanoY) {
		this.vulcanoY = vulcanoY;
	}

	public BigDecimal getBetasoideX() {
		return betasoideX;
	}

	public void setBetasoideX(BigDecimal betasoideX) {
		this.betasoideX = betasoideX;
	}

	public BigDecimal getBetasoideY() {
		return betasoideY;
	}

	public void setBetasoideY(BigDecimal betasoideY) {
		this.betasoideY = betasoideY;
	}

	public Weather getCondition() {
		return condition;
	}

	public void setCondition(Weather condition) {
		this.condition = condition;
	}

	public BigDecimal getPlanetsDistance() {
		return planetsDistance;
	}

	public void setPlanetsDistance(BigDecimal planetsDistance) {
		this.planetsDistance = planetsDistance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
