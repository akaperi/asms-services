/**
@{author} mallikarjun.guranna
11-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class name : AcademicYear
 * This table created dynamically when school setup creates from school admin
 * And this class is hibernate mapping class to _AcademicYear table in DB. 
 */

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement
@Entity
@Table(name = "AcademicYearClass")
public class AcademicYear {
	/**
	@{author} mallikarjun.guranna
	11-Sep-2017
	*/
	
	@Id
	@GeneratedValue
	@Column(name = "serial_no")
	private int serialNo;

	@Column(name = "academicYear_id")
	private int academicYearId;

	@Column(name = "academicYear_FromTo")
	private String  academicYearFromTo;

	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "AcademicYearClassMap", joinColumns = { @JoinColumn(name = "serialNo") }, inverseJoinColumns = { @JoinColumn(name = "classId") })
	private List<Class> classes = new ArrayList<Class>();
	
	public int getAcademicYearId() {
		return academicYearId;
	}
	public void setAcademicYearId(int academicYearId) {
		this.academicYearId = academicYearId;
	}
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	
	@JsonIgnore
	public List<Class> getClasses() {
		return classes;
	}
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
	public String getAcademicYearFromTo() {
		return academicYearFromTo;
	}
	public void setAcademicYearFromTo(String academicYearFromTo) {
		this.academicYearFromTo = academicYearFromTo;
	}
	
	
	
	
	
	
}
