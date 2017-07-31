package com.asms.common.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.common.helper.Constants;
import com.asms.usermgmt.dao.UserMgmtDao;

/*
 * PrivilegesManager : For each request this class checks whether
 * 					   user who sent request is privileged to do the
 * 					   requested action 
 * 
 */



@Component
public class PrivilegesManager {
	
	@Autowired
	UserMgmtDao userMgmtDao;
	
	/*
	 * Method : isPrivileged
	 * parameters: String userEmail, String role, String action
	 * return : boolean
	 * 
	 * This method checks if the logged in user is privileged to do the requested
	 * action
	 */
	public boolean isPrivileged(String userEmail, String role, String action) throws AsmsException {
		
		boolean isPrivileged = false;
		String loggedInUserRole = userMgmtDao.getUserRole(userEmail);
		if(Constants.role_admin.equalsIgnoreCase(loggedInUserRole)){
			isPrivileged = true;
		}else {
			
		}
		return isPrivileged;
	}
	

}
