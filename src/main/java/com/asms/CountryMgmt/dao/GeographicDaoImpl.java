package com.asms.CountryMgmt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.CountryMgmt.Entity.StateEntity;


@Service
@Component
public class GeographicDaoImpl implements GeographicDao {

	/*@Override
	public ArrayList<GeograhicEntity> getAll() {
		return null;
	}*/
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private static final Logger logger= LoggerFactory.getLogger(CountryMgmtDaoImpl.class);
	Session session = null;
	Transaction tx = null;
	List<StateEntity> stateEntity = new ArrayList<StateEntity>();
	
	public ArrayList<StateEntity> getCountries() {
		
		
			/*try
			{
			session = this.sessionFactory.getCurrentSession();
			tx = session.beginTransaction();
				logger.debug("sessionid :{} Contry: StateList {}");
				stateEntity = (ArrayList<StateEntity>) session.createQuery("select G.siNo,G.stateName from StateEntity AS G").list();
				//countryList = (ArrayList<GeograhicEntity>) session.createQuery("select G from GeograhicEntity G").list();
				//countryList = (ArrayList<GeograhicEntity>) session.createQuery("SELECT c FROM GeograhicEntity AS c").list();
				//countryList = (ArrayList<GeograhicEntity>) session.createQuery("select distinct G.countryName from GeograhicEntity AS G").list();
				//countryList = (ArrayList<GeograhicEntity>) session.createQuery("select G.countryName, G.stateName from GeograhicEntity AS G").list();
				
				tx.commit();
				logger.info(stateEntity.toString());
			}
			catch (Exception e) 
			{	
				logger.info("ERROR",e);
				logger.error("We could't able fetch State names");
			}

				return (ArrayList<StateEntity>) stateEntity;*/
		return null;
		}
		
		/*
		 * Method Name: getStateByCountry
		 * input parameters : No
		 * outcome: List of states name with Contry name
		 * default country name is INDIA
		 * 
		 */
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<StateEntity> getStateByCountry() {
		try
		{
		session = this.sessionFactory.getCurrentSession();
		tx = session.beginTransaction();
			logger.debug("sessionid :{} Contry: StateList {}");
			stateEntity = (ArrayList<StateEntity>) session.createQuery("select G.states from StateEntity AS G").list();
			//countryList = (ArrayList<GeograhicEntity>) session.createQuery("select G from GeograhicEntity G").list();
			//countryList = (ArrayList<GeograhicEntity>) session.createQuery("SELECT c FROM GeograhicEntity AS c").list();
			//countryList = (ArrayList<GeograhicEntity>) session.createQuery("select distinct G.countryName from GeograhicEntity AS G").list();
			//countryList = (ArrayList<GeograhicEntity>) session.createQuery("select G.countryName, G.stateName from GeograhicEntity AS G").list();
			
			tx.commit();
			
			
			logger.info(stateEntity.toString());
		}
		catch (Exception e) 
		{	
			logger.info("ERROR",e);
			logger.error("We could't able fetch state names");
		}

			return (ArrayList<StateEntity>) stateEntity;
		
	}
	@Override
	public ArrayList<?> getDistrictByState() {
		return null;
	}
	@Override
	public ArrayList<?> getSubDivisionBySistrict() {
		return null;
	}
	@Override
	public ArrayList<?> getTalukBySubDivision() {
		return null;
	}
	@Override
	public ArrayList<?> getVillageByTaluk() {
		return null;
	}

}
