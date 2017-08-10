package com.asms.usermgmt.helper;

import org.springframework.stereotype.Component;

import com.asms.usermgmt.entity.Student;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.request.StudentDetails;

/*
 * EntityCreator.java does creation of entity objects
 * from ui object
 * 
 */

@Component
public class EntityCreator {
	
	/*
	 * Method: createStudent -> maps ui values to entity
	 * input : StudentDetails
	 * return : Student
	 * 
	 */
	
	public Student createStudent(StudentDetails details, User user) {
		Student student = new Student();
		student.setAdmissionDate(details.getAdmissionDate());
		student.setAdmissionNo(details.getAdmissionNo());
		
		student.setSchoolId("SCH001");
		student.setStudentAgeInYears(details.getStudentAgeInYears());
		student.setStudentBirthplace(details.getStudentBirthplace());
		student.setStudentCasteCategory(details.getStudentCasteCategory());
		student.setStudentClass(details.getStudentClass());
		student.setStudentCreatedByWadmin(user.getUserId());
		student.setStudentDob(details.getStudentDob());
		student.setStudentFirstName(details.getStudentFirstName());
		student.setStudentGender(details.getStudentGender());
		student.setStudentIdentificationMarks(details.getStudentIdentificationMarks());
		student.setStudentLastName(details.getStudentLastName());
		student.setStudentMiddleName(details.getStudentMiddleName());
		student.setStudentMotherTongue(details.getStudentMotherTongue());
		student.setStudentNationality(details.getStudentNationality());
		student.setStudentPhoto(details.getStudentPhoto());
		student.setStudentReligion(details.getStudentReligion());
		student.setStudentSection(details.getStudentSection());
		student.setEmergencyontactNo(details.getEmergencyContactNo());
		student.setStudentSubCaste(details.getStudentSubCaste());
		student.setStudentType(details.getStudentType());
		return student;
	}

}
