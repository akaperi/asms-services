package com.asms.usermgmt.entity.student;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/* Class name : Parent
 * 
 * Parent class is the entity class for parent_details table in DB
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "student_siblings")
public class Sibling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_no")
	private int serialNo;
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="student_id")  
	private Student studentObject;
	
	

	@Column(name = "student_sibling_name")
	private String name;

	@Column(name = "student_sibling_age")
	private int age;
	
	@Column(name = "student_sibling_gender")
	private String gender;
	
	@Column(name = "student_sibling_class")
	private String studentClass;
	
	@Column(name = "student_sibling_current_school")
	private String school;
	
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	
	@JsonIgnore
	public Student getStudentObject() {
		return studentObject;
	}

	public void setStudentObject(Student studentObject) {
		this.studentObject = studentObject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}


	


}
