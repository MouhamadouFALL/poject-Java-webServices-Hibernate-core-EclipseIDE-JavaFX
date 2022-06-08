package sn.boom.apptennis.core.runtime;

import java.util.List;
import java.util.Scanner;

import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.entities.Match;
import sn.boom.apptennis.core.entities.Score;
import sn.boom.apptennis.core.repository.JoueurRepositoryImpl;
import sn.boom.apptennis.core.services.JoueurService;
import sn.boom.apptennis.core.services.MatchService;

public class DemoApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Tester les services
		MatchService matchService = new MatchService();
		
		Match match = new Match();
		Score score = new Score();
		score.setSet1((byte)3);
		score.setSet2((byte)4);
		score.setSet3((byte)6);
		match.setScore(score);
		Joueur vainqueur = new Joueur();
		vainqueur.setId(74L);
		Joueur finaliste = new Joueur();
		finaliste.setId(77l);
		match.setVainqueur(vainqueur);
		match.setFinaliste(finaliste);
		Epreuve epreuve = new Epreuve();
		epreuve.setId(3l);
		match.setEpreuve(epreuve);
		score.setMatch(match);
		
		matchService.saveMatch(match);

		scan.close();
	}

}
