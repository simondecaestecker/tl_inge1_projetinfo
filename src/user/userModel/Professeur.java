package userModel;

import java.util.Date;
import java.util.HashMap;

/**
 * Cette classe permet la création d'un Professeur, dépendant de Utilisateur. 
 * Elle contient un attribut correspondant aux contraintes du professeur.
 * Elle contient la fonction permettant d'ajouter les contraintes du professeur.
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class Professeur extends Utilisateur{

	/**
	 * HashMap contenant les contraintes du professeur 
	 */
	HashMap<Integer, Contrainte> contrainte;

	/**
	 * Constructeur de professeur en fournissant ses informations
	 * 
	 * @param login
	 * 		Login du professeur
	 * 
	 * @param password
	 * 		Password du professeur
	 * 
	 * @param nom
	 * 		Nom du professeur
	 * 
	 * @param prenom
	 * 		Prénom du professeur
	 * 
	 * @param id
	 * 		ID du professeur
	 */
	public Professeur(String login, String password, String nom, String prenom, int id) {
		super(login, password, nom, prenom, id);
	}

	/**
	 * Fonction permettant d'ajouter une contrainte
	 * Cette fonction créée une contrainte qui est liée au professeur.
	 * 
	 * @param dateDebut
	 * 				Date de début de la contrainte
	 * 
	 * @param dateFin
	 * 				Date de fin de la contrainte
	 * 
	 * @param commentaire
	 * 				Description de la contrainte
	 */
	public void addContrainte(Date dateDebut, Date dateFin, String commentaire){
		contrainte.put(super.id, new Contrainte(super.id, dateDebut, dateFin, commentaire));
	}

}
