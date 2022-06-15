/**
 * 
 */
package sn.boom.apptennis.core.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author nabyFall
 *
 */
@Entity
public class Epreuve {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Short annee;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tournoi")
	private Tournoi tournoi;
	@Column(name = "type_epreuve")
	private Character typeEpreuve;
	
	@ManyToMany
	@JoinTable(
			name = "participants", 
			joinColumns = {@JoinColumn(name="id_epreuve")}, 
			inverseJoinColumns = {@JoinColumn(name="id_joueur")
						})
	private Set<Joueur> participants;
	
	public Epreuve() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Short getAnnee() {
		return annee;
	}
	public void setAnnee(Short annee) {
		this.annee = annee;
	}
	public Tournoi getTournoi() {
		return tournoi;
	}
	public void setTournoi(Tournoi tournoi) {
		this.tournoi = tournoi;
	}
	public Character getTypeEpreuve() {
		return typeEpreuve;
	}
	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}

	public Set<Joueur> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Joueur> participants) {
		this.participants = participants;
	}
}
