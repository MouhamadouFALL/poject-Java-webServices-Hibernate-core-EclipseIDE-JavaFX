/**
 * 
 */
package sn.boom.apptennis.controller;

import java.util.List;
import java.util.Scanner;

import sn.boom.apptennis.core.entities.Tournoi;
import sn.boom.apptennis.core.services.TournoiService;

/**
 * @author nabyFall
 *
 */
public class TournoiController {
	
	private TournoiService tournoiService;
	
	public TournoiController() {
		this.tournoiService = new TournoiService();
	}
	
	public void afficheTournoi() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant du Tournoi :");
		Long identifiant = Long.parseLong(scan.nextLine());
		Tournoi tournoi = tournoiService.getTournoi(identifiant);
		
		System.out.println(" Code : "+ tournoi.getCode() +" | Nom : "+ tournoi.getNom());
		
		scan.close();
	}
	
	public void saveTournoi() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nom du Tournoi :");
		String nom = scan.nextLine();
		System.out.println("code du Tournoi :");
		String code = scan.nextLine();
		
		Tournoi tournoi = new Tournoi();
		tournoi.setNom(nom);
		tournoi.setCode(code);
		
		tournoiService.saveTournoi(tournoi);
		
		scan.close();
	}
	
	public void updateTournoi() {
		Scanner scan = new Scanner(System.in);
			
		System.out.println("------------------------- Modifier -------------------------");
		
		System.out.println("Entrer l'identifiant du Tournoi :");
		Long id = Long.parseLong(scan.nextLine());
		System.out.println("Nouveau nom du tournoi :");
		String nom = scan.nextLine();
		System.out.println("Nouveau code du tournoi :");
		String code = scan.nextLine();
		
		Tournoi tournoi = new Tournoi();
		tournoi.setId(id);
		tournoi.setCode(code);
		tournoi.setNom(nom);
		
		tournoiService.updateTournoi(tournoi);
		
		scan.close();
	}
	
	public void supprimerTournoi() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant du tournoi à supprimer :");
		Long identifiant = Long.parseLong(scan.nextLine());
		
		tournoiService.delete(identifiant);
		
		System.out.println("Id : "+ identifiant +" bien supprimer ");
		
		scan.close();
	}
	
	public void listeTournois() {
		List<Tournoi> tournois = tournoiService.listeTournoi();
		System.out.println("-------------------------------- Liste des Joueurs ---------------------------------");
		for(Tournoi tournoi : tournois) {
			System.out.println("Id : "+ tournoi.getId() +" | Nom : "+ tournoi.getNom() +" | Code : "+ tournoi.getCode());
		}
	}

}
