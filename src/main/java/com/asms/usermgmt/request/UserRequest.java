package com.asms.usermgmt.request;

import com.asms.usermgmt.entity.User;

/* User Request class represents the request object
 * for user management operations 
 * 
 */

public class UserRequest {

	// request type can be create/update/delete
	private String requestType;

	// user role can be student/management/ teaching staff/ non teaching staff
	private String userRole;

	// object is for user object
	private User userDetails;
	
	//logged in user's email
	private String loggedInUserEmail;

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	
	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public String getLoggedInUserEmail() {
		return loggedInUserEmail;
	}

	public void setLoggedInUserEmail(String loggedInUserEmail) {
		this.loggedInUserEmail = loggedInUserEmail;
	}
	
	

}
