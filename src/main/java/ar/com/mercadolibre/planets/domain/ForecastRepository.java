package ar.com.mercadolibre.planets.domain;

import org.apache.commons.lang3.Validate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Repository to access Forecasts instances.
 * @author malico
 */
@Repository("forecastRepository")
public class ForecastRepository extends BaseRepository<Forecast, Long> {

	/**
	 * Saves a Forecast instance.
	 * @param instance the Forecast to save, cannot be null.
	 */
	public void save(Forecast instance) {
		Validate.notNull(instance, "The instance to save cannot be null");
		persist(instance);
	}
	
	/**
	 * Finds a instance by its day.
	 * @param day the day of the instance to find, must be greater than 0.
	 * @return a instance matching the given day, if any.
	 */
	public Forecast byDay(final int day) {
		Validate.isTrue(day > 0, "The day must be greater than 0.");
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("day", day));
		return (Forecast) criteria.uniqueResult();
	}

	/**
	 * Gets the number of days with the given weather conditions between the passed dates.
	 * @param condition the weather condition, cannot be null.
	 * @param fromDay the from day, must be greater than 0.
	 * @param toDay the to day, must be greater than 0 and greater than fromDay.
	 * @return the count of days matching the restrictions.
	 */
	public long getDaysWithCondition(WeatherCondition condition, int fromDay, int toDay) {
		Validate.isTrue(fromDay > 0, "The day must be greater than 0.");
		Validate.isTrue(toDay > 0, "The day must be greater than 0.");
		Validate.isTrue(toDay >= fromDay, "The to day must be greater than the from date.");
		Validate.notNull(condition, "The condition cannot be null");
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ge("day", fromDay));
		criteria.add(Restrictions.le("day", toDay));
		criteria.add(Restrictions.eq("condition", condition));
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}

	/**
	 * Gets the day of maximum rain.
	 * @param fromDay the from day, must be greater than 0.
	 * @param toDay the to day, must be greater than 0 and greater than fromDay.
	 * @return the day which the day was the maximum.
	 */
	public Forecast getMaximumRainDay(int fromDay, int toDay) {
		Validate.isTrue(fromDay > 0, "The day must be greater than 0.");
		Validate.isTrue(toDay > 0, "The day must be greater than 0.");
		Validate.isTrue(toDay >= fromDay, "The to day must be greater than the from date.");
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ge("day", fromDay));
		criteria.add(Restrictions.le("day", toDay));
		criteria.add(Restrictions.eq("condition", WeatherCondition.RAIN));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	    criteria.addOrder(org.hibernate.criterion.Order.desc("mmRained"));
	    criteria.setMaxResults(1);
		return (Forecast) criteria.uniqueResult();
	}
}
