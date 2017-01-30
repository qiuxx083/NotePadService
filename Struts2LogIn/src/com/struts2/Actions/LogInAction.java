package com.struts2.Actions;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bl.service.impl.UserService;
import com.db.pojo.User;

public class LogInAction extends BaseAction {
	
	private static final Logger log = LogManager.getLogger(LogInAction.class);
	
	private String userName;
	
	private String passWord;
	
	private UserService userService;
	
	private boolean validateDoLogin() {
		boolean result = true;
		if (StringUtils.isEmpty(getUserName())) {
			result = false;
			addFieldError("userName", "Username is required.");
		}
		if (StringUtils.isEmpty(getPassWord())) {
			result = false;
			addFieldError("passWord", "Password is required.");
		}
		return result;
	}
	
	public String doLogin() {
		log.info(getUserName() + "is trying to log in, and the password is " + getPassWord());
		
		if (!validateDoLogin()) {
			return INPUT;
		}
		
		// authenticate credential against database
		User user = getUserService().getUserDAO().read(getUserName());
		if (user != null) {
			if (!getPassWord().equals(user.getPassWord())) {
				addActionError("Username and Password do not match!");
				return ERROR;
			}
		} else {
			addActionError("The user does not exist!");
			return ERROR;
		}
		
		HttpSession session = getServletRequest().getSession();
		session.setAttribute("userName", getUserName());
		session.setAttribute("passWord", getPassWord());
		
		return SUCCESS;
	}
	
	public String doLogout() {
		HttpSession session = getServletRequest().getSession();
		if (session != null) {
			session.invalidate();
		}
		return "loginPage";
	}
	
	public String showRegisterPage() {
		return "registerPage";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
