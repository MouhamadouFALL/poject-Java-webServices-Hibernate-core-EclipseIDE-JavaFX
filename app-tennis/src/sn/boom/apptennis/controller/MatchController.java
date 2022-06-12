package sn.boom.apptennis.controller;

import java.util.Scanner;

import sn.boom.apptennis.core.dto.MatchDto;
import sn.boom.apptennis.core.services.MatchService;

public class MatchController {
	
	private MatchService matchService;
	
	public MatchController() {
		this.matchService = new MatchService();
	}
	
	public void afficheMatch() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant du Match :");
		Long identifiant = Long.parseLong(scan.nextLine());
		MatchDto matchDto = matchService.getById(identifiant);
		
		System.out.println(" Annee : "+ matchDto.getEpreuve().getAnnee() +" | Nom : "+ matchDto.getEpreuve().getTournoi().getNom()
				+"  |  Vainqueur : "+ matchDto.getVainqueur().getNom() +"  |  Finaliste : "+ matchDto.getFinaliste().getNom());
		
		scan.close();
	}

}
