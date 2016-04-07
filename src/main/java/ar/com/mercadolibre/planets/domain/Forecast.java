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
		setMillimetersRained(millimetersRained);
	}

	/**
	 * Gets the forecasted day.
	 * @return a day number
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Sets the day to forecast.
	 * @param theDay a day number.
	 */
	public void setDay(int theDay) {
		this.day = theDay;
	}

	/**
	 * Gets the axis-x position of Ferengi planet.
	 * @return the position, in BigDecimal
	 */
	public BigDecimal getFerengiX() {
		return ferengiX;
	}

	/**
	 * Sets the axis-x position of Ferengi planet
	 * @param theFerengiX the position to set, cannot be null.
	 */
	public void setFerengiX(BigDecimal theFerengiX) {
		Validate.notNull(theFerengiX, "The position cannot be null");
		this.ferengiX = theFerengiX;
	}

	/**
	 * Gets the axis-y position of Ferengi planet.
	 * @return the position, in BigDecimal
	 */
	public BigDecimal getFerengiY() {
		return ferengiY;
	}

	/**
	 * Sets the axis-y position of Ferengi planet
	 * @param theFerengiY the position to set, cannot be null.
	 */
	public void setFerengiY(BigDecimal theFerengiY) {
		Validate.notNull(theFerengiY, "The position cannot be null");
		this.ferengiY = theFerengiY;
	}

	/**
	 * Gets the axis-x position of Vulcano planet.
	 * @return the position, in BigDecimal
	 */
	public BigDecimal getVulcanoX() {
		return vulcanoX;
	}

	/**
	 * Sets the axis-y position of Vulcano planet
	 * @param theVulcanoX the position to set, cannot be null.
	 */
	public void setVulcanoX(BigDecimal theVulcanoX) {
		Validate.notNull(theVulcanoX, "The position cannot be null");
		this.vulcanoX = theVulcanoX;
	}

	/**
	 * Gets the axis-y position of Vulcano planet.
	 * @return the position, in BigDecimal
	 */
	public BigDecimal getVulcanoY() {
		return vulcanoY;
	}

	/**
	 * Sets the axis-y position of Vulcano planet
	 * @param theVulcanoY the position to set, cannot be null.
	 */
	public void setVulcanoY(BigDecimal theVulcanoY) {
		Validate.notNull(theVulcanoY, "The position cannot be null");
		this.vulcanoY = theVulcanoY;
	}

	/**
	 * Gets the axis-x position of Betasoide planet.
	 * @return the position, in BigDecimal
	 */
	public BigDecimal getBetasoideX() {
		return betasoideX;
	}
	
	/**
	 * Sets the axis-x position of Betasoide planet
	 * @param theBetasoideX the position to set, cannot be null.
	 */
	public void setBetasoideX(BigDecimal theBetasoideX) {
		Validate.notNull(theBetasoideX, "The position cannot be null");
		this.betasoideX = theBetasoideX;
	}

	/**
	 * Gets the axis-y position of Betasoide planet.
	 * @return the position, in BigDecimal
	 */
	public BigDecimal getBetasoideY() {
		return betasoideY;
	}

	/**
	 * Sets the axis-y position of Ferengi planet
	 * @param theBetasoideY the position to set, cannot be null.
	 */
	public void setBetasoideY(BigDecimal theBetasoideY) {
		Validate.notNull(theBetasoideY, "The position cannot be null");
		this.betasoideY = theBetasoideY;
	}

	/**
	 * Gets the weather forecasted.
	 * @return a Weather value.
	 */
	public WeatherCondition getCondition() {
		return condition;
	}

	/**
	 * Sets the weather forecasted.
	 * @param theCondition the weather condition, cannot be null.
	 */
	public void setCondition(WeatherCondition theCondition) {
		Validate.notNull(theCondition, "The condition cannot be null");
		this.condition = theCondition;
	}

	/**
	 * Gets the rained millimeters.
	 * @return the mm, in BigDecimal.
	 */
	public BigDecimal getMillimetersRaied() {
		return mmRained;
	}

	/**
	 * Sets the rained millimeters.
	 * @param theMillimeters the millimeters, cannot be null.
	 */
	public void setMillimetersRained(BigDecimal theMillimeters) {
		Validate.notNull(theMillimeters, "The mm cannot be null");
		this.mmRained = theMillimeters;
	}

	public Long getId() {
		return id;
	}

}
