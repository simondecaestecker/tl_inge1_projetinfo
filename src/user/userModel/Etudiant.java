package userModel;

public class Etudiant extends Utilisateur{
	
	private int idGroupe;
	
	public Etudiant(String login, String password, String nom, String prenom, int role) {
		super(login, password, nom, prenom, role);
		this.idGroupe = 0;
	}

}
