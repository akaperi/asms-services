package com.asms.multitenancy.helper;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.multitenancy.request.TrustDetails;
import com.asms.schoolmgmt.request.UserRequest;


@Service
@Component
public class TrustValidator {
	
	
	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(TrustValidator.class);
	
	

	/*
	 * Method: validateTrustDetails -> validates TrustDetailsRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */
	public void validateTrustDetails(UserRequest request, ResourceBundle messages, String type) throws AsmsException {
		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}
		
		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}
		
		TrustDetails  trustDetails = request.getTrustDetails();
		
		if (null == trustDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_DETAILS_NULL_CODE"),
					messages.getString("TRUST_DETAILS_NULL_MSG"));
		} 
		
		if (null == trustDetails.getName() || trustDetails.getName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_NAME_NULL_CODE"),
					messages.getString("TRUST_NAME_NULL_MSG"));
		}

		if (null == trustDetails.getAddress() || trustDetails.getAddress().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_ADDRESS_NULL_CODE"),
					messages.getString("TRUST_ADDRESS_NULL_MSG"));
		} 
		
		if (null == trustDetails.getContactNo() || trustDetails.getContactNo().isEmpty()) { /// u have to validate if it contains only number
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_CONTACT_NO_NULL_CODE"),
					messages.getString("TRUST_CONTACT_NO_NULL_MSG"));
		} 
		
		if (null == trustDetails.getEmail() || trustDetails.getEmail().isEmpty()) {      // email validation
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_EMAIL_NULL_CODE"),
					messages.getString("TRUST_EMAIL_NULL_MSG"));
		} 
		
		if (null == trustDetails.getAffiliationId() || trustDetails.getAffiliationId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_AID_NULL_CODE"),
					messages.getString("TRUST_AID_NULL_MSG"));
		} 
		
		if (null == trustDetails.getLatitude()) {
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_LATITUDE_NULL_CODE"),
					messages.getString("TRUST_LATITUDE_NULL_MSG"));
		} 
		
		if (null == trustDetails.getLongitude()) {
			throw exceptionHandler.constructAsmsException(messages.getString("TRUST_LONGITUDE_NULL_CODE"),
					messages.getString("TRUST_LONGITUDE_NULL_MSG"));
		} 

		if (null == trustDetails.getCity() || trustDetails.getCity().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("CITY_NULL_CODE"),
					messages.getString("CITY_NULL_MSG"));
		} 
		
		

		if (null == trustDetails.getState() || trustDetails.getState().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("STATE_NULL_CODE"),
					messages.getString("STATE_NULL_MSG"));
		} 
		
	

		if (null == trustDetails.getCountry() || trustDetails.getCountry().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("COUNTRY_NULL_CODE"),
					messages.getString("COUNTRY_NULL_MSG"));
		} 
		

		
		
	}

}
