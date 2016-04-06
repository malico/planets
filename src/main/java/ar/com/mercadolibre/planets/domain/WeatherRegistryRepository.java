package ar.com.mercadolibre.planets.domain;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.mercadolibre.planets.service.ForecastService.Weather;

@Repository("weatherRegistryRepository")
public class WeatherRegistryRepository extends BaseRepository<WeatherRegistry, Long> {

	public WeatherRegistry findById(Long id) {
		return byId(id);
	}
	
	public void save(WeatherRegistry registry) {
		persist(registry);
	}
	
	public WeatherRegistry byDay(final int day) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("day", day));
		return (WeatherRegistry) criteria.uniqueResult();
	}

	public long getDaysWithCondition(Weather condition, int fromDay, int toDay) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ge("day", fromDay));
		criteria.add(Restrictions.le("day", toDay));
		criteria.add(Restrictions.eq("condition", condition));
		criteria.setProjection(Projections.rowCount());
		return (long) criteria.uniqueResult();
	}

	public long getMaximumRain(int fromDay, int toDay) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.ge("day", fromDay));
		criteria.add(Restrictions.le("day", toDay));
		criteria.add(Restrictions.eq("condition", Weather.RAIN));
		criteria.setProjection(Projections.max("planetsDistance"));
		return (long) criteria.uniqueResult();
	}
}
