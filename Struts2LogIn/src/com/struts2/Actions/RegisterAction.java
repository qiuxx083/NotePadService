package com.struts2.Actions;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bl.service.impl.UserService;
import com.db.pojo.User;

public class RegisterAction extends BaseAction {
	
	private static final Logger log = LogManager.getLogger(RegisterAction.class);
	
	private String userName;
	
	private String passWord;
	
	private String rePWord;
	
	private int age;
	
	private String gender;
	
	private UserService userService;
	
	private boolean validateDoRegister() {
		boolean result = true;
		if (StringUtils.isEmpty(getUserName())) {
			result = false;
			addFieldError("userName", "Username is required.");
		}
		if (StringUtils.isEmpty(getPassWord())) {
			result = false;
			addFieldError("passWord", "Password is required.");
		}
		if (!getPassWord().equals(getRePWord())) {
			result = false;
			addFieldError("rePWord", "The passwords don't match.");
		}
		return result;
	}
	
	public String doRegister() {
		if (!validateDoRegister()) {
			return INPUT;
		}
		
		User user = populateUser();
		getUserService().getUserDAO().create(user);
		
		return SUCCESS;
	}
	
	private User populateUser() {
		User user = new User();
		user.setUserName(getUserName());
		user.setPassWord(getPassWord());
		user.setAge(getAge());
		user.setGender(getGender());
		return user;
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

	public String getRePWord() {
		return rePWord;
	}

	public void setRePWord(String rePWord) {
		this.rePWord = rePWord;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
