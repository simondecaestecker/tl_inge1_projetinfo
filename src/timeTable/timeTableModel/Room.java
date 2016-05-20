/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;

import java.util.HashSet;


/**
 * Description of Room.
 * 
 * @author aleroysouque
 */
public class Room {
	
	/**
	 * Attributs
	 */
	private Object capacity ;
	private Object RoomId ;
	public HashSet<Booking> bookings = new HashSet<Booking>();


	/**
	 * Constructeur
	 */
	public Room() {
		super();
	}

	/**
	 * Méthodes
	 */
	public void addRoom() {
		// Start of user code for method addRoom
		// End of user code
	}

	/**
	 * Description of the method removeRoom.
	 */
	public void removeRoom() {
		// Start of user code for method removeRoom
		// End of user code
	}

	// Start of user code (user defined methods for Room)

	// End of user code
	/**
	 * Returns capacity.
	 * @return capacity 
	 */
	public Object getCapacity() {
		return this.capacity;
	}

	/**
	 * Sets a value to attribute capacity. 
	 * @param newCapacity 
	 */
	public void setCapacity(Object newCapacity) {
		this.capacity = newCapacity;
	}

	/**
	 * Returns RoomId.
	 * @return RoomId 
	 */
	public Object getRoomId() {
		return this.RoomId;
	}

	/**
	 * Sets a value to attribute RoomId. 
	 * @param newRoomId 
	 */
	public void setRoomId(Object newRoomId) {
		this.RoomId = newRoomId;
	}

	/**
	 * Returns bookings.
	 * @return bookings 
	 */
	public HashSet<Booking> getBookings() {
		return this.bookings;
	}

}
