/**
@{author} mallikarjun.guranna
01-Sep-2017
*/
package com.asms.schoolmgmt.helper;

import java.util.ResourceBundle;

import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.schoolmgmt.entity.SetupSchoolDetails;
import com.asms.schoolmgmt.request.SchoolDetails;
import com.asms.schoolmgmt.request.UserRequest;

@Component
public class SchoolValidator {
	/**
	@{author} mallikarjun.guranna
	01-Sep-2017
	*/
	
	
	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(SchoolValidator.class);

	/*
	 * Method: validateSchoolDetailsRequest -> validates SchoolDetailsRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */
	
	public void validateSchoolDetailsRequest(UserRequest request, ResourceBundle messages, String type) throws AsmsException {
	
		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}
		
		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}
		
		SchoolDetails details = request.getSchoolDetails();
		
		if (null == details) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_DETAILS_NULL_CODE"),
					messages.getString("SCHOOL_DETAILS_NULL_MSG"));
		} 
		
		if (null == details.getId() || details.getId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_ID_NULL_CODE"),
					messages.getString("SCHOOL_ID_NULL_MSG"));
		} 
		if (null == details.getName() || details.getName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_NAME_NULL_CODE"),
					messages.getString("SCHOOL_NAME_NULL_MSG"));
		} 
		
		if (null == details.getAffiliationId() || details.getAffiliationId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_AID_NULL_CODE"),
					messages.getString("SCHOOL_AID_NULL_MSG"));
		} 
		
		if (null == details.getRegistrationCode() || details.getRegistrationCode().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_RCODE_NULL_CODE"),
					messages.getString("SCHOOL_RCODE_NULL_MSG"));
		} 
		
		if (null == details.getAddressLine1() || details.getAddressLine1().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_ADD1_NULL_CODE"),
					messages.getString("SCHOOL_ADD1_NULL_MSG"));
		} 
		
		if (null == details.getAddressLine2() || details.getAddressLine2().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_ADD2_NULL_CODE"),
					messages.getString("SCHOOL_ADD2_NULL_MSG"));
		} 
		
		if (null == details.getLocation() || details.getLocation().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_LOCATION_NULL_CODE"),
					messages.getString("SCHOOL_LOCATION_NULL_CODE"));
		} 
		

		if (null == details.getCountry() || details.getCountry().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("COUNTRY_NULL_CODE"),
					messages.getString("COUNTRY_NULL_MSG"));
		} 
		

		if (null == details.getState() || details.getState().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("STATE_NULL_CODE"),
					messages.getString("STATE_NULL_MSG"));
		} 
		
		if (null == details.getDistrict() || details.getDistrict().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("DISTRICT_NULL_CODE"),
					messages.getString("DISTRICT_NULL_MSG"));
		} 
		
		if (null == details.getTehsil() || details.getTehsil().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("TALUK_NULL_CODE"),
					messages.getString("TALUK_NULL_MSG"));
		} 
		if (null == details.getPincode() || details.getPincode().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
					messages.getString("PIN_NULL_MSG"));
		} 
		
		if (null == details.getContactNo1()|| details.getContactNo1().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
					messages.getString("PIN_NULL_MSG"));
		} 
		
		if (null == details.getMobileNo()|| details.getMobileNo().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("PIN_NULL_CODE"),
					messages.getString("PIN_NULL_MSG"));
		} 
		
		if (null == details.getEmailId()|| details.getEmailId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_EMAIL_NULL_CODE"),
					messages.getString("SCHOOL_SCHOOL_EMAIL_NULL_MSG"));
		} 
		else {
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (!emailValidator.isValid(details.getEmailId())) {
				throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
						messages.getString("EMAIL_INVALID"));
			}
		}
		
		if (details.getTotalNoOfStudents() <= 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_TOTAL_STUDENT_NO_INVALID"),
					messages.getString("SCHOOL_SCHOOL_TOTAL_STUDENT_NO_INVALID_MSG"));
		} 
		
		if (null == details.getContactPersonName()|| details.getContactPersonName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_CONTACT_NAME_NULL_CODE"),
					messages.getString("SCHOOL_SCHOOL_CONTACT_NAME_NULL_MSG"));
		} 
		
		if (null == details.getContactPersonEmailId()|| details.getContactPersonEmailId().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_SCHOOL_CONTACT_EMAIL_NULL_CODE"),
					messages.getString("SCHOOL_SCHOOL_CONTACT_EMAIL_NULL_MSG"));
		}
		else {
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (!emailValidator.isValid(details.getContactPersonEmailId())) {
				throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
						messages.getString("EMAIL_INVALID"));
			}
		}
		
		if (null == details.getBoardDetails() || details.getBoardDetails().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SCHOOL_BOARD_NULL_CODE"),
					messages.getString("SCHOOL_BOARD_NULL_MSG"));
		} 
		
	//validate for lattitude
	
	}
	//validate Setup School Request
	//---------------------------------------------
	/**
	 * method: validateSetupSchoolRequest
	 * input: request,messages and req type
	 * return type : void
	 * validates user request for setup school details
	 * 
	 */
	public void validateSetupSchoolRequest(UserRequest request, ResourceBundle messages, String type) throws AsmsException {
		
		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));
		}
		
		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}
		
		SetupSchoolDetails setupSchoolDetails = request.getSetupSchoolDetails();
		
		if (null == setupSchoolDetails) {
			throw exceptionHandler.constructAsmsException(messages.getString("SETUP_SCHOOL_TYPE_NULL_CODE"),
					messages.getString("SETUP_SCHOOL_TYPE_NULL_MSG"));
		} 
		
		if (null == setupSchoolDetails.getCurrentAcademicYear() || setupSchoolDetails.getCurrentAcademicYear().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SETUP_SCHOOL_ACADEMIC_YEAR_NULL_CODE"),
					messages.getString("SETUP_SCHOOL_ACADEMIC_YEAR_NULL_MSG"));
		} 
		
	}
	
}

	

