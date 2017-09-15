/**
@{author} mallikarjun.guranna
13-Sep-2017
*/
package com.asms.schoolmgmt.request;

import java.util.ArrayList;
import java.util.List;

import com.asms.schoolmgmt.entity.Class;
import com.asms.usermgmt.entity.teachingStaff.TeachingSubjects;

public class SectionDetails {
	/**
	@{author} mallikarjun.guranna
	13-Sep-2017
	*/
	
	private List<String> name;
	
	
	private List<SubjectDetails> subjectDetails;
	
	private List<AdditionalSubjectsDetails> additionalSubjectsDetails;
	
	
	
	
	private List<TeachingSubjects> teachingSubjects = new ArrayList<TeachingSubjects>();

	private Class classObject;

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
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

	public List<TeachingSubjects> getTeachingSubjects() {
		return teachingSubjects;
	}

	public void setTeachingSubjects(List<TeachingSubjects> teachingSubjects) {
		this.teachingSubjects = teachingSubjects;
	}

	public Class getClassObject() {
		return classObject;
	}

	public void setClassObject(Class classObject) {
		this.classObject = classObject;
	}
}
