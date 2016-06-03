package userModel;

public class Administrateur extends Utilisateur {
	public Administrateur(String login, String password, String nom, String prenom, int id){
		super(login, password, nom, prenom, id);
	}
	
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
	
	public Groupe createGroup(int id) {
		Groupe groupe = new Groupe(id);
		return groupe;
	}
}