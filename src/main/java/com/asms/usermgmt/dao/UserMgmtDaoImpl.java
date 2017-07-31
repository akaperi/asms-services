package com.asms.usermgmt.dao;

import java.util.ResourceBundle;

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
import com.asms.usermgmt.entity.User;
import com.asms.usermgmt.service.UserMgmtService;



@Service
@Component
public class UserMgmtDaoImpl implements UserMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ExceptionHandler exceptionHandler;
	
	private static final Logger logger = LoggerFactory.getLogger(UserMgmtService.class);

	//@Override
	/*public void createAdmin(Admin admin) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			ArrayList<Admin> adminList = new ArrayList<>();
			SchoolDetails schoolDetailsObject = new SchoolDetails();
			
			
			schoolDetailsObject.setAbout("vv");
			schoolDetailsObject.setAddress("ll");
			schoolDetailsObject.setAdminEmail("k");
			schoolDetailsObject.setAdminId(admin.getAdminId());
			schoolDetailsObject.setAdminName(admin.getUserName());
			schoolDetailsObject.setAdminObjectList(adminList);
			schoolDetailsObject.setAffiliationId(5);
			schoolDetailsObject.setCity("fff");
			schoolDetailsObject.setContactNo("kkk");
			schoolDetailsObject.setCountry("India");
			schoolDetailsObject.setEmail("mm");
			schoolDetailsObject.setFax("ll");
			schoolDetailsObject.setGpsLatitude(new BigDecimal("11.11"));
			schoolDetailsObject.setGpsLongitude(new BigDecimal("10.10"));
			schoolDetailsObject.setNoOfStudents(10);
			schoolDetailsObject.setPinCode("77777");
			schoolDetailsObject.setRegistrationCode("kkk");
			schoolDetailsObject.setSchoolBoard("kkk");
			schoolDetailsObject.setSchoolId("jjjjj");
			schoolDetailsObject.setSchoolName("gggg");
			schoolDetailsObject.setState("karnataka");
			admin.setSchoolDetailsObject(schoolDetailsObject);
			adminList.add(admin);
			schoolDetailsObject.setAdminObjectList(adminList);
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
			session.save(schoolDetailsObject);

			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (tx != null) {
				if (tx.wasCommitted() == false) {
					tx.rollback();
				}
			} else {
				System.out.println("sessionid :{} error while inserting admin :{}" + ex);
				session.close();
			}
			throw ex;
		}
	}*/

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * Method : getUserRole : gets the user role from database
	 * input : String (email)
	 * return : String
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
			String role = (String) session.createQuery(hql)
					.setParameter(0, email)
					.uniqueResult();
			tx.commit();
			return role;
			
		}
		catch (Exception e) {
			logger.error("Session Id: " + MDC.get("sessionId") + "   "+
					"Method: "+ this.getClass().getName()+ "." + "getUserRole()" + "   "+e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"), messages.getString("SYSTEM_EXCEPTION"));	
		}
	
	}

}
