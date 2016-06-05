package userModel;

/**
 * Cette classe permet la création d'un Administrateur, dépendant de Utilisateur. 
 * Elle contient les fonctions permettant de créer un utilisateur et un groupe.
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class Administrateur extends Utilisateur {

	/**
	 * Constructeur d'administrateur en fournissant ses informations
	 * 
	 * @param login
	 * 		Login de l'administrateur
	 * 
	 * @param password
	 * 		Password de l'administrateur
	 * 
	 * @param nom
	 * 		Nom de l'administrateur
	 * 
	 * @param prenom
	 * 		Prénom de l'administrateur
	 * 
	 * @param id
	 * 		ID de l'administrateur
	 */
	public Administrateur(String login, String password, String nom, String prenom, int id){
		super(login, password, nom, prenom, id);
	}

	/**
	 * Fonction permettant de créer un utilisateur
	 * 
	 * @param login
	 * 		Login du nouvel utilisateur
	 * 
	 * @param password
	 * 		Password du nouvel utilisateur
	 * 
	 * @param nom
	 * 		Nom du nouvel utilisateur
	 * 
	 * @param prenom
	 * 		Prénom du nouvel utilisateur
	 * 
	 * @param id
	 * 		ID du nouvel utilisateur
	 * 
	 * @param type
	 * 		Type d'utilisateur :
	 * 			0 pour un Administrateur
	 * 			1 pour un Professeur
	 * 			2 pour un Etudiant
	 * 
	 * @return Utilisateur créé
	 */
	public Utilisateur createUser(String login, String password, String nom, String prenom, int id, int type) {
		if (type == 0) {
			return new Administrateur(login, password, nom, prenom, id);
		}
		else if (type == 1) {
			return new Professeur(login, password, nom, prenom, id);
		}
		else if (type == 2) {
			return new Etudiant(login, password, nom, prenom, id);
		}
		else {
			return null;
		}		
	}

	/**
	 * Fonction permettant de créer un groupe
	 * 
	 * @param id
	 * 		ID du nouveau groupe
	 * 
	 * @return Groupe créé
	 */
	public Groupe createGroup(int id) {
		Groupe groupe = new Groupe(id);
		return groupe;
	}
}