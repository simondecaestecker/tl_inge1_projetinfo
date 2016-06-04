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
 * Cette classe est le contr�leur d'emplois du temps.
 * Elle contient un attribut correspondant � la base de donn�es d'emplois (TimeTableDB).
 * Elle contient toutes les fonctions de l'interface ITimeTableController.
 * 
 * @author Antoine Leroy-Souque
 * @version 06/2016
 * 
 */

public class TimeTableController implements ITimeTableController{
	/**
	 * Contient une instance de base de donn�es d'emplois du temps
	 * 
	 */
	TimeTableDB tTDB;
	
	/**
	 * Constructeur de contr�leur d'emplois du temps cr�ant la base de donn�es d'emplois du temps
	 * 
	 * @param tTfile
	 * 		Fichier XML contenant la base de donn�es d'emplois du temps
	 */
	public TimeTableController(String tTfile) {
		TimeTableDB tTDB=new TimeTableDB(tTfile);
		this.tTDB=tTDB;
	}

	/**
	 * Getter du login du professeur correspondant � l'identifiant d'une r�servation contenue dans un emploi du temps identifi�
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps
	 * 
	 * @param bookId
	 * 		identifiant de la r�servation
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
	 * Donne les identifiants des salles dans un tableau de caract�res
	 * 
	 * @return tableau de caract�res contenant les identifiants de toutes les salles de la base de donn�es
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
	 * Donne les informations sur les salles de la base de donn�es (identifiant + capacit�) dans un tableau de caract�res
	 * 
	 * @return tableau de caract�res contenant les informations sur toutes les salles de la base de donn�es
	 */
	@Override
	public String[] roomsToString() {
		String rooms[] =  new String[tTDB.getrooms().size()] ;
		int i = 0 ;
		
		for(Entry<Integer, Room> entry : tTDB.getrooms().entrySet()) {
			Room valeur = entry.getValue();
			
			rooms[i] = "Room " + Integer.toString(valeur.getRoomId()) + " | capacit� de " + Integer.toString(valeur.getCapacity()) + " personnes";
			i++ ;
		}
		
		return rooms ;
	}

	/**
	 * Donne les identifiants des emplois du temps de la base de donn�es dans un tableau de caract�res
	 * 
	 * @return tableau de caract�res contenant les identifiants de tous les emplois du temps de la base de donn�es
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
	 * Donne les identifiants de toutes les r�servations d'un emploi du temps donn�
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps dont on veut r�cup�rer les r�servations
	 * 
	 * @return tableau de caract�res contenant les identifiants de toutes les r�servations d'un emploi du temps
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
	 * Ajoute une salle dans la base de donn�es
	 * 
	 * @param roomId
	 * 		identifiant de la nouvelle salle � ajouter
	 * 
	 * @param capacity
	 * 		capacit� maximale de la salle � ajouter
	 * 
	 * @return "true" si la salle a �t� ajout�e � la base de donn�es, "false" sinon
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
	 * Supprime une salle de la base de donn�es
	 * 
	 * @param roomId
	 * 		identifiant de la salle � supprimer
	 * 
	 * @return "true" si la salle a bien �t� supprim�e, "false" sinon
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
	 * Getter de l'identifiant de la salle d'une r�servation pr�cise d'un emploi du temps donn�
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps
	 * 
	 * @param bookId
	 * 		identifiant de la r�servation
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
	 * 		identifiant de l'emploi du temps � cr�er
	 * 
	 * @return "true" si l'emploi du temps a bien �t� cr��, "false" sinon
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
	 * 		identifiant de l'emploi du temps � supprimer
	 * 
	 * @return "true" si l'emploi du temps a bien �t� supprim�, "false" sinon
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
	 * Ajoute une r�servation dans un emploi du temps
	 * 
	 * @param timeTableId 
	 * 		identifiant de l'emploi du temps dans lequel on veut ajouter une r�servation
	 * 
	 * @param bookingId
	 * 		identifiant de la nouvelle r�servation
	 * 
	 * @param login
	 * 		login du professeur qui effectue la r�servation
	 * 
	 * @param dateBegin
	 * 		Date de d�but de la nouvelle r�servation
	 * 
	 * @param dateEnd
	 * 		Date de fin de la nouvelle r�servation
	 * 
	 * @param roomId
	 * 		identifiant de la salle � r�server
	 * 
	 * @return "true" si la r�servation a bien �t� effectu�e, "false" sinon
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
	 * R�cup�re les dates de d�but et de fin de toutes les r�servations d'un emploi du temps
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps dont on veut r�cup�rer les dates
	 * 
	 * @param dateBegin
	 * 		Hashtable contenant toutes les dates de d�but des r�servations
	 * 
	 * @param dateEnd
	 * 		Hashtable contenant toutes les dates de fin des r�servations
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
	 * Supprime une r�servation dans un emploi du temps donn�
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps dont on veut supprimer la r�servation
	 * 
	 * @param bookId
	 * 		identifiant de la r�servation � supprimer
	 * 
	 * @return "true" si la r�servation a bien �t� supprim�e, "false" sinon
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
	 * Donne l'identifiant max des r�servations d'un emploi du temps
	 * 
	 * @param timeTableId
	 * 		identifiant de l'emploi du temps
	 * 
	 * @return l'identifiant (int) max des r�servations de cet emploi du temps
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
	 * Sauvegarde la base de donn�es d'emploi du temps et de salles
	 * 
	 * @return "true" si la sauvegarde s'est correctement d�roul�e, "false" sinon
	 */
	@Override
	public boolean saveDB() {
		return tTDB.saveDB();
	}

	/**
	 * Charge la base de donn�es d'emploi du temps et de salles
	 * 
	 * @return "true" si le chargement s'est correctement d�roul�, "false" sinon
	 */
	@Override
	public boolean loadDB() {
		return tTDB.loadDB();
	}

}
