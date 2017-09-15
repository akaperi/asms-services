package com.asms.schoolmgmt.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.common.helper.Constants;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.schoolmgmt.entity.Class;
import com.asms.schoolmgmt.entity.School;
import com.asms.schoolmgmt.entity.Section;
import com.asms.schoolmgmt.entity.SetupSchoolDetails;
import com.asms.schoolmgmt.request.BroadCasteSearchTypesDetails;
import com.asms.schoolmgmt.request.ClassDetails;
import com.asms.schoolmgmt.request.SchoolDetails;
import com.asms.schoolmgmt.request.SectionDetails;
import com.asms.usermgmt.entity.Admin;

@Service
@Component
public class SchoolMgmtDaoImpl implements SchoolMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(SchoolMgmtDaoImpl.class);

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Autowired
	private MultitenancyDao multitenancyDao;
	
	

	ResourceBundle messages;

	/*
	 * Method : getUserRole : gets the user role from database input : String
	 * (email) return : String
	 *
	 */

	@Override
	public Class getClassByName(String name, String schema) throws AsmsException {
		Session session = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from Class C where C.name=?";
			Class classObject = (Class) session.createQuery(hql).setParameter(0, name).uniqueResult();
			session.close();
			return classObject;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getClassByName()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public Section getSectionByName(String className, String sectionName, String schema) throws AsmsException {
		Session session = null;
		try{
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Section C where C.name=? and C.classObject.name = ?";
				Section section = (Section) session.createQuery(hql).setParameter(0, sectionName)
						.setParameter(1, className).uniqueResult();
				session.close();
				return section;
			

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSectionByName()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<String> getNames() throws AsmsException {

		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select B.boardNames from BoardMaster B";

			@SuppressWarnings("unchecked")
			List<String> names = session.createQuery(hql).list();
			session.close();
			return names;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getNames()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public List<Class> getClasses(String tenantId) throws AsmsException {
		Session session = null;
		try {

			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Class";

				@SuppressWarnings("unchecked")
				List<Class> classes = session.createQuery(hql).list();
				session.close();
				return classes;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getClasses()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public List<String> getSujects(int boardId) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select B.subjectName from BoardSubjectMaster B where B.boardId='" + boardId + "'";

			@SuppressWarnings("unchecked")
			List<String> subjects = session.createQuery(hql).list();
			session.close();
			return subjects;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSujects()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	@Override
	public List<String> getClassSujects(int classId, String tenantId) throws AsmsException {
		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "select C.classSubjectName from ClassSubjects C where C.classId='" + classId + "'";

				@SuppressWarnings("unchecked")
				List<String> user = session.createQuery(hql).list();
				session.close();
				return user;
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getClassSujects()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.schoolmgmt.dao.SchoolMgmtDao#createSchool(com.asms.schoolmgmt.
	 * request.SchoolDetails)
	 */
	@Override
	public void createSchool(SchoolDetails schoolDetails, String schema) throws AsmsException {

		School school = new School();
		school.setId(schoolDetails.getId());
		school.setName(schoolDetails.getName());
		school.setAffiliationId(schoolDetails.getAffiliationId());
		school.setRegistrationCode(schoolDetails.getRegistrationCode());
		school.setWebsite(schoolDetails.getWebsite());
		school.setLogo(schoolDetails.getLogo());
		school.setAddressLine1(schoolDetails.getAddressLine1());
		school.setAddressLine2(schoolDetails.getAddressLine2());
		school.setLocation(schoolDetails.getLocation());
		school.setCountry(schoolDetails.getCountry());
		school.setState(schoolDetails.getState());
		school.setDistrict(schoolDetails.getDistrict());
		school.setSubdivision(schoolDetails.getSubdivision());
		school.setTehsil(schoolDetails.getTehsil());
		school.setVillage(schoolDetails.getVillage());
		school.setPincode(Integer.parseInt(schoolDetails.getPincode()));
		school.setContactNo1(schoolDetails.getContactNo1());
		school.setContactNo2(schoolDetails.getContactNo2());
		school.setMobileNo(schoolDetails.getMobileNo());
		school.setEmailId(schoolDetails.getEmailId());
		school.setTotalNoOfStudents(schoolDetails.getTotalNoOfStudents());
		school.setContactPersonName(schoolDetails.getContactPersonName());
		school.setContactPersonEmailId(schoolDetails.getContactPersonEmailId());
		school.setGpsLatitude(schoolDetails.getGpsLatitude());
		school.setGpsLongitude(schoolDetails.getGpsLongitude());
		// enter trust id if not null and it has only integer value
		if (null != schoolDetails.getTrustId()) {
			if (schoolDetails.getTrustId().matches("[0-9]+")) {
				school.setTrustId(Integer.parseInt(schoolDetails.getTrustId()));
			}
		}

		insertSchool(school, schema);

		// return null;

	}

	public void insertSchool(School school, String schema) throws AsmsException {

		Session session = null;
		Transaction tx = null;
		
		try {

			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();

			Role role = (Role) session.createQuery("from Role R where R.roleName = ?")
					.setParameter(0, Constants.role_admin).uniqueResult();

			SubRole sRole = (SubRole) session.createQuery("from SubRole S where S.subRoleName = ?")
					.setParameter(0, Constants.role_admin_subrole_admin).uniqueResult();
			
			session.save(school);
			Admin admin = createAdmin(school);
			admin.setRoleObject(role);
			admin.setSubRoleObject(sRole);

			session.save(admin);

			tx.commit();
			session.close();
		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertSchool()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

	private Admin createAdmin(School school) throws AsmsException {

		Admin admin = new Admin();
		admin.setCreatedOn(new Date());
		admin.setEmail(school.getContactPersonEmailId());
		admin.setIsNew("true");
		admin.setName(school.getContactPersonName());
		admin.setStatus("Complete");
		admin.setUserId(generateUserId());
		admin.setUserPassword("password");
		admin.setSchoolId(school.getSerialNo());
		return admin;

	}

	// generate userid
	private String generateUserId() {
		return UUID.randomUUID().toString();
	}

	@Override
	public School getSchool(String schema) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			String hql = "from School S";

			@SuppressWarnings("unchecked")
			List<School> schools = session.createQuery(hql).list();

			session.close();
			return schools.get(0);

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSchool" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	} 

	/* (non-Javadoc)
	 * @see com.asms.schoolmgmt.dao.SchoolMgmtDao#setupSchool(com.asms.schoolmgmt.entity.SetupSchoolDetails, java.lang.String)
	 */
	@Override
	public void setupSchool(SetupSchoolDetails setupSchoolDetail, String schema) throws AsmsException {
		// TODO Auto-generated method stub
		/**
		@{author} mallikarjun.guranna
		13-Sep-2017
		*/ 
		
		
		List<Class> classes = new ArrayList<Class>();
		List<ClassDetails> classDetails = new ArrayList<ClassDetails>();
		
		List<Section> sections = new ArrayList<Section>();
		List<SectionDetails> sectionDetails = new ArrayList<SectionDetails>();
		
		
		for(int i=0; i<setupSchoolDetail.getClassDetails().size(); i++)
		{
			classDetails.addAll(setupSchoolDetail.getClassDetails());
		}
		
		for(int i=0; i<setupSchoolDetail.getSectionDetails().size(); i++)
		{
			sectionDetails.addAll(setupSchoolDetail.getSectionDetails());
		}
		
		
		
		
		
			
		
		
		
		
		
		

	}

	/* (non-Javadoc)
	 * @see com.asms.schoolmgmt.dao.SchoolMgmtDao#get(com.asms.schoolmgmt.request.BroadCasteSearchTypesDetails, java.lang.String)
	 */
	@Override
	public List<BroadCasteSearchTypesDetails> get(BroadCasteSearchTypesDetails typesDetails, String tenantId)
			throws AsmsException {
		// TODO Auto-generated method stub
		return null;
		/**
		@{author} mallikarjun.guranna
		15-Sep-2017
		*/
	}
	
	
	
	
	

}