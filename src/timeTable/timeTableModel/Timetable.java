/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.util.HashMap;


// Start of user code (user defined imports)

// End of user code

/**
 * Description of Timetable.
 * 
 * @author aleroysouque
 */
public class Timetable {
	/**
	 * Attributs
	 */
	private int timeTableId;
	public HashMap<Object, Booking> bookings ;

	
	/**
	 * Constructeur
	 */
	public Timetable(int newtimeTableId) {
		this.timeTableId = newtimeTableId ;
		bookings = new HashMap<Object, Booking>() ;
	}
	

	/**
	 * Returns timeTableId.
	 * @return timeTableId 
	 */
	public int getTimeTableId() {
		return this.timeTableId;
	}
	
	/**
	 * Sets a value to attribute timeTableId. 
	 * @param newTimeTableId 
	 */
	public void setTimeTableId(int newTimeTableId) {
	    this.timeTableId = newTimeTableId;
	}

	/**
	 * Returns bookings.
	 * @return bookings 
	 */
	public HashMap<Object, Booking> getBookings() {
		return this.bookings;
	}

}
