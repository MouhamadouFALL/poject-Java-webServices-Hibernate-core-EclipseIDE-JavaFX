package sn.boom.apptennis.controller;

import java.util.Scanner;

import sn.boom.apptennis.core.services.ScoreService;

public class ScoreController {
	
	private ScoreService scoreService;
	
	public ScoreController() {
		scoreService = new ScoreService();
	}

	public void supprimerScore() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant du score à supprimer :");
		Long identifiant = Long.parseLong(scan.nextLine());
		
		scoreService.delete(identifiant);
		
		System.out.println("Id : "+ identifiant +" bien supprimer ");
		
		scan.close();
	}
}
