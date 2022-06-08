/**
 * 
 */
package sn.boom.apptennis.core.services;

import sn.boom.apptennis.core.entities.Match;
import sn.boom.apptennis.core.repository.MatchRepositoryImpl;
import sn.boom.apptennis.core.repository.ScoreRepositoryImpl;

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
	
	public void saveMatch(Match match) {
		try {
			matchRepository.create(match);
			scoreRepository.create(match.getScore());
			
		} catch (Exception e) {
			System.err.println(e.getMessage() +" | "+ e.getClass());
		}
	}

	private String printStackTrace(Exception e) {
		// TODO Auto-generated method stub
		return null;
	}
}
