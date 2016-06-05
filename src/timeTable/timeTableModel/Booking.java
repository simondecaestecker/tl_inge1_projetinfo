/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

/**
 * Modèle d'emploi du temps pour le projet
 */
package timeTableModel;
import java.util.Date;

/**
 * Classe représentant une réservation dans le projet
 * 
 * @author Antoine Leroy-Souque
 * @version 06/2016
 * 
 */

public class Booking {
	/**
	 * Identifiant de réservation (int)
	 */
	private int bookId ;
	
	/**
	 * Identifiant du professeur (String)
	 */
	private String loginProf ;
	
	/**
	 * Date de début de la réservation (Date)
	 */
	private Date dateBegin ;
	
	/**
	 * Date de fin de la réservation (Date)
	 */
	private Date dateEnd ;	
	
	/**
	 * Identifiant de la salle à réserver (int)
	 */
	private int roomId ;

	/**
	 * Constructeur de Booking créant une réservation
	 * 
	 * @param dateBegin
	 * 		Date de début de la réservation (Date)
	 * 
	 * @param dateEnd
	 * 		Date de fin de la réservation (Date)
	 * 
	 * @param loginProf
	 * 		Login du professeur qui effectue la réservation (String)
	 * 
	 * @param roomId
	 * 		Identifiant de la salle à réserver (int)
	 * 
	 * @param bookId
	 * 		Identifiant de la réservation (int)
	 */
	public Booking(Date dateBegin, Date dateEnd, String loginProf, int roomId, int bookId){
		this.bookId = bookId ;
		this.dateBegin = dateBegin ;
		this.dateEnd = dateEnd ;
		this.loginProf = loginProf ;
		this.roomId = roomId ;
	}

	/**
	 * Getter de l'attribut bookId.
	 * 
	 * @return bookId 
	 */
	public int getBookId(){
		return this.bookId;
	}

	/**
	 * Setter de l'attribut bookId.
	 *  
	 * @param newBookId
	 * 		identifiant de la nouvelle réservation
	 */
	public void setBookId(int newBookId){
		this.bookId = newBookId ;
	}

	/**
	 * Getter de l'attribut loginProf.
	 * 
	 * @return loginProf 
	 */
	public String getLoginProf(){
		return this.loginProf;
	}

	/**
	 * Setter de l'attribut loginProf. 
	 * 
	 * @param newLoginProf
	 * 		identifiant du login du professeur 
	 */
	public void setLoginProf(String newLoginProf){
		this.loginProf = newLoginProf;
	}

	/**
	 * Getter de l'attribut dateBegin.
	 * 
	 * @return dateBegin 
	 */
	public Date getDateBegin(){
		return this.dateBegin;
	}

	/**
	 * Setter de l'attribut dateBegin.
	 *  
	 * @param newDateBegin
	 * 		nouvelle date de début de la réservation
	 */
	public void setDateBegin(Date newDateBegin){
		this.dateBegin = newDateBegin;
	}

	/**
	 * Getter de l'attribut dateEnd.
	 * 
	 * @return dateEnd 
	 */
	public Date getDateEnd(){
		return this.dateEnd;
	}

	/**
	 * Setter de l'attribut dateEnd. 
	 * 
	 * @param newDateEnd 
	 * 		nouvelle date de fin de la réservation
	 */
	public void setDateEnd(Date newDateEnd){
		this.dateEnd = newDateEnd;
	}
	
	/**
	 * Getter de l'attribut roomId.
	 * 
	 * @return roomId 
	 */
	public int getroomId(){
		return this.roomId;
	}

	/**
	 * Seter de l'attribut roomId.
	 *  
	 * @param newRoomId
	 * 		identifiant de la nouvelle salle
	 */
	public void setroomId(int newRoomId){
		this.bookId = newRoomId ;
	}

}
