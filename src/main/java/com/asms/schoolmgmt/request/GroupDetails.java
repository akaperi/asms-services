/**
@{author} gayithri.hegde
25-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.ArrayList;
import java.util.List;

import com.asms.schoolmgmt.entity.Breaks;

public class GroupDetails {
	/**
	 * @{author} gayithri.hg 25-Sep-2017
	 */

	private String groupName;
	private List<String> classes;
	private String startTime;
	private String endTime;
	private String periodDuration;  // in minutes
	private int noOfPeriods;
	
	

	private WeaklyHolidayDetails weaklyHolidayDetails;
	
	private WeaklyHalfDayDetails weaklyHalfDayDetails;

	private List<Breaks> breaks; 

	

	public int getNoOfPeriods() {
		return noOfPeriods;
	}

	public void setNoOfPeriods(int noOfPeriods) {
		this.noOfPeriods = noOfPeriods;
	}

	public List<String> getClasses() {
		return classes;
	}

	public void setClasses(List<String> classes) {
		this.classes = classes;
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

	

	public List<Breaks> getBreaks() {
		return breaks;
	}

	public void setBreaks(List<Breaks> breaks) {
		this.breaks = breaks;
	}


	public String getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}

	public WeaklyHolidayDetails getWeaklyHolidayDetails() {
		return weaklyHolidayDetails;
	}

	public void setWeaklyHolidayDetails(WeaklyHolidayDetails weaklyHolidayDetails) {
		this.weaklyHolidayDetails = weaklyHolidayDetails;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public WeaklyHalfDayDetails getWeaklyHalfDayDetails() {
		return weaklyHalfDayDetails;
	}

	public void setWeaklyHalfDayDetails(WeaklyHalfDayDetails weaklyHalfDayDetails) {
		this.weaklyHalfDayDetails = weaklyHalfDayDetails;
	}
	
	
	
	

}
