/**
@{author} gayithri.hegde
25-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.ArrayList;
import java.util.List;

import com.asms.schoolmgmt.entity.Breaks;

public class TimeTableDetails {
	/**
	 * @{author} gayithri.hg 25-Sep-2017
	 */

	private String academicYear;
	private String className;
	private String sectionName;
	private String startTime;
	private String endTime;
	private String periodDuration;  // in minutes


	List<TimeTableData> timeTableData = null;


	public String getAcademicYear() {
		return academicYear;
	}


	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getSectionName() {
		return sectionName;
	}


	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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


	public List<TimeTableData> getTimeTableData() {
		return timeTableData;
	}


	public void setTimeTableData(List<TimeTableData> timeTableData) {
		this.timeTableData = timeTableData;
	}
	
	

	

	
	

}
