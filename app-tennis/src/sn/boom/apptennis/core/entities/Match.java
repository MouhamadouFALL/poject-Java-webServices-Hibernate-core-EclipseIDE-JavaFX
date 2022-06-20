package sn.boom.apptennis.core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_tennis")
public class Match {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vainqueur")
	private Joueur vainqueur;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_finaliste")
	private Joueur finaliste;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_epreuve")
	private Epreuve epreuve;
	
	@OneToOne(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private Score score;
	
	public Match() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Joueur getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}

	public Joueur getFinaliste() {
		return finaliste;
	}

	public void setFinaliste(Joueur finaliste) {
		this.finaliste = finaliste;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
}
