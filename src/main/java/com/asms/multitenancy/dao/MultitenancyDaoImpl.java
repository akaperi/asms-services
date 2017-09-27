package com.asms.multitenancy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asms.Exception.AsmsException;
import com.asms.Exception.ExceptionHandler;
import com.asms.common.helper.AsmsHelper;
import com.asms.multitenancy.entity.Tenant;
import com.asms.multitenancy.entity.Trust;
import com.asms.multitenancy.request.TrustDetails;
import com.asms.rolemgmt.dao.RoleMgmtDao;
import com.asms.schoolmgmt.entity.AcademicYear;

@Service
@Component
public class MultitenancyDaoImpl implements MultitenancyDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ExceptionHandler exceptionHandler;

	@Resource(name = "asmsProperties")
	private Properties properties;

	@Resource(name = "asmsdbProperties")
	private Properties dbProperties;
	
	@Autowired
	private RoleMgmtDao roleMgmtDao;
	
	ResourceBundle messages;

	private static final Logger logger = LoggerFactory.getLogger(MultitenancyDaoImpl.class);

	/**
	 * @param id
	 * @param name
	 * @return
	 * @throws AsmsException
	 */

	@Override
	public boolean createSchema(String name) throws AsmsException {
		try {

			Configuration _configuration = new Configuration();
			_configuration.configure("database_multitenancy.xml");
			// Get a local configuration to configure
			final Configuration tenantConfig = _configuration;

			// Set the properties for this configuration
			Properties props = new Properties();
			props.put(Environment.DEFAULT_SCHEMA, name);
			tenantConfig.addProperties(props);
			Class.forName("com.mysql.jdbc.Driver");
			// Get connection
			Connection connection = DriverManager.getConnection(dbProperties.getProperty("db_url_multitenancy"),
					dbProperties.getProperty("username"), dbProperties.getProperty("password"));

			// Create the schema
			connection.createStatement().execute("CREATE SCHEMA " + name + "");

			// Run the schema update from configuration
			SchemaUpdate schemaUpdate = new SchemaUpdate(tenantConfig);
			schemaUpdate.execute(true, true);

			//insert default values
			roleMgmtDao.createDefaultRoles(name);
			
			return true;

		} catch (Exception ex) {
			// construct failure response
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "createSchema()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}

	}
	
	
	/**
	 * @param id
	 * @param name
	 * @return
	 * @throws AsmsException
	 */

	@Override
	public boolean updateSchema(String name) throws AsmsException {
		try {

			Configuration _configuration = new Configuration();
			_configuration.configure("database_multitenancy.xml");
			// Get a local configuration to configure
			final Configuration tenantConfig = _configuration;

			// Set the properties for this configuration
			Properties props = new Properties();
			props.put(Environment.DEFAULT_SCHEMA, name);
			tenantConfig.addProperties(props);
			Class.forName("com.mysql.jdbc.Driver");
			// Get connection
			Connection connection = DriverManager.getConnection(dbProperties.getProperty("db_url_multitenancy"),
					dbProperties.getProperty("username"), dbProperties.getProperty("password"));

			// Create the schema
			connection.createStatement().execute("USE " + name + "");

			// Run the schema update from configuration
			SchemaUpdate schemaUpdate = new SchemaUpdate(tenantConfig);
			schemaUpdate.execute(true, true);

			
			return true;

		} catch (Exception ex) {
			// construct failure response
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "updateSchema()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}

	}

	@Override
	public String createTenantId(String id, String name) throws AsmsException {

		Session session = null;
		Transaction tx = null;
		try {
			name = id + "_" + name;
			Tenant tenant = new Tenant();
			tenant.setTenantId(randomAlphaNumeric(id, name));
			tenant.setName(name);
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			tx = session.beginTransaction();

			session.save(tenant);

			tx.commit();
			session.close();
			return tenant.getName();
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "createTenantId()" + "   ", ex);

			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));

		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	public String randomAlphaNumeric(String id, String name) {
		String stringToGenerateFrom = id + "_" + name;
		int count = stringToGenerateFrom.length();
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * stringToGenerateFrom.length());
			builder.append(stringToGenerateFrom.charAt(character));
		}
		return builder.toString();
	}

	@Override
	public String getSchema(String tenantId) throws AsmsException {
		Session session = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(dbProperties.getProperty("default_schema"))
					.openSession();
			String hql = "from Tenant U where U.tenantId=?";
			Tenant tenant = (Tenant) session.createQuery(hql).setParameter(0, tenantId).uniqueResult();
			session.close();
			return tenant.getName();
			
			
			
		} catch (Exception e) {
			if (session.isOpen()){
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getSchema()" + "   ", e);
			ResourceBundle messages = AsmsHelper.getMessageFromBundle();
			throw exceptionHandler.constructAsmsException(messages.getString("SYSTEM_EXCEPTION_CODE"),
					messages.getString("SYSTEM_EXCEPTION"));
		}
		finally{
			if (session.isOpen()){
				session.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.asms.multitenancy.dao.MultitenancyDao#createTrust(com.asms.multitenancy.
	 * request.TrustDetails)
	 */
	@Override
	public void createTrust(TrustDetails trustDetails, String schema) throws AsmsException {
        Trust trust = new Trust();

		
		trust.setName(trustDetails.getName());
		
		trust.setAddress(trustDetails.getAddress());
		trust.setContactNo(Integer.parseInt(trustDetails.getContactNo()));
		
		trust.setEmail(trustDetails.getEmail());
		
		trust.setAffiliationId(trustDetails.getAffiliationId());
		
		trust.setLatitude(trustDetails.getLatitude());
		trust.setLongitude(trustDetails.getLongitude());
		
		trust.setCity(trustDetails.getCity());
		
		trust.setState(trustDetails.getState());
		
		trust.setCountry(trustDetails.getCountry());
		
         
		insertTrust(trust,schema);
		
		
	}

	public void insertTrust(Trust trust, String schema) throws AsmsException {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
			tx = session.beginTransaction();
			session.save(trust);
			tx.commit();
			session.close();

		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
			}
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."

					+ "insertTrust()" + "   ", ex);

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
	 * getTrust : This method retrieves Trusts from database 
	 * 
	 * parameters: 
	 * 
	 * return List Of trust
	 * 
	 * 
	 */

	@Override
	public List<Trust> getTrust(String schema) throws AsmsException {
		Session session = null;
		try {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from Trust ";

				@SuppressWarnings("unchecked")
				List<Trust> trust = session.createQuery(hql).list();
				session.close();
				return trust;
			
			

		} catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getTrust" + "   ", e);
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
	 * getAcademicYear : This method retrieves AcademicYear from database 
	 * 
	 * parameters: 
	 * 
	 * return List Of AcademicYear
	 * 
	 * 
	 */
	
	@Override
	public List<AcademicYear> getAcademicYear(String tenantId) throws AsmsException {
		Session session = null;
		try {
			
			String schema = getSchema(tenantId);
			if (null != schema) {
				session = sessionFactory.withOptions().tenantIdentifier(schema).openSession();
				String hql = "from AcademicYear A";
			@SuppressWarnings("unchecked")
			List<AcademicYear> academicYear = session.createQuery(hql).list();
			
			session.close();
			return academicYear;

			
			}else {
				throw exceptionHandler.constructAsmsException(messages.getString("TENANT_INVALID_CODE"),
						messages.getString("TENANT_INVALID_CODE_MSG"));
			}
			}
		catch (Exception e) {
			if (session.isOpen()) {
				session.close();
			}
			logger.error("Session Id: " + MDC.get("sessionId") + "   " + "Method: " + this.getClass().getName() + "."
					+ "getAcademicYear()" + "   ", e);
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
