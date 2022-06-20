/**
 * 
 */
package sn.boom.apptennis.core.services;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sn.boom.apptennis.core.dto.ScoreFullDto;
import sn.boom.apptennis.core.entities.Match;
import sn.boom.apptennis.core.entities.Score;
import sn.boom.apptennis.core.repository.EpreuveRepositoryImpl;
import sn.boom.apptennis.core.repository.JoueurRepositoryImpl;
import sn.boom.apptennis.core.repository.ScoreRepositoryImpl;
import sn.boom.sgi.hibernate.HibernateManager;

/**
 * @author nabyFall
 *
 */ 
public class ScoreService {

	private ScoreRepositoryImpl scoreRepository;
	private EpreuveRepositoryImpl epreuveRepository;
	private JoueurRepositoryImpl joueurRepository;
	
	public ScoreService() {
		scoreRepository = new ScoreRepositoryImpl();
		epreuveRepository = new EpreuveRepositoryImpl();
		joueurRepository = new JoueurRepositoryImpl();
		
	}
	
	
	public void createScore(ScoreFullDto scoreDto) {
		
		Session session = null;
		Transaction tx = null;
		
		try {	
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			Score score = new Score();
			score.setId(scoreDto.getId());
			
			Match match = new Match();
			match.setId(scoreDto.getMatchDto().getId());
			match.setVainqueur(joueurRepository.getById(scoreDto.getMatchDto().getVainqueur().getId()));
			match.setFinaliste(joueurRepository.getById(scoreDto.getMatchDto().getFinaliste().getId()));
			match.setEpreuve(epreuveRepository.getById(scoreDto.getMatchDto().getEpreuve().getId()));
			match.setScore(score);
			
			score.setMatch(match);
			score.setSet1(scoreDto.getSet1());
			score.setSet2(scoreDto.getSet2());
			score.setSet3(scoreDto.getSet3());
			score.setSet4(scoreDto.getSet4());
			score.setSet5(scoreDto.getSet5());
			
			
			scoreRepository.create(score);; 
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
	}
	
	public void delete(long id) {
		
		Session session = null;
		Transaction tx = null;
		
		try {	
			session = HibernateManager.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			
			scoreRepository.delete(id); 
			
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null) tx.rollback();
			System.err.println("Error : "+ e.getMessage() +" | "+ e.getClass());
		} finally {
			if (session != null) session.close();
		}
	}
	
}
