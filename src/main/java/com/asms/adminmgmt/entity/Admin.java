package com.asms.adminmgmt.entity;
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
@Table(name = "admin_login")
public class Admin {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private long serialNo;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	private SchoolDetails schoolDetailsObject;
	

	



	@Column(name = "admin_id")
	private String adminId;

	@Column(name = "admin_username")
	private String userName;

	@Column(name = "admin_password")
	private String password;

	@Column(name = "admin_new_flag")
	private String isNew;

	@Column(name = "admin_timestamp")
	private Date timestamp;

	@Column(name = "create_check")
	private String createCheck;

	@Column(name = "update_check")
	private String updateCheck;

	
	@Column(name = "retrieve_check")
	private String retrieveCheck;

	@Column(name = "delete_check")
	private String deleteCheck;
	
	
	
	
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




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public String getIsNew() {
		return isNew;
	}




	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}




	public Date getTimestamp() {
		return timestamp;
	}




	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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




	@Override
	public String toString() {
		return "";
	}
}
