/**
mallikarjun.guranna
Oct 9, 2017
*/
package com.asms.attendance.helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.student.StudentDetails;

@Component
public class EntityCreate {
	
	public List<UserDetails> createStudentBasicDetails(List<User> users) throws ParseException {
	//	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		//UserDetails userDetails = new UserDetails();
		
		UserDetails userDetails = new UserDetails();
		List<StudentDetails> studentDetails = new ArrayList<StudentDetails>();
		for (User user : users) {
		Student student = (Student) user;
		StudentDetails sDetails = new StudentDetails();

		
		
		sDetails.setStudentFirstName(student.getStudentFirstName());
		sDetails.setStudentMiddleName(student.getStudentMiddleName());
		sDetails.setStudentLastName(student.getStudentLastName());
		
		userDetails.setStudentDetails(sDetails);
		
		studentDetails.add(userDetails.getStudentDetails());
		}

		return (List<UserDetails>) userDetails;
	}

}