/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;
import java.util.Date;
import java.util.HashMap;

/**
 * Classe représentant un emploi du temps dans le projet
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
	 * Contient des instances de réservations associées respectivement à un identifiant entier
	 */
	private HashMap<Integer, Booking> bookings ;
	
	/**
	 * Constructeur d'emploi du temps créant la base de données de réservations
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
     * Réserve un créneau dans l'emploi du temps
     * 
     * @return booléen "true"
     * 
	 * @param BeginDate
	 * 		Date de début de la réservation
	 * 
	 * @param EndDate
	 * 		Date de fin de la réservation
	 * 
	 * @param Proflogin
	 * 		Chaîne de caractères correspondant à l'identifiant du professeur
	 * 
	 * @param RoomId
	 * 		Nombre entier donnant l'identifiant de la salle
	 * 
	 * @param BookId
	 * 		Nombre entier donnant l'identifiant de la réservation
	 */
	public boolean BookTimeSlot(Date BeginDate, Date EndDate, String Proflogin, int RoomId, int BookId){
			Booking Book = new Booking(BeginDate, EndDate, Proflogin, RoomId, BookId) ;
			this.bookings.put(BookId, Book);
			return true ;	
	}

	/**
     * Supprime un créneau précédemment réservé dans l'emploi du temps
     * 
     * @return booléen "true" si la réservation a bien été supprimée, "false" sinon
     * 
	 * @param bookId
	 * 		Nombre entier correspondant au numéro de la réservation qu'on désire supprimer
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
	 * Getter de l'attribut de base de données de réservations
	 * 
	 * @return bookings 
	 */
	public HashMap<Integer, Booking> getBookings(){
		return this.bookings;
	}

}
