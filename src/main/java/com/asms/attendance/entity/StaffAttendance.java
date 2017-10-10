/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Staff_Attendance")
public class StaffAttendance {
	
	
	@Id
	@GeneratedValue
	@Column(name = "serialNo")
	private int serialNo;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "id")
	private String id;
	
	@Column(name = "status")
	private String status;
	
}
