package com.struts2.Actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bl.service.impl.NotesService;
import com.bl.service.impl.UserService;
import com.db.pojo.Notes;
import com.db.pojo.User;

public class NotesAction extends BaseAction {
	
	private static final Logger log = LogManager.getLogger(NotesAction.class);
	
	private String content;
	
	private String noteName;
	
	private String noteId;
	
	private NotesService notesService;
	
	private UserService userService;
	
	public boolean validateSaveNotes() {
		boolean result = true;
		if (StringUtils.isEmpty(getNoteName())) {
			result = false;
			addFieldError("noteName", "Title cannot be empty.");
		}
		return result;
	}
	
	public String saveNotes() {
		log.info("NotesAction -> saving notes now..");
		
		if (!validateSaveNotes()) {
			return INPUT;
		}
		// populate note
		Notes note = populateNotes();
		getNotesService().getNotesDAO().create(note);
		
		log.info("NotesAction -> notes saved successfully. Noe directing to success page.");
		return SUCCESS;
	}
	
	public String showNotes() {
		log.info("NotesAction -> request received to display the notes table");
		
		HttpSession session = getServletRequest().getSession();
		String userName = (String) session.getAttribute("userName");
		List<Notes> notesList = getNotesService().getNotesDAO().readByUserName(userName);
		getServletRequest().setAttribute("notesList", notesList); // save in request for table on the jsp
		
		return "showNotes";
	}
	
	public String viewNote() {
		log.info("NotesAction -> retrieving the record..");
		
		Notes note = getNotesService().getNotesDAO().readByNoteId(Long.parseLong(getNoteId()));
		// set form fields
		setNoteName(note.getNoteName());
		setContent(note.getContent());
		
		// set view window flag
		getServletRequest().setAttribute("windowType", "viewWindow");
		
		// populate the background
		showNotes();
		
		return "viewNote";
	}
	
	private Notes populateNotes() {
		Notes note = new Notes();
		note.setContent(getContent());
		note.setCreatedTime(new Date());
		note.setNoteName(getNoteName());
		HttpSession session = getServletRequest().getSession();
		String userName = (String) session.getAttribute("userName");
		User user = getUserService().getUserDAO().read(userName);
		note.setUserId(user.getUserId());
		
		return note;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NotesService getNotesService() {
		return notesService;
	}

	public void setNotesService(NotesService notesService) {
		this.notesService = notesService;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

}
