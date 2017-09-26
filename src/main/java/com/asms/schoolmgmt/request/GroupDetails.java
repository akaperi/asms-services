/**
@{author} gayithri.hegde
25-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.List;

public class GroupDetails {
	/**
	 * @{author} gayithri.hg 25-Sep-2017
	 */

	private String requestType;
	private List<String> classnames;
	private String startTime;
	private String endTime;
	private String periodDuration;


	private List<String> breaks; // in the form of 09:00-09:30

	public List<String> getClassnames() {
		return classnames;
	}

	public void setClassnames(List<String> classnames) {
		this.classnames = classnames;
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

	public List<String> getBreaks() {
		return breaks;
	}

	public void setBreaks(List<String> breaks) {
		this.breaks = breaks;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	public String getPeriodDuration() {
		return periodDuration;
	}

	public void setPeriodDuration(String periodDuration) {
		this.periodDuration = periodDuration;
	}
	

}
