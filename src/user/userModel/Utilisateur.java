package userModel;

public class Utilisateur {
	protected String login;
	protected String password;
	protected String nom;
	protected String prenom;
	protected int id;
	
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