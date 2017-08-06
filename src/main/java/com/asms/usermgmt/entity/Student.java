package com.asms.usermgmt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name : Student
 * 
 * Student class is the entity class for student_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "student_details")
@PrimaryKeyJoinColumn(name = "student_id", referencedColumnName = "serial_no")
public class Student extends User{

	

	@Column(name = "school_id")
	private String schoolId;
	
	
	@Column(name = "admission_no")
	private String admissionNo;

	@Column(name = "student_type")
	private String studentType;

	@Column(name = "admission_date")
	private Date admissionDate;

	@Column(name = "student_class")
	private String studentClass;

	@Column(name = "student_section")
	private String studentSection;

	@Column(name = "student_first_name")
	private String studentFirstName;

	@Column(name = "student_middle_name")
	private String studentMiddleName;

	@Column(name = "student_last_name")
	private String studentLastName;

	@Column(name = "student_dob")
	private Date studentDob;

	@Column(name = "student_gender")
	private String studentGender;

	@Column(name = "student_identification_marks")
	private String studentIdentificationMarks;

	@Column(name = "student_age_in_years")
	private int studentAgeInYears;

	@Column(name = "student_birthplace")
	private String studentBirthplace;

	@Column(name = "student_nationality")
	private String studentNationality;

	@Column(name = "student_religion")
	private String studentReligion;

	@Column(name = "student_caste_category")
	private String studentCasteCategory;

	@Column(name = "student_sub_caste")
	private String studentSubCaste;

	@Column(name = "student_mother_tongue")
	private String studentMotherTongue;

	@Column(name = "student_created_by_wadmin")
	private String studentCreatedByWadmin;

	@Column(name = "student_registered_on")
	private Date studentRegisteredOn;

	@Column(name = "student_photo")
	private String studentPhoto;
	
	@Column(name = "student_status")
	private String status;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	

	public String getAdmissionNo() {
		return admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentSection() {
		return studentSection;
	}

	public void setStudentSection(String studentSection) {
		this.studentSection = studentSection;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public Date getStudentDob() {
		return studentDob;
	}

	public void setStudentDob(Date studentDob) {
		this.studentDob = studentDob;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public String getStudentIdentificationMarks() {
		return studentIdentificationMarks;
	}

	public void setStudentIdentificationMarks(String studentIdentificationMarks) {
		this.studentIdentificationMarks = studentIdentificationMarks;
	}

	public int getStudentAgeInYears() {
		return studentAgeInYears;
	}

	public void setStudentAgeInYears(int studentAgeInYears) {
		this.studentAgeInYears = studentAgeInYears;
	}

	public String getStudentBirthplace() {
		return studentBirthplace;
	}

	public void setStudentBirthplace(String studentBirthplace) {
		this.studentBirthplace = studentBirthplace;
	}

	public String getStudentNationality() {
		return studentNationality;
	}

	public void setStudentNationality(String studentNationality) {
		this.studentNationality = studentNationality;
	}

	public String getStudentReligion() {
		return studentReligion;
	}

	public void setStudentReligion(String studentReligion) {
		this.studentReligion = studentReligion;
	}

	public String getStudentCasteCategory() {
		return studentCasteCategory;
	}

	public void setStudentCasteCategory(String studentCasteCategory) {
		this.studentCasteCategory = studentCasteCategory;
	}

	public String getStudentSubCaste() {
		return studentSubCaste;
	}

	public void setStudentSubCaste(String studentSubCaste) {
		this.studentSubCaste = studentSubCaste;
	}

	public String getStudentMotherTongue() {
		return studentMotherTongue;
	}

	public void setStudentMotherTongue(String studentMotherTongue) {
		this.studentMotherTongue = studentMotherTongue;
	}

	public String getStudentCreatedByWadmin() {
		return studentCreatedByWadmin;
	}

	public void setStudentCreatedByWadmin(String studentCreatedByWadmin) {
		this.studentCreatedByWadmin = studentCreatedByWadmin;
	}

	public Date getStudentRegisteredOn() {
		return studentRegisteredOn;
	}

	public void setStudentRegisteredOn(Date studentRegisteredOn) {
		this.studentRegisteredOn = studentRegisteredOn;
	}

	public String getStudentPhoto() {
		return studentPhoto;
	}

	public void setStudentPhoto(String studentPhoto) {
		this.studentPhoto = studentPhoto;
	}



	
	


}
