package com.shopping.core.base;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.shopping.core.dao.IGenericDAO;

public class GenericDAO<T> implements IGenericDAO<T> {
	protected Class<T> entityClass;

	@Autowired
	@Qualifier("genericEntityDao")
	private GenericEntityDao geDao;

	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public GenericEntityDao getGeDao() {
		return this.geDao;
	}

	public void setGeDao(GenericEntityDao geDao) {
		this.geDao = geDao;
	}

	public GenericDAO() {
		this.entityClass =
				((Class) ((java.lang.reflect.ParameterizedType) getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	public GenericDAO(Class<T> type) {
		this.entityClass = type;
	}

	@Override
	public int batchUpdate(String jpql, Object[] params) {
		return this.geDao.batchUpdate(jpql, params);
	}

	@Override
	public List executeNamedQuery(String queryName, Object[] params, int begin, int max) {
		return this.geDao.executeNamedQuery(queryName, params, begin, max);
	}

	@Override
	public List executeNativeNamedQuery(String nnq) {
		return this.geDao.executeNativeNamedQuery(nnq);
	}

	@Override
	public List executeNativeQuery(String nnq, Object[] params, int begin, int max) {
		return this.geDao.executeNativeQuery(nnq, params, begin, max);
	}

	@Override
	public int executeNativeSQL(String nnq) {
		return this.geDao.executeNativeSQL(nnq);
	}

	@Override
	public List find(String query, Map params, int begin, int max) {
		return getGeDao()
				.find(this.entityClass, query, params, begin, max);
	}

	@Override
	public void flush() {
		this.geDao.flush();
	}

	@Override
	public T get(Serializable id) {
		return (T) getGeDao().get(this.entityClass, id);//.get(this.entityClass, id);
	}

	@Override
	public T getBy(String propertyName, Object value) {
		return (T) getGeDao().getBy(this.entityClass, propertyName, value);
	}

	@Override
	public List query(String query, Map params, int begin, int max) {
		return getGeDao().query(query, params, begin, max);
	}

	@Override
	public void remove(Serializable id) {
		getGeDao().remove(this.entityClass, id);
	}

	@Override
	public void save(Object newInstance) {
		getGeDao().save(newInstance);
	}

	@Override
	public void update(Object transientObject) {
		getGeDao().update(transientObject);
	}
}