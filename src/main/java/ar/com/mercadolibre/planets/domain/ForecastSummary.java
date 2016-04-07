package ar.com.mercadolibre.planets.domain;

import java.util.List;

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
	
	/** The days of maximum rain. */
	private List<Long> maxRainDays;

	public ForecastSummary(long clearDays, long rainyDays, long droughtDays,
			long optimalDays, List<Long> theMaxRainyDays) {
		setClear(clearDays);
		setRain(rainyDays);
		setDrought(droughtDays);
		setOptimal(optimalDays);
		setMaxRainDay(theMaxRainyDays);
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

	public List<Long> getMaxRainDays() {
		return maxRainDays;
	}

	public void setMaxRainDay(List<Long> maxRainDays) {
		this.maxRainDays = maxRainDays;
	}

}
