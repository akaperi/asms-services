/**
@{author} Gayithri.Hg
25-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class name : ClassGroup This table cis for grouping classes
 */

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Class_Group")
public class ClassGroup {
	/**
	 * @{author} Gayithri.Hg
	 */

	@Id
	@GeneratedValue
	@Column(name = "serial_no")
	private int serialNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoOfPeriods() {
		return noOfPeriods;
	}

	public void setNoOfPeriods(String noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "period_duration")
	private String periodDuration;

	@Column(name = "no_of_periods")
	private String noOfPeriods;
	
	@Transient
	private List<Integer> classIds = new ArrayList<Integer>();  // this is to store class names while creating groups

	@XmlElement
	@OneToMany(mappedBy = "classGroupObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Breaks> breaks = new HashSet<Breaks>();

	

	@XmlElement
	@OneToMany(mappedBy = "classGroupObject", cascade = { javax.persistence.CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<WeaklyHoliday> weaklyHolidays = new HashSet<WeaklyHoliday>();

	@XmlElement
	@OneToOne(mappedBy="classGroupObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private WeaklyHalfDay weaklyHalfDay;
	

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@JsonIgnore
	public Set<Breaks> getBreaks() {
		return breaks;
	}

	public void setBreaks(Set<Breaks> breaks) {
		this.breaks = breaks;
	}


	public String getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	@JsonIgnore
	public Set<WeaklyHoliday> getWeaklyHolidays() {
		return weaklyHolidays;
	}

	public void setWeaklyHolidays(Set<WeaklyHoliday> weaklyHolidays) {
		this.weaklyHolidays = weaklyHolidays;
	}

	@JsonIgnore
	public WeaklyHalfDay getWeaklyHalfDay() {
		return weaklyHalfDay;
	}

	public void setWeaklyHalfDay(WeaklyHalfDay weaklyHalfDay) {
		this.weaklyHalfDay = weaklyHalfDay;
	}

	public List<Integer> getClassIds() {
		return classIds;
	}

	public void setClassIds(List<Integer> classIds) {
		this.classIds = classIds;
	}
	
	
	
	

}
