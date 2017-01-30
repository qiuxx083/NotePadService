package com.db.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.db.pojo.Notes;

public class NotesDao {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void create(Notes note) {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(note);
		session.getTransaction().commit();
	}
	
	public List<Notes> readByUserName(String userName) {
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("readByUserName");
		query.setString("userName", userName);
		// verify the list since it could be empty
		List<Notes> resultList = (List<Notes>) query.list();
		session.getTransaction().commit();
		return resultList;
	}
	
	public Notes readByNoteId(Long id) {
		Notes result = null;
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("readByNoteId");
		query.setLong("noteId", id);
		List<Notes> resultList = (List<Notes>) query.list();
		if (!resultList.isEmpty()) {
			result = resultList.get(0);
		}
		return result;
	}

}
