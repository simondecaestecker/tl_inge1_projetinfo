package userModel;

import java.util.HashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

				int groupId = Integer.parseInt(unGroupElmt.getChild("groupId").getText());

				groupes.put(groupId, new Groupe(groupId));
			}

			Element studentsDB = rootElt.getChild("Students");
			List<Element> studentsElmts = studentsDB.getChildren("Student");
			Iterator<Element> itStudent = studentsElmts.iterator();
			
			while(itStudent.hasNext()){
				Element unStudentElmt = (Element)itStudent.next();

				String login = unStudentElmt.getChild("login").getText();
				Etudiant student = new Etudiant(login, unStudentElmt.getChild("pwd").getText(), unStudentElmt.getChild("surname").getText(), unStudentElmt.getChild("firstname").getText(), Integer.parseInt(unStudentElmt.getChild("studentId").getText()));
				student.setIdGroupe(Integer.parseInt(unStudentElmt.getChild("groupId").getText()));

				utilisateurs.put(login, student);
			}

			Element teachersDB = rootElt.getChild("Teachers");
			List<Element> teachersElmts = teachersDB.getChildren("Teacher");
			Iterator<Element> itTeacher = teachersElmts.iterator();
			
			while(itTeacher.hasNext()){
				Element unTeacherElmt = (Element)itTeacher.next();

				String login = unTeacherElmt.getChild("login").getText();
				Professeur teacher = new Professeur(login, unTeacherElmt.getChild("pwd").getText(), unTeacherElmt.getChild("surname").getText(), unTeacherElmt.getChild("firstname").getText(), Integer.parseInt(unTeacherElmt.getChild("teacherId").getText()));

				utilisateurs.put(login, teacher);
			}

			Element administratorsDB = rootElt.getChild("Students");
			List<Element> administratorsElmts = administratorsDB.getChildren("Administrator");
			Iterator<Element> itAdministrator = administratorsElmts.iterator();

			while(itAdministrator.hasNext()){
				Element unAdministratorElmt = (Element)itAdministrator.next();

				String login = unAdministratorElmt.getChild("login").getText();
				Administrateur administrator = new Administrateur(login, unAdministratorElmt.getChild("pwd").getText(), unAdministratorElmt.getChild("surname").getText(), unAdministratorElmt.getChild("firstname").getText(), Integer.parseInt(unAdministratorElmt.getChild("administratorId").getText()));

				utilisateurs.put(login, administrator);
			}
		}
		
		return true;
	}
	
	public boolean saveDB(){
		Element rootElmt = new Element("UsersDB");
		org.jdom2.Document document = new Document(rootElmt);
		
		Element groupsElmts = new Element("Groups");
		rootElmt.setContent(groupsElmts);
		
		Element studentsElmts = new Element("Students");
		rootElmt.setContent(studentsElmts);
		
		Element teachersElmts = new Element("Teachers");
		rootElmt.setContent(teachersElmts);
		
		Element administratorsElmts = new Element("Administrators");
		rootElmt.setContent(administratorsElmts);
	
		return true;
	}
}
