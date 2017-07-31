package com.asms.usermgmt.student.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.schoolmgmt.entity.SchoolDetails;
import com.asms.usermgmt.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/*Entity class for admin_login table.
 * 
 * this class is the hibernate mapping class for admin_login table
 * 
 * 
 */

 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "student_details")
public class Student{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private long serialNo;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private SchoolDetails schoolDetailsObject;


	@Column(name = "student_id")
	private String studentId;

	@Column(name = "student_password")
	private String password;

	@Column(name = "admission_no")
	private String admissionNo;

	@Column(name = "student_type")
	private int type;

	
	@Column(name = "student_class")
	private String studentClass;

	@Column(name = "student_section")
	private String section;
	
	@Column(name = "admission_date")
	private Date admissionDate;	
	
	@Column(name = "student_first_name")
	private String firstName;

	@Column(name = "student_middle_name")
	private String middleName;
	
	@Column(name = "student_last_name")
	private String lastName;
	
	@Column(name = "student_dob")
	private Date dateOfBirth;
	
	@Column(name = "student_gender")
	private String gender;	
	
	@Column(name = "student_identification_marks")
	private String identificationMarks;
	
	@Column(name = "student_aadhaar_no")
	private String adhaarNo;
	
	@Column(name = "student_sibling_name")
	private String siblingName;
	
	@Column(name = "student_sibling_age")
	private int siblingAge;	
	
	@Column(name = "student_sibling_gender")
	private String siblingGender;
	
	@Column(name = "student_sibling_class")
	private String siblingClass;
	
	@Column(name = "student_sibling_current_school")
	private String siblingCurrentSchool;
	
	
	@Column(name = "student_age_in_years")
	private int age;	
	
	@Column(name = "student_birthplace")
	private String birthPlace;
	
	@Column(name = "student_nationality")
	private String nationality;
	
	@Column(name = "student_religion")
	private String religion;
	
	@Column(name = "student_caste_category")
	private String casteCategory;	
	
	@Column(name = "student_sub_caste")
	private String subCaste;
	
	@Column(name = "student_mother_tongue")
	private String motherTongue;
	
	@Column(name = "student_previous_school_name")
	private String previSchoolName;
	
	@Column(name = "student_previous_studied_from_to")
	private String previousSchoolDuration;	
	
	@Column(name = "student_previous_class")
	private String previousClass;
	
	@Column(name = "student_previous_obtained_marks")
	private int obtainedMarks;
	
	@Column(name = "student_previous_total_marks")
	private int totalMarks;
	
	
	@Column(name = "student_permanent_address")
	private String permanentAddress;	
	
	@Column(name = "student_paddress_pincode")
	private int pPinCode;
	
	@Column(name = "student_paddress_city")
	private String pCity;
	
	@Column(name = "student_paddress_state")
	private String pState;
	
	
	@Column(name = "student_paddress_country")
	private String pCountry;	
	
	@Column(name = "student_caddress")
	private String cAddress;
	
	@Column(name = "student_caddress_pincode")
	private int cPinCode;
	
	@Column(name = "student_caddress_city")
	private String cCity;
	
	@Column(name = "student_caddress_state")
	private String cState;
	
	@Column(name = "student_caddress_country")
	private String cCountry;
	
	@Column(name = "student_family_income")
	private int familyIncome;
	
	
	@Column(name = "student_created_by_wadmin")
	private String adminId;
	
	@Column(name = "student_registered_on")
	private Date registeredOn;
	
	@Column(name = "student_photo")
	private int imagePath;
	
	
	@Column(name = "student_previous_bonafied_certificate")
	private String previousBonafiedCertificate;
	
	@Column(name = "student_aadhaar_card")
	private Date adhaarCardImage;
	
	@Column(name = "student_other_certificate")
	private int otherCertificate;
	
	
	@Column(name = "create_check")
	private String createCheck;
	
	@Column(name = "update_check")
	private String updateCheck;
	
	@Column(name = "retrieve_check")
	private String retrieveCheck;
	
	
	@Column(name = "delete_check")
	private String deleteCheck;
	
	
	
	
	

	


