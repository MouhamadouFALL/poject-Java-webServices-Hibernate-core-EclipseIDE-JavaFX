package sn.boom.apptennis.core.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import sn.boom.apptennis.core.dto.EpreuveFullDto;
import sn.boom.apptennis.core.dto.EpreuveLightDto;
import sn.boom.apptennis.core.dto.JoueurDto;
import sn.boom.apptennis.core.dto.TournoiDto;
import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.entities.Tournoi;
import sn.boom.apptennis.core.repository.EpreuveRepositoryImpl;
import sn.boom.sgi.jpa.EntityManagerHolder;

public class EpreuveService {
	
	private EpreuveRepositoryImpl epreuveRepository;
	
	public EpreuveService() {
		this.epreuveRepository = new EpreuveRepositoryImpl();
	}

	public void createEpreuve(Epreuve epreuve) {
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			epreuveRepository.create(epreuve);
			
			tx.commit();
			
			System.out.println("Joueur "+ epreuve.getId() +" a été bien sauvegarder!");
		} 
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (em != null) em.close();
		}
	}
	
	public EpreuveFullDto getEpreuveWithTournoi(long id) {
		
		Epreuve epreuve = null;
		EpreuveFullDto dto  = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
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
			if (em != null) em.close();
		}
		
		return dto;
	}
	
	public EpreuveLightDto getEpreuveWithoutTournoi(long id) {
		
		Epreuve epreuve = null;
		EpreuveLightDto dto  = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
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
			if (em != null) em.close();
		}
		
		return dto;
	}
	
	public EpreuveFullDto getEpreuveWithTournoiAndListJoueur(long id) {
		
		EpreuveFullDto dto  = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
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
			if (em != null) em.close();
		}
		
		return dto;
	}
	
	public void updateEpreuve(EpreuveFullDto obj) {
		
		EntityManager em = null;
		EntityTransaction tx = null;
		try {
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Epreuve epreuve = epreuveRepository.getById(obj.getId());
			if (epreuve != null) {
				epreuve.setAnnee(obj.getAnnee());
				epreuve.setTypeEpreuve(obj.getTypeEpreuve());
				Tournoi tournoi = new Tournoi();
				tournoi.setId(obj.getTournoi().getId());
				tournoi.setCode(obj.getTournoi().getCode());
				tournoi.setNom(obj.getTournoi().getCode());
				epreuve.setTournoi(tournoi);
				
			}
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (em != null) em.close();
		}
	}
	
	public void deleteEpreuve(long id) {
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			epreuveRepository.delete(id);
			
			tx.commit();
		}
		catch (Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if (em != null) em.close();
		}
	}
	
	public List<EpreuveFullDto> listeEpreuve(){
		
		List<EpreuveFullDto> epreuvesDto = null;
		EntityManager em = null;
		EntityTransaction tx= null;
		try {
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			List<Epreuve> epreuves = epreuveRepository.list();
			for (Epreuve e : epreuves) {
				final EpreuveFullDto eDto = new EpreuveFullDto();
				eDto.setId(e.getId());
				eDto.setAnnee(e.getAnnee());
				eDto.setTypeEpreuve(e.getTypeEpreuve());
				
				eDto.setParticipants(new HashSet<>());
				for (Joueur joueur : e.getParticipants()) {
					JoueurDto joueurdto = new JoueurDto();
					joueurdto.setId(joueur.getId());
					joueurdto.setNom(joueur.getNom());
					joueurdto.setPrenom(joueur.getPrenom());
					joueurdto.setSexe(joueur.getSexe());
					
					eDto.getParticipants().add(joueurdto);
				}
				
				TournoiDto tournoiDto = new TournoiDto();
				tournoiDto.setId(e.getTournoi().getId());
				tournoiDto.setCode(e.getTournoi().getCode());
				tournoiDto.setNom(e.getTournoi().getCode());
				
				eDto.setTournoi(tournoiDto);
			}
			
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if(em != null) em.close();
		}
		
		return epreuvesDto;
	}
	
	public List<EpreuveFullDto> listeEpreuve(String code){
		
		List<EpreuveFullDto> epreuvesDto = new ArrayList<>();
		EntityManager em = null;
		EntityTransaction tx= null;
		try {
			em = EntityManagerHolder.getCurrentEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			List<Epreuve> epreuves = epreuveRepository.list(code);
			for (Epreuve epreuve : epreuves) {
				final EpreuveFullDto epreuveDto = new EpreuveFullDto();
				epreuveDto.setId(epreuve.getId());
				epreuveDto.setAnnee(epreuve.getAnnee());
				epreuveDto.setTypeEpreuve(epreuve.getTypeEpreuve());
				TournoiDto tournoiDto = new TournoiDto();
				tournoiDto.setId(epreuve.getTournoi().getId());
				tournoiDto.setNom(epreuve.getTournoi().getNom());
				tournoiDto.setCode(epreuve.getTournoi().getCode());
				epreuveDto.setTournoi(tournoiDto);
				
				epreuvesDto.add(epreuveDto);
			}
			
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println(e.getMessage());
		}
		finally {
			if(em != null) em.close();
		}
		
		return epreuvesDto;
	}
}
