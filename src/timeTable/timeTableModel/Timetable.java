/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;
import java.util.Date;
import java.util.HashMap;

/**
 * Classe repr�sentant un emploi du temps dans le projet
 * 
 * @author Antoine Leroy-Souque
 * @version 06/2016
 * 
 */

public class Timetable {
	/**
	 * Identifiant de l'emploi du temps
	 */
	private int timeTableId;
	
	/**
	 * Contient des instances de r�servations associ�es respectivement � un identifiant entier
	 */
	private HashMap<Integer, Booking> bookings ;
	
	/**
	 * Constructeur d'emploi du temps cr�ant la base de donn�es de r�servations
	 * 
	 * @param timeTableId
	 * 		Identifiant (int) de l'emploi du temps
	 * 
	 */
	public Timetable(int timeTableId){
		this.timeTableId = timeTableId ;
		this.bookings = new HashMap<Integer, Booking>() ;
	}
	
	/**
     * R�serve un cr�neau dans l'emploi du temps
     * 
     * @return bool�en "true"
     * 
	 * @param BeginDate
	 * 		Date de d�but de la r�servation
	 * 
	 * @param EndDate
	 * 		Date de fin de la r�servation
	 * 
	 * @param Proflogin
	 * 		Cha�ne de caract�res correspondant � l'identifiant du professeur
	 * 
	 * @param RoomId
	 * 		Nombre entier donnant l'identifiant de la salle
	 * 
	 * @param BookId
	 * 		Nombre entier donnant l'identifiant de la r�servation
	 */
	public boolean BookTimeSlot(Date BeginDate, Date EndDate, String Proflogin, int RoomId, int BookId){
			Booking Book = new Booking(BeginDate, EndDate, Proflogin, RoomId, BookId) ;
			this.bookings.put(BookId, Book);
			return true ;	
	}

	/**
     * Supprime un cr�neau pr�c�demment r�serv� dans l'emploi du temps
     * 
     * @return bool�en "true" si la r�servation a bien �t� supprim�e, "false" sinon
     * 
	 * @param bookId
	 * 		Nombre entier correspondant au num�ro de la r�servation qu'on d�sire supprimer
	 * 
	 */
	public boolean UnbookTimeSlot(int bookId){
		if(this.bookings.containsKey(bookId)){
			this.bookings.remove(bookId);
			return true ;
		}
		return false ;
	}

	/**
	 * Getter de l'attribut timeTableId.
	 * 
	 * @return timeTableId 
	 */
	public int getTimeTableId(){
		return this.timeTableId;
	}
	
	/**
	 * Setter de l'attribut timeTableId.
	 * 
	 * @param newTimeTableId
	 * 		identifiant du nouvel emploi du temps 
	 */
	public void setTimeTableId(int newTimeTableId){
	    this.timeTableId = newTimeTableId;
	}

	/**
	 * Getter de l'attribut de base de donn�es de r�servations
	 * 
	 * @return bookings 
	 */
	public HashMap<Integer, Booking> getBookings(){
		return this.bookings;
	}

}
