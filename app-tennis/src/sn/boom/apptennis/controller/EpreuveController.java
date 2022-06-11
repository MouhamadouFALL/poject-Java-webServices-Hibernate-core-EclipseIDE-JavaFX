/**
 * 
 */
package sn.boom.apptennis.controller;

import java.util.Scanner;

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
	
	public void afficheDerniereEpreuve() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant de l'epreuve :");
		Long identifiant = Long.parseLong(scan.nextLine());
		Epreuve epreuve = epreuveService.getEpreuveWithTournoi(identifiant);
		
		System.out.println(" Annee : "+ epreuve.getAnnee() +" | Type : "+ epreuve.getTypeEpreuve() +"  |  Nom Tournoi : "+ epreuve.getTournoi().getNom());
		
		scan.close();
	}
	
	public void afficheEpreuve() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant de l'epreuve :");
		Long identifiant = Long.parseLong(scan.nextLine());
		Epreuve epreuve = epreuveService.getEpreuveWithoutTournoi(identifiant);
		
		System.out.println(" Annee : "+ epreuve.getAnnee() +" | Type : "+ epreuve.getTypeEpreuve());
		
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
