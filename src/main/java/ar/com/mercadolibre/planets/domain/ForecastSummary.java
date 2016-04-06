package ar.com.mercadolibre.planets.domain;

public class ForecastSummary {

	private long clear;
	private long rain;
	private long drought;
	private long optimal;
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
