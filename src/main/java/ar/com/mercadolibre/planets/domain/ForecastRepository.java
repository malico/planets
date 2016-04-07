package ar.com.mercadolibre.planets.domain;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
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
	 * Gets the days of maximum rain.
	 * @param fromDay the from day, must be greater than 0.
	 * @param toDay the to day, must be greater than 0 and greater than fromDay.
	 * @return the day which the day was the maximum.
	 */
	public List<Forecast> getMostRainyDays(int fromDay, int toDay) {
		Validate.isTrue(fromDay > 0, "The day must be greater than 0.");
		Validate.isTrue(toDay > 0, "The day must be greater than 0.");
		Validate.isTrue(toDay >= fromDay, "The to day must be greater than the from date.");
		
		Query maxMmQuery = getSession().createQuery(
				"select ff.mmRained from Forecast ff order by mmRained desc");
		maxMmQuery.setMaxResults(1);
		Object result = maxMmQuery.uniqueResult();
		
		Query daysQuery = getSession().createQuery(
		        "select f from Forecast f where f.mmRained = :max ");
		daysQuery.setParameter("max", result);
		return daysQuery.list();
	}
}
