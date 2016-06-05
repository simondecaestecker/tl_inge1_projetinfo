package userModel;

import java.util.Date;

/**
 * Cette classe permet la création d'une contraite pour un Administrateur. 
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class Contrainte {
	/**
	 * ID de la contrainte
	 */
	protected int id;

	/**
	 * Date de début de la contrainte
	 */
	protected Date dateDebut;

	/**
	 * Date de fin de la contrainte
	 */
	protected Date dateFin;

	/**
	 * Description de la contrainte
	 */
	protected String commentaire;

	/**
	 * Création d'une nouvelle contrainte
	 * 
	 * @param id
	 * 		ID de la contrainte
	 * 
	 * @param dateDebut
	 * 		Date de début de la contrainte
	 * 
	 * @param dateFin
	 * 		Date de fin de la contrainte
	 * 
	 * @param commentaire	 
	 * 		Description de la contrainte
	 */
	public Contrainte(int id, Date dateDebut, Date dateFin, String commentaire) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.commentaire = commentaire;		
	}

}
