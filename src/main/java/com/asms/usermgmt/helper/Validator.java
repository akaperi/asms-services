package com.asms.usermgmt.helper;

import java.util.ResourceBundle;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.usermgmt.request.ManagementDetails;
import com.asms.usermgmt.request.StudentDetails;
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
	 * Method: validateRequest -> validates userRequest for null. Parameters ->
	 * UserRequest throws -> AsmsException
	 */

	public void validateRequest(UserRequest request) throws AsmsException {
		ResourceBundle messages = AsmsHelper.getMessageFromBundle();

		if (null == request) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_NULL_CODE"),
					messages.getString("REQUEST_NULL"));

		}
		if (null == request.getRequestType() || request.getRequestType().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("REQUEST_TYPE_NULL_CODE"),
					messages.getString("REQUEST_TYPE_NULL"));
		}

		if (null == request.getLoggedInUserEmail() || request.getLoggedInUserEmail().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("LOGGEDINUSER_EMAIL_NULL_CODE"),
					messages.getString("LOGGEDINUSER_EMAIL_NULL"));
		}

		if (null == request.getUserRole() || request.getUserRole().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("LOGGEDINUSER_EMAIL_NULL_CODE"),
					messages.getString("NEW_USER_ROLE_NULL"));

		}
		if (null == request.getUserDetails()) {
			throw exceptionHandler.constructAsmsException(messages.getString("USER_OBJECT_NULL_CODE"),
					messages.getString("USER_OBJECT_NULL"));
		}

		// email validation starts
		if (null == request.getUserDetails().getEmail() || request.getUserDetails().getEmail().trim().isEmpty()) {
			throw exceptionHandler.constructAsmsException(messages.getString("USER_EMAIL_NULL_CODE"),
					messages.getString("USER_EMAIL_NULL"));
		} else {
			EmailValidator emailValidator = EmailValidator.getInstance();
			if (!emailValidator.isValid(request.getUserDetails().getEmail())) {
				throw exceptionHandler.constructAsmsException(messages.getString("EMAIL_INVALID_CODE"),
						messages.getString("EMAIL_INVALID"));
			}
		}
		// email validation ends

		if (request.getUserRole().equalsIgnoreCase(Constants.role_student)) {

			// student validation starts
			StudentDetails studentDetails = request.getUserDetails().getStudenrDetails();

			if (null == studentDetails) {
				throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_NULL_CODE"),
						messages.getString("STUDENT_DETAILS_NULL"));
			} else {
				if (null == studentDetails.getAdmissionNo() || studentDetails.getAdmissionNo().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_ADMISSION_NO_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_ADMISSION_NO_NULL"));

				}
				if (null == studentDetails.getStudentType() || studentDetails.getStudentType().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_TYPE_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_TYPE_NULL"));
				}
				
				if (null == studentDetails.getAdmissionDate()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_ADMISSION_DATE_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_ADMISSION_DATE_NULL"));
				

				}
				
				if (null == studentDetails.getStudentClass() || studentDetails.getStudentClass().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_CLASS_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_CLASS_NULL"));

				}
				if (null == studentDetails.getStudentSection() || studentDetails.getStudentSection().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_SECTION_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_SECTION_NULL"));

				}
				if (null == studentDetails.getStudentFirstName() || studentDetails.getStudentFirstName().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_FIRSTNAME_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_FIRSTNAME_NULL"));

				}
				if (null == studentDetails.getStudentLastName() || studentDetails.getStudentLastName().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_LASTNAME_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_LASTNAME_NULL"));

				}
				
				
				if (null == studentDetails.getStudentDob()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_DOB_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_DOB_NULL"));

				}
				
				if (null == studentDetails.getStudentGender() || studentDetails.getStudentGender().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_GENDER_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_GENDER_NULL"));

				}
				
				if (null == studentDetails.getStudentIdentificationMarks() || studentDetails.getStudentIdentificationMarks().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_IMARK_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_IMARK_NULL"));

				}
				if (studentDetails.getStudentAgeInYears() <= 0) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_AGE_INVALID_CODE"),
							messages.getString("STUDENT_DETAILS_AGE_INVALID"));

				}
				if (null == studentDetails.getStudentBirthplace() || studentDetails.getStudentBirthplace().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_BIRTHPLACE_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_BIRTHPLACE_NULL"));

				}
				if (null == studentDetails.getStudentNationality() || studentDetails.getStudentNationality().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_NATIONALITY_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_NATIONALITY_NULL"));

				}
				if (null == studentDetails.getStudentReligion() || studentDetails.getStudentReligion().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_RELIGION_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_RELIGION_NULL"));

				}
				if (null == studentDetails.getStudentCasteCategory() || studentDetails.getStudentCasteCategory().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_CASTE_CATEGORY_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_CASTE_CATEGORY_NULL"));

				}
				if (null == studentDetails.getStudentSubCaste() || studentDetails.getStudentSubCaste().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_SUB_CASTE_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_SUB_CASTE_NULL"));

				}
				if (null == studentDetails.getStudentMotherTongue() || studentDetails.getStudentMotherTongue().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_MOTHERTOUNGUE_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_MOTHERTOUNGUE_NULL"));

				}
				if (null == studentDetails.getStudentPhoto() || studentDetails.getStudentPhoto().isEmpty()) {
					throw exceptionHandler.constructAsmsException(messages.getString("STUDENT_DETAILS_SECTION_NULL_CODE"),
							messages.getString("STUDENT_DETAILS_SECTION_NULL"));

				}
					
			}

		} else if (request.getUserRole().equalsIgnoreCase(Constants.role_management)) {
			
			ManagementDetails managementDetails = request.getUserDetails().getManagementDetails();
			
			if(managementDetails == null)
				throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_NULL_CODE"),
						messages.getString("MGMT_DETAILS_NULL_MSG"));
			
			if((managementDetails.getSchoolId().isEmpty() == true) || (managementDetails.getSchoolId() == null))
						throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_SCHOOLID_NULL_CODE"),
								messages.getString("MGMT_DETAILS_SCHOOLID_NULL_MSG"));
			
			/*if((managementDetails.getTrustId().isEmpty() == true) || (managementDetails.getTrustName() == null))
					throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_TRUSTID_NULL_CODE"),
							messages.getString("MGMT_DETAILS_TRUSTID_NULL_MSG"));*/
			
			/*if((managementDetails.getTrustName().isEmpty() == true) || (managementDetails.getTrustName() == null))
						throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_TRUSTNAME_NULL_CODE"),
								messages.getString("MGMT_DETAILS_TRUSTNAME_NULL_MSG"));*/
			
			if((managementDetails.getMngmtRole().isEmpty() == true) || (managementDetails.getMngmtRole() == null))
							throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTROLE_NULL_CODE"),
									messages.getString("MGMT_DETAILS_MNGMTROLE_NULL_MSG"));
			
			if((managementDetails.getMngmtFirstName().isEmpty() == true) || (managementDetails.getMngmtFirstName() == null))
								throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTFIRSTNAME_NULL_CODE"),
										messages.getString("MGMT_DETAILS_MNGMTFIRSTNAME_NULL_MSG"));
			
			if((managementDetails.getMngmtMiddleName().isEmpty() == true) || (managementDetails.getMngmtMiddleName() == null))
									throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTMIDDLENAME_NULL_CODE"),
											messages.getString("MGMT_DETAILS_MNGMTMIDDLENAME_NULL_MSG"));
			
			if((managementDetails.getMngmtLastName().isEmpty() == true) || (managementDetails.getMngmtLastName() == null))
										throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTLASTNAME_NULL_CODE"),
												messages.getString("MGMT_DETAILS_MNGMTLASTNAME_NULL_MSG"));
			
			if((managementDetails.getMngmtDesignation().isEmpty() == true) || (managementDetails.getMngmtDesignation() == null))
											throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTDESIGNATION_NULL_CODE"),
													messages.getString("MGMT_DETAILS_MNGMTDESIGNATION_NULL_MSG"));
			
			/*if((managementDetails.getMngmtLoginId().isEmpty() == true) || (managementDetails.getMngmtLoginId() == null))
												throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTLOGINID_NULL_CODE"),
														messages.getString("MGMT_DETAILS_MNGMTLOGINID_NULL_MSG"));
			*/									
			if((managementDetails.getMngmtContactNo().isEmpty() == true) || (managementDetails.getMngmtCreationTime() == null))
				throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTCONTACTNO_NULL_CODE"),
						messages.getString("MGMT_DETAILS_MNGMTCONTACTNO_NULL_MSG"));
			
			if((managementDetails.getMngmtEmailId().isEmpty() == true) || (managementDetails.getMngmtEmailId() == null))
				throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTEMAILID_NULL_CODE"),
						messages.getString("MGMT_DETAILS_MNGMTEMAILID_NULL_MSG"));
			
			/*if((managementDetails.getMngmtCreatedByWadmin().isEmpty() == true) || (managementDetails.getMngmtCreatedByWadmin() == null))
				throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTCREATEDBYWADMIn_NULL_CODE"),
						messages.getString("MGMT_DETAILS_MNGMTCREATEDBYWADMIn_NULL_MSG"));
*/			
			/*if((managementDetails.getMngmtCreationTime().toString().isEmpty() == true) || (managementDetails.getMngmtCreationTime() == null))
				throw exceptionHandler.constructAsmsException(messages.getString("MGMT_DETAILS_MNGMTCREATIONTIME_NULL_CODE"),
						messages.getString("MGMT_DETAILS_MNGMTCREATIONTIME_NULL_MSG"));*/
			
		}
		else
		{
			// invalid role sent
			throw exceptionHandler.constructAsmsException(messages.getString("ROLE_INVALID_CODE"),
					messages.getString("ROLE_INVALID"));
		}
	}
	}


