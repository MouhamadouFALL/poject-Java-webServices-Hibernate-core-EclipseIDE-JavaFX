/**
 * 
 */
package sn.boom.apptennis.core.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.dto.EpreuveFullDto;
import sn.boom.apptennis.core.dto.JoueurDto;
import sn.boom.apptennis.core.dto.MatchDto;
import sn.boom.apptennis.core.dto.TournoiDto;
import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.entities.Match;
import sn.boom.apptennis.core.entities.Tournoi;
import sn.boom.apptennis.core.repository.MatchRepositoryImpl;
import sn.boom.apptennis.core.repository.ScoreRepositoryImpl;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */ 
public class MatchService {

	private MatchRepositoryImpl matchRepository;
	private ScoreRepositoryImpl scoreRepository;
	
	public MatchService() {
		matchRepository = new MatchRepositoryImpl();
		scoreRepository = new ScoreRepositoryImpl();
	}
	
	public void saveMatch(MatchDto matchDto) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Match match = new Match();
			
			Joueur vainqueur = new Joueur();
			vainqueur.setId(matchDto.getVainqueur().getId());
			vainqueur.setNom(matchDto.getVainqueur().getNom());
			vainqueur.setPrenom(matchDto.getVainqueur().getPrenom());
			vainqueur.setSexe(matchDto.getVainqueur().getSexe());
			Joueur finaliste = new Joueur();
			finaliste.setId(matchDto.getFinaliste().getId());
			finaliste.setNom(matchDto.getFinaliste().getNom());
			finaliste.setPrenom(matchDto.getFinaliste().getPrenom());
			finaliste.setSexe(matchDto.getFinaliste().getSexe());
			Epreuve epreuve = new Epreuve();
			epreuve.setId(matchDto.getEpreuve().getId());
			epreuve.setAnnee(matchDto.getEpreuve().getAnnee());
			epreuve.setTypeEpreuve(matchDto.getEpreuve().getTypeEpreuve());
			Tournoi tournoi = new Tournoi();
			tournoi.setId(matchDto.getEpreuve().getTournoi().getId());
			tournoi.setCode(matchDto.getEpreuve().getTournoi().getCode());
			tournoi.setNom(matchDto.getEpreuve().getTournoi().getNom());
			epreuve.setTournoi(tournoi);
			
			match.setId(matchDto.getId());
			match.setVainqueur(vainqueur);
			match.setFinaliste(finaliste);
			match.setEpreuve(epreuve);
			
			matchRepository.create(match);
			scoreRepository.create(match.getScore());
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
	}
	
	@SuppressWarnings("null")
	public MatchDto getById(long id) {
		
		Session session = null;
		Transaction tx = null;
		MatchDto dto = null;
		try {
			
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Match match = matchRepository.getById(id);
			
			dto = new MatchDto();
			dto.setId(match.getId());
			
			JoueurDto vainqueur = new JoueurDto();
			vainqueur.setId(match.getVainqueur().getId());
			vainqueur.setNom(match.getVainqueur().getNom());
			vainqueur.setPrenom(match.getVainqueur().getPrenom());
			vainqueur.setSexe(match.getVainqueur().getSexe());
			
			dto.setVainqueur(vainqueur);
			
			JoueurDto finaliste = new JoueurDto();
			finaliste.setId(match.getFinaliste().getId());
			finaliste.setNom(match.getFinaliste().getNom());
			finaliste.setPrenom(match.getFinaliste().getPrenom());
			finaliste.setSexe(match.getFinaliste().getSexe());
			
			dto.setFinaliste(finaliste);
			
			TournoiDto tournoiDto = new TournoiDto();
			tournoiDto.setId(match.getEpreuve().getTournoi().getId());
			tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
			tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
			
			EpreuveFullDto epreuvedto = new EpreuveFullDto();
			epreuvedto.setTournoi(tournoiDto);
			epreuvedto.setId(match.getEpreuve().getId());
			epreuvedto.setAnnee(match.getEpreuve().getAnnee());
			epreuvedto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());
			
			dto.setEpreuve(epreuvedto);
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
		
		return dto;
	}
}
