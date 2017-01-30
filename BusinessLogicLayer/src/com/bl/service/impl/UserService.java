package com.bl.service.impl;

import com.db.dao.UserDao;

public class UserService {
	
	private UserDao userDAO;

	public UserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

}
