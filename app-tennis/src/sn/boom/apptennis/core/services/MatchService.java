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
			scoreRepository.create(match.getScore());
			matchRepository.create(match);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
