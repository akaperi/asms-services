package com.asms.usermgmt.dao;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asms.Exception.AsmsException;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.usermgmt.entity.StudentType;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.entity.student.Student;
import com.asms.usermgmt.request.ChangePasswordDetails;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.service.CasteTypes;
import com.asms.usermgmt.service.QualificationType;
import com.asms.usermgmt.service.ReligionTypes;

public interface UserMgmtDao {
	public String getUserRole(String email, String tenant) throws AsmsException;

	public String registerUser(UserDetails userDetails, User user, String tenant) throws AsmsException;
	public User getUser(String email, String tenant) throws AsmsException;

	public void insertUser(User user, String schema) throws AsmsException;

	public Role getRoleObject(String roleName, String schema) throws AsmsException;

	public SubRole getSubRoleObject(String roleName, String schema) throws AsmsException;

	boolean authenticate(HttpServletRequest request, HttpServletResponse response, String id, String email,
			String password) throws AsmsException;

	public void addDetails(UserDetails userDetails, User user, String tenant) throws AsmsException;

	public User getUserById(String userId, String tenant) throws AsmsException;

	public Student getStudentByUserId(String userId, String tenant) throws AsmsException;
	public User getUserDetailsById(String userId, String tenant) throws AsmsException;
	// teaching insert

	List<UserDetails> search(String role, String id, String studentFirstName, String studentLastName,
			String mngmtFirstName, String mngmtLastName, String firstName, String lastName, String tenant) throws AsmsException;

	public List<StudentType> getAll() throws AsmsException;
	
	public List<QualificationType> getQualification() throws AsmsException;
	
	public List<ReligionTypes> getReligions() throws AsmsException;
	
	public List<CasteTypes> getCasteName() throws AsmsException;

	public void updateUser(UserDetails userDetails, User user, String tenant) throws AsmsException;
	public UserDetails getUserDetails(String userId, String tenant) throws AsmsException;
	public void createDefaultPrivileges(String role, User user) throws AsmsException ;
	
	

	public void changePassword(ChangePasswordDetails changePasswordDetails, User user, String tenant) throws AsmsException;

	public List<UserDetails> searchForPrivileges(String role, String subRole, String id, String tenant) throws AsmsException;
	
	public void assignPrivileges(UserDetails details, String tenant) throws AsmsException;

	

	


}
