package com.asms.schoolmgmt.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "class_subjects")
public class ClassSubjects 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial_no")
	private int serialNo;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name ="class_id")
	private Class classObject;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@JsonIgnore
	public Class getClassObject() {
		return classObject;
	}

	public void setClassObject(Class classObject) {
		this.classObject = classObject;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}



	

}
