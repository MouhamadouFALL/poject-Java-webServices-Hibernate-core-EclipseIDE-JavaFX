package sn.boom.apptennis.runtime;

import sn.boom.apptennis.controller.EpreuveController;
import sn.boom.apptennis.controller.MatchController;
import sn.boom.apptennis.controller.ScoreController;
import sn.boom.apptennis.controller.TournoiController;

public class DemoApp {

	public static void main(String[] args) {
		//JoueurController joueurController = new JoueurController();
		//joueurController.afficheJoueur();
		//joueurController.saveJoueur();
		//joueurController.updateJoueur();
		//joueurController.supprimerJoueur();
		//joueurController.listeJoueur();
		
		//TournoiController tournoiController = new TournoiController();
		//tournoiController.afficheTournoi();
		//tournoiController.saveTournoi();
		//tournoiController.updateTournoi();
		//tournoiController.supprimerTournoi();
		//tournoiController.listeTournois();
		
		//EpreuveController epreuveController = new EpreuveController();
		//epreuveController.afficheEpreuve();
		//epreuveController.afficheEpreuveTournoi();
		//epreuveController.saveEpreuve();
		//epreuveController.afficheEpreuveListJoueur();
		
		//MatchController matchController = new MatchController();
		//matchController.afficheMatch();
		//matchController.tapisVert();
		//matchController.createMatch();
		
		ScoreController scoreController = new ScoreController();
		scoreController.supprimerScore();
	}

}
