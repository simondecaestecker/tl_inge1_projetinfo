package userModel;

public class Administrateur extends Utilisateur {
	public Administrateur(String login, String password, String nom, String prenom, int role){
		super(login, password, nom, prenom, role);
	}

	private boolean createUser() {
		return false;
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