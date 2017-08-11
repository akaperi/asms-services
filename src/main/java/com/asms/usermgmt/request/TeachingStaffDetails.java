/**
@{author} mallikarjun.guranna
08-Aug-2017
*/
package com.asms.usermgmt.request;

import java.util.Date;
import java.util.List;

public class TeachingStaffDetails {
	/**
	@{author} mallikarjun.guranna
	08-Aug-2017
	*/

	private String schoolId;

	private String designation;
	private String firstName;
	private String middleName;
	private String lastName;
	private boolean flagMakeAdmin;
	private Date Dob;
	private String Gender;
	private int ageInYears;
	private long contactNo;
	private String qualification;
	private String emailId;
	private String religion;
	private String casteCategory;
	private String photo;
	private List<?> classesHandled;
	private List<?> subjectsHandled;
	private String maritalStatus;
	private String spouseName;
	private int spouseContactNo;
	private String createdByWadmin;
	private Date creationTime;
	private String acStatus;
	
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	public boolean isFlagMakeAdmin() {
		return flagMakeAdmin;
	}
	public void setFlagMakeAdmin(boolean flagMakeAdmin) {
		this.flagMakeAdmin = flagMakeAdmin;
	}
	public Date getDob() {
		return Dob;
	}
	public void setDob(Date dob) {
		Dob = dob;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public int getAgeInYears() {
		return ageInYears;
	}
	public void setAgeInYears(int ageInYears) {
		this.ageInYears = ageInYears;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCasteCategory() {
		return casteCategory;
	}
	public void setCasteCategory(String casteCategory) {
		this.casteCategory = casteCategory;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<?> getClassesHandled() {
		return classesHandled;
	}
	public void setClassesHandled(List<?> classesHandled) {
		this.classesHandled = classesHandled;
	}
	public List<?> getSubjectsHandled() {
		return subjectsHandled;
	}
	public void setSubjectsHandled(List<?> subjectsHandled) {
		this.subjectsHandled = subjectsHandled;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public int getSpouseContactNo() {
		return spouseContactNo;
	}
	public void setSpouseContactNo(int spouseContactNo) {
		this.spouseContactNo = spouseContactNo;
	}
	public String getCreatedByWadmin() {
		return createdByWadmin;
	}
	public void setCreatedByWadmin(String createdByWadmin) {
		this.createdByWadmin = createdByWadmin;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public String getAcStatus() {
		return acStatus;
	}
	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}
	
}
