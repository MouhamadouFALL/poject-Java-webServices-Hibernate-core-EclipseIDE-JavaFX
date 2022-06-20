/**
 * 
 */
package sn.boom.apptennis.core.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

/**
 * @author nabyFall
 *
 */

@Entity
@Table(name = "joueur")
@NamedQuery(query = "Select j From Joueur j where j.sexe=?0", name = "getBySexe")
@NamedQuery(query = "From Joueur where nom=?0", name = "get_by_name")
public class Joueur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private Character sexe;
	
	public Joueur() {}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(!(obj instanceof Joueur)) return false;
		Joueur joueur = (Joueur) obj;
		return Objects.equals(id, joueur.id) &&
				Objects.equals(nom, joueur.nom) &&
				Objects.equals(prenom, joueur.prenom) &&
				Objects.equals(sexe, joueur.sexe);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, nom, prenom, sexe);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Character getSexe() {
		return sexe;
	}

	public void setSexe(Character sexe) {
		this.sexe = sexe;
	}

}
