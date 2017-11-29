package com.asms.multitenancy.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




/*
 * Entity Class: Standard
 * 
 * This class is the Hibernate mapping entity class for Standard table in DB
 * 
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "class")
public class Classes
{
	/**
	 * 
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_no")
	private int serialNo;
	
	@Column(name= "class_name")
	private String name;

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	

}
