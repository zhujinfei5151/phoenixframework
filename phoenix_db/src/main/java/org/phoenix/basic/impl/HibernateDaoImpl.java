package org.phoenix.basic.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.phoenix.basic.dao.IBaseDao;
import org.phoenix.basic.utils.HibernateUtil;

/**
 * 通用的Dao方法
 * @author mengfeiyang
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class HibernateDaoImpl<T> implements IBaseDao<T>{
	private Class<?> clz;
	public Class<?> getClz() {
		if(clz==null) {
			//获取泛型的Class对象
			clz = ((Class<?>)
					(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}
	@Override
	public T add(T t) {
		
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();	
		session.close();
		return t;
	}

	@Override
	public void update(T t) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		session.update(t);
		session.getTransaction().commit();		
		session.close();
	}

	@Override
	public void delete(int id) {
		Session session = HibernateUtil.openSession();
		session.beginTransaction();
		T t = load(id);
		session.delete(t);
		session.getTransaction().commit();	
		session.close();
	}

	//使用load会启用延迟加载，get不会
	@Override
	public T load(int id) {
		Session session = null;
		try{
			session = HibernateUtil.openSession();
			T t = (T) session.get(getClz(), id);
			return t;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(session!=null)session.close();
		}
			return null;
	}
	
	public T load(String hql){
			Session session = HibernateUtil.openSession();
			Query query = session.createQuery(hql);
			T t = (T) query.uniqueResult();
			session.close();
			return t;
	}

	@Override
	public List<T> loadAll(String hql) {
			Session session = HibernateUtil.openSession();
			Query query = session.createQuery(hql);
			List<T> t = (List<T>) query.list();
			session.close();
			return t;
	}
	@Override
	public List<T> loadAll() {
		Session session = HibernateUtil.openSession();
		Query query = session.createQuery("from "+getClz());
		List<T> t = (List<T>) query.list();
		session.close();
		return t;
	}

}
