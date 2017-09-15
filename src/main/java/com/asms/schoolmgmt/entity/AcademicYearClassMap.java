/**
@{author} mallikarjun.guranna
11-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "AcademicYearClassMap")
public class AcademicYearClassMap {
	/**
	@{author} mallikarjun.guranna
	11-Sep-2017
	*/

//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "AcademicYearClassMap", joinColumns = { @JoinColumn(name = "serial_no") }, inverseJoinColumns = { @JoinColumn(name = "class_id") })
//	@Column(name = "academicYear_Id")
//	private int academicYearId;
	@Id
	@GeneratedValue
	@Column(name = "serialNo")
	private int serialNo;
	
	@Column(name = "academicYear_Id")
	private int academicYearId;
		
	@Column(name = "class_Id")
	private int classId;
}
