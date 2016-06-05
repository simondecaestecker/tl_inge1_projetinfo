/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableController;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map.Entry;
import timeTableModel.Booking;
import timeTableModel.Room;
import timeTableModel.TimeTableDB;
import timeTableModel.Timetable;

/**
 * Cette classe est le contrôleur d'emplois du temps.
 * Elle contient un attribut correspondant à la base de données d'emplois (TimeTableDB).
 * Elle contient toutes les fonctions de l'interface ITimeTableController.
 * 
 * @author Antoine Leroy-Souque
 * @version 06/2016
 * 
 */

public class TimeTableController implements ITimeTableController{
	/**
	 * Contient une instance de base de données d'emplois du temps
	 * 
	 */
	TimeTableDB tTDB;
	
	/**
	 * Constructeur de contrôleur d'emplois du temps créant la base de données d'emplois du temps
	 * 
	 * @param tTfile
	 * 		Fichier XML contenant la base de données d'emplois du temps
	 */
	public TimeTableController(String tTfile) {
		TimeTableDB tTDB=new TimeTableDB(tTfile);
		this.tTDB=tTDB;
	}

	/**
	 * Getter du login du professeur correspondant à l'identifiant d'une réservation contenue dans un emploi du temps identifié
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps
	 * 
	 * @param bookId
	 * 		identifiant de la réservation
	 * 
	 * @return l'identifiant du professeur correspondant, "erreur" sinon
	 */
	@Override
	public String getTeacherLogin(int timeTableId, int bookId) {
		if(tTDB.gettimetables().containsKey(timeTableId) && tTDB.gettimetables().get(timeTableId).getBookings().containsKey(bookId)){
			return tTDB.gettimetables().get(timeTableId).getBookings().get(bookId).getLoginProf();
		}
		
		return "erreur" ;
	}

	/**
	 * Donne les identifiants des salles dans un tableau de caractères
	 * 
	 * @return tableau de caractères contenant les identifiants de toutes les salles de la base de données
	 */
	@Override
	public String[] roomsIdToString() {
		String roomsId[] =  new String[tTDB.getrooms().size()] ;
		int i = 0 ;
		
		for(Entry<Integer, Room> entry : tTDB.getrooms().entrySet()) {
			Room valeur = entry.getValue();
		
			roomsId[i] = Integer.toString(valeur.getRoomId());
			i++;
			
		}
		
		return roomsId ;
	}

	/**
	 * Donne les informations sur les salles de la base de données (identifiant + capacité) dans un tableau de caractères
	 * 
	 * @return tableau de caractères contenant les informations sur toutes les salles de la base de données
	 */
	@Override
	public String[] roomsToString() {
		String rooms[] =  new String[tTDB.getrooms().size()] ;
		int i = 0 ;
		
		for(Entry<Integer, Room> entry : tTDB.getrooms().entrySet()) {
			Room valeur = entry.getValue();
			
			rooms[i] = "Room " + Integer.toString(valeur.getRoomId()) + " | capacité de " + Integer.toString(valeur.getCapacity()) + " personnes";
			i++ ;
		}
		
		return rooms ;
	}

	/**
	 * Donne les identifiants des emplois du temps de la base de données dans un tableau de caractères
	 * 
	 * @return tableau de caractères contenant les identifiants de tous les emplois du temps de la base de données
	 */
	@Override
	public String[] timeTablesIDToString(){
		String timetablesId[] = new String[tTDB.gettimetables().size()];
		int i = 0 ;
				
		for(Entry<Integer, Timetable> entry : tTDB.gettimetables().entrySet()) {
			Timetable valeur = entry.getValue();
		
			timetablesId[i] = Integer.toString(valeur.getTimeTableId());
			i++;
		}
		return timetablesId;
	}

	/**
	 * Donne les identifiants de toutes les réservations d'un emploi du temps donné
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps dont on veut récupérer les réservations
	 * 
	 * @return tableau de caractères contenant les identifiants de toutes les réservations d'un emploi du temps
	 */
	@Override
	public String[] booksIdToString(int timeTableId){
		if(tTDB.gettimetables().containsKey(timeTableId)){
			String booksId[] = new String[tTDB.gettimetables().get(timeTableId).getBookings().size()];
			int i = 0 ;
			for(Entry<Integer, Booking> entry : tTDB.gettimetables().get(timeTableId).getBookings().entrySet()){
				Booking valeur = entry.getValue();
			
				booksId[i] = Integer.toString(valeur.getBookId());
				i++;
			}
			return booksId;	
		}
		return null ;
	}

	/**
	 * Ajoute une salle dans la base de données
	 * 
	 * @param roomId
	 * 		identifiant de la nouvelle salle à ajouter
	 * 
	 * @param capacity
	 * 		capacité maximale de la salle à ajouter
	 * 
	 * @return "true" si la salle a été ajoutée à la base de données, "false" sinon
	 */
	@Override
	public boolean addRoom(int roomId, int capacity) {
		loadDB();
		if(tTDB.addRoom(roomId, capacity)){
			saveDB();
			return true;
		}
		return false;
	}

	/**
	 * Supprime une salle de la base de données
	 * 
	 * @param roomId
	 * 		identifiant de la salle à supprimer
	 * 
	 * @return "true" si la salle a bien été supprimée, "false" sinon
	 */
	@Override
	public boolean removeRoom(int roomId) {
		loadDB();
		if(tTDB.removeRoom(roomId)){
			saveDB();
			return true;
		}
		return false;
	}

