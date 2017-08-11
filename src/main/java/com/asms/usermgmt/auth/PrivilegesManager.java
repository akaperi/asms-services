package com.asms.usermgmt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.common.helper.Constants;
import com.asms.usermgmt.dao.UserMgmtDao;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.PrincipalUser;

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
	public PrincipalUser isPrivileged(User user, String role, String action) throws AsmsException {
		
		PrincipalUser pUser = new PrincipalUser();
		if(Constants.role_admin.equalsIgnoreCase(user.getRoleObject().getRoleName())){
			pUser.setPrivileged(true);
			pUser.setLoggedInUser(user);
		}else {
			System.out.println(new AsmsException());
			
		}
		return pUser;
	}
	

}
