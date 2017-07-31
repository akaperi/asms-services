package com.asms.adminmgmt.dao;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.adminmgmt.entity.Admin;
import com.asms.schoolmgmt.entity.SchoolDetails;

@Service
@Component
public class AdminMgmtDaoImpl implements AdminMgmtDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createAdmin(Admin admin) {
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
	}

}
