package com.asms.usermgmt.response;

import com.asms.common.response.SuccessResponse;


/*
 * RegistrationResponse.java is to return user registration response
 */

public class RegistrationResponse extends SuccessResponse{
	
	private String userId;
	
	private String isNew;
	
	private int progressPercentage = 0;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public int getProgressPercentage() {
		return progressPercentage;
	}

	public void setProgressPercentage(int progressPercentage) {
		this.progressPercentage = progressPercentage;
	}
	
	

}
