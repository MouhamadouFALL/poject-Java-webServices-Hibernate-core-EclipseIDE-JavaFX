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
	
}
