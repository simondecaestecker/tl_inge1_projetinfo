package userModel;

public class Utilisateur {
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private int id;
	
	public Utilisateur(String login, String password, String nom, String prenom, int id){
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	

}