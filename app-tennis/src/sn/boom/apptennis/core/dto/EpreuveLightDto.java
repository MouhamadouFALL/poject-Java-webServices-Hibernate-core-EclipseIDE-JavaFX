package sn.boom.apptennis.core.dto;

public class EpreuveLightDto {

	private Long id;
	private Short annee;
	private Character typeEpreuve;
	
	public EpreuveLightDto() {}
	
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

	public Character getTypeEpreuve() {
		return typeEpreuve;
	}
	public void setTypeEpreuve(Character typeEpreuve) {
		this.typeEpreuve = typeEpreuve;
	}
}
