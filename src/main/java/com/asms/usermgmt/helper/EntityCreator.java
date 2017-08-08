package com.asms.usermgmt.helper;


import java.util.Date;

import org.springframework.stereotype.Component;

import com.asms.usermgmt.entity.Management;
import com.asms.usermgmt.entity.Student;
import com.asms.usermgmt.entity.TeachingStaff;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.request.ManagementDetails;
import com.asms.usermgmt.request.StudentDetails;
import com.asms.usermgmt.request.TeachingStaffDetails;

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
		student.setStudentSubCaste(details.getStudentSubCaste());
		student.setStudentType(details.getStudentType());
		return student;
	}
	
	
	public Management createManagement(ManagementDetails managementDetails,User user) {
		
		Management management =new Management();

		management.setTrustId(managementDetails.getTrustId());
		management.setMngmtRole(managementDetails.getMngmtRole());
		management.setMngmtFirstName(managementDetails.getMngmtFirstName());
		management.setMngmtMiddleName(managementDetails.getMngmtMiddleName());
		management.setMngmtLastName(managementDetails.getMngmtLastName());
		management.setMngmtDesignation(managementDetails.getMngmtDesignation());
		management.setMngmtContactNo(managementDetails.getMngmtContactNo());
		management.setMngmtEmailId(managementDetails.getMngmtEmailId());
		
		management.setMngmtCreationTime(new Date());
		management.setSchoolId("SCH001");
		management.setMngmtCreatedByWadmin(user.getUserId());
		management.setAcStatus("Complete");
		
		return management;
	}
	
	
public TeachingStaff createTeachingStaff(TeachingStaffDetails teachingStaffDetails,User user) {
		 
	TeachingStaff teachingStaff =new TeachingStaff();
	
	
	
	
	
	
	
	//--------------------------------------------------------
	teachingStaff.setId(teachingStaffDetails.getId());
	
	teachingStaff.setRoleName(teachingStaffDetails.getRoleName());
	teachingStaff.setDesignation(teachingStaffDetails.getDesignation());
	teachingStaff.setFirstName(teachingStaffDetails.getFirstName());
	teachingStaff.setMiddleName(teachingStaffDetails.getMiddleName());
	teachingStaff.setLastName(teachingStaffDetails.getMiddleName());
	teachingStaff.setFlagMakeAdmin(teachingStaffDetails.isFlagMakeAdmin());
	teachingStaff.setDob(teachingStaffDetails.getDob());
	teachingStaff.setGender(teachingStaffDetails.getGender());
	teachingStaff.setAgeInYears(teachingStaffDetails.getAgeInYears());
	teachingStaff.setContactNo(teachingStaffDetails.getContactNo());
	teachingStaff.setQualification(teachingStaffDetails.getQualification());
	teachingStaff.setEmail(teachingStaffDetails.getEmailId());
	teachingStaff.setReligion(teachingStaffDetails.getReligion());
	teachingStaff.setCasteCategory(teachingStaffDetails.getCasteCategory());
	teachingStaff.setPhoto(teachingStaffDetails.getPhoto());
	teachingStaff.setClassesHandled(teachingStaffDetails.getClassesHandled());
	teachingStaff.setSubjectsHandled(teachingStaffDetails.getSubjectsHandled());
	teachingStaff.setMaritalStatus(teachingStaffDetails.getMaritalStatus());
	teachingStaff.setSpouseName(teachingStaffDetails.getSpouseName());
	teachingStaff.setSpouseContactNo(teachingStaffDetails.getSpouseContactNo());
	//hard coded values
	teachingStaff.setSchoolId("SCH001");
	teachingStaff.setCreatedByWadmin(user.getUserId());
	teachingStaff.setCreationTime(new Date());
	teachingStaff.setAcStatus("Incomplete");
		
	return teachingStaff;
	}

}
