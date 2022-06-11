/**
 * 
 */
package sn.boom.apptennis.core.services;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.entities.Tournoi;
import sn.boom.apptennis.core.repository.TournoiRepositoryImpl;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */
public class TournoiService {
	
	private TournoiRepositoryImpl tournoiRepository;
	
	public TournoiService() {
		this.tournoiRepository = new TournoiRepositoryImpl();
	}
	
	public void saveTournoi(Tournoi tournoi) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			tournoiRepository.create(tournoi);
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +"  |  "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
	}

	public Tournoi getTournoi(long id) {
		
		Tournoi tournoi = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			tournoi = tournoiRepository.getById(id);
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +"  |  "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
		
		return tournoi;
	}
	
	public List<Tournoi> listeTournoi() {
		
		List<Tournoi> tournois = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			tournois = tournoiRepository.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +"  |  "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
		
		return tournois;
	}
	
	public void updateTournoi(Tournoi tournoi) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			tournoiRepository.update(tournoi);
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +"  |  "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	public void delete(long id) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			tournoiRepository.delete(id);
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +"  |  "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
	}
}
