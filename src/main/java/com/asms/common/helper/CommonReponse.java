package com.asms.common.helper;

import java.util.List;

import com.asms.common.response.SuccessResponse;
import com.asms.multitenancy.entity.Classes;
import com.asms.multitenancy.entity.Grades;
import com.asms.multitenancy.entity.Standard;
import com.asms.schoolmgmt.entity.AcademicYear;



public class CommonReponse extends SuccessResponse {
	
	private List<AcademicYear> academicYear;
	private List<Grades> grades;
	private List<Standard> standards;
	private List<Classes> classes;

	

	public List<AcademicYear> getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(List<AcademicYear> academicYear) {
		this.academicYear = academicYear;
	}

	public List<Grades> getGrades() {
		return grades;
	}

	public void setGrades(List<Grades> grades) {
		this.grades = grades;
	}

	public List<Standard> getStandards() {
		return standards;
	}

	public void setStandards(List<Standard> standards) {
		this.standards = standards;
	}

	public List<Classes> getClasses() {
		return classes;
	}

	public void setClasses(List<Classes> classes) {
		this.classes = classes;
	}
	
	
	
	
	
	
}
