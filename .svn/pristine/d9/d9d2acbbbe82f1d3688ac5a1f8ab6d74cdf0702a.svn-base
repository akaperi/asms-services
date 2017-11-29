//NewStudentAdmission
package com.asms.studentAdmission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : AdmissionEnquiry
 * This class is Hibernate mapping Entity to Admission_Enquiry table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "new_student_admission")
public class NewStudentAdmission {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "student_name")
	private String studentName;

	@Column(name = "admission_applied_date")
	private String admissionAppliedDate;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "class")
	private String className;
	
	@Column(name = "section")
	private String section;
	
	@Column(name = "Phone_Number")
	private long phoneNumber;
	
	@Column(name = "admission_year")
	private String admissionYear;
	
	@Column(name = "Previous_School_Name")
	private String previousSchoolName;
	
	@Column(name = "Previous_School_Board")
	private String previousSchoolBoard;
	
	@Column(name = "DOB")
	private String dob;
	
	@Column(name = "status")
	private String status;
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAdmissionAppliedDate() {
		return admissionAppliedDate;
	}

	public void setAdmissionAppliedDate(String admissionAppliedDate) {
		this.admissionAppliedDate = admissionAppliedDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

	public String getPreviousSchoolName() {
		return previousSchoolName;
	}

	public void setPreviousSchoolName(String previousSchoolName) {
		this.previousSchoolName = previousSchoolName;
	}

	public String getPreviousSchoolBoard() {
		return previousSchoolBoard;
	}

	public void setPreviousSchoolBoard(String previousSchoolBoard) {
		this.previousSchoolBoard = previousSchoolBoard;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	


	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}

	
	
	
}
