package sn.boom.apptennis.controller;

import java.util.Scanner;

import sn.boom.apptennis.core.dto.EpreuveFullDto;
import sn.boom.apptennis.core.dto.JoueurDto;
import sn.boom.apptennis.core.dto.MatchDto;
import sn.boom.apptennis.core.dto.ScoreFullDto;
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
		
		System.out.println("--------------------------- Les sets du score sont --------------------------");
		System.out.println("set 1 :"+ matchDto.getScore().getSet1());
		System.out.println("set 2 :"+ matchDto.getScore().getSet2());
		if (matchDto.getScore().getSet3() != null)
			System.out.println("set 3 :"+ matchDto.getScore().getSet3());
		if (matchDto.getScore().getSet4() != null)
			System.out.println("set 4 :"+ matchDto.getScore().getSet4());
		if (matchDto.getScore().getSet5() != null)
			System.out.println("set 5 :"+ matchDto.getScore().getSet5());
		
		scan.close();
	}
	
	public void tapisVert() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant du Match à reviser :");
		Long identifiant = Long.parseLong(scan.nextLine());
		matchService.tapisVert(identifiant);
		
		scan.close();
	}
	
	public void createMatch() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant de l'epreuve :");
		Long epreuveId = Long.parseLong(scan.nextLine());
		System.out.println("Entrer l'identifiant du vainqueur :");
		Long vainqueurId = Long.parseLong(scan.nextLine());
		System.out.println("Entrer l'identifiant du Finaliste :");
		Long finalisteId = Long.parseLong(scan.nextLine());
		
		MatchDto matchDto = new MatchDto();
		matchDto.setEpreuve(new EpreuveFullDto());
		matchDto.getEpreuve().setId(epreuveId);
		matchDto.setFinaliste(new JoueurDto());
		matchDto.getFinaliste().setId(finalisteId);
		matchDto.setVainqueur(new JoueurDto());
		matchDto.getVainqueur().setId(vainqueurId);
		
		System.out.println("Entrer la valeur du set 1 :");
		byte set1 = Byte.parseByte(scan.nextLine());
		System.out.println("Entrer la valeur du set 2 :");
		byte set2 = Byte.parseByte(scan.nextLine());
		System.out.println("Entrer la valeur du set 3 :");
		byte set3 = Byte.parseByte(scan.nextLine());
		System.out.println("Entrer la valeur du set 4 :");
		byte set4 = Byte.parseByte(scan.nextLine());
		System.out.println("Entrer la valeur du set 5 :");
		byte set5 = Byte.parseByte(scan.nextLine());
		
		ScoreFullDto scoreFullDto = new ScoreFullDto();
		scoreFullDto.setSet1(set1);
		scoreFullDto.setSet2(set2);
		scoreFullDto.setSet3(set3);
		scoreFullDto.setSet4(set4);
		scoreFullDto.setSet5(set5);
		
		matchDto.setScore(scoreFullDto);
		scoreFullDto.setMatchDto(matchDto);
		
		matchService.saveMatch(matchDto);
		
		scan.close();
	}
	
	public void deleteMatch() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant du Match à supprimer :");
		Long identifiant = Long.parseLong(scan.nextLine());
		matchService.deleteMatch(identifiant);;
		
		scan.close();
	}

}
