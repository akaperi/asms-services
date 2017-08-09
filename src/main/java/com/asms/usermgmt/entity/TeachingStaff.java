package com.asms.usermgmt.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : TeachingStaff
 * This class is the Hibernate mapping Entity/model class to map teaching_staff table in DB
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "teaching_staff")
@PrimaryKeyJoinColumn(name = "siNo", referencedColumnName = "serial_no")
public class TeachingStaff extends User{
		
	@Column(name = "t_staff_id")
	private String Id;
	@Column(name = "school_id")
	private String schoolId;
	@Column(name = "t_staff_role_name")
	private String roleName;
	@Column(name = "t_staff_designation")
	private String designation;
	@Column(name = "t_staff_first_name")
	private String firstName;
	@Column(name = "t_staff_middle_name")
	private String middleName;
	@Column(name = "t_staff_last_name")
	private String lastName;
	@Column(name = "t_staff_flag_make_admin")
	private boolean flagMakeAdmin;
	@Column(name = "t_staff_dob")
	private Date Dob;
	@Column(name = "t_staff_gender")
	private String Gender;
	@Column(name = "t_staff_age_in_years")
	private int ageInYears;
	@Column(name = "t_staff_contact_no")
	private long contactNo;
	@Column(name = "t_staff_qualification")
	private String qualification;
	@Column(name = "t_staff_email_id")
	private String emailId;
	@Column(name = "t_staff_religion")
	private String religion;
	@Column(name = "t_staff_caste_category")
	private String casteCategory;
	@Column(name = "t_staff_photo")
	private String photo;
	@Column(name = "t_staff_classes_handled")
	private List<?> classesHandled;
	@Column(name = "t_staff_subjects_handled")
	private List<?> subjectsHandled;
	@Column(name = "t_staff_marital_status")
	private String maritalStatus;
	@Column(name = "t_staff_spouse_name")
	private String spouseName;
	@Column(name = "t_staff_spouse_contact_no")
	private int spouseContactNo;
	@Column(name = "t_staff_created_by_wadmin")
	private String createdByWadmin;
	@Column(name = "t_staff_creation_time")
	private Date creationTime;
	@Column(name = "t_staff_status")
	private String acStatus;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
