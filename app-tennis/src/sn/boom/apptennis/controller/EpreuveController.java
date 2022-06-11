/**
 * 
 */
package sn.boom.apptennis.controller;

import java.util.Scanner;

import sn.boom.apptennis.core.dto.EpreuveFullDto;
import sn.boom.apptennis.core.dto.EpreuveLightDto;
import sn.boom.apptennis.core.entities.Epreuve;
import sn.boom.apptennis.core.services.EpreuveService;

/**
 * @author nabyFall
 *
 */
public class EpreuveController {

	private EpreuveService epreuveService;
	
	public EpreuveController() {
		this.epreuveService = new EpreuveService();
	}
	
	public void afficheEpreuveTournoi() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant de l'epreuve :");
		Long identifiant = Long.parseLong(scan.nextLine());
		EpreuveFullDto epreuveFullDto = epreuveService.getEpreuveWithTournoi(identifiant);
		
		System.out.println(" Annee : "+ epreuveFullDto.getAnnee() +" | Type : "+ epreuveFullDto.getTypeEpreuve() +"  |  Nom Tournoi : "+ epreuveFullDto.getTournoi().getNom());
		
		scan.close();
	}
	
	public void afficheEpreuve() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant de l'epreuve :");
		Long identifiant = Long.parseLong(scan.nextLine());
		EpreuveLightDto epreuveLightDto = epreuveService.getEpreuveWithoutTournoi(identifiant);
		
		System.out.println(" Annee : "+ epreuveLightDto.getAnnee() +" | Type : "+ epreuveLightDto.getTypeEpreuve());
		
		scan.close();
	}
	
	public void saveEpreuve() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Annee de l'epreuve :");
		short annee = Short.parseShort(scan.nextLine());
		System.out.println("Type epreuve :");
		char type = scan.nextLine().charAt(0);
		
		Epreuve epreuve = new Epreuve();
		epreuve.setAnnee(annee);
		epreuve.setTypeEpreuve(type);
		
		epreuveService.createEpreuve(epreuve);
		
		scan.close();
	}
	
	public void supprimerEpreuve() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant de l'epreuve à supprimer :");
		Long identifiant = Long.parseLong(scan.nextLine());
		
		epreuveService.deleteEpreuve(identifiant);
		
		System.out.println("Id : "+ identifiant +" bien supprimer ");
		
		scan.close();
	}
}
