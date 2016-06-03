package userModel;

import java.util.HashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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

public class UserDB {

	/**
	 * 
	 * Le fichier contenant la base de donnÃ©es.
	 * 
	 */
	private String file;

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
	public UserDB(String file){
		//TODO Fonction Ã  modifier
		super();
		
		this.utilisateurs = new HashMap<String, Utilisateur>();
		this.groupes = new HashMap<Integer, Groupe>();
		
		this.root = new Administrateur("root", "root", "root", "root", 0);
		
		loadDB();
		
		this.setFile(file);
	}

	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de donnÃ©es.
	 */

	public String getFile() {
		return file;
	}

	/**
	 * Setter de file
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de donnÃ©es.
	 */

	public void setFile(String file) {
		this.file = file;
	}

	public HashMap<String, Utilisateur> getListeUtilisateur(){
		return utilisateurs;
	}

	public HashMap<Integer, Groupe> getListeGroupe(){
		return groupes;
	}

	public boolean loadDB(){
		org.jdom2.Document document = null ;

		Element rootElt;
		SAXBuilder sxb = new SAXBuilder();

		try{
			document = sxb.build(new File("userDB.xml"));
		}catch(Exception e){}

		if(document!=null){
			rootElt = document.getRootElement();

			Element groupsDB = rootElt.getChild("Groups");
			List<Element> groupsElmts = groupsDB.getChildren("Group");
			Iterator<Element> itGroup = groupsElmts.iterator();

			while(itGroup.hasNext()){
				Element unGroupElmt = (Element)itGroup.next();
				
				groupes.put(Integer.parseInt(unGroupElmt.getChild("groupId").getText()), root.createGroup(Integer.parseInt(unGroupElmt.getChild("groupId").getText())));
			}

			Element studentsDB = rootElt.getChild("Students");
			List<Element> studentsElmts = studentsDB.getChildren("Student");
			Iterator<Element> itStudent = studentsElmts.iterator();

			while(itStudent.hasNext()){
				Element unStudentElmt = (Element)itStudent.next();
				
				Etudiant etudiant = (Etudiant) root.createUser(unStudentElmt.getChild("login").getText(), unStudentElmt.getChild("pwd").getText(), unStudentElmt.getChild("surname").getText(), unStudentElmt.getChild("firstname").getText(), Integer.parseInt(unStudentElmt.getChild("studentId").getText()), 2);
				
				utilisateurs.put(unStudentElmt.getChild("login").getText(), etudiant);
				if (Integer.parseInt(unStudentElmt.getChild("groupId").getText()) > 0) {
					groupes.get(Integer.parseInt(unStudentElmt.getChild("groupId").getText())).addEtudiant(etudiant);
				}				
			}

			Element teachersDB = rootElt.getChild("Teachers");
			List<Element> teachersElmts = teachersDB.getChildren("Teacher");
			Iterator<Element> itTeacher = teachersElmts.iterator();

			while(itTeacher.hasNext()){
				Element unTeacherElmt = (Element)itTeacher.next();

				utilisateurs.put(unTeacherElmt.getChild("login").getText(), root.createUser(unTeacherElmt.getChild("login").getText(), unTeacherElmt.getChild("pwd").getText(), unTeacherElmt.getChild("surname").getText(), unTeacherElmt.getChild("firstname").getText(), Integer.parseInt(unTeacherElmt.getChild("teacherId").getText()), 1));
			}

			Element administratorsDB = rootElt.getChild("Administrators");
			List<Element> administratorsElmts = administratorsDB.getChildren("Administrator");
			Iterator<Element> itAdministrator = administratorsElmts.iterator();

			while(itAdministrator.hasNext()){
				Element unAdministratorElmt = (Element)itAdministrator.next();
			
				utilisateurs.put(unAdministratorElmt.getChild("login").getText(), root.createUser(unAdministratorElmt.getChild("login").getText(), unAdministratorElmt.getChild("pwd").getText(), unAdministratorElmt.getChild("surname").getText(), unAdministratorElmt.getChild("firstname").getText(), Integer.parseInt(unAdministratorElmt.getChild("adminId").getText()), 0));
			}
		}

		return true;
	}

	public boolean saveDB(){
		Element rootElmt = new Element("UsersDB");
		org.jdom2.Document document = new Document(rootElmt);

		Element groupsElmts = new Element("Groups");
		rootElmt.addContent(groupsElmts);

		Element studentsElmts = new Element("Students");
		rootElmt.addContent(studentsElmts);

		Element teachersElmts = new Element("Teachers");
		rootElmt.addContent(teachersElmts);

		Element administratorsElmts = new Element("Administrators");
		rootElmt.addContent(administratorsElmts);

		//Save les Groups
		for (Entry<Integer, Groupe> entry : groupes.entrySet()) {
			Groupe valeur = entry.getValue();

			Element unGroup = new Element("Group");
			groupsElmts.addContent(unGroup);

			Element unGroupId = new Element("groupId");
			unGroup.addContent(unGroupId);
			unGroupId.setText(Integer.toString(valeur.getId()));
		}

		//Save les Utilisateurs
		for (Entry<String, Utilisateur> entry : utilisateurs.entrySet()) {
			Utilisateur valeur = entry.getValue();

			Element unUser = new Element(valeur.getClassUser());

			Element unUserLogin = new Element("login");
			unUser.addContent(unUserLogin);
			unUserLogin.setText(valeur.getLogin());

			Element unUserFirstname = new Element("firstname");
			unUser.addContent(unUserFirstname);
			unUserFirstname.setText(valeur.getFirstName());

			Element unUserSurname = new Element("surname");
			unUser.addContent(unUserSurname);
			unUserSurname.setText(valeur.getSurname());

			Element unUserPwd = new Element("pwd");
			unUser.addContent(unUserPwd);
			unUserPwd.setText(valeur.getPassword());

			Element unUserId;

			if (valeur.getClassUser() == "Student") {
				studentsElmts.addContent(unUser);

				unUserId = new Element("studentId");

				Element unUserGroupId = new Element("groupId");
				unUser.addContent(unUserGroupId);
				unUserGroupId.setText(Integer.toString(((Etudiant) valeur).getIdGroupe()));
			}

			else if (valeur.getClassUser() == "Teacher") {
				teachersElmts.addContent(unUser);

				unUserId = new Element("teacherId");
			}

			else if (valeur.getClassUser() == "Administrator") {
				administratorsElmts.addContent(unUser);

				unUserId = new Element("adminId");
			}

			else {
				return false;
			}

			unUserId.setText(Integer.toString(valeur.getId()));
			unUser.addContent(unUserId);
		}

		try{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream("userDB.xml"));
			return true ;
		}catch (java.io.IOException e){
			return false ;
		}
	}
}