package sn.boom.apptennis.core.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.apptennis.core.repository.EpreuveRepository;
import sn.boom.sgi.hibernate.HibernateManager;

public class EpreuveService {
	
	private EpreuveRepository epreuveRepository;
	
	public EpreuveService() {
		this.epreuveRepository = new EpreuveRepository();
	}

	public void createEpreuve(Epreuve epreuve) {
		Session session = null;
		Transaction tx = null;
		try {
			
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			epreuveRepository.create(epreuve);
			
			tx.commit();
			
			System.out.println("Joueur "+ epreuve.getId() +" a été bien sauvegarder!");
		} 
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	public Epreuve getEpreuveWithTournoi(long id) {
		
		Epreuve epreuve = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			epreuve = epreuveRepository.getById(id);
			Hibernate.initialize(epreuve.getTournoi());
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
		
		return epreuve;
	}
	
	public Epreuve getEpreuveWithoutTournoi(long id) {
		
		Epreuve epreuve = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			epreuve = epreuveRepository.getById(id);
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
		
		return epreuve;
	}
	
	public void updateEpreuve(Epreuve obj) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Epreuve epreuve = epreuveRepository.getById(obj.getId());
			if (epreuve != null) {
				epreuve.setAnnee(obj.getAnnee());
				epreuve.setTournoi(obj.getTournoi());
				epreuve.setTypeEpreuve(obj.getTypeEpreuve());
			}
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	public void deleteEpreuve(long id) {
		
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			epreuveRepository.delete(id);
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	public List<Epreuve> listeEpreuve(){
		
		List<Epreuve> epreuves = null;
		Session session = null;
		Transaction tx= null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			epreuves = epreuveRepository.list();
			
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if(session != null) session.close();
		}
		
		return epreuves;
	}
}
