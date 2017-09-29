/**
@{author} gayithri.hegde
25-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.List;

import com.asms.schoolmgmt.entity.Breaks;
import com.asms.usermgmt.request.teachingStaff.TeachingStaffDetails;

public class TimeTableData {
	/**
	 * @{author} gayithri.hg 28-Sep-2017
	 */

	private String periodStartTime;
	
	private String periodEndTime;
	
	private boolean isBreak = false;
	
	private List<SubjectDetails> subjectDetails;
	
	private List<TeachingStaffDetails> teacherDetails;

	public String getPeriodStartTime() {
		return periodStartTime;
	}

	public void setPeriodStartTime(String periodStartTime) {
		this.periodStartTime = periodStartTime;
	}

	public String getPeriodEndTime() {
		return periodEndTime;
	}

	public void setPeriodEndTime(String periodEndTime) {
		this.periodEndTime = periodEndTime;
	}

	public boolean isBreak() {
		return isBreak;
	}

	public void setBreak(boolean isBreak) {
		this.isBreak = isBreak;
	}

	public List<SubjectDetails> getSubjectDetails() {
		return subjectDetails;
	}

	public void setSubjectDetails(List<SubjectDetails> subjectDetails) {
		this.subjectDetails = subjectDetails;
	}

	public List<TeachingStaffDetails> getTeacherDetails() {
		return teacherDetails;
	}

	public void setTeacherDetails(List<TeachingStaffDetails> teacherDetails) {
		this.teacherDetails = teacherDetails;
	}
	
	
	
	

	

	
	

}
