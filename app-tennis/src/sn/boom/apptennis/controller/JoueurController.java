/**
 * 
 */
package sn.boom.apptennis.controller;

import java.util.Scanner;

import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.services.JoueurService;

/**
 * @author nabyFall
 *
 */
public class JoueurController {
	
	private JoueurService joueurService;
	
	public JoueurController() {
		this.joueurService = new JoueurService();
	}
	
	public void afficheJoueur() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entrer l'identifiant du Joueur :");
		Long identifiant = Long.parseLong(scan.nextLine());
		
		Joueur joueur = joueurService.getJoueur(identifiant);
		
		System.out.println(" Nom : "+ joueur.getNom() +" | Prenom : "+ joueur.getPrenom());
		
		scan.close();
	}
	
	public void saveJoueur() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nom du joueur :");
		String nom = scan.nextLine();
		System.out.println("Prenom du joueur :");
		String prenom = scan.nextLine();
		System.out.println("Sexe du joueur :");
		char sexe = scan.nextLine().charAt(0);
		
		Joueur joueur = new Joueur();
		joueur.setNom(nom);
		joueur.setPrenom(prenom);
		joueur.setSexe(sexe);
		
		joueurService.createJoueur(joueur);
		
		scan.close();
	}
	
	public void updateJoueur() {
		Scanner scan = new Scanner(System.in);
		Joueur joueur = null;
		
		System.out.println("Entrer l'identifiant du Joueur à modifier :");
		Long identifiant = Long.parseLong(scan.nextLine());
		joueur = joueurService.getJoueur(identifiant);
		if (joueur != null) {
			System.out.println("Id : "+ joueur.getId() +" Nom : "+ joueur.getNom() +" | Prenom : "+ joueur.getPrenom());
			
			System.out.println("------------------------- Modifier -------------------------");
			
			System.out.println("Nouveau nom du joueur :");
			String nom = scan.nextLine();
			joueur.setNom(nom);
			joueurService.updateJoueur(identifiant, nom);
			
			System.out.println("Id : "+ joueur.getId() +" Nom : "+ joueur.getNom() +" | Prenom : "+ joueur.getPrenom());
		}
		else {
			System.out.println("Joueur inexistant !");
		}
		
		scan.close();
	}
}
