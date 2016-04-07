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

import org.apache.commons.lang3.Validate;

/**
 * Represents the data of a weather forecast for a given day.
 * @author malico
 */
@Entity
@Table(name = "forecasts")
public class Forecast {

	/** The precision for the big decimals. */
	private static final int PRECISION = 19;

	/** The scale of the big decimals. */
	private static final int SCALE = 2;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The forecasted day, never null.*/
	@Column(name = "day", nullable = false, unique = true)
	private int day;

	/** The axis-x position of the Ferengi planet, never null*/
	@Column(name = "ferengi_x", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal ferengiX;

	/** The axis-y position of the Ferengi planet, never null*/
	@Column(name = "ferengi_y", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal ferengiY;

	/** The axis-x position of the Vulcano planet, never null*/
	@Column(name = "vulcano_x", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal vulcanoX;

	/** The axis-y position of the Vulcano planet, never null*/
	@Column(name = "vulcano_y", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal vulcanoY;

	/** The axis-x position of the Betasoide planet, never null*/
	@Column(name = "betasoide_x", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal betasoideX;

	/** The axis-y position of the Betasoide planet, never null*/
	@Column(name = "betasoide_y", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal betasoideY;

	/** The forecasted weather, never null.*/
	@Column(name="weather", nullable=false)
	@Enumerated(EnumType.STRING)
	private WeatherCondition condition;

	/** The amount of millimeters rained, never null.*/
	@Column(name = "mm_rained", precision = PRECISION, scale = SCALE, nullable = false)
	private BigDecimal mmRained;
	
	/** Default constructor, for Hibernate.*/
	public Forecast() {
	}

	/**
	 * Builds a Forecast new instance.
	 * 
	 * @param aDay the forecasted day.
	 * @param ferengiXPos the axis-x position of Ferengi, cannot be null.
	 * @param ferengiYPos the axis-y position of Ferengi, cannot be null.
	 * @param vulcanoXPos the axis-x position of Vulcano, cannot be null.
	 * @param vulcanoYPos the axis-y position of Vulcano, cannot be null.
	 * @param betasoideXPos the axis-x position of Betasoide, cannot be null.
	 * @param betasoideYPos the axis-y position of Betasoide, cannot be null.
	 * @param theWeahter the weather, cannot be null.
	 * @param millimetersRained the rained millimeters, cannot be null.
	 */
	public Forecast(final int aDay,
			final BigDecimal ferengiXPos, final BigDecimal ferengiYPos,
			final BigDecimal vulcanoXPos, final BigDecimal vulcanoYPos,
			final BigDecimal betasoideXPos, final BigDecimal betasoideYPos,
			final WeatherCondition theWeahter, final BigDecimal millimetersRained) {
		setDay(aDay);
		setFerengiX(ferengiXPos);
		setFerengiY(ferengiYPos);
		setVulcanoX(vulcanoXPos);
		setVulcanoY(vulcanoYPos);
		setBetasoideX(betasoideXPos);
		setBetasoideY(betasoideYPos);
		setCondition(theWeahter);
		setPlanetsDistance(millimetersRained);
	}

	public int getDay() {
		return day;
	}

	public void setDay(int theDay) {
		this.day = theDay;
	}

	public BigDecimal getFerengiX() {
		return ferengiX;
	}

	public void setFerengiX(BigDecimal theFerengiX) {
		Validate.notNull(theFerengiX, "The position cannot be null");
		this.ferengiX = theFerengiX;
	}

	public BigDecimal getFerengiY() {
		return ferengiY;
	}

	public void setFerengiY(BigDecimal theFerengiY) {
		Validate.notNull(theFerengiY, "The position cannot be null");
		this.ferengiY = theFerengiY;
	}

	public BigDecimal getVulcanoX() {
		return vulcanoX;
	}

	public void setVulcanoX(BigDecimal theVulcanoX) {
		Validate.notNull(theVulcanoX, "The position cannot be null");
		this.vulcanoX = theVulcanoX;
	}

	public BigDecimal getVulcanoY() {
		return vulcanoY;
	}

	public void setVulcanoY(BigDecimal theVulcanoY) {
		Validate.notNull(theVulcanoY, "The position cannot be null");
		this.vulcanoY = theVulcanoY;
	}

	public BigDecimal getBetasoideX() {
		return betasoideX;
	}

	public void setBetasoideX(BigDecimal theBetasoideX) {
		Validate.notNull(theBetasoideX, "The position cannot be null");
		this.betasoideX = theBetasoideX;
	}

	public BigDecimal getBetasoideY() {
		return betasoideY;
	}

	public void setBetasoideY(BigDecimal theBetasoideY) {
		Validate.notNull(theBetasoideY, "The position cannot be null");
		this.betasoideY = theBetasoideY;
	}

	public WeatherCondition getCondition() {
		return condition;
	}

	public void setCondition(WeatherCondition theCondition) {
		Validate.notNull(theCondition, "The condition cannot be null");
		this.condition = theCondition;
	}

	public BigDecimal getPlanetsDistance() {
		return mmRained;
	}

	public void setPlanetsDistance(BigDecimal theMillimeters) {
		Validate.notNull(theMillimeters, "The mm cannot be null");
		this.mmRained = theMillimeters;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
