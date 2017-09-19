/**
@{author} mallikarjun.guranna
12-Sep-2017
*/
package com.asms.schoolmgmt.entity;

import java.util.List;

import com.asms.schoolmgmt.request.AdditionalSubjectsDetails;
import com.asms.schoolmgmt.request.ClassDetails;
import com.asms.schoolmgmt.request.SectionDetails;
import com.asms.schoolmgmt.request.SubjectDetails;

/**
 * add comment SetupSchoolDetails
 */
public class SetupSchoolDetails {
	/**
	@{author} mallikarjun.guranna
	12-Sep-2017
	*/
	
	private String currentAcademicYear;
	
	private List<ClassDetails> classDetails;
	private List<SectionDetails> sectionDetails;
	
	private List<SubjectDetails> subjectDetails;
	
	private List<AdditionalSubjectsDetails> additionalSubjectsDetails;
	
	public String getCurrentAcademicYear() {
		return currentAcademicYear;
	}

	public void setCurrentAcademicYear(String currentAcademicYear) {
		this.currentAcademicYear = currentAcademicYear;
	}

	public List<ClassDetails> getClassDetails() {
		return classDetails;
	}

	public void setClassDetails(List<ClassDetails> classDetails) {
		this.classDetails = classDetails;
	}

	public List<SubjectDetails> getSubjectDetails() {
		return subjectDetails;
	}

	public void setSubjectDetails(List<SubjectDetails> subjectDetails) {
		this.subjectDetails = subjectDetails;
	}

	public List<AdditionalSubjectsDetails> getAdditionalSubjectsDetails() {
		return additionalSubjectsDetails;
	}

	public void setAdditionalSubjectsDetails(List<AdditionalSubjectsDetails> additionalSubjectsDetails) {
		this.additionalSubjectsDetails = additionalSubjectsDetails;
	}

	public List<SectionDetails> getSectionDetails() {
		return sectionDetails;
	}

	public void setSectionDetails(List<SectionDetails> sectionDetails) {
		this.sectionDetails = sectionDetails;
	}

	
	
	
	
}
