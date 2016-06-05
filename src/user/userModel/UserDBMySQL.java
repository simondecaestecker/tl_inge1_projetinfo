package userModel;

import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 * 
 * Cette classe gÃ©re la base de donnÃ©es d'utilisateurs. Elle doit permettre de sauvegarder et charger les utilisateurs et les groupes Ã  partir d'un fichier XML. 
 * La structure du fichier XML devra Ãªtre la mÃªme que celle du fichier userDB.xml.
 * @see <a href="../../userDB.xml">userDB.xml</a> 
 * 
 * @author Jose Mennesson (Mettre Ã  jour)
 * @version 04/2016 (Mettre Ã  jour)
 * 
 */

//TODO Classe Ã  modifier

public class UserDBMySQL {
	private HashMap<String, Utilisateur> utilisateurs;
	private HashMap<Integer, Groupe> groupes;

	private Administrateur root;

	/**
	 * 
	 * Constructeur de UserDB. 
	 * 
	 * !!!!!!!!!!!! PENSEZ Ã€ AJOUTER UN ADMINISTRATEUR (su par exemple) QUI VOUS PERMETTRA DE CHARGER LA BASE DE DONNÃ‰ES AU DEMARRAGE DE L'APPLICATION !!!!!!
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de donnÃ©es.
	 */
	public UserDBMySQL(){
		super();

		this.utilisateurs = new HashMap<String, Utilisateur>();
		this.groupes = new HashMap<Integer, Groupe>();

		this.root = new Administrateur("root", "root", "root", "root", 0);

		Connection con = connectionMySQL();
		loadDB(con);
	}

	public HashMap<String, Utilisateur> getListeUtilisateur(){
		return utilisateurs;
	}

	public HashMap<Integer, Groupe> getListeGroupe(){
		return groupes;
	}

	public Connection connectionMySQL() {
		//Informations de connexion à la base de données
		String protocole = "jdbc:mysql:";
		String adresseDB = "mysql.simondecaestecker.com";
		String portDB = "3306";
		String nameDB = "tl_inge1_projetinfo";
		String conString = protocole +  "//" + adresseDB +  ":" + portDB +  "/" + nameDB;
		String usernameDB = "tl_inge1_projeti"; 
		String passwordDB = "RmXM6dyKf53A69dV";

		Connection con = null;

		try{
			//Connexion à la base de données
			con = DriverManager.getConnection(conString, usernameDB, passwordDB);
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public boolean loadDB(Connection con){
		String sql = "SELECT * FROM groups";

		try{
			Statement statement = con.createStatement();
			ResultSet results = statement.executeQuery(sql);
			while (results.next()) {
				groupes.put(Integer.parseInt(results.getString("id")), root.createGroup(Integer.parseInt(results.getString("id"))));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
}