package com.cshop.cafeservice.configure;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfiguration {

	public static final Logger LOG = LogManager.getFormatterLogger();
	public static SessionFactory WRITE_DB_SESSION = null;
	public static SessionFactory GET_DB_SESSION = null;
	public static String WRITE = "W";
	public static String READ = "R";

	@Value("${db.properties.file}")
	private String dbPropertiesFile;

	@SuppressWarnings("deprecation")
	@Bean
	@Primary
	public Boolean createDBSession() {

		Properties properties = new Properties();
		org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
		SessionFactory sessionFactory = null;
		Properties settings = new Properties();
		Boolean isSessionCreated = false;

		try (FileInputStream input = new FileInputStream(dbPropertiesFile)) {
			properties.load(input);
			settings.put(Environment.DRIVER, properties.getProperty("driver-class-name"));
			settings.put(Environment.URL, properties.getProperty("url"));
			settings.put(Environment.USER, properties.getProperty("username"));
			settings.put(Environment.PASS, properties.getProperty("password"));
			settings.put(Environment.DIALECT, properties.getProperty("hibernate.dialect"));
			settings.put(Environment.SHOW_SQL, properties.getProperty("hibernate.show_sql"));
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,
					properties.getProperty("hibernate.current_session_context_class"));
			settings.put(Environment.HBM2DDL_AUTO,properties.getProperty("hibernate.hbm2ddl.auto"));
			settings.put("hibernate.connection.characterEncoding", "utf8");

			configuration.setProperties(settings);

			for (Class<Object> classes : AnnotatedClass.getAnnotaClasses()) {
				configuration.addAnnotatedClass(classes);
			}

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			WRITE_DB_SESSION = sessionFactory;
			if (WRITE_DB_SESSION != null) {
				isSessionCreated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSessionCreated;
	}

	public static SessionFactory getSessionFactory() {
		return WRITE_DB_SESSION;
	}

}
