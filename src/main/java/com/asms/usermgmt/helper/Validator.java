package com.asms.usermgmt.helper;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.usermgmt.request.UserRequest;

/*
 * Validator.java does the validation of requests
 * 
 */

@Component
public class Validator {
	
		@Autowired
		private ExceptionHandler exceptionHandler;
		
		/*
		 * Method: validateRequest -> validates userRequest for null.
		 * Parameters -> UserRequest
		 * throws -> AsmsException
		 */
		
		public void validateRequest(UserRequest request) throws AsmsException{
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			
			if(null == request) {				
				throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"), messages.getString("REQUEST_NULL"));			
				
			}
			if(null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"), messages.getString("REQUEST_TYPE_NULL"));	
			}
			
			if(null == request.getLoggedInUserEmail() || request.getLoggedInUserEmail().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("LOGGEDINUSER_EMAIL_NULL_CODE"), messages.getString("LOGGEDINUSER_EMAIL_NULL"));	
			}
			
			if(null == request.getUserRole() || request.getUserRole().trim().isEmpty()) {
				throw exceptionHandler.constructAsmsException(messages.getString("LOGGEDINUSER_EMAIL_NULL_CODE"), messages.getString("NEW_USER_ROLE_NULL"));
				
			}if(null == request.getUserDetails()) {
				throw exceptionHandler.constructAsmsException(messages.getString("USER_OBJECT_NULL_CODE"), messages.getString("USER_OBJECT_NULL"));	
			}
			
			
		}
	
}
