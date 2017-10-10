/**
mallikarjun.guranna
Oct 6, 2017
*/
package com.asms.attendance.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.attendance.helper.EntityCreate;
import com.asms.common.helper.AsmsHelper;
import com.asms.multitenancy.dao.MultitenancyDao;
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.request.UserDetails;
import com.asms.usermgmt.request.student.StudentDetails;
import com.asms.usermgmt.service.UserMgmtService;

@Service
@Component
public class AttendanceDaoImpl implements AttendanceDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;
	
	@Autowired
	private MultitenancyDao multitenancyDao;
	
	@Autowired
	private EntityCreate entityCreator;
	
	ResourceBundle messages;

	private static final Logger logger = LoggerFactory.getLogger(AttendanceDaoImpl.class);


	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetails> search(String className, String sectionName, String tenant) throws AsmsException {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			messages = AsmsHelper.getMessageFromBundle();
			String schema = multitenancyDao.getSchema(tenant);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				if (true) {
					String hql = "from Student S where";
					if (className != null) {
						hql = hql + " S.studentClass='"+className+"'";
					}
					if (className != null && sectionName != null) {
						hql = hql + " and S.studentSection='"+sectionName+"'";
					}
					
					@SuppressWarnings("unchecked")
					List<User> users =  session.createQuery(hql).list();
					
					
					return entityCreator.createStudentBasicDetails(users);

				}  else {
					return new ArrayList<UserDetails>();
				}

			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}

		} catch (Exception ex) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "search()" + "   ", ex);
			if (ex instanceof AsmsException) {
				throw exceptionHandler.constructAsmsException(((AsmsException) ex).getCode(),
						((AsmsException) ex).getDescription());
			} else {
				throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
						messages.getString("SYSTEM_EXCEPTION"));
			}
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

	}

}
