package userModel;

public class Utilisateur {
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private int role;
	
	public Utilisateur(String login, String password, String nom, String prenom, int role){
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}
	
	

}