package ar.com.mercadolibre.planets.domain;

/**
 * A forecasts summary.
 * @author malico
 */
public class ForecastSummary {

	/** The number of clear days. */
	private long clear;
	
	/** The number of rainy days. */
	private long rain;
	
	/** The number of drought days. */
	private long drought;
	
	/** The number of optimal days. */
	private long optimal;
	
	/** The day of maximum rain. */
	private long maxRainDay;

	public ForecastSummary(long clearDays, long rainyDays, long droughtDays,
			long optimalDays, long theMaxRainyDay) {
		setClear(clearDays);
		setRain(rainyDays);
		setDrought(droughtDays);
		setOptimal(optimalDays);
		setMaxRainDay(theMaxRainyDay);
	}

	public long getClear() {
		return clear;
	}

	public void setClear(long clear) {
		this.clear = clear;
	}

	public long getRain() {
		return rain;
	}

	public void setRain(long rain) {
		this.rain = rain;
	}

	public long getDrought() {
		return drought;
	}

	public void setDrought(long drought) {
		this.drought = drought;
	}

	public long getOptimal() {
		return optimal;
	}

	public void setOptimal(long optimal) {
		this.optimal = optimal;
	}

	public long getMaxRainDay() {
		return maxRainDay;
	}

	public void setMaxRainDay(long maxRainDay) {
		this.maxRainDay = maxRainDay;
	}

}
