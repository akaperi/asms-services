package com.asms.rolemgmt.helper;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.validator.routines.EmailValidator;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.Constants;
import com.asms.rolemgmt.request.RoleDetails;
import com.asms.rolemgmt.request.SubRoleDetails;
import com.asms.usermgmt.request.ChangePasswordDetails;
import com.asms.usermgmt.request.TeachingSubjectDetails;
import com.asms.usermgmt.request.UserRequest;
import com.asms.usermgmt.request.management.ManagementDetails;
import com.asms.usermgmt.request.nonTeachingStaff.AddressDetails;
import com.asms.usermgmt.request.nonTeachingStaff.NonTeachingStaffDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffDocumentsDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffPreviousInformationDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffStatutoryDetails;
import com.asms.usermgmt.request.student.ParentDetails;
import com.asms.usermgmt.request.student.SiblingDetails;
import com.asms.usermgmt.request.student.StudentAddressDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.request.student.StudentDocumentDetails;
import com.asms.usermgmt.request.student.StudentPreviousDetails;
import com.asms.usermgmt.request.teachingStaff.TeachingStaffDetails;

@Component

public class RoleValidator {
	/*
	 * Validator.java does the validation of requests
	 * 
	 */

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(RoleValidator.class);

	public void validateRoleDetails(RoleDetails roleDetails, ResourceBundle messages) throws AsmsException {

		if (null == roleDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("ROLE_DETAILS_NULL_CODE"),
					messages.getString("ROLE_DETAILS_NULL_MSG"));

		if (roleDetails.getRoleId() > 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("ROLEID_DETAILS_NULL_CODE"),
					messages.getString("ROLEID_DETAILS_NULL_MSG"));
		}
		if (null == roleDetails.getRoleName() || roleDetails.getRoleName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("ROLENAME_DETAILS_NULL_CODE"),
					messages.getString("ROLENAME_DETAILS_NULL_MSG"));
		}
		if (null == roleDetails.getUserObject() || roleDetails.getUserObject().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("USEROBJECT_DETAILS_NULL_CODE"),
					messages.getString("USEROBJECT_DETAILS_NULL_MSG"));
		}

	}

	public void validateSubRoleDetails(SubRoleDetails subroleDetails, ResourceBundle messages) throws AsmsException {

		if (null == subroleDetails)
			throw exceptionHandler.constructAsmsException(messages.getString("SUBROLE_DETAILS_NULL_CODE"),
					messages.getString("SUBROLE_DETAILS_NULL_MSG"));

		if (subroleDetails.getSubRoleId() > 0) {
			throw exceptionHandler.constructAsmsException(messages.getString("SUBROLEID_DETAILS_NULL_CODE"),
					messages.getString("SUBROLEID_DETAILS_NULL_MSG"));
		}
		if (null == subroleDetails.getSubRoleName() || subroleDetails.getSubRoleName().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("SUBROLENAME_DETAILS_NULL_CODE"),
					messages.getString("SUBROLENAME_DETAILS_NULL_MSG"));
		}
	
		if (null == subroleDetails.getUserObject() || subroleDetails.getUserObject().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("USEROBJECT_DETAILS_NULL_CODE"),
					messages.getString("USEROBJECT_DETAILS_NULL_MSG"));
		}

	}
}
