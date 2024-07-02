package com.cshop.login;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.cshop.cs_helper.db.HibernateUtil;
import com.cshop.login.configure.AnnotatedClass;

import jakarta.annotation.PostConstruct;

@Configuration
@SpringBootApplication
public class LoginApplication {

	
	@Value("${db.properties.file}")
	private String dbPropertiesfiles;
	
	public static void main(String[] args) {

		SpringApplication.run(LoginApplication.class, args);

		//boolean isSessionCreated = creationDBConnection(DBpropertiesfiles);
	}

	@PostConstruct
	 public void init() {
        boolean isSessionCreated = creationDBConnection(dbPropertiesfiles);
        if (!isSessionCreated) {
            System.err.println("Failed to create Hibernate session.");
        }
    }
	
	
	private static boolean creationDBConnection(String DBpropertiesfiles) {
		Boolean sessionCreated =HibernateUtil.setsessionFactory( DBpropertiesfiles, AnnotatedClass.getAnnotaClasses());
		return sessionCreated;
	}

}
