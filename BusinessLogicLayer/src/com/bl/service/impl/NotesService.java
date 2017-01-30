package com.bl.service.impl;

import com.db.dao.NotesDao;

public class NotesService {
	
	private NotesDao notesDAO;

	public NotesDao getNotesDAO() {
		return notesDAO;
	}

	public void setNotesDAO(NotesDao notesDAO) {
		this.notesDAO = notesDAO;
	}

}
