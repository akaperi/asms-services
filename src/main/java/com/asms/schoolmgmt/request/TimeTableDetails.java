/**
@{author} gayithri.hegde
25-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.List;

import com.asms.schoolmgmt.entity.ClassSubjects;

public class TimeTableDetails {
	/**
	 * @{author} gayithri.hg 25-Sep-2017
	 */

	

	List<TimeTableData> timeTableData = null;

	private List<ClassSubjects> subjectDetails;

	private List<String> teachers;

	

	public List<TimeTableData> getTimeTableData() {
		return timeTableData;
	}

	public void setTimeTableData(List<TimeTableData> timeTableData) {
		this.timeTableData = timeTableData;
	}

	

	public List<ClassSubjects> getSubjectDetails() {
		return subjectDetails;
	}

	public void setSubjectDetails(List<ClassSubjects> subjectDetails) {
		this.subjectDetails = subjectDetails;
	}

	public List<String> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<String> teachers) {
		this.teachers = teachers;
	}



	
	
}
