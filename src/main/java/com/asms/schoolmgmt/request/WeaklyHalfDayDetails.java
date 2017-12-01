package com.asms.schoolmgmt.request;

import java.util.List;

import com.asms.schoolmgmt.entity.Breaks;

public class WeaklyHalfDayDetails {

	/**
	 * @{author} Praveen.Tiwari 10-Nov-2017
	 */
	private String day;
	
	private String startTime;
	private String endTime;
	private String periodDuration;  // in minutes
	private int noOfPeriods;
	private List<Breaks> breaks;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
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
	public int getNoOfPeriods() {
		return noOfPeriods;
	}
	public void setNoOfPeriods(int noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
	}
	public List<Breaks> getBreaks() {
		return breaks;
	}
	public void setBreaks(List<Breaks> breaks) {
		this.breaks = breaks;
	} 
	
	
	
	
	

}
