package com.asms.schoolmgmt.entity;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.adminmgmt.entity.Admin;
import com.asms.usermgmt.entity.Student;
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
@Table(name = "school_details")
public class SchoolDetails {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "trust_id")
	private String trustId;

	@Column(name = "school_name")
	private String schoolName;

	@Column(name = "school_board")
	private String schoolBoard;

	@Column(name = "school_registration_code")
	private String registrationCode;

	@Column(name = "school_id")
	private String schoolId;

	@Column(name = "school_about")
	private String about;

	@Column(name = "school_address")
	private String address;

	@Column(name = "school_pincode")
	private String pinCode;

	
	@Column(name = "school_city")
	private String city;

	@Column(name = "school_state")
	private String state;
	
	
	@Column(name = "school_country")
	private String country;
	
	
	@Column(name = "school_contact_no")
	private String contactNo;

	@Column(name = "school_email_id")
	private String email;
	
	
	@Column(name = "school_fax_no")
	private String fax;
	
	@Column(name = "school_total_no_of_students")
	private int noOfStudents;
	
	
	@Column(name = "school_admin_id")
	private String adminId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolDetailsObject")
	private List<Admin> adminObjectList;

	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "schoolDetailsObject")
	//private List<Student> studentObjectList;
	
	
	















	@Column(name = "school_admin_name")
	private String adminName;
	
	@Column(name = "school_admin_email_id")
	private String adminEmail;
	
	
	@Column(name = "school_gps_latitude")
	private BigDecimal gpsLatitude;
	
	@Column(name = "school_gps_longitude")
	private BigDecimal gpsLongitude;
	
	@Column(name = "school_affiliation_id")
	private int affiliationId;
	
	
	
	
	
	
	

	public int getSerialNo() {
		return serialNo;
	}








	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}








	public String getTrustId() {
		return trustId;
	}








	public void setTrustId(String trustId) {
		this.trustId = trustId;
	}








	public String getSchoolName() {
		return schoolName;
	}








	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}








	public String getSchoolBoard() {
		return schoolBoard;
	}








	public void setSchoolBoard(String schoolBoard) {
		this.schoolBoard = schoolBoard;
	}








	public String getRegistrationCode() {
		return registrationCode;
	}








	public void setRegistrationCode(String registrationCode) {
		this.registrationCode = registrationCode;
	}








	public String getSchoolId() {
		return schoolId;
	}








	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}








	public String getAbout() {
		return about;
	}








	public void setAbout(String about) {
		this.about = about;
	}








	public String getAddress() {
		return address;
	}








	public void setAddress(String address) {
		this.address = address;
	}








	public String getPinCode() {
		return pinCode;
	}








	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}








	public String getCity() {
		return city;
	}








	public void setCity(String city) {
		this.city = city;
	}








	public String getState() {
		return state;
	}








	public void setState(String state) {
		this.state = state;
	}








	public String getCountry() {
		return country;
	}








	public void setCountry(String country) {
		this.country = country;
	}








	public String getContactNo() {
		return contactNo;
	}








	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}








	public String getEmail() {
		return email;
	}








	public void setEmail(String email) {
		this.email = email;
	}








	public String getFax() {
		return fax;
	}








	public void setFax(String fax) {
		this.fax = fax;
	}








	public int getNoOfStudents() {
		return noOfStudents;
	}








	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}








	public String getAdminId() {
		return adminId;
	}








	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}








	public String getAdminName() {
		return adminName;
	}








	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}








	public String getAdminEmail() {
		return adminEmail;
	}








	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}








	public BigDecimal getGpsLatitude() {
		return gpsLatitude;
	}








	public void setGpsLatitude(BigDecimal gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}








	public BigDecimal getGpsLongitude() {
		return gpsLongitude;
	}








	public void setGpsLongitude(BigDecimal gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}








	public int getAffiliationId() {
		return affiliationId;
	}








	public void setAffiliationId(int affiliationId) {
		this.affiliationId = affiliationId;
	}







	@JsonIgnore
	public List<Admin> getAdminObjectList() {
		return adminObjectList;
	}


	public void setAdminObjectList(List<Admin> adminObjectList) {
		this.adminObjectList = adminObjectList;
	}








	@Override
	public String toString() {
		return "";
	}
}
