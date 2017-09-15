package com.asms.usermgmt.request;
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

/* UserDetails class represents the user create request object
 * for user management operations 
 * 
 */

public class UserDetails {

	private String userId;	
	
	private String email;	
	
	private String userPassword;	
	
	private String role;	
	
	private String subRole;
	
	private StudentDetails studentDetails;
	
	private ParentDetails parentDetails;
	
	private SiblingDetails siblingDetails;
	
	private StudentDocumentDetails studentDocumentDetails;
	
	private StudentPreviousInfo studentPreviousInfo;
	
	private StudentAddress studentAddress;
	
	private Address address; 
	
	private ManagementDetails managementDetails;
	
	private TeachingStaffDetails teachingStaffDetails;
	
	private NonTeachingStaffDetails nonTeachingStaffDetails;
	
	private AddressDetails addressDetails; 
	
	private StaffDocumentsDetails staffDocumentsDetails;
	
	private StaffPreviousInformationDetails staffPreviousInformationDetails;
	
	private StaffStatutoryDetails staffStatutoryDetails; 
	

	private StaffDocumentsDetails1 staffDocumentsDetails1;
	
	private StaffPreviousInformationDetails1 staffPreviousInformationDetails1;
	
	private StaffStatutoryDetails1 statutoryDetails1;
	
	private ChangePasswordDetails changePasswordDetails;
	
	
	
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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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


	public StudentDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDetails studentDetails) {
		this.studentDetails = studentDetails;
	}

	public ManagementDetails getManagementDetails() {
		return managementDetails;
	}

	public void setManagementDetails(ManagementDetails managementDetails) {
		this.managementDetails = managementDetails;
	}

	public TeachingStaffDetails getTeachingStaffDetails() {
		return teachingStaffDetails;
	}

	public void setTeachingStaffDetails(TeachingStaffDetails teachingStaffDetails) {
		this.teachingStaffDetails = teachingStaffDetails;
	}

	public NonTeachingStaffDetails getNonTeachingStaffDetails() {
		return nonTeachingStaffDetails;
	}

	public void setNonTeachingStaffDetails(NonTeachingStaffDetails nonTeachingStaffDetails) {
		this.nonTeachingStaffDetails = nonTeachingStaffDetails;
	}

	public AddressDetails getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(AddressDetails addressDetails) {
		this.addressDetails = addressDetails;
	}

	public StaffDocumentsDetails getStaffDocumentsDetails() {
		return staffDocumentsDetails;
	}

	public void setStaffDocumentsDetails(StaffDocumentsDetails staffDocumentsDetails) {
		this.staffDocumentsDetails = staffDocumentsDetails;
	}

	public StaffPreviousInformationDetails getStaffPreviousInformationDetails() {
		return staffPreviousInformationDetails;
	}

	public void setStaffPreviousInformationDetails(StaffPreviousInformationDetails staffPreviousInformationDetails) {
		this.staffPreviousInformationDetails = staffPreviousInformationDetails;
	}

	public StaffStatutoryDetails getStaffStatutoryDetails() {
		return staffStatutoryDetails;
	}

	public void setStaffStatutoryDetails(StaffStatutoryDetails staffStatutoryDetails) {
		this.staffStatutoryDetails = staffStatutoryDetails;
	}

	public StaffDocumentsDetails1 getStaffDocumentsDetails1() {
		return staffDocumentsDetails1;
	}

	public void setStaffDocumentsDetails1(StaffDocumentsDetails1 staffDocumentsDetails1) {
		this.staffDocumentsDetails1 = staffDocumentsDetails1;
	}

	public StaffPreviousInformationDetails1 getStaffPreviousInformationDetails1() {
		return staffPreviousInformationDetails1;
	}

	public void setStaffPreviousInformationDetails1(StaffPreviousInformationDetails1 staffPreviousInformationDetails1) {
		this.staffPreviousInformationDetails1 = staffPreviousInformationDetails1;
	}

	public StaffStatutoryDetails1 getStatutoryDetails1() {
		return statutoryDetails1;
	}

	public void setStatutoryDetails1(StaffStatutoryDetails1 statutoryDetails1) {
		this.statutoryDetails1 = statutoryDetails1;
	}


	public ParentDetails getParentDetails() {
		return parentDetails;
	}

	public void setParentDetails(ParentDetails parentDetails) {
		this.parentDetails = parentDetails;
	}

	public SiblingDetails getSiblingDetails() {
		return siblingDetails;
	}

	public void setSiblingDetails(SiblingDetails siblingDetails) {
		this.siblingDetails = siblingDetails;
	}

	public StudentDocumentDetails getStudentDocumentDetails() {
		return studentDocumentDetails;
	}

	public void setStudentDocumentDetails(StudentDocumentDetails studentDocumentDetails) {
		this.studentDocumentDetails = studentDocumentDetails;
	}

	public StudentPreviousInfo getStudentPreviousInfo() {
		return studentPreviousInfo;
	}

	public void setStudentPreviousInfo(StudentPreviousInfo studentPreviousInfo) {
		this.studentPreviousInfo = studentPreviousInfo;
	}

	public StudentAddress getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(StudentAddress studentAddress) {
		this.studentAddress = studentAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ChangePasswordDetails getChangePasswordDetails() {
		return changePasswordDetails;
	}

	public void setChangePasswordDetails(ChangePasswordDetails changePasswordDetails) {
		this.changePasswordDetails = changePasswordDetails;
	}
	
}
