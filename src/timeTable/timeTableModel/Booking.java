/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.util.Date;

/**
 * Description of Booking.
 * 
 * @author aleroysouque
 */
public class Booking {
	/**
	 * Attributs
	 */
	private int bookId ;
	private int loginProf ;
	private Date dateBegin ;
	private Date dateEnd ;	

	/**
	 * Constructeur
	 */
	public Booking() {
		super();
	}

	/**
	 * Méthodes
	 */
	public void BookTimeSlot() {
		// Start of user code for method BookTimeSlot
		// End of user code
	}

	/**
	 * Description of the method UnbookTimeSlot.
	 */
	public void UnbookTimeSlot() {
		// Start of user code for method UnbookTimeSlot
		// End of user code
	}

	/**
	 * Returns bookId.
	 * @return bookId 
	 */
	public Object getBookId() {
		return this.bookId;
	}

	/**
	 * Sets a value to attribute bookId. 
	 * @param newBookId 
	 */
	public void setBookId(int newBookId) {
		this.bookId = newBookId ;
	}

	/**
	 * Returns loginProf.
	 * @return loginProf 
	 */
	public Object getLoginProf() {
		return this.loginProf;
	}

	/**
	 * Sets a value to attribute loginProf. 
	 * @param newLoginProf 
	 */
	public void setLoginProf(int newLoginProf) {
		this.loginProf = newLoginProf;
	}

	/**
	 * Returns dateBegin.
	 * @return dateBegin 
	 */
	public Object getDateBegin() {
		return this.dateBegin;
	}

	/**
	 * Sets a value to attribute dateBegin. 
	 * @param newDateBegin 
	 */
	public void setDateBegin(Date newDateBegin) {
		this.dateBegin = newDateBegin;
	}

	/**
	 * Returns dateEnd.
	 * @return dateEnd 
	 */
	public Object getDateEnd() {
		return this.dateEnd;
	}

	/**
	 * Sets a value to attribute dateEnd. 
	 * @param newDateEnd 
	 */
	public void setDateEnd(Date newDateEnd) {
		this.dateEnd = newDateEnd;
	}

}