	/**
	 * Getter de l'identifiant de la salle d'une réservation précise d'un emploi du temps donné
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps
	 * 
	 * @param bookId
	 * 		identifiant de la réservation
	 * 
	 * @return l'identifiant de la salle correspondante (int), -1 sinon
	 */
	@Override
	public int getRoom(int timeTableId, int bookId) {
		//loadDB();
		if(tTDB.gettimetables().containsKey(timeTableId)){
			if(tTDB.gettimetables().get(timeTableId).getBookings().containsKey(bookId)){
				return tTDB.gettimetables().get(timeTableId).getBookings().get(bookId).getroomId();
			}
		}
		
		return -1 ;
	}

	/**
	 * Ajoute un emploi du temps
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps à créer
	 * 
	 * @return "true" si l'emploi du temps a bien été créé, "false" sinon
	 */
	@Override
	public boolean addTimeTable(int timeTableId) {
		tTDB.loadDB();
		if(tTDB.addTimetable(timeTableId)){
			return true ;
		}
		return false ;
	}

	/**
	 * Supprime un emploi du temps
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps à supprimer
	 * 
	 * @return "true" si l'emploi du temps a bien été supprimé, "false" sinon
	 */
	@Override
	public boolean removeTimeTable(int timeTableId) {
		tTDB.loadDB();
		if(tTDB.removeTimetable(timeTableId)){
			return true ;
		}
		return false ;
	}

	/**
	 * Ajoute une réservation dans un emploi du temps
	 * 
	 * @param timeTableId 
	 * 		identifiant de l'emploi du temps dans lequel on veut ajouter une réservation
	 * 
	 * @param bookingId
	 * 		identifiant de la nouvelle réservation
	 * 
	 * @param login
	 * 		login du professeur qui effectue la réservation
	 * 
	 * @param dateBegin
	 * 		Date de début de la nouvelle réservation
	 * 
	 * @param dateEnd
	 * 		Date de fin de la nouvelle réservation
	 * 
	 * @param roomId
	 * 		identifiant de la salle à réserver
	 * 
	 * @return "true" si la réservation a bien été effectuée, "false" sinon
	 */
	@Override
	public boolean addBooking(int timeTableId, int bookingId, String login, Date dateBegin, Date dateEnd, int roomId) {
		loadDB();
		if(tTDB.addBooking(timeTableId, bookingId, login, dateBegin, dateEnd, roomId)){
			saveDB();
			return true;
		}
		
		return false;
	}

	/**
	 * Récupère les dates de début et de fin de toutes les réservations d'un emploi du temps
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps dont on veut récupérer les dates
	 * 
	 * @param dateBegin
	 * 		Hashtable contenant toutes les dates de début des réservations
	 * 
	 * @param dateEnd
	 * 		Hashtable contenant toutes les dates de fin des réservations
	 */
	@Override
	public void getBookingsDate(int timeTableId, Hashtable<Integer, Date> dateBegin, Hashtable<Integer, Date> dateEnd) {
		loadDB();
		if(tTDB.gettimetables().containsKey(timeTableId)){
			for(Entry<Integer, Booking> entry : tTDB.gettimetables().get(timeTableId).getBookings().entrySet()) {
				Integer cle = entry.getKey();
				Booking valeur = entry.getValue();
				
				dateBegin.put(cle, valeur.getDateBegin());
				dateEnd.put(cle, valeur.getDateEnd());

			}
		}
		
	}

	/**
	 * Supprime une réservation dans un emploi du temps donné
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps dont on veut supprimer la réservation
	 * 
	 * @param bookId
	 * 		identifiant de la réservation à supprimer
	 * 
	 * @return "true" si la réservation a bien été supprimée, "false" sinon
	 */
	@Override
	public boolean removeBook(int timeTableId, int bookId) {
		tTDB.loadDB();
		if(tTDB.gettimetables().get(timeTableId).UnbookTimeSlot(bookId)){
			tTDB.saveDB();
			return true ;
		}
		
		return false ;
	}

	/**
	 * Donne l'identifiant max des réservations d'un emploi du temps
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps
	 * 
	 * @return l'identifiant (int) max des réservations de cet emploi du temps
	 */
	@Override
	public int getBookingsMaxId(int timeTableId) {
		int max = 0 ;
		loadDB();
		if(tTDB.gettimetables().containsKey(timeTableId)){
			
			for(Entry<Integer, Booking> entry : tTDB.gettimetables().get(timeTableId).getBookings().entrySet()) {
				Booking valeur = entry.getValue();

				if(valeur.getBookId() > max){
					max = valeur.getBookId() ;
				}
			}

		}
		return max+1 ;
	}

	/**
	 * Sauvegarde la base de données d'emploi du temps et de salles
	 * 
	 * @return "true" si la sauvegarde s'est correctement déroulée, "false" sinon
	 */
	@Override
	public boolean saveDB() {
		return tTDB.saveDB();
	}

	/**
	 * Charge la base de données d'emploi du temps et de salles
	 * 
	 * @return "true" si le chargement s'est correctement déroulé, "false" sinon
	 */
	@Override
	public boolean loadDB() {
		return tTDB.loadDB();
	}

}
