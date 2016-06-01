package userController;

import java.util.Map.Entry;

import userModel.UserDB;
import userModel.Etudiant;
import userModel.Administrateur;
import userModel.Professeur;
import userModel.Utilisateur;
/**
 * Cette classe est le contrôleur d'utilisateurs que vous devez implémenter. 
 * Elle contient un attribut correspondant à la base de données utilisateurs que vous allez créer.
 * Elle contient toutes les fonctions de l'interface IUserController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

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

	@Override
	public String getUserName(String userLogin) {
		if(userDB.getListeUtilisateur().containsKey(userLogin)){
			return userDB.getListeUtilisateur().get(userLogin).getName();
		}
		else{
			return null;
		}
	}

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

	@Override
	public int getStudentGroup(String studentLogin) {
		if(userDB.getListeUtilisateur().get(studentLogin) instanceof Etudiant){
			return ((Etudiant)(userDB.getListeUtilisateur().get(studentLogin))).getIdGroupe();
		}
		else{
			return 0;
		}
	}

	@Override
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname,
			String pwd) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin)){
			return false;
		}
		else{
			if(userDB.getListeUtilisateur().get(adminLogin) instanceof Administrateur){
				return ((Administrateur)(userDB.getListeUtilisateur().get(adminLogin))).createUser(newAdminlogin, pwd, surname, firstname, adminID, 0);
			}
			else{
				return false;
			}
		}
	}

	@Override
	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,
			String surname, String pwd) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin)){
			return false;
		}
		else{
			if(userDB.getListeUtilisateur().get(adminLogin) instanceof Administrateur){
				return ((Administrateur)(userDB.getListeUtilisateur().get(adminLogin))).createUser(newteacherLogin, pwd, surname, firstname, teacherID, 0);
			}
			else{
				return false;
			}
		}
	}

	@Override
	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,
			String surname, String pwd) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin)){
			return false;
		}
		else{
			if(userDB.getListeUtilisateur().get(adminLogin) instanceof Administrateur){
				return ((Administrateur)(userDB.getListeUtilisateur().get(adminLogin))).createUser(newStudentLogin, pwd, surname, firstname, studentID, 0);
			}
			return false;
		}
	}

	@Override
	public boolean removeUser(String adminLogin, String userLogin) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin) && userDB.getListeUtilisateur().containsKey(userLogin)){
			if(userDB.getListeUtilisateur().get(userLogin) instanceof Etudiant){
				userDB.getListeGroupe().get(((Etudiant)userDB.getListeUtilisateur().get(userLogin)).getIdGroupe()).removeEtudiant((Etudiant)userDB.getListeUtilisateur().get(userLogin));
			}
			userDB.getListeUtilisateur().remove(userLogin);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean addGroup(String adminLogin, int groupId) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin) && (userDB.getListeGroupe().containsKey(groupId)==false)){
			userDB.getListeGroupe().put(groupId, ((Administrateur) (userDB.getListeUtilisateur().get(adminLogin))).createGroup(groupId));
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean removeGroup(String adminLogin, int groupId) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin) && userDB.getListeGroupe().containsKey(groupId)){
			userDB.getListeGroupe().get(groupId).removeAllEtudiant();
			userDB.getListeGroupe().remove(groupId);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		if(userDB.getListeUtilisateur().containsKey(adminLogin) && userDB.getListeUtilisateur().containsKey(studentLogin) && userDB.getListeGroupe().containsKey(groupId)){
			((Etudiant) (userDB.getListeUtilisateur().get(studentLogin))).setIdGroupe(groupId);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public String[] usersToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] usersLoginToString() {
		String[] usersLogin = new String[userDB.getListeUtilisateur().size()];
		int i = 0;
		for(Entry<String, Utilisateur> entry : userDB.getListeUtilisateur().entrySet()){
			usersLogin[i]=entry.getValue().getLogin();
			i++;
		}
		return usersLogin;
	}

	@Override
	public String[] studentsLoginToString() {
		String[] usersLogin = new String[userDB.getListeUtilisateur().size()];
		int i = 0;
		for(Entry<String, Utilisateur> entry : userDB.getListeUtilisateur().entrySet()){
			usersLogin[i]=entry.getValue().getLogin();
			i++;
		}
		return usersLogin;
	}

	@Override
	public String[] groupsIdToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] groupsToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loadDB() {
		return userDB.loadDB();
	}

	@Override
	public boolean saveDB() {
		return userDB.saveDB();		
	}

	public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}
	
}

