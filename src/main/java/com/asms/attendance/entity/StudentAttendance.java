/**
mallikarjun.guranna
Oct 5, 2017
*/
package com.asms.attendance.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.schoolmgmt.entity.Section;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Student_Attendance")
public class StudentAttendance {
	

	@Id
	@GeneratedValue
	@Column(name = "serialNo")
	private int serialNo;
	
	@Column(name = "date")
	private Date date;
	
	/*@ManyToOne
	@JoinColumn(name = "class_id")
	private Class classObject;*/
	
	@ManyToOne
	@JoinColumn(name = "serial_no")
	private Section sectionObject;
	
	@Column(name = "studentName")
	private String stdName;
	
	@Column(name = "studentId")
	private String stdId;
	
	@Column(name = "status")
	private String status;

/*	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}*/

/*	public String getStudentSection() {
		return studentSection;
	}

	public void setStudentSection(String studentSection) {
		this.studentSection = studentSection;
	}*/

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public String getStdId() {
		return stdId;
	}

	public void setStdId(String stdId) {
		this.stdId = stdId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Section getSectionObject() {
		return sectionObject;
	}

	public void setSectionObject(Section sectionObject) {
		this.sectionObject = sectionObject;
	}

	

}
