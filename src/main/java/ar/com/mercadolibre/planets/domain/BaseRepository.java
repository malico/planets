package ar.com.mercadolibre.planets.domain;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.lang.reflect.ParameterizedType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base Repository implementation for Hibernate.
 * @author malico
 *
 * @param <T> the Class Type that the repository handle.
 * @param <ID> the Primary Key of the class.
 */
public abstract class BaseRepository<T, ID extends Serializable> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BaseRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public T byId(ID key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}

	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

}
