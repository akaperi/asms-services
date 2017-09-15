package com.asms.usermgmt.response;

import com.asms.common.response.SuccessResponse;


/*
 * RegistrationResponse.java is to return user registration response
 */

public class LoginResponse extends SuccessResponse{
	
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
