package com.struts2.Actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ServletRequestAware {
	
	private String actionType;
	
	private HttpServletRequest request;
	
	public String execute() {
		Object result = null;
		ActionContext context = ActionContext.getContext();
		ActionInvocation ai = context.getActionInvocation();
		Class<?> targetClass = ai.getAction().getClass();
		HttpSession session = getServletRequest().getSession();
		try {
			if (session.getAttribute("userName")  == null && getActionType() == null) {
				result = "globalLogin";
			} else if (getActionType() == null) {
				return SUCCESS;
			} else {
				Method targetMethod = targetClass.getDeclaredMethod(getActionType());
				result = targetMethod.invoke(ai.getAction());
			}
			/**
			 * need to check result here (validation result)
			 */
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) result;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	public HttpServletRequest getServletRequest() {
		return request;
	}

}
