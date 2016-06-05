/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Cette classe gère la base de données d'emplois du temps. Elle doit permettre de sauvegarder et charger les emplois du temps ainsi que les salles à partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier TimeTableDB.xml.
 * @see <a href="../../TimeTableDB.xml">TimeTableDB.xml</a> 
 * 
 * @author Antoine Leroy-Souque
 * @version 06/2016
 * 
 */

public class TimeTableDB {
	/**
	 * 
	 */
	private HashMap<Integer, Timetable> timetables ;
	
	/**
	 * 
	 */
	private HashMap<Integer, Room> rooms ;
	
	/**
	 * 
	 */
	private Timetable timetableprof ;
	
	/**
	 * Le fichier contenant la base de données.
	 */
	private String file;
	
	/**
	 * Constructeur de la base de données d'emploi du temps créant les emplois du temps et les salles.
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public TimeTableDB(String file){
		timetables = new HashMap<Integer, Timetable>();
		rooms = new HashMap<Integer,Room>();
		timetableprof = new Timetable(0);
		this.setFile(file);
		loadDB();
	}

	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public String getFile() {
		return file ;
	}
	
	/**
	 * Setter de file
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	/**
	 * Charge la base de données d'emplois du temps à partir du fichier XML.
	 * 
	 * @return "true" si le chargement s'est correctement effectué, "false" sinon
	 */
	public boolean loadDB(){
		DateFormat fd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		org.jdom2.Document document = null ;
		Element rootElt;
		SAXBuilder sxb = new SAXBuilder();
		try{
			document = sxb.build(new File(this.file));
		}catch(Exception e){
			return false ;
		}
		if(document!=null){
			rootElt = document.getRootElement();
			
			//On récupère les Rooms
			Element roomsDB = rootElt.getChild("Rooms");
			List<Element> roomsElmts = roomsDB.getChildren("Room");
			Iterator<Element> itRoom = roomsElmts.iterator();
			
			//On récupère les Timetables
			Element timetablesDB = rootElt.getChild("TimeTables");
			List<Element> timetablesElmts = timetablesDB.getChildren("TimeTable");
			Iterator<Element> itTimetable = timetablesElmts.iterator();
			
			while(itRoom.hasNext()){
				Element uneRoomElt = (Element)itRoom.next();
				
				//récupérer RoomId et le convertir en int
				int roomId = Integer.parseInt(uneRoomElt.getChild("RoomId").getText());
				
				//récupérer Capacity et le convertir en int
				int capacity = Integer.parseInt(uneRoomElt.getChild("Capacity").getText());
				
				//Mettre RoomId et Capacity dans l'HashMap rooms
				Room RoomTemp = new Room(roomId,capacity);
				rooms.put(roomId,RoomTemp);
			}
			
			while(itTimetable.hasNext()){
				Element unTimetableElt = (Element)itTimetable.next();
				
				//récupérer GroupId et le convertir en int
				int GroupId = Integer.parseInt(unTimetableElt.getChild("GroupId").getText());
				Timetable TimetableTemp = new Timetable(GroupId);
				
				//récupérer les Books
				Element booksDB = unTimetableElt.getChild("Books");
				List<Element> booksElmts = booksDB.getChildren("Book");
				Iterator<Element> itBook = booksElmts.iterator();
				
				while(itBook.hasNext()){
					Element unBookElt = (Element)itBook.next();
					
					//récupérer BookingId et le convertir en int
					int BookingId = Integer.parseInt(unBookElt.getChild("BookingId").getText());
					
					//récupérer Login
					String Login = unBookElt.getChild("Login").getText();
					
					//récupérer RoomId
					int RoomId = Integer.parseInt(unBookElt.getChild("RoomId").getText());

					//récupérer DateBegin
					Date DateBegin = null ;
					try {
						DateBegin = fd.parse(unBookElt.getChild("DateBegin").getText());
					} catch (ParseException e1) {
						// Auto-generated catch block
						e1.printStackTrace();
					}

					//récupérer DateEnd
					Date DateEnd = null ;
					try {
						DateEnd = fd.parse(unBookElt.getChild("DateEnd").getText());
					} catch (ParseException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}

					//Mettre les valeurs récupérées dans l'HashMap bookings
					TimetableTemp.BookTimeSlot(DateBegin, DateEnd, Login, RoomId, BookingId);
					
					//Tester le login prof
					if(Login.equals("GS")){
						timetableprof.BookTimeSlot(DateBegin, DateEnd, Login, RoomId, BookingId);
					}
					
				}
				
			timetables.put(GroupId, TimetableTemp);
			timetables.put(0, timetableprof);

			}
			
		}
		return true ;
		
	}
	
