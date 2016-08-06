package com.hoyoung.fortis.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED)
public class FortisDAO {
	private static final Logger log = LoggerFactory.getLogger(FortisDAO.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	// 新增
	public void save(Object transientInstance) {
		log.debug("saving " + transientInstance.getClass().getSimpleName() + " instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	// 刪除
	public void delete(Object persistentInstance) {
		log.debug("deleting " + persistentInstance.getClass().getSimpleName() + " instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	// 查詢 by Id
	public Object findById(Class clazz, Serializable id) {
		log.debug("getting " + clazz.getSimpleName() + " instance with id: " + id);
		try {
			Object instance = getCurrentSession().get(clazz, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	// 查詢 All
	public List findAll(Class clazz) {
		log.debug("finding all " + clazz.getSimpleName() + " instances");
		try {
			String queryString = "from " + clazz.getSimpleName();
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	// 查詢 by Property
	public List findByProperty(Class clazz, String propertyName, Object value) {
		log.debug("finding " + clazz.getSimpleName() + " instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from "+clazz.getSimpleName()+" as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	// 修改 by Dirty
	public void attachDirty(Object instance) {
		log.debug("attaching dirty " + instance.getClass().getSimpleName() + " instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	// 修改 by Clean
	public void attachClean(Object instance) {
		log.debug("attaching clean " + instance.getClass().getSimpleName() + " instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	// 查詢資料 by Criteria
	public List findByCriteria(DetachedCriteria detachedCriteria, int startRow, int endRow) {
		
		Session session = getCurrentSession();

		Criteria criteria = detachedCriteria.getExecutableCriteria(session);
		criteria.setFirstResult(startRow);
		criteria.setMaxResults(endRow);

		return criteria.list();
	}
	
	// 查詢資料筆數 by Criteria
	public long fetchCountByCriteria(DetachedCriteria detachedCriteria) {
		
		Session session = getCurrentSession();
		
		Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
		Long count =(Long) executableCriteria.setProjection(Projections.rowCount()).uniqueResult();
		
		return count;
	}
	
}
