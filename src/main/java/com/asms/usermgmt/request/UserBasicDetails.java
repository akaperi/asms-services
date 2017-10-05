package com.asms.usermgmt.request;
import java.util.Set;

import com.asms.common.helper.Constants.privileges;
import com.asms.usermgmt.entity.Privilege;
import com.asms.usermgmt.entity.nonTeachingStaff.Address;
import com.asms.usermgmt.entity.student.StudentAddress;
import com.asms.usermgmt.entity.student.StudentPreviousInfo;
import com.asms.usermgmt.request.management.ManagementDetails;
import com.asms.usermgmt.request.nonTeachingStaff.AddressDetails;
import com.asms.usermgmt.request.nonTeachingStaff.NonTeachingStaffDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffDocumentsDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffPreviousInformationDetails;
import com.asms.usermgmt.request.nonTeachingStaff.StaffStatutoryDetails;
import com.asms.usermgmt.request.student.ParentDetails;
import com.asms.usermgmt.request.student.SiblingDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.request.student.StudentDocumentDetails;
import com.asms.usermgmt.request.teachingStaff.StaffDocumentsDetails1;
import com.asms.usermgmt.request.teachingStaff.StaffPreviousInformationDetails1;
import com.asms.usermgmt.request.teachingStaff.StaffStatutoryDetails1;
import com.asms.usermgmt.request.teachingStaff.TeachingStaffDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

/* UserDetails class represents the user create request object
 * for user management operations 
 * 
 */

public class UserBasicDetails {

	private String userId;	
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	


	private String email;	
	
	private String role;	
	
	private String subRole;
	
	
	
	


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSubRole() {
		return subRole;
	}

	public void setSubRole(String subRole) {
		this.subRole = subRole;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

}
