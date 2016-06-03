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
	
	public String getPassword(){
		return password;
	}
	
	public String getName(){
		return getFirstName()+" "+getSurname();
	}
	
	public String getFirstName(){
		return prenom;
	}
	
	public String getSurname(){
		return nom;
	}
	
	public String getLogin(){
		return login;
	}
	
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