	public String getStudentId() {
		return studentId;
	}




	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getAdmissionNo() {
		return admissionNo;
	}




	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}




	public int getType() {
		return type;
	}




	public void setType(int type) {
		this.type = type;
	}




	public String getStudentClass() {
		return studentClass;
	}




	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}




	public String getSection() {
		return section;
	}




	public void setSection(String section) {
		this.section = section;
	}




	public Date getAdmissionDate() {
		return admissionDate;
	}




	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
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




	public Date getDateOfBirth() {
		return dateOfBirth;
	}




	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getIdentificationMarks() {
		return identificationMarks;
	}




	public void setIdentificationMarks(String identificationMarks) {
		this.identificationMarks = identificationMarks;
	}




	public String getAdhaarNo() {
		return adhaarNo;
	}




	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}




	public String getSiblingName() {
		return siblingName;
	}




	public void setSiblingName(String siblingName) {
		this.siblingName = siblingName;
	}




	public int getSiblingAge() {
		return siblingAge;
	}




	public void setSiblingAge(int siblingAge) {
		this.siblingAge = siblingAge;
	}




	public String getSiblingGender() {
		return siblingGender;
	}




	public void setSiblingGender(String siblingGender) {
		this.siblingGender = siblingGender;
	}




	public String getSiblingClass() {
		return siblingClass;
	}




	public void setSiblingClass(String siblingClass) {
		this.siblingClass = siblingClass;
	}




	public String getSiblingCurrentSchool() {
		return siblingCurrentSchool;
	}




	public void setSiblingCurrentSchool(String siblingCurrentSchool) {
		this.siblingCurrentSchool = siblingCurrentSchool;
	}




	public int getAge() {
		return age;
	}




	public void setAge(int age) {
		this.age = age;
	}




	public String getBirthPlace() {
		return birthPlace;
	}




	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}




	public String getNationality() {
		return nationality;
	}




	public void setNationality(String nationality) {
		this.nationality = nationality;
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




	public String getSubCaste() {
		return subCaste;
	}




	public void setSubCaste(String subCaste) {
		this.subCaste = subCaste;
	}




	public String getMotherTongue() {
		return motherTongue;
	}




	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}




	public String getPreviSchoolName() {
		return previSchoolName;
	}




	public void setPreviSchoolName(String previSchoolName) {
		this.previSchoolName = previSchoolName;
	}




	public String getPreviousSchoolDuration() {
		return previousSchoolDuration;
	}




	public void setPreviousSchoolDuration(String previousSchoolDuration) {
		this.previousSchoolDuration = previousSchoolDuration;
	}




	public String getPreviousClass() {
		return previousClass;
	}




	public void setPreviousClass(String previousClass) {
		this.previousClass = previousClass;
	}




	public int getObtainedMarks() {
		return obtainedMarks;
	}




	public void setObtainedMarks(int obtainedMarks) {
		this.obtainedMarks = obtainedMarks;
	}




	public int getTotalMarks() {
		return totalMarks;
	}




	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}




	public String getPermanentAddress() {
		return permanentAddress;
	}




	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}




	public int getpPinCode() {
		return pPinCode;
	}




	public void setpPinCode(int pPinCode) {
		this.pPinCode = pPinCode;
	}




	public String getpCity() {
		return pCity;
	}




	public void setpCity(String pCity) {
		this.pCity = pCity;
	}




	public String getpState() {
		return pState;
	}




	public void setpState(String pState) {
		this.pState = pState;
	}




	public String getpCountry() {
		return pCountry;
	}




	public void setpCountry(String pCountry) {
		this.pCountry = pCountry;
	}




	public String getcAddress() {
		return cAddress;
	}




	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}




	public int getcPinCode() {
		return cPinCode;
	}




	public void setcPinCode(int cPinCode) {
		this.cPinCode = cPinCode;
	}




	public String getcCity() {
		return cCity;
	}




	public void setcCity(String cCity) {
		this.cCity = cCity;
	}




	public String getcState() {
		return cState;
	}




	public void setcState(String cState) {
		this.cState = cState;
	}




	public String getcCountry() {
		return cCountry;
	}




	public void setcCountry(String cCountry) {
		this.cCountry = cCountry;
	}




	public int getFamilyIncome() {
		return familyIncome;
	}




	public void setFamilyIncome(int familyIncome) {
		this.familyIncome = familyIncome;
	}




	public Date getRegisteredOn() {
		return registeredOn;
	}




	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}




	public int getImagePath() {
		return imagePath;
	}




	public void setImagePath(int imagePath) {
		this.imagePath = imagePath;
	}




	public String getPreviousBonafiedCertificate() {
		return previousBonafiedCertificate;
	}




	public void setPreviousBonafiedCertificate(String previousBonafiedCertificate) {
		this.previousBonafiedCertificate = previousBonafiedCertificate;
	}




	public Date getAdhaarCardImage() {
		return adhaarCardImage;
	}




	public void setAdhaarCardImage(Date adhaarCardImage) {
		this.adhaarCardImage = adhaarCardImage;
	}




	public int getOtherCertificate() {
		return otherCertificate;
	}




	public void setOtherCertificate(int otherCertificate) {
		this.otherCertificate = otherCertificate;
	}




	public String getCreateCheck() {
		return createCheck;
	}




	public void setCreateCheck(String createCheck) {
		this.createCheck = createCheck;
	}




	public String getUpdateCheck() {
		return updateCheck;
	}




	public void setUpdateCheck(String updateCheck) {
		this.updateCheck = updateCheck;
	}




	




	public String getRetrieveCheck() {
		return retrieveCheck;
	}




	public void setRetrieveCheck(String retrieveCheck) {
		this.retrieveCheck = retrieveCheck;
	}




	public String getDeleteCheck() {
		return deleteCheck;
	}




	public void setDeleteCheck(String deleteCheck) {
		this.deleteCheck = deleteCheck;
	}





	
	
	
	
	public long getSerialNo() {
		return serialNo;
	}




	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}



	@JsonIgnore
	public SchoolDetails getSchoolDetailsObject() {
		return schoolDetailsObject;
	}




	public void setSchoolDetailsObject(SchoolDetails schoolDetailsObject) {
		this.schoolDetailsObject = schoolDetailsObject;
	}




	public String getAdminId() {
		return adminId;
	}




	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}





	@Override
	public String toString() {
		return "";
	}
}
