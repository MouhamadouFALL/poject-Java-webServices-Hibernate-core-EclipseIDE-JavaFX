package sn.boom.apptennis.core.runtime;

import java.util.List;
import java.util.Scanner;

import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.repository.JoueurRepositoryImpl;
import sn.boom.apptennis.core.services.JoueurService;

public class DemoApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// test the method create : Test OK
		System.out.println("Entrer le nom du Joueur : ");
		String nom = scan.nextLine();
		System.out.println("Entrer le prenom du Joueur : ");
		String prenom = scan.nextLine();
		System.out.println("Entrer le genre du Joueur : ");
		Character sexe = scan.nextLine().charAt(0);
		
		Joueur joueur = new Joueur();
		joueur.setNom(nom);
		joueur.setPrenom(prenom);
		joueur.setSexe(sexe);
		
		try {
			JoueurRepositoryImpl repo = new JoueurRepositoryImpl();
			repo.create(joueur);
			System.out.println("success to save player of id : "+ joueur.getId());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		// Test method getById() : Test OK
		/*System.out.println("---------------------- Test Method getById() ----------------------");
		try {
			JoueurRepositoryImpl repo = new JoueurRepositoryImpl();
			Joueur joueur = repo.getById(122l);
			System.out.println("*** Nom : "+ joueur.getNom() +" | Prenom : "+ joueur.getPrenom() +" | Sexe : "+ joueur.getSexe() +". ***");
		
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
		}*/
		
		// Test method list() : Test OK
		/*System.out.println("---------------------- Test Method list ----------------------");
		try {
			JoueurRepositoryImpl repo = new JoueurRepositoryImpl();
			List<Joueur> joueurs = repo.list();
			if (joueurs != null) {
				for (Joueur joueur : joueurs) {
					System.out.println("*** Nom : "+ joueur.getNom() +" | Prenom : "+ joueur.getPrenom() +" | Sexe : "+ joueur.getSexe() +". ***");
				}
			}
		
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
		}*/
		
		// test the method update : Test OK
		/*try {
			
				
			JoueurRepositoryImpl repo = new JoueurRepositoryImpl();
			//System.out.println("*** Nom : "+ joueur.getNom() +" | Prenom : "+ joueur.getPrenom() +" | Sexe : "+ joueur.getSexe() +". ***");
			
			System.out.println("Entrer l'identifiant du joueur à modifier : ");
			Long identifiant = Long.parseLong(scan.nextLine());
			System.out.println("Entrer le nouveau nom du Joueur : ");
			String nom = scan.nextLine();
			System.out.println("Entrer le nouveau prenom du Joueur : ");
			String prenom = scan.nextLine();
			System.out.println("Entrer le nouveau genre du Joueur : ");
			Character sexe = scan.nextLine().charAt(0);
			
			Joueur joueur = new Joueur();
			joueur.setId(identifiant);
			joueur.setNom(nom);
			joueur.setPrenom(prenom);
			joueur.setSexe(sexe);
			
			repo.update(joueur);
			
			System.out.println("success to update player !");
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
		}*/
		
		// test the method delete : Test OK
		/*try {
			JoueurRepositoryImpl repo = new JoueurRepositoryImpl();
			
			System.out.println("Entrer l'identifiant du joueur à supprimer : ");
			Long identifiant = Long.parseLong(scan.nextLine());
			
			repo.delete(identifiant);
			
			System.out.println("delete player success !");
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
		}*/


		scan.close();

	}

}
