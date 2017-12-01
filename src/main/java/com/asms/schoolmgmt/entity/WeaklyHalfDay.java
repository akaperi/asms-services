package com.asms.schoolmgmt.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.asms.usermgmt.entity.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@XmlRootElement
@Entity
@Table(name = "Weakly_Halfday")
public class WeaklyHalfDay {

	/**
	 * @{author} Praveen.Tiwari 10-Nov-2017
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "serial_no")
	private int serialNo;

	@Column(name = "day_of_holiday")
	private String dayofholiday;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "period_duration")
	private String periodDuration;

	@Column(name = "no_of_periods")
	private String noOfPeriods;

	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="group_id")  
	private ClassGroup classGroupObject;
	
	
	
	public int getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public String getDayofholiday() {
		return this.dayofholiday;
	}

	public void setDayofholiday(String dayofholiday) {
		this.dayofholiday = dayofholiday;
	}

	@JsonIgnore
	public ClassGroup getClassGroupObject() {
		return this.classGroupObject;
	}

	public void setClassGroupObject(ClassGroup classGroupObject) {
		this.classGroupObject = classGroupObject;
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

	public String getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	public String getNoOfPeriods() {
		return noOfPeriods;
	}

	public void setNoOfPeriods(String noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
	}
	
	
	
	
}