	/**
	 * Sauvegarde la base de données d'emplois du temps dans un fichier XML
	 * 
	 * @return "true" si la sauvegarde s'est correctement effectuée, "false" sinon
	 */
	@SuppressWarnings("unused")
	public boolean saveDB(){
		Element rootElt = new Element("TimeTablesDB");
		org.jdom2.Document document = new Document(rootElt);
		Element RoomsElt = new Element("Rooms");
		Element TimeTablesElt = new Element("TimeTables");
		DateFormat fd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		//save les Rooms
		for(Entry<Integer, Room> entry : rooms.entrySet()) {
			Integer cle = entry.getKey();
			Room valeur = entry.getValue();
			
			Element unRoom = new Element("Room");
			
			Element unRoomId = new Element("RoomId");
			unRoomId.setText(Integer.toString(valeur.getRoomId()));
			unRoom.addContent(unRoomId);

			Element unRoomCapacity = new Element("Capacity");
			unRoomCapacity.setText(Integer.toString(valeur.getCapacity()));
			unRoom.addContent(unRoomCapacity);
			
			RoomsElt.addContent(unRoom);
		}
		
		//save les timetables
		for(Entry<Integer, Timetable> entry : timetables.entrySet()) {
			Integer cle = entry.getKey();
			Timetable valeur = entry.getValue();
			
			Element unTimetable = new Element("TimeTable");
			
			Element unTimetableId = new Element("GroupId");
			unTimetableId.setText(Integer.toString(valeur.getTimeTableId()));
			unTimetable.addContent(unTimetableId);
	
			Element booksElmts = new Element("Books");
			for(Entry<Integer, Booking> entry2 : valeur.getBookings().entrySet()) {
				Integer cle2 = entry2.getKey();
				Booking valeur2 = entry2.getValue();
				
				Element unBook = new Element("Book");
				
				Element unBookId = new Element("BookingId");
				unBookId.setText(Integer.toString(valeur2.getBookId()));
				unBook.addContent(unBookId);

				Element unBookLogin = new Element("Login");
				unBookLogin.setText(valeur2.getLoginProf());
				unBook.addContent(unBookLogin);
				
				Element unBookDateBegin = new Element("DateBegin");
			    unBookDateBegin.setText(fd.format(valeur2.getDateBegin()));
				unBook.addContent(unBookDateBegin);
				
				Element unBookDateEnd = new Element("DateEnd");
			    unBookDateEnd.setText(fd.format(valeur2.getDateEnd()));
				unBook.addContent(unBookDateEnd);
				
				Element unRoomId = new Element("RoomId");
				unRoomId.setText(Integer.toString(valeur2.getroomId()));
				unBook.addContent(unRoomId);
				
				booksElmts.addContent(unBook);
			}
			
			unTimetable.addContent(booksElmts);
			TimeTablesElt.addContent(unTimetable);			
		}
		
		//Lier à TimeTablesDB
		rootElt.addContent(RoomsElt);
		rootElt.addContent(TimeTablesElt);
		
		try{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(this.file));
			return true ;
		}catch (java.io.IOException e){
			return false ;
		}
				
	}
	
	/**
	 * Geter de l'attribut timetables
	 * 
	 * @return timetables
	 */
	public HashMap<Integer, Timetable> gettimetables(){
		return this.timetables ;
	}
	
	/**
	 * Getter de l'attribut rooms
	 * 
	 * @return rooms
	 */
	public HashMap<Integer, Room> getrooms(){
		return this.rooms ;
	}

	/**
	 * Getter de l'attribut timetableprof
	 * 
	 * @return timetableprof
	 */
	public Timetable gettimetableprof(){
		return this.timetableprof;
	}
	
	/**
	 * Ajoute un nouvel emploi du temps dans timetables s'il n'existe pas déjà
	 * 
	 * @param TimetableId
	 * 		identifiant de l'emploi du temps à ajouter
	 * 
	 * @return "true" si l'ajout s'est bien effectué, "false" sinon
	 */
	public boolean addTimetable(int TimetableId){
		if(this.timetables.containsKey(TimetableId)){
			return false ;
		}
		else{
			Timetable temp = new Timetable(TimetableId);
			this.timetables.put(TimetableId, temp);
			saveDB();
			return true ;
		}	
	}

	/**
	 * Supprime un emploi du temps correspondant à l'identifiant donné si celui-ci existe
	 * 
	 * @param TimetableId
	 * 		identifiant de l'emploi du temps à supprimer
	 * 
	 * @return "true" si la suppression s'est bien effectuée, "false" sinon
	 */
	public boolean removeTimetable(int TimetableId){
		if(this.timetables.containsKey(TimetableId)){
			this.timetables.remove(TimetableId);
			saveDB();
			return true ;
		}
		return false ;
	}

	/**
	 * Ajoute une salle dans rooms si elle n'existe pas déjà
	 * 
	 * @param roomId
	 * 		identifiant de la salle à ajouter
	 * 
	 * @param capacity
	 * 		capacité maximale de la salle à ajouter
	 * 
	 * @return "true" si l'ajout s'est bien effectué, "false" sinon
	 */
	public boolean addRoom(int roomId, int capacity){
		if(this.rooms.containsKey(roomId)){
			return false;
		}
		else{
			Room temp = new Room(roomId,capacity);
			this.rooms.put(roomId, temp);
			saveDB();
			return true;
		}
	}
	
	/**
	 * Supprime une salle dans rooms si elle existe
	 * 
	 * @param roomId
	 * 		identifiant de la salle à supprimer
	 * 
	 * @return "true" si la suppression s'est bien effectuée, "false" sinon
	 */
	public boolean removeRoom(int roomId){
		if(this.rooms.containsKey(roomId)){
			this.rooms.remove(roomId);
			saveDB();
			return true ;
		}
		return false ;
	}

	/**
	 * Ajoute une réservation dans timetables s'il n'y a pas de conflit avec une précédente réservation
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps dans lequel on veut ajouter une réservation
	 * 
	 * @param bookingId
	 * 		identifiant de la nouvelle réservation
	 * 
	 * @param login
	 * 		login du prof qui effectue la réservation
	 * 
	 * @param dateBegin
	 * 		Date de début de la réservation
	 * 
	 * @param dateEnd
	 * 		Date de fin de la réservation
	 * 
	 * @param roomId
	 * 		identifiant de la salle à réserver
	 * 
	 * @return "true" si la réservation s'est bien effectuée, "false" sinon
	 */
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId){
		if(timetables.containsKey(timeTableId) && rooms.containsKey(roomId) && timeTableId > 0){
			if(timetables.get(timeTableId).getBookings().size() > 0){
				for(Entry<Integer, Booking> entry : timetables.get(timeTableId).getBookings().entrySet()) {
					Booking valeur = entry.getValue();
					
					if(dateBegin.before(valeur.getDateBegin())){
						if(dateEnd.after(valeur.getDateBegin())){ //ici ça regroupe les deux cas : date début avant date début existante et date fin compris entre ou après la date fin de l'existant
							return false ;
						}
					}
					
					if(dateBegin.after(valeur.getDateBegin()) && dateBegin.before(valeur.getDateEnd())){
						return false ;
					}

				}
			}
			
			return timetables.get(timeTableId).BookTimeSlot(dateBegin, dateEnd, login, roomId, bookingId);
			
		}
		return false;
	}
	
}
