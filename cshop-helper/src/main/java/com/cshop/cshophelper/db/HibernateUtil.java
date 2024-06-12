package com.cshop.cshophelper.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.cshop.cshophelper.constants.MessageConstants;
import com.cshop.cshophelper.helper.LogException;

public class HibernateUtil {

	private static final Logger LOG = LogManager.getFormatterLogger();
	public static SessionFactory WRITE_DB_SESSION = null;
	public static SessionFactory GET_DB_SESSION = null;
	public static String WRITE = "W";
	public static String READ = "R";

	public static Boolean setsessionFactory(String filePath, List<Class> listClasses, String requestID) {
		long startTime = System.currentTimeMillis();
		Properties properties = new Properties();
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = null;
		Properties settings = new Properties();
		Boolean isSessionCreated = false;

		try (InputStream input = new FileInputStream(filePath)) {
			properties.load(input);
			settings.put(Environment.DRIVER, properties.getProperty("driver-class-name"));
			settings.put(Environment.URL, properties.getProperty("url"));
			settings.put(Environment.USER, properties.getProperty("username"));
			settings.put(Environment.PASS, properties.getProperty("password"));
			settings.put(Environment.DIALECT, properties.getProperty("hibernate.dialect"));
			settings.put(Environment.SHOW_SQL, properties.getProperty("hibernate.show_sql"));
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,
					properties.getProperty("hibernate.current_session_context_class"));
			settings.put("hibernate.connection.characterEncoding", "utf8");

			configuration.setProperties(settings);

			for (Class<Object> class1 : listClasses) {
				configuration.addAnnotatedClass(class1);
			}

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			WRITE_DB_SESSION = sessionFactory;
			if (WRITE_DB_SESSION != null) {
				isSessionCreated = true;
			}
		} catch (Exception e) {
			LOG.info(requestID + MessageConstants.ERROR_OCCURED_SESSION_CREATION);
			LogException.logException(e, requestID);
		}
		long endTime = System.currentTimeMillis();
		LOG.info(requestID + MessageConstants.EXEC_TIME + (endTime - startTime));
		return isSessionCreated;
	}

	public static SessionFactory getSessionFactory() {
		return WRITE_DB_SESSION;
	}
}
