package com.asms.usermgmt.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Class name : Management
 * This class is Hibernate mapping Entity to management table in DB
 */
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "management")
@PrimaryKeyJoinColumn(name = "siNo", referencedColumnName = "serial_no")
public class Management extends User{

	@Column(name = "school_id")
	private String schoolId;
	
	@Column(name = "trust_id")	
	private String trustId;
	
	@Column(name = "mngmt_role")
	private String mngmtRole;
	
	@Column(name = "mngmt_first_name")
	private String mngmtFirstName;
	
	@Column(name = "mngmt_middle_name")
	private String mngmtMiddleName;
	
	@Column(name = "mngmt_last_name")
	private String mngmtLastName;
	
	@Column(name = "mngmt_designation")
	private String mngmtDesignation;
	
	@Column(name = "mngmt_contact_no")
	private String mngmtContactNo;
	
	@Column(name = "mngmt_email_id")
	private String mngmtEmailId;
	
	@Column(name = "mngmt_created_by_wadmin")
	private String mngmtCreatedByWadmin;
	
	@Column(name = "mngmt_creation_time")
	private Date mngmtCreationTime;

	@Column(name ="mngmt_status")
	private String acStatus;

	
	
	
	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getTrustId() {
		return trustId;
	}

	public void setTrustId(String trustId) {
		this.trustId = trustId;
	}

	public String getMngmtRole() {
		return mngmtRole;
	}

	public void setMngmtRole(String mngmtRole) {
		this.mngmtRole = mngmtRole;
	}

	public String getMngmtFirstName() {
		return mngmtFirstName;
	}

	public void setMngmtFirstName(String mngmtFirstName) {
		this.mngmtFirstName = mngmtFirstName;
	}

	public String getMngmtMiddleName() {
		return mngmtMiddleName;
	}

	public void setMngmtMiddleName(String mngmtMiddleName) {
		this.mngmtMiddleName = mngmtMiddleName;
	}

	public String getMngmtLastName() {
		return mngmtLastName;
	}

	public void setMngmtLastName(String mngmtLastName) {
		this.mngmtLastName = mngmtLastName;
	}

	public String getMngmtDesignation() {
		return mngmtDesignation;
	}

	public void setMngmtDesignation(String mngmtDesignation) {
		this.mngmtDesignation = mngmtDesignation;
	}

	/*public User getUserObject() {
		return userObject;
	}

	public void setUserObject(User userObject) {
		this.userObject = userObject;
	}
*/
	public String getMngmtContactNo() {
		return mngmtContactNo;
	}

	public void setMngmtContactNo(String mngmtContactNo) {
		this.mngmtContactNo = mngmtContactNo;
	}

	public String getMngmtEmailId() {
		return mngmtEmailId;
	}

	public void setMngmtEmailId(String mngmtEmailId) {
		this.mngmtEmailId = mngmtEmailId;
	}

	public String getMngmtCreatedByWadmin() {
		return mngmtCreatedByWadmin;
	}

	public void setMngmtCreatedByWadmin(String string) {
		this.mngmtCreatedByWadmin = string;
	}

	public Date getMngmtCreationTime() {
		return mngmtCreationTime;
	}

	public void setMngmtCreationTime(Date mngmtCreationTime) {
		this.mngmtCreationTime = mngmtCreationTime;
	}

	public String getAcStatus() {
		return acStatus;
	}

	public void setAcStatus(String acStatus) {
		this.acStatus = acStatus;
	}


	
	

	

}
