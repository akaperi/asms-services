package com.asms.usermgmt.request;


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
	private UserDetails userDetails;
	
	

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

	
	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	
	

}
