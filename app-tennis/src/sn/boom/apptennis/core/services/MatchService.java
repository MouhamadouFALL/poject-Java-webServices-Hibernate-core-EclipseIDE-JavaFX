/**
 * 
 */
package sn.boom.apptennis.core.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.dto.EpreuveFullDto;
import sn.boom.apptennis.core.dto.JoueurDto;
import sn.boom.apptennis.core.dto.MatchDto;
import sn.boom.apptennis.core.dto.ScoreFullDto;
import sn.boom.apptennis.core.dto.TournoiDto;
import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.entities.Match;
import sn.boom.apptennis.core.entities.Score;
import sn.boom.apptennis.core.entities.Tournoi;
import sn.boom.apptennis.core.repository.EpreuveRepository;
import sn.boom.apptennis.core.repository.JoueurRepositoryImpl;
import sn.boom.apptennis.core.repository.MatchRepositoryImpl;
import sn.boom.apptennis.core.repository.ScoreRepositoryImpl;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */ 
public class MatchService {

	private MatchRepositoryImpl matchRepository;
	private EpreuveRepository  epreuveRepository;
	private JoueurRepositoryImpl joueurRepository;
	
	public MatchService() {
		matchRepository = new MatchRepositoryImpl();
		epreuveRepository = new EpreuveRepository();
		joueurRepository = new JoueurRepositoryImpl();
	}
	
	public void saveMatch(MatchDto matchDto) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Match match = new Match();
			
			match.setEpreuve(epreuveRepository.getById(matchDto.getEpreuve().getId()));
			match.setVainqueur(joueurRepository.getById(matchDto.getVainqueur().getId()));
			match.setFinaliste(joueurRepository.getById(matchDto.getFinaliste().getId()));
			
			Score score = new Score();
			score.setMatch(match);
			match.setScore(score);
			
			score.setSet1(matchDto.getScore().getSet1());
			score.setSet2(matchDto.getScore().getSet2());
			score.setSet3(matchDto.getScore().getSet3());
			score.setSet4(matchDto.getScore().getSet4());
			score.setSet5(matchDto.getScore().getSet5());
			
			matchRepository.create(match);
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
	}
	
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
			
			ScoreFullDto scoreFullDto = new ScoreFullDto();
			scoreFullDto.setId(match.getScore().getId());
			scoreFullDto.setSet1(match.getScore().getSet1());
			scoreFullDto.setSet2(match.getScore().getSet2());
			scoreFullDto.setSet3(match.getScore().getSet3());
			scoreFullDto.setSet4(match.getScore().getSet4());
			scoreFullDto.setSet5(match.getScore().getSet5());
			
			dto.setScore(scoreFullDto);
			scoreFullDto.setMatchDto(dto);
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
		
		return dto;
	}
	
	public void tapisVert(long id) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Match match = matchRepository.getById(id);
			
			// le finaliste devient le vainqueur 
			Joueur ancienVainqueur = match.getVainqueur();
			match.setVainqueur(match.getFinaliste());
			match.setFinaliste(ancienVainqueur);
			
			// Annuler les sets du match
			match.getScore().setSet1((byte)0);
			match.getScore().setSet2((byte)0);
			match.getScore().setSet3((byte)0);
			match.getScore().setSet4((byte)0);
			match.getScore().setSet5((byte)0);
			
			tx.commit();
		}
		catch(Exception e) {
			if (tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		}
		finally {
			if (session != null) session.close();
		}
	}
	
	
	
	
}
