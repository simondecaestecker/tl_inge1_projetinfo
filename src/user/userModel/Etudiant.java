package userModel;

/**
 * Cette classe permet la création d'un Etudiant, dépendant de Utilisateur
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class Etudiant extends Utilisateur{
	/**
	 * ID du groupe auquel appartient l'étudiant
	 */
	private int idGroupe;

	/**
	 * Constructeur de la classe Etudiant
	 * 
	 * @param login
	 * 		Login de l'étudiant
	 * 
	 * @param password
	 * 		Mot de passe de l'étudiant
	 * 
	 * @param nom
	 * 		Nom de l'étudiant
	 * 
	 * @param prenom
	 * 		Prénom de l'étudiant
	 * 
	 * @param id
	 * 		ID de l'étudiant
	 */
	public Etudiant(String login, String password, String nom, String prenom, int id) {
		super(login, password, nom, prenom, id);
		this.idGroupe = 0;
	}

	/**
	 * Getter du groupe auquel appartient l'étudiant
	 * @return int contenant l'ID du groupe auquel appartient l'étudiant
	 */
	public int getIdGroupe(){
		return idGroupe;
	}

	/**
	 * Setter du groupe auquel appartient l'étudiant
	 * @param idGroupe
	 * 		Groupe auquel associer l'étudiant
	 */
	public void setIdGroupe(int idGroupe){
		this.idGroupe = idGroupe;
	}

}