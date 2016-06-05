package userController;

import java.util.Iterator;
import java.util.Map.Entry;

import userModel.Groupe;
import userModel.UserDB;
import userModel.Etudiant;
import userModel.Administrateur;
import userModel.Utilisateur;

/**
 * Cette classe est le contrôleur d'utilisateurs. 
 * Elle contient un attribut correspondant à la base de données utilisateurs.
 * Elle contient toutes les fonctions de l'interface IUserController.
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class UserController implements IUserController
{
	
	/**
	 * Contient une instance de base de données d'utilisateurs
	 * 
	 */
	private UserDB userDB=null;
	
	
	/**
	 * Constructeur de controleur d'utilisateurs créant la base de données d'utilisateurs
	 * 
	 * @param userfile
	 * 		Fichier XML contenant la base de données d'utilisateurs
	 */
	public UserController(String userfile){
		UserDB userDB=new UserDB(userfile);
		this.setUserDB(userDB);
	}

	/**
	 * Fonction permettant de récupérer le nom et le prénom de l'utilisateur à partir de son login
	 * @param userLogin
	 * 		Le login de l'utilisateur
	 * @return
	 * 		Une chaine de caractère contenant le prénom et le nom de l'utilisateur
	 */
	@Override
	public String getUserName(String userLogin) {
		if(userDB.getListeUtilisateur().containsKey(userLogin)){
			return userDB.getListeUtilisateur().get(userLogin).getName();
		}
		else{
			return null;
		}
	}

	/**
	 * Fonction permettant de récupérer la classe de l'utilisateur à partir de son login et de son mot de passe. 
	 * Elle renvoie :
	 * 			- "" si l'utilisateur n'est pas reconnu (vérification du login et mdp).
	 * 			- "Student" si l'utilisateur est un étudiant 
	 *			- "Teacher" si l'utilisateur est un professeur
	 *			- "Administrator" si l'utilisateur est un administrateur 
	 * @param userLogin
	 * 		Le login de l'utilisateur
	 * @param userPwd
	 * 		Le mot de passe de l'utilisateur
	 * @return
	 * 		Une chaine de caractère contenant la classe de l'utilisateur
	 */
	@Override
	public String getUserClass(String userLogin, String userPwd) {
		if(userDB.getListeUtilisateur().containsKey(userLogin)){
			if(userPwd != userDB.getListeUtilisateur().get(userLogin).getPassword()){
				return userDB.getListeUtilisateur().get(userLogin).getClassUser();
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
	}

	/**
	 * Fonction permettant de récupérer l'identifiant de groupe de l'étudiant à partir de son login. Elle renvoie l'identifiant du groupe de l'étudiant s'il existe et -1 sinon.
	 * @param studentLogin
	 * 		Le login de l'étudiant
	 * @return
	 * 		L'identifiant de groupe de l'étudiant 
	 */
	@Override
	public int getStudentGroup(String studentLogin) {
		if(userDB.getListeUtilisateur().get(studentLogin) instanceof Etudiant){
			return ((Etudiant)(userDB.getListeUtilisateur().get(studentLogin))).getIdGroupe();
		}
		else{
			return 0;
		}
	}

	/**
	 * Fonction permettant d'ajouter un administrateur. Elle renvoie true si l'administrateur a été créé et false sinon. 
	 * Cette fonction teste si l'administrateur existe déjà ou non, puis le sauvegarde dans la base de données.
	 * @param adminLogin
	 * 				Le login de l'administrateur qui va créer le nouvel administrateur.
	 * @param newAdminlogin
	 * 				Le login du nouvel administrateur.
	 * @param adminID
	 * 				L'identifiant du nouvel administrateur.
	 * @param firstname
	 * 				Le prénom du nouvel administrateur.
	 * @param surname
	 * 				Le nom du nouvel administrateur.
	 * @param pwd
	 * 				Le mot de passe du nouvel administrateur.
	 * @return
	 * 		Un boolean indiquant si l'administrateur a bien été créé
	 */
	@Override
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname, String pwd) {
		if (userDB.getListeUtilisateur().get(adminLogin) instanceof Administrateur) {
			if (!userDB.getListeUtilisateur().containsKey(newAdminlogin) && adminID <= 999) {
				userDB.getListeUtilisateur().put(newAdminlogin, ((Administrateur)(userDB.getListeUtilisateur().get(adminLogin))).createUser(newAdminlogin, pwd, surname, firstname, adminID, 0));
				return saveDB();
			}
			return false;
		}
		
		return false;
	}

	/**
	 * Fonction permettant d'ajouter un professeur. Elle renvoie true si le professeur a été créé et false sinon. 
	 * Cette fonction teste si le professeur existe déjà ou non, puis elle le sauvegarde dans la base de données.
	 * @param adminLogin
	 * 				Le login de l'administrateur qui va créer le nouveau professeur.
	 * @param newteacherLogin
	 * 				Le login du nouveau professeur.
	 * @param teacherID
	 * 				L'identifiant du nouveau professeur.
	 * @param firstname
	 * 				Le prénom du nouveau professeur.
	 * @param surname
	 * 				Le nom du nouveau professeur.
	 * @param pwd
	 * 				Le mot de passe du nouveau professeur.
	 * @return
	 * 		Un boolean indiquant si le nouveau professeur a bien été créé
	 */
	@Override
	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,
			String surname, String pwd) {
		if (userDB.getListeUtilisateur().get(adminLogin) instanceof Administrateur) {
			if (!userDB.getListeUtilisateur().containsKey(newteacherLogin) && teacherID > 1000 && teacherID <= 1999) {
				userDB.getListeUtilisateur().put(newteacherLogin, ((Administrateur)(userDB.getListeUtilisateur().get(adminLogin))).createUser(newteacherLogin, pwd, surname, firstname, teacherID, 1));
				return saveDB();
			}
			return false;
		}
		
		return false;
	}

	/**
	 * Fonction permettant d'ajouter un étudiant. Elle renvoie true si l'étudiant a été créé et false sinon. 
	 * Cette fonction teste si l'étudiant existe déjà ou non, puis elle le sauvegarde dans la base de données.
	 * @param adminLogin
	 * 				Le login de l'administrateur qui va créer le nouvel étudiant.
	 * @param newStudentLogin
	 * 				Le login du nouvel étudiant.
	 * @param studentID
	 * 				L'identifiant du nouvel étudiant.
	 * @param firstname
	 * 				Le prénom du nouvel étudiant.
	 * @param surname
	 * 				Le nom du nouvel étudiant.
	 * @param pwd
	 * 				Le mot de passe du nouvel étudiant.
	 * @return
	 * 		Un boolean indiquant si le nouvel étudiant a bien été créé
	 */
	@Override
	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,
			String surname, String pwd) {
		if (userDB.getListeUtilisateur().get(adminLogin) instanceof Administrateur) {
			if (!userDB.getListeUtilisateur().containsKey(newStudentLogin) && studentID > 2000 && studentID <= 2999) {
				userDB.getListeUtilisateur().put(newStudentLogin, ((Administrateur)(userDB.getListeUtilisateur().get(adminLogin))).createUser(newStudentLogin, pwd, surname, firstname, studentID, 2));
				return saveDB();
			}
			return false;
		}
		
		return false;
	}

	/**
	 * Fonction permettant de supprimer un utilisateur. Elle renvoie true si l'utilisateur a été supprimé et false sinon. 
	 * Cette fonction teste si l'utilisateur existe ou non, puis elle le retire de la base de données.
	 * @param adminLogin
	 * 				Le login de l'administrateur qui va supprimer l'utilisateur.
	 * @param userLogin
	 * 				Le login d'utilisateur à supprimer.
	 * @return
	 * 		Un boolean indiquant si l'utilisateur a bien été supprimé.
	 */
	@Override
	public boolean removeUser(String adminLogin, String userLogin) {
		if (userDB.getListeUtilisateur().containsKey(adminLogin) && userDB.getListeUtilisateur().containsKey(userLogin)){
			if ((userDB.getListeUtilisateur().get(userLogin) instanceof Etudiant) && ((Etudiant) userDB.getListeUtilisateur().get(userLogin)).getIdGroupe() > 0){
				userDB.getListeGroupe().get(((Etudiant)userDB.getListeUtilisateur().get(userLogin)).getIdGroupe()).removeEtudiant((Etudiant)userDB.getListeUtilisateur().get(userLogin));
			}
			userDB.getListeUtilisateur().remove(userLogin);
			return saveDB();
		}
		else {
			return false;
		}
	}

	/**
	 * Fonction permettant d'ajouter un groupe. Elle renvoie true si le groupe a été ajouté et false sinon. 
	 * Cette fonction teste si le groupe existe déjà ou non, puis elle le crée et le sauvegarde dans la base de données. 
	 * @param adminLogin
	 * 				Le login de l'administrateur qui va créer le groupe.
	 * @param groupId
	 * 				L'identifiant du groupe à créer.
	 * @return
	 * 		Un boolean indiquant si le groupe a été créé.
	 */
	@Override
	public boolean addGroup(String adminLogin, int groupId) {
		if(groupId > 0 && userDB.getListeUtilisateur().containsKey(adminLogin) && (userDB.getListeGroupe().containsKey(groupId)==false)){
			userDB.getListeGroupe().put(groupId, ((Administrateur) (userDB.getListeUtilisateur().get(adminLogin))).createGroup(groupId));
			return saveDB();
		}
		else{
			return false;
		}
	}

	/**
	 * Fonction permettant de supprimer un groupe. Elle renvoie true si le groupe a été supprimé et false sinon. 
	 * Cette fonction teste si le groupe existe ou non, puis elle le retire de la base de données. 
	 * @param adminLogin
	 * 				Le login de l'administrateur qui va supprimer le groupe.
	 * @param groupId
	 * 				Identifiant du groupe à supprimer.
	 * @return
	 * 		Un boolean indiquant si le groupe a bien été supprimé.
	 */
	@Override
	public boolean removeGroup(String adminLogin, int groupId) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin) && userDB.getListeGroupe().containsKey(groupId)){
			userDB.getListeGroupe().get(groupId).removeAllEtudiant();
			userDB.getListeGroupe().remove(groupId);
			return saveDB();
		}
		else{
			return false;
		}
	}

	/**
	 * Fonction permettant d'associer un étudiant à un groupe. Elle renvoie true si l'association a été réalisée et false sinon. 
	 * Cette fonction teste si l'étudiant et le groupe existent ou non, puis elle sauvegarde la base de données. 
	 * @param adminLogin
	 * 				Le login de l'administrateur qui va associer un étudiant à un groupe.
	 * 
	 * @param studentLogin
	 * 				Login de l'étudiant
	 * @param groupId
	 * 				Identifiant du groupe.
	 * @return
	 * 		Un boolean indiquant si l'association a bien été réalisée.
	 */
	@Override
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {		
		if ((userDB.getListeUtilisateur().get(adminLogin) instanceof Administrateur) && userDB.getListeUtilisateur().containsKey(studentLogin) && userDB.getListeGroupe().containsKey(groupId)) {
			Etudiant etudiant = ((Etudiant) (userDB.getListeUtilisateur().get(studentLogin)));
			
			if (etudiant.getIdGroupe() > 0) {
				userDB.getListeGroupe().get(etudiant.getIdGroupe()).removeEtudiant(etudiant);				
			}
			
			etudiant.setIdGroupe(groupId);
			userDB.getListeGroupe().get(groupId).addEtudiant(etudiant);
			
			return saveDB();
		}
		else{
			return false;
		}
	}

	/**
	 * Fonction permettant de récupérer toutes les informations des utilisateurs sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient toutes les informations d'un utilisateur.
	 * 
	 * @return
	 * 		Un tableau de String contenant toutes les infos de tous les utilisateurs.
	 */
	@Override
	public String[] usersToString() {
		String[] users = new String[userDB.getListeUtilisateur().size()];
		int i = 0;

		for(Entry<String, Utilisateur> entry : userDB.getListeUtilisateur().entrySet()){
			users[i] = "Rôle : " + entry.getValue().getClassUser();
			users[i] += " - Id : " + entry.getValue().getId();
			users[i] += " - Login : " + entry.getValue().getLogin();
			users[i] += " - Password : " + entry.getValue().getPassword();
			users[i] += " - Prénom Nom : " + entry.getValue().getName();
			
			if(entry.getValue() instanceof Etudiant){
				users[i] += " - Groupe : " + ((Etudiant) entry.getValue()).getIdGroupe();
			}
			
			i++;
		}
		
		return users;
	}

	/**
	 * Fonction permettant de récupérer les logins des utilisateurs sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient le login d'un utilisateur.
	 * 
	 * @return
	 * 		Un tableau de String contenant le login de tous les utilisateurs.
	 */
	@Override
	public String[] usersLoginToString() {
		String[] usersLogin = new String[userDB.getListeUtilisateur().size()];
		int i = 0;
		for(Entry<String, Utilisateur> entry : userDB.getListeUtilisateur().entrySet()){
			usersLogin[i] = entry.getValue().getLogin();
			i++;
		}
		return usersLogin;
	}

	/**
	 * Fonction permettant de récupérer les logins des étudiants sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient le login d'un étudiant.
	 * 
	 * @return
	 * 		Un tableau de String contenant le login de tous les étudiants.
	 */
	@Override
	public String[] studentsLoginToString() {
		String[] studentsLogin = new String[userDB.getListeUtilisateur().size()];
		int i = 0;
		for(Entry<String, Utilisateur> entry : userDB.getListeUtilisateur().entrySet()){
			if(entry.getValue() instanceof Etudiant){
				studentsLogin[i]=entry.getValue().getLogin();
				i++;
			}
		}
		return studentsLogin;
	}

	/**
	 * Fonction permettant de récupérer les identifiants des groupes sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient l'identifiant d'un groupe.
	 * 
	 * @return
	 * 		Un tableau de String contenant l'identifiant de tous les groupes.
	 */
	@Override
	public String[] groupsIdToString() {
		String[] groupsId = new String[userDB.getListeGroupe().size()];
		int i = 0;
		for(Entry<Integer, Groupe> entry : userDB.getListeGroupe().entrySet()){
			groupsId[i]=Integer.toString(entry.getValue().getId());
			i++;
		}
		return groupsId;
	}

	/**
	 * Fonction permettant de récupérer toutes les informations des groupes sous la forme d'un 
	 * tableau de chaînes de caractères où chaque ligne contient les informations d'un groupe.
	 * 
	 * @return
	 * 		Un tableau de String contenant toutes les informations de tous les groupes.
	 */
	@Override
	public String[] groupsToString() {
		String[] groups = new String[userDB.getListeGroupe().size()];

		int i = 0;
		for(Entry<Integer, Groupe> entry : userDB.getListeGroupe().entrySet()){
			groups[i] = "Id : " + entry.getValue().getId();
			groups[i] += " - Nombre d'étudiants : " + entry.getValue().getNombre();
			
			if (entry.getValue().getNombre() > 0) {
				groups[i] += " - Etudiants : ";
				
				Iterator<String> iterator = entry.getValue().getEtudiantsGroupe().iterator(); 
				
				int j = 0;
				while (iterator.hasNext()) {
					
					Etudiant etudiant = (Etudiant) userDB.getListeUtilisateur().get(iterator.next());
					groups[i] += etudiant.getName() + " (" + etudiant.getLogin() + ")";
					
					if (j < entry.getValue().getEtudiantsGroupe().size() - 1) {
						groups[i] += ", ";
					}
				}
				j++;
			}

			i++;
		}
		
		return groups;
	}

	/**
	 * Fonction chargeant la base de donnée contenue dans un fichier XML.
	 * @return
	 * 		Un boolean indiquant si le chargement a bien été réalisée.
	 */
	@Override
	public boolean loadDB() {
		return userDB.loadDB();
	}

	/**
	 * Fonction sauvegardant la base de donnée dans un fichier XML.
	 * @return
	 * 		Un boolean indiquant si la sauvegarde a bien été réalisée.
	 */
	@Override
	public boolean saveDB() {
		return userDB.saveDB();		
	}

	/**
	 * Getter de l'attribut userDB
	 * @return userDB
	 */
	public UserDB getUserDB() {
		return userDB;
	}

	/**
	 * Setter de l'attribut userDB
	 * @param userDB
	 * @return userDB
	 */
	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}
	
}
