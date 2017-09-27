package com.asms.rolemgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.multitenancy.entity.Trust;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.request.BroadCasteSearchTypesDetails;
import com.asms.usermgmt.entity.StudentType;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.service.CasteTypes;
import com.asms.usermgmt.service.QualificationType;
import com.asms.usermgmt.service.ReligionTypes;

/*
 * RegistrationResponse.java is to return user registration response
 */

public class GetRoleResponse extends SuccessResponse {
	
	private List<Role> roles;
	
	private List<SubRole> subRoles;

	public List<SubRole> getSubRoles() {
		return subRoles;
	}

	public void setSubRoles(List<SubRole> subRoles) {
		this.subRoles = subRoles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	


	

	

}
