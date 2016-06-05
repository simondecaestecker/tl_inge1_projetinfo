package userModel;

import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 * Cette classe gère la base de données d'utilisateurs. Elle doit permet de sauvegarder et charger les utilisateurs et les groupes à partir d'une base de données MySQL. 
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class UserDBMySQL {
	/**
	 * HashMap contenant le login de l'utilisateur ainsi que l'utilisateur lui-même
	 */
	private HashMap<String, Utilisateur> utilisateurs;

	/**
	 * HashMap contenant l'ID du groupe ainsi que le groupe lui-même
	 */
	private HashMap<Integer, Groupe> groupes;

	/**
	 * Administrateur permettant le chargement de la base de données au démarrage du programme
	 */
	private Administrateur root;

	/**
	 * Constructeur de UserDB
	 */
	public UserDBMySQL(){
		super();

		this.utilisateurs = new HashMap<String, Utilisateur>();
		this.groupes = new HashMap<Integer, Groupe>();

		this.root = new Administrateur("root", "root", "root", "root", 0);

		Connection con = connectionMySQL();
		loadDB(con);
	}

	/**
	 * Getter de la liste des utilisateurs
	 * 
	 * @return HashMap<String, Utilisateur> contenant le login de l'utilisateur ainsi que l'utilisateur lui-même
	 */
	public HashMap<String, Utilisateur> getListeUtilisateur(){
		return utilisateurs;
	}

	/**
	 * Getter de la liste des groupes
	 * 
	 * @return HashMap<Integer, Groupe> contenant l'ID du groupe ainsi que le groupe lui-même
	 */
	public HashMap<Integer, Groupe> getListeGroupe(){
		return groupes;
	}

	/**
	 * Connexion à la base de données MySQL
	 * 
	 * @return Connection permettant l'accès à la base de données
	 */
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

	/**
	 * Charge la base de données
	 * 
	 * @param con
	 * 		Connexion à la base de données
	 * 
	 * @return boolean indiquant la réussite ou non du chargement de la base de données
	 */
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