/**
 * 
 */
package sn.boom.apptennis.core.dto;

/**
 * @author nabyFall
 *
 */
public class JoueurDto {

	private Long id;
	private String nom;
	private String prenom;
	private Character sexe;
	
	public JoueurDto() {}
	

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
