package ar.com.mercadolibre.planets.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ar.com.mercadolibre.planets.service.ForecastService.Weather;

@Entity
@Table(name = "weather_registries")
public class WeatherRegistry {

	/** The precision for the big decimals. */
	private static final int PRECISION = 19;

	/** The scale of the big decimals. */
	private static final int SCALE = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "day", nullable = false, unique = true)
	private int day;

	@Column(name = "ferengi_x", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal ferengiX;

	@Column(name = "ferengi_y", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal ferengiY;

	@Column(name = "vulcano_x", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal vulcanoX;

	@Column(name = "vulcano_y", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal vulcanoY;

	@Column(name = "betasoide_x", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal betasoideX;

	@Column(name = "betasoide_y", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal betasoideY;

	@Column(name="weather", nullable=false)
	@Enumerated(EnumType.STRING)
	private Weather condition;

	@Column(name = "distance", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal planetsDistance;
	
	public WeatherRegistry() {
	}

	public WeatherRegistry(final int aDay,
			final BigDecimal ferengiXPos, final BigDecimal ferengiYPos,
			final BigDecimal vulcanoXPos, final BigDecimal vulcanoYPos,
			final BigDecimal betasoideXPos, final BigDecimal betasoideYPos,
			final Weather theWeahter, final BigDecimal theDistance) {
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
