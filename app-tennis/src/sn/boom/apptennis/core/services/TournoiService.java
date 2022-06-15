/**
 * 
 */
package sn.boom.apptennis.core.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.dto.TournoiDto;
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
	
	public void saveTournoi(TournoiDto tournoiDto) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			// traduire l'objet dto en object entity
			Tournoi tournoi = new Tournoi();
			tournoi.setId(tournoiDto.getId());
			tournoi.setCode(tournoiDto.getCode());
			tournoi.setNom(tournoiDto.getNom());
			
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

	public TournoiDto getTournoi(long id) {
		
		TournoiDto tournoiDto = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Tournoi tournoi = tournoiRepository.getById(id);
			
			// Traduire objet entity tournoi en objet DTO tournoiDto
			tournoiDto = new TournoiDto();
			tournoiDto.setId(tournoi.getId());
			tournoiDto.setCode(tournoi.getCode());
			tournoiDto.setNom(tournoi.getNom());
			
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +"  |  "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
		
		return tournoiDto;
	}
	
	@SuppressWarnings("null")
	public List<TournoiDto> listeTournoi() {
		
		List<Tournoi> tournois = null;
		List<TournoiDto> tournoisDto = new ArrayList<>();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			tournois = tournoiRepository.list();
			for (Tournoi dto : tournois) {
				TournoiDto tournoiDto = new TournoiDto();
				tournoiDto.setId(dto.getId());
				tournoiDto.setCode(dto.getCode());
				tournoiDto.setNom(dto.getNom());
				
				tournoisDto.add(tournoiDto);
			}
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +"  |  "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
		
		return tournoisDto;
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
