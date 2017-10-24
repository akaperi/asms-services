package com.asms.CountryMgmt.dao;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.CountryMgmt.Entity.Country;
import com.asms.CountryMgmt.Entity.StateEntity;
import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
/*
 * 
 * Class name : CountryMgmtDaoImpl
 * This class contains the implementation details of CountryNamesDto interface.
 */
import com.asms.common.helper.AsmsHelper;

@Service
@Component
public class CountryMgmtDaoImpl implements CountryNamesDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;

	private static final Logger logger = LoggerFactory.getLogger(CountryMgmtDaoImpl.class);

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.CountryMgmt.dao.CountryNamesDao#getCountries()
	 */
	@Override
	public List<Country> getCountries() throws AsmsException {
		Session session = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from Country";
			@SuppressWarnings("unchecked")
			List<Country> countries = session.createQuery(hql).list();
			session.close();
			return countries;
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}

			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getCountries()" + "   ", e);
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
	 * @see
	 * com.asms.CountryMgmt.dao.CountryNamesDao#getStates()
	 */
	@Override
	public List<StateEntity> getStates() throws AsmsException {

		Session session = null;
		try {

			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from StateEntity";
			@SuppressWarnings("unchecked")
			List<StateEntity> states = session.createQuery(hql).list();
			session.close();
			return states;
		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}

			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getStates()" + "   ", e);
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
	 * @see
	 * com.asms.CountryMgmt.dao.CountryNamesDao#getDistrict(int  stateId )
	 */
	@Override
	public List<String> getDistrict(int stateId) throws AsmsException {
		Session session = null;

		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select D.dName from District D where D.stateId='" + stateId + "'";

			@SuppressWarnings("unchecked")
			List<String> districts = session.createQuery(hql).list();
			session.close();
			return districts;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getDistrict()" + "   ", e);
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
	 * @see
	 * com.asms.CountryMgmt.dao.CountryNamesDao#getTehsil(int districtId )
	 */
	@Override
	public List<String> getTehsil(int districtId) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select T.tehsilName from Tehsil T where T.districtId='" + districtId + "'";

			@SuppressWarnings("unchecked")
			List<String> tehsil = session.createQuery(hql).list();
			session.close();
			return tehsil;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getTehsil()" + "   ", e);
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
	 * @see
	 * com.asms.CountryMgmt.dao.CountryNamesDao#getVillage(int tehsilId )
	 */
	@Override
	public List<String> getVillage(int tehsilId) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select V.villageName from Village V where V.tehsilId='" + tehsilId + "'";

			@SuppressWarnings("unchecked")
			List<String> villages = session.createQuery(hql).list();
			session.close();
			return villages;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getVillage()" + "   ", e);
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
	 * @see
	 * com.asms.CountryMgmt.dao.CountryNamesDao#getSubDivision(int districtId )
	 */
	@Override
	public List<String> getSubDivision(int districtId) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "select S.subDivisionName from SubDivision S where S.districtId='" + districtId + "'";

			@SuppressWarnings("unchecked")
			List<String> subDivisions = session.createQuery(hql).list();
			return subDivisions;

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSubDivision()" + "   ", e);
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
