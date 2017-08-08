package com.asms.usermgmt.dao;

import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

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
import com.asms.rolemgmt.entity.Role;
import com.asms.rolemgmt.entity.SubRole;
import com.asms.usermgmt.entity.Management;
import com.asms.usermgmt.entity.Student;
import com.asms.usermgmt.entity.TeachingStaff;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.helper.EntityCreator;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.service.UserMgmtService;

@Service
@Component
public class UserMgmtDaoImpl implements UserMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Autowired
	private EntityCreator entityCreator;

	private static final Logger logger = LoggerFactory.getLogger(UserMgmtService.class);

	/*
	 * Method : getUserRole : gets the user role from database input : String
	 * (email) return : String
	 *
	 */

	@Override
	public String getUserRole(String email) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "Select U.roleObject.roleName from User U where U.email=?";
			String role = (String) session.createQuery(hql).setParameter(0, email).uniqueResult();
			tx.commit();
			return role;

		} catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUserRole()" + "   " + e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}
	}

	@Override
	public User getUser(String email) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from User U where U.email=?";
			User user = (User) session.createQuery(hql).setParameter(0, email).uniqueResult();
			tx.commit();
			return user;

		} catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getUser()" + "   " + e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}

	}

	@Override
	public Role getRoleObject(String roleName) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from Role U where U.roleName=?";
			Role role = (Role) session.createQuery(hql).setParameter(0, roleName).uniqueResult();
			tx.commit();
			return role;

		} catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getRoleObject()" + "   " + e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}

	}

	@Override
	public SubRole getSubRoleObject(String roleName) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			String hql = "from SubRole U where U.subRoleName=?";
			@SuppressWarnings("unchecked")
			List<SubRole> sRole = session.createQuery(hql).setParameter(0, roleName).list();
			tx.commit();
			return sRole.get(0);

		} catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSubRoleObject()" + "   " + e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}

	}

	/*
	 * Method : registerUser : inserts user details into database input :
	 * UserDetails return : void
	 *
	 */
	@Override
	public void registerUser(UserDetails userDetails, User user) throws AsmsException {
		try {
			if (userDetails.getRole().equalsIgnoreCase(Constants.role_student)) {
				Role role = getRoleObject(userDetails.getRole());
				SubRole sRole = getSubRoleObject(userDetails.getRole());
				if (null != role && null != sRole) {
					Student student = entityCreator.createStudent(userDetails.getStudenrDetails(), user);
					student.setUserPassword(generatePassword(Constants.role_student));
					student.setUserId(generateUserId());
					student.setEmail(userDetails.getEmail());
					student.setRoleObject(role);
					student.setSubRoleObject(sRole);
					student.setStatus("Complete");

					insertStudent(student);
				} else {
					logger.debug("role not matched");
				}

			}

			else if (userDetails.getRole().equalsIgnoreCase(Constants.role_management)) {
				Role role = getRoleObject(userDetails.getRole());
				SubRole sRole = getSubRoleObject(userDetails.getSubRole());
				if (null != role && null != sRole) {
					Management management = entityCreator.createManagement(userDetails.getManagementDetails(), user);
					
					management.setUserPassword(generatePassword(Constants.role_management));
					management.setUserId(generateUserId());
					management.setEmail(userDetails.getEmail());
					management.setRoleObject(role);
					management.setSubRoleObject(sRole);
					

					insertManagement(management);
				}

			} 
			else if (userDetails.getRole().equalsIgnoreCase(Constants.role_teacher)) {
				Role role = getRoleObject(userDetails.getRole());
				SubRole sRole = getSubRoleObject(userDetails.getSubRole());
				if (null != role && null != sRole) {
					TeachingStaff teachingStaff = entityCreator.createTeachingStaff(userDetails.getTeachingStaffDetails(), user);
					
					teachingStaff.setUserPassword(generatePassword(Constants.role_teacher));
					teachingStaff.setUserId(generateUserId());
					teachingStaff.setEmail(userDetails.getEmail());
					teachingStaff.setRoleObject(role);
					teachingStaff.setSubRoleObject(sRole);
					

					insertTeachingStaff(teachingStaff);
				}

			}else {
				logger.debug("role not matched");
			}

		} catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "registerUser()" + "   " + e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}

	}

	/*
	 * Method : insertStudent : inserts Student entity into database input :
	 * student return : void
	 *
	 */

	@Override
	public void insertUser(User user) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {

			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.save(user);

			tx.commit();
		} catch (Exception ex) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertUser()" + "   ", ex);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		}

	}

	// generate default encrypted password
	private String generatePassword(String role) {
		// return BCrypt.hashpw(role + "123", BCrypt.gensalt(10));
		return role + "123";
	}

	// generate userid
	private String generateUserId() {
		return UUID.randomUUID().toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertStudent(com.asms.usermgmt.entity.
	 * Student)
	 */
	@Override
	public void insertStudent(Student student) throws AsmsException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {

			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();

			session.save(student);

			tx.commit();
		} catch (Exception ex) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "insertStudent()" + "   " + ex);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertManagement(com.asms.usermgmt.
	 * entity.Management)
	 */
	@Override
	public void insertManagement(Management management) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		session = this.sessionFactory.getCurrentSession();
		try {
			tx = session.beginTransaction();
			session.save(management);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				if (tx.wasCommitted() == false) {
					tx.rollback();
				}
			} else {
				System.out.println("sessionid :{} error while inserting Management :{}" + ex);
				session.close();
			}
			throw ex;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.usermgmt.dao.UserMgmtDao#insertTeachingStaff(com.asms.usermgmt.
	 * entity.TeachingStaff)
	 */
	@Override
	public void insertTeachingStaff(TeachingStaff teachingStaff) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		session = this.sessionFactory.getCurrentSession();
		try {
			tx = session.beginTransaction();
			session.save(teachingStaff);
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				if (tx.wasCommitted() == false) {
					tx.rollback();
				}
			} else {
				System.out.println("sessionid :{} error while inserting Management :{}" + ex);
				session.close();
			}
			throw ex;
		}
	}

}
