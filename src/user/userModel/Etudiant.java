package userModel;

public class Etudiant extends Utilisateur{
	
	private int idGroupe;
	
	public Etudiant(String login, String password, String nom, String prenom, int id) {
		super(login, password, nom, prenom, id);
		this.idGroupe = 0;
	}
	
	public int getIdGroupe(){
		return idGroupe;
	}

}
