package sn.boom.apptennis.runtime;

import sn.boom.apptennis.controller.EpreuveController;

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
		
		EpreuveController epreuveController = new EpreuveController();
		epreuveController.afficheEpreuve();
		//epreuveController.afficheEpreuveTournoi();
		//epreuveController.saveEpreuve();
	}

}
