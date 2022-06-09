package sn.boom.apptennis.core.services;

import sn.boom.apptennis.core.entities.Joueur;
import sn.boom.apptennis.core.repository.JoueurRepositoryImpl;

public class JoueurService {
	
	private JoueurRepositoryImpl joueurRepository;
	
	public JoueurService() {
		this.joueurRepository = new JoueurRepositoryImpl();
	}

	public void createJouuer(Joueur joueur) {
		
		try {
			joueurRepository.create(joueur);
		} 
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public Joueur getJoueur(long id) {
		Joueur joueur = null;
		try {
			 joueur = joueurRepository.getById(id);
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return joueur;
	}
}
