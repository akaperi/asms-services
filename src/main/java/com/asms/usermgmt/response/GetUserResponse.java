package com.asms.usermgmt.response;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.multitenancy.entity.Trust;
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

public class GetUserResponse extends SuccessResponse {

	private UserDetails user;

	private List<User> users;

	private List<StudentType> studentTypes;

	private List<String> boardNames;

	private List<Class> class1;

	private List<UserDetails> userDetails;

	private List<Integer> listOfStateId;
	
	private List<StudentDetails> studentDetails;
	
	private List<QualificationType> qualificationTypes;

	private List<ReligionTypes> religionTypes;
	
	private List<CasteTypes> casteTypes;
	
	private List<Trust> trusts;
	
	private List<BroadCasteSearchTypesDetails> broadCasteSearchTypesDetails;
	
	private List<String> emails;
	
//	private BroadCasteSearchTypesDetails broadCasteSearchTypesDetails2;
	
	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<StudentType> getStudentTypes() {
		return studentTypes;
	}

	public void setStudentTypes(List<StudentType> studentTypes) {
		this.studentTypes = studentTypes;
	}

	public List<String> getBoardNames() {
		return boardNames;
	}

	public void setBoardNames(List<String> boardNames) {
		this.boardNames = boardNames;
	}

	public List<Class> getClass1() {
		return class1;
	}

	public void setClass1(List<Class> class1) {
		this.class1 = class1;
	}

	public List<Integer> getListOfStateId() {
		return listOfStateId;
	}

	public void setListOfStateId(List<Integer> listOfStateId) {
		this.listOfStateId = listOfStateId;
	}

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}

	public List<StudentDetails> getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(List<StudentDetails> studentDetails) {
		this.studentDetails = studentDetails;
	}

	public List<QualificationType> getQualificationTypes() {
		return qualificationTypes;
	}

	public void setQualificationTypes(List<QualificationType> qualificationTypes) {
		this.qualificationTypes = qualificationTypes;
	}

	public List<ReligionTypes> getReligionTypes() {
		return religionTypes;
	}

	public void setReligionTypes(List<ReligionTypes> religionTypes) {
		this.religionTypes = religionTypes;
	}

	public List<CasteTypes> getCasteTypes() {
		return casteTypes;
	}

	public void setCasteTypes(List<CasteTypes> casteTypes) {
		this.casteTypes = casteTypes;
	}

	public List<Trust> getTrusts() {
		return trusts;
	}

	public void setTrusts(List<Trust> trusts) {
		this.trusts = trusts;
	}

	public List<BroadCasteSearchTypesDetails> getBroadCasteSearchTypesDetails() {
		return broadCasteSearchTypesDetails;
	}

	public void setBroadCasteSearchTypesDetails(List<BroadCasteSearchTypesDetails> broadCasteSearchTypesDetails) {
		this.broadCasteSearchTypesDetails = broadCasteSearchTypesDetails;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	
	

}
