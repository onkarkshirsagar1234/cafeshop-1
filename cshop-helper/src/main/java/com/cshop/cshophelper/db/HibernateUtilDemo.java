package com.cshop.cshophelper.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.cshop.cshophelper.bean.DBConnectProperties;
import com.cshop.cshophelper.constants.MessageConstants;
import com.cshop.cshophelper.helper.LogException;

public class HibernateUtilDemo {

	private static final Logger LOG = LogManager.getLogger();
	public static SessionFactory WRITE_DB_SESSION = null;
	public static SessionFactory GET_DB_SESSION = null;
	public static String WRITE = "W";
	public static String READ = "R";
	public static String READ_WRITE = "RW";
	public static String REPORT_READ_WRITE = "RDB";
	public static SessionFactory GET_RWDB_SESSION = null;

	/**
	 * This method is used to get session factory
	 * 
	 * @param type an type of db read or write
	 * @return an object of SessionFactory
	 */
	public static SessionFactory getSessionFactory(String type) {
		if (type.equals(WRITE)) {
			if (WRITE_DB_SESSION == null) {
				LOG.error(MessageConstants.WRITE_DB_ERROR);
			}
			return WRITE_DB_SESSION;
		} else if (type.equals(READ)) {
			if (GET_DB_SESSION == null) {
				LOG.error(MessageConstants.READ_DB_ERROR);
			}
			return GET_DB_SESSION;
		} else {
			LOG.error(MessageConstants.DB_TYPE_NULL + type);
			return null;
		}
	}

	/**
	 * This method is used to set session factory
	 * 
	 * @param redisData   an details of dbUrl & other info
	 * @param listClasses list of classes
	 * @param logID       an logID used to trace service call
	 * @return boolean true if session factory created else return false
	 */
	public static boolean setSessionFactory(String fileUrl, List<Class> listClasses, String requestID) {
		boolean sessionCreated = false;
		DBConnectProperties dbConnectProperties = null;
		try {
			dbConnectProperties = GetDBProperty.getDatabaseProperties(fileUrl, requestID);
			dbConnectProperties.setDbenv(HibernateUtilDemo.WRITE);
			Configuration configuration = new Configuration();
			Properties settings = new Properties();
			String driverName = dbConnectProperties.getDriverName();
			String dbUrl = dbConnectProperties.getDbUrl();
			String dbUserName = dbConnectProperties.getDbUserName();
			String dbPassword = dbConnectProperties.getDbPassword();
			LOG.info(requestID + MessageConstants.CREATE_DB_SESSION + dbUrl);

			settings.put(Environment.DRIVER, driverName);
			settings.put(Environment.URL, dbUrl + "?useSSL=" + dbConnectProperties.getUseSSL());
			settings.put(Environment.USER, dbUserName);
			settings.put(Environment.PASS, dbPassword);
			settings.put(Environment.DIALECT, dbConnectProperties.getSqlDialect());
			settings.put(Environment.SHOW_SQL, dbConnectProperties.getShowSql());
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, dbConnectProperties.getCurrentSessionContxtClass());
			settings.put("hibernate.connection.characterEncoding", "utf8");
			settings.put(Environment.DEFAULT_BATCH_FETCH_SIZE, dbConnectProperties.getDefaultBatchFetchSize());

//				settings.put(Environment.CONNECTION_PROVIDER, "org.hibernate.connection.C3P0ConnectionProvider");

			configuration.setProperties(settings);

			for (Class<Object> class1 : listClasses) {
				configuration.addAnnotatedClass(class1);
			}

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			if (dbConnectProperties.getDbType().equals(HibernateUtilDemo.WRITE)) {
				WRITE_DB_SESSION = sessionFactory;
				LOG.info(requestID + MessageConstants.SESSION_CREATED_MSG_WRITE);
			} else {
				GET_DB_SESSION = sessionFactory;
				LOG.info(requestID + MessageConstants.SESSION_CREATED_MSG_GET);
			}
			sessionFactory = null;
			sessionCreated = true;

		} catch (Exception e) {
			sessionCreated = false;
			LOG.info(requestID + MessageConstants.ERROR_OCCURED_SESSION_CREATION);
			LogException.logException(e, requestID);
		}
		return sessionCreated;

	}
}
