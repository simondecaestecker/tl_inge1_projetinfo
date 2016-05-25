package userModel;

import java.util.Date;

public class Contrainte {
	
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private String commentaire;
	
	public Contrainte(int id, Date dateDebut, Date dateFin, String commentaire) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.commentaire = commentaire;		
	}

}
