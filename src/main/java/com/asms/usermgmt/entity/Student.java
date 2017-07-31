package com.asms.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/* Class name: student_details 
 * class is the entity class for student_details table
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "student_details")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "school_")
	private String userId;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String userPassword;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roleObject;

	
	@ManyToOne
	@JoinColumn(name = "sub_role_id")
	private SubRole subRoleObject;
	
	//@OneToOne(cascade = CascadeType.ALL, mappedBy = "bookingObject")
	//private RejectedBooking rejectedBookingObject;
	

	
	
	
	public String getUserId() {
		return userId;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getRoleObject() {
		return roleObject;
	}

	public void setRoleObject(Role roleObject) {
		this.roleObject = roleObject;
	}

	public SubRole getSubRoleObject() {
		return subRoleObject;
	}

	public void setSubRoleObject(SubRole subRoleObject) {
		this.subRoleObject = subRoleObject;
	}
	
	

	
}
