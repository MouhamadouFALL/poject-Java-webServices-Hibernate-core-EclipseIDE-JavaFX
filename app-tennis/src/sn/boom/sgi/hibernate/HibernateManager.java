package sn.boom.sgi.hibernate;

import org.hibernate.cfg.Configuration;

import org.hibernate.SessionFactory;

public class HibernateManager {
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			// create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex) {
			System.err.println("Error : unable to open sessionFactory with hibernate.cfg.xml");
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	
	

}
