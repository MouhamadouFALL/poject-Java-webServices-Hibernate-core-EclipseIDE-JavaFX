package sn.boom.apptennis.core.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.dto.EpreuveFullDto;
import sn.boom.apptennis.core.dto.EpreuveLightDto;
import sn.boom.apptennis.core.dto.JoueurDto;
import sn.boom.apptennis.core.dto.TournoiDto;
import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.apptennis.core.entities.Joueur;
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
	
	public EpreuveFullDto getEpreuveWithTournoi(long id) {
		
		Epreuve epreuve = null;
		EpreuveFullDto dto  = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			epreuve = epreuveRepository.getById(id);
			
			// transformation entity en entity dto
			dto = new EpreuveFullDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
			
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(epreuve.getTournoi().getId());
			tournoiDto.setCode(epreuve.getTournoi().getCode());
			tournoiDto.setNom(epreuve.getTournoi().getNom());
			
			dto.setTournoi(tournoiDto);
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
		
		return dto;
	}
	
	public EpreuveLightDto getEpreuveWithoutTournoi(long id) {
		
		Epreuve epreuve = null;
		EpreuveLightDto dto  = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			epreuve = epreuveRepository.getById(id);
			
			// transformation entity en entity dto
			dto = new EpreuveLightDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (session != null) session.close();
		}
		
		return dto;
	}
	
	public EpreuveFullDto getEpreuveWithTournoiAndListJoueur(long id) {
		
		EpreuveFullDto dto  = null;
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Epreuve epreuve = epreuveRepository.getById(id);
			
			// transformation entity en entity dto
			dto = new EpreuveFullDto();
			dto.setId(epreuve.getId());
			dto.setAnnee(epreuve.getAnnee());
			dto.setTypeEpreuve(epreuve.getTypeEpreuve());
			
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(epreuve.getTournoi().getId());
			tournoiDto.setCode(epreuve.getTournoi().getCode());
			tournoiDto.setNom(epreuve.getTournoi().getNom());
			
			dto.setTournoi(tournoiDto);
			
			dto.setParticipants(new HashSet<>());
			for (Joueur joueur : epreuve.getParticipants()) {
				JoueurDto joueurdto = new JoueurDto();
				joueurdto.setId(joueur.getId());
				joueurdto.setNom(joueur.getNom());
				joueurdto.setPrenom(joueur.getPrenom());
				joueurdto.setSexe(joueur.getSexe());
				
				dto.getParticipants().add(joueurdto);
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
		
		return dto;
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
