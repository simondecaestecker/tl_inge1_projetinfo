/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/

/**
 * Mod�le d'emploi du temps pour le projet
 */
package timeTableModel;
import java.util.Date;

/**
 * Classe repr�sentant une r�servation dans le projet
 * 
 * @author Antoine Leroy-Souque
 * @version 06/2016
 * 
 */

public class Booking {
	/**
	 * Identifiant de r�servation (int)
	 */
	private int bookId ;
	
	/**
	 * Identifiant du professeur (String)
	 */
	private String loginProf ;
	
	/**
	 * Date de d�but de la r�servation (Date)
	 */
	private Date dateBegin ;
	
	/**
	 * Date de fin de la r�servation (Date)
	 */
	private Date dateEnd ;	
	
	/**
	 * Identifiant de la salle � r�server (int)
	 */
	private int roomId ;

	/**
	 * Constructeur de Booking cr�ant une r�servation
	 * 
	 * @param dateBegin
	 * 		Date de d�but de la r�servation (Date)
	 * 
	 * @param dateEnd
	 * 		Date de fin de la r�servation (Date)
	 * 
	 * @param loginProf
	 * 		Login du professeur qui effectue la r�servation (String)
	 * 
	 * @param roomId
	 * 		Identifiant de la salle � r�server (int)
	 * 
	 * @param bookId
	 * 		Identifiant de la r�servation (int)
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
	 * 		identifiant de la nouvelle r�servation
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
	 * 		nouvelle date de d�but de la r�servation
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
	 * 		nouvelle date de fin de la r�servation
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
	 * Seter de l'attribut roomId..
	 *  
	 * @param newRoomId
	 * 		identifiant de la nouvelle salle
	 */
	public void setroomId(int newRoomId){
		this.bookId = newRoomId ;
	}

}
