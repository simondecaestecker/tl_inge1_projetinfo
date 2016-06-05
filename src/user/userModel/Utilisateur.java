package userModel;

/**
 * Cette classe permet la création d'un Utilisateur
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class Utilisateur {
	/**
	 * Login de l'utilisateur
	 */
	protected String login;

	/**
	 * Password de l'utilisateur
	 */
	protected String password;

	/**
	 * Nom de l'utilisateur
	 */
	protected String nom;

	/**
	 * Prénom de l'utilisateur
	 */
	protected String prenom;

	/**
	 * ID de l'utilisateur
	 */
	protected int id;

	/**
	 * Constructeur de la classe Utilisateur
	 * 
	 * @param login
	 * 		Login de l'utilisateur
	 * 
	 * @param password
	 * 		Mot de passe de l'utilisateur
	 * 
	 * @param nom
	 * 		Nom de l'utilisateur
	 * 
	 * @param prenom
	 * 		Prénom de l'utilisateur
	 * 
	 * @param id
	 * 		ID de l'utilisateur
	 */
	public Utilisateur(String login, String password, String nom, String prenom, int id){
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}

	/**
	 * Getter de l'ID de l'utilisateur
	 * @return int contenant l'ID de l'utilisateur
	 */
	public int getId(){
		return id;
	}

	/**
	 * Getter du mot de passe de l'utilisateur
	 * @return String contenant le mot de passe de l'utilisateur
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * Getter du prénom et du nom de l'utilisateur
	 * @return String contenant le prénom et le nom de l'utilisateur
	 */
	public String getName(){
		return getFirstName()+" "+getSurname();
	}

	/**
	 * Getter du prénom de l'utilisateur
	 * @return String contenant le prénom de l'utilisateur
	 */
	public String getFirstName(){
		return prenom;
	}

	/**
	 * Getter du nom de l'utilisateur
	 * @return String contenant le nom de l'utilisateur
	 */
	public String getSurname(){
		return nom;
	}

	/**
	 * Getter du login de l'utilisateur
	 * @return String contenant le login de l'utilisateur
	 */
	public String getLogin(){
		return login;
	}

	/**
	 * Getter de la classe de l'utilisateur
	 * @return String contenant la classe de l'utilisateur (Administrateur/Professeur/Etudiant)
	 */
	public String getClassUser(){

		if(id <= 999){
			return "Administrator";
		}
		else if(id > 1000 && id <= 1999){
			return "Teacher";
		}
		else if(id > 2000 && id <= 2999){
			return "Student";
		}
		else{
			return null;
		}
	}
}