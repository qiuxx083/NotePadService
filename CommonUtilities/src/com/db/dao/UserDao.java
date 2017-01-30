package com.db.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.db.pojo.User;

public class UserDao {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void create(User user) {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}
	
	public User read(String userName) {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("read");
		query.setString("userName", userName);
		// verify the list since it could be empty
		List resultList = query.list();
		if (resultList.isEmpty()) {
			session.getTransaction().commit();
			return null;
		} else {
			User result = (User) resultList.get(0);
			session.getTransaction().commit();
			return result;
		}
	}
	
}
