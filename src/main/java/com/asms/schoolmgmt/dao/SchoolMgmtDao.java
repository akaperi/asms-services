package com.asms.schoolmgmt.dao;

import java.util.List;

import com.asms.Exception.AsmsException;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.ClassSubjects;
import com.asms.schoolmgmt.entity.School;
import com.asms.schoolmgmt.entity.Section;
import com.asms.schoolmgmt.entity.SetupSchoolDetails;
import com.asms.schoolmgmt.request.BroadCasteSearchTypesDetails;
import com.asms.schoolmgmt.request.GroupDetails;
import com.asms.schoolmgmt.request.SchoolDetails;

public interface SchoolMgmtDao {


	public List<Section> getSections(String tenantId) throws AsmsException;
	

	
	 


	public Class getClassByName(String name, String tenantId) throws AsmsException;

	public Section getSectionByName(String className, String sectionName, String tenantId) throws AsmsException;
	
	public List<ClassSubjects> getSubjectByName(String className, String sectionName ,String tenantId) throws AsmsException;
	
	

	public List<String> getNames() throws AsmsException;

	public List<Class> getClasses(String tenantId) throws AsmsException;

	public List<String> getSujects(int boardId) throws AsmsException;

	public List<String> getClassSujects(int classId, String tenantId) throws AsmsException;

	public School getSchool(String schema) throws AsmsException;


	/**
	 * @param schoolDetails
	 * @return
	 * @throws AsmsException
	 */
	public void createSchool(SchoolDetails schoolDetails, String schema) throws AsmsException;

	public void setupSchool(SetupSchoolDetails setupSchoolDetail, String schema) throws AsmsException;

	public List<String> createBoradCasteMessage(BroadCasteSearchTypesDetails searchTypesDetails, String tenantId)
			throws AsmsException;

	/**
	 * method : createGroups : creates classes in groups according start time
	 * and end time and breaks
	 * 
	 * @param List<GroupDetails>
	 *            details
	 * @return
	 * @throws AsmsException
	 */
	public void createGroups(List<GroupDetails> details, String tenant) throws AsmsException;

	public void setupSchoolCopy(String academicyear, String tenantId) throws AsmsException;

}
