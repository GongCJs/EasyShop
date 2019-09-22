package com.shopping.core.query;


import com.shopping.core.dao.IGenericDAO;
import com.shopping.core.query.support.IQuery;

import java.util.List;
import java.util.Map;

public class GenericQuery
		implements IQuery {
	private IGenericDAO dao;
	private int begin;
	private int max;
	private Map params;

	public GenericQuery(IGenericDAO dao) {
		this.dao = dao;
	}

	@Override
	public List getResult(String condition) {
		return this.dao.find(condition, this.params, this.begin, this.max);
	}

	@Override
	public List getResult(String condition, int begin, int max) {
		Object[] params = null;
		return this.dao.find(condition, this.params, begin, max);
	}

	@Override
	public int getRows(String condition) {
		int n = condition.toLowerCase().indexOf("order by");
		Object[] params = null;
		if (n > 0) {
			condition = condition.substring(0, n);
		}
		List ret = this.dao.query(condition, this.params, 0, 0);
		if ((ret != null) && (ret.size() > 0)) {
			return ((Long) ret.get(0)).intValue();
		}
		return 0;
	}

	@Override
	public void setFirstResult(int begin) {
		this.begin = begin;
	}

	@Override
	public void setMaxResults(int max) {
		this.max = max;
	}

	@Override
	public void setParaValues(Map params) {
		this.params = params;
	}
}