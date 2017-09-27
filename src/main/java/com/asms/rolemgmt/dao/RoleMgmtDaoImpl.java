package com.asms.rolemgmt.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

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

@Service
@Component
public class RoleMgmtDaoImpl implements RoleMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;	

	@Autowired
	private ExceptionHandler exceptionHandler;

	private static final Logger logger = LoggerFactory.getLogger(RoleMgmtDaoImpl.class);
	
	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	@Resource(name = "asmsProperties")
	private Properties properties;

	
	@Autowired
	private MultitenancyDao multitenancyDao;
	
	
	ResourceBundle messages;


	/*
	 * Method : createRole :insert role
	 * 
	 */
	@Override
	public void createDefaultRoles(String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			
			session = sessionFactory.withOptions().tenantIdentifier(schema)
					.openSession();
			tx = session.beginTransaction();
			
			
			Role role = new Role();
			SubRole sRole = new SubRole();
			role.setRoleName(Constants.role_admin);
			
			sRole.setRoleObject(role);
			sRole.setSubRoleName(Constants.role_admin_subrole_admin);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			session.save(role);
			
			role = new Role();
			sRole = new SubRole();			
			role.setRoleName(Constants.role_non_teaching_staff);			
			sRole.setSubRoleName(Constants.role_nonTeaching_subrole_advisor);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_nonTeaching_subrole_frontClerk);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_nonTeaching_subrole_jAccountant);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_nonTeaching_subrole_sAccountant);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_nonTeaching_subrole_manager);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			session.save(role);			
			
			
			role = new Role();
			sRole = new SubRole();			
			role.setRoleName(Constants.role_teaching_staff);			
			sRole.setSubRoleName(Constants.role_teaching_subrole_classTeacher);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_teaching_subrole_teacher);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_teaching_subrole_principal);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_teaching_subrole_viceprincipal);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_teaching_subrole_headMistress);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			session.save(role);
			
			
			role = new Role();
			sRole = new SubRole();			
			role.setRoleName(Constants.role_management);			
			sRole.setSubRoleName(Constants.role_management_subrole_CEO);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_management_subrole_President);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_management_subrole_Treasurer);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_management_subrole_Trustee);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			session.save(role);
			
			
			
			
			role = new Role();
			sRole = new SubRole();			
			role.setRoleName(Constants.role_student);			
			sRole.setSubRoleName(Constants.role_student_subrole_leader);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			sRole = new SubRole();	
			sRole.setSubRoleName(Constants.role_student_subrole_student);
			sRole.setRoleObject(role);
			role.getSubRoleObjectList().add(sRole);
			
			session.save(role);
			
			

			tx.commit();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "createDefaultRoles()" + "   ", ex);

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
	 * insertSubRole : This method inserts subRole in the  database.
	 * 
	 * 
	 * parameters: String roleName , String subRoleName
	 * 
	 * return: void
	 * 
	 * 
	 */
	@Override
	public void insertSubRole(String roleName, String subRoleName, String tenantId) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		
		
		try {
		String schema=	multitenancyDao.getSchema(tenantId);
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
		String hqry="from Role  where roleName='"+roleName+"'";
		Role role = (Role) session.createQuery(hqry).uniqueResult();
	
		SubRole subRole = new SubRole();
		
		// subRole.setRoleObject(role);
		subRole.setSubRoleName(subRoleName);
		
		subRole.getRoleObject().setRoleName(subRoleName);
		role.getSubRoleObjectList().add(subRole);
            

		tx = session.beginTransaction();
		session.save(subRole);
		tx.commit();
		session.close();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertSubRole()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	

	
}
