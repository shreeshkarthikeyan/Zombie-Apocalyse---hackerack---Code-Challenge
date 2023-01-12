package zombie;

/**
 * Description
 * ===========
 * Coordinate class holds the information of everything present in the position (name of the zombie/creature, 
 *				its coordinates)
 * 
 * Concepts involved
 * =================
 * Getter & setter function
 *
 * @author - Shreesh Karthikeyan
 * @version - 24/07/2022
 */
public class Position {

	private String name;
	private Coordinates cordinates;
	
	/**
     * Constructor for objects of class Coordinates
     * 
     * @param name - name of the zombie / creature
     * @param cordinates - its coordinates
     * 
     */
	public Position(String name, Coordinates cordinates) {
		super();
		this.name = name;
		this.cordinates = cordinates;
	}

	/**
     * An accessor method to return the name of the zombie/creature
     * 
     * @return String an object containing the name of the zombie/creature
     * 
     */
	public String getName() {
		return name;
	}

	/**
     * A mutator method to set the name of the zombie/creature
     * 
     * @param name - the name of the zombie/creature
     * 
     */
	public void setName(String name) {
		this.name = name;
	}

	/**
     * An accessor method to return the coordinate of the zombie/creature
     * 
     * @return Coordinates an object containing the coordinate of the zombie/creature
     * 
     */
	public Coordinates getCordinates() {
		return cordinates;
	}

	/**
     * A mutator method to set the coordinates of the zombie/creature
     * 
     * @param cordinates - the coordinates of the zombie/creature
     * 
     */
	public void setCordinates(Coordinates cordinates) {
		this.cordinates = cordinates;
	}

	@Override
	public String toString() {
		return name + " moved to " + cordinates;
	}
	
	
}
