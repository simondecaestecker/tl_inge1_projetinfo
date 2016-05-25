package userModel;

public class Administrateur extends Utilisateur {
	public Administrateur(String login, String password, String nom, String prenom, int id){
		super(login, password, nom, prenom, id);
	}
	
	public boolean createUser(String login, String password, String nom, String prenom, int id, int type) {
		if (type == 0) {
			//Administrateur
			Administrateur newUser = new Administrateur(login, password, nom, prenom, id);
			return true;
		}
		else if (type == 1) {
			Professeur newUser = new Professeur(login, password, nom, prenom, /*1000 + */id);
			return true;
		}
		else if (type == 2) {
			Etudiant newUser = new Etudiant(login, password, nom, prenom, /*2000 + */id);
			return true;
		}
		else {
			return false;
		}		
	}
	
	private boolean createGroup() {
		return false;
	}
	
	private boolean removeUser() {
		return false;
	}
	
	private boolean removeGroup() {
		return false;
	}
	
	private boolean addUserGroup() {
		return false;
	}
}