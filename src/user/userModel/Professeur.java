package userModel;

import java.util.Date;
import java.util.HashMap;

public class Professeur extends Utilisateur{

	HashMap<Integer, Contrainte> contrainte;
	
	public Professeur(String login, String password, String nom, String prenom, int id) {
		super(login, password, nom, prenom, id);
	}
	
	public void addContrainte(Date dateDebut, Date dateFin, String commentaire){
		contrainte.put(super.id, new Contrainte(super.id, dateDebut, dateFin, commentaire));
	}
	
}
