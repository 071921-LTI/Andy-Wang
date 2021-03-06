package com.lti.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
private static SessionFactory sf;
	
	public static SessionFactory getSessionFactory() {
		
		if(sf == null || sf.isClosed() == true) {
			Configuration cfg = new Configuration();
			cfg.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
			cfg.setProperty("hibernate.connection.username", System.getenv("DB_USER"));
			cfg.setProperty("hibernate.connection.password", System.getenv("DB_PASS"));
			cfg.setProperty("hibernate.default_schema", "project1");
			sf = cfg.configure("hibernate.cfg.xml").buildSessionFactory();
			
			
		}
		
		return sf;
	}
}
