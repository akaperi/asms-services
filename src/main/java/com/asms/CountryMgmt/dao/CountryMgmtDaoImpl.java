package com.asms.CountryMgmt.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.CountryMgmt.Entity.Country;
/*
 * 
 * Class name : CountryMgmtDaoImpl
 * This class contains the implementation details of CountryNamesDto interface.
 */

@Service
@Component
public class CountryMgmtDaoImpl implements CountryNamesDao{
	 
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private static final Logger logger= LoggerFactory.getLogger(CountryMgmtDaoImpl.class);
	Session session = null;
	Transaction tx = null;
	ArrayList<Country> countryList = new ArrayList<>();
	//@SuppressWarnings("unchecked")
	@SuppressWarnings("unchecked")
	@Override
	public  ArrayList<Country> getCountries() 
	{
		try
		{
			
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
			logger.debug("sessionid :{} CountryList {}");
			countryList =    (ArrayList<Country>) session.createQuery("FROM Country").list();
			tx.commit();
			logger.info(countryList.toString());
		}
		catch (Exception e) 
		{	
			logger.info("ERROR",e);
			logger.error("We could't able fetch country names");
		}


			return countryList;

			/*session = this.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();

			User cBeanToUpdate = (User) session.load(User.class, id);
			cBeanToUpdate.setProfileImage(getUserProfileImageFilePathForUpload(id,milliseconds));
			cBeanToUpdate.setCoverImage(getUserCoverImageFilePathForUpload(id,milliseconds));

			cBeanToUpdate.setLastUpdatedOn(new Date());
			session.update(cBeanToUpdate);
			tx.commit();*/
		
	}

}
