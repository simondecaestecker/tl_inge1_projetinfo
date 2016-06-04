/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package timeTableModel;

/**
 * Classe repr�sentant une salle dans le projet
 * 
 * @author Antoine Leroy-Souque
 * @version 06/2016
 * 
 */

public class Room {
	/**
	 * Capacit� maximale de la salle (int)
	 */
	private int capacity ;
	
	/**
	 * Identifiant de la salle (int)
	 */
	private int roomId ;

	/**
	 * Constructeur de Room cr�ant une salle
	 * 
	 * @param roomId
	 * 		Identifiant de la salle (int)
	 * 
	 * @param capacity
	 * 		Capacit� maximale de la salle (int)
	 */
	public Room(int roomId , int capacity) {
		this.capacity = capacity ;
		this.roomId = roomId ;
	}
	
	/**
	 * Getter de l'attribut capacity.
	 * 
	 * @return capacity 
	 */
	public int getCapacity() {
		return this.capacity;
	}

	/**
	 * Setter de l'attribut capacity. 
	 * 
	 * @param newCapacity
	 * 		capacit� de la nouvelle salle 
	 */
	public void setCapacity(int newCapacity) {
		this.capacity = newCapacity;
	}

	/**
	 * Getter de l'attribut roomId.
	 * 
	 * @return roomId 
	 */
	public int getRoomId() {
		return this.roomId;
	}

	/**
	 * Setter de l'attribut roomId.
	 * 
	 * @param newRoomId
	 * 		identifiant de la nouvelle salle
	 */
	public void setRoomId(int newRoomId) {
		this.roomId = newRoomId;
	}

}
