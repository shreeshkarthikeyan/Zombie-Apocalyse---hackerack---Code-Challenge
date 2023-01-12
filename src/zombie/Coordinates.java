package zombie;

/**
 * Description
 * ===========
 * Coordinate class holds the information of everything present in the coordinate (xAxis, yAxis)
 * 
 * Concepts involved
 * =================
 * Getter & setter function
 *
 * @author - Shreesh Karthikeyan
 * @version - 25/07/2022
 */

public class Coordinates {

	private int xAxis;
	private int yAxis;
	
	/**
     * Constructor for objects of class Coordinates
     * 
     * @param xAxis - describes the x-axis coordinate of the world
     * @param yAxis - describes the y-axis coordinate of the world
     * 
     */
	public Coordinates(int xAxis, int yAxis) {
		super();
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	
	/**
     * An accessor method to return the x-axis
     * 
     * @return String an object containing the x-axis
     * 
     */
	public int getxAxis() {
		return xAxis;
	}
	
	/**
     * A mutator method to set the x-axis
     * 
     * @param xAxis - the x-axis of the coordinate
     * 
     */
	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}
	
	/**
     * An accessor method to return the y-axis
     * 
     * @return String an object containing the y-axis
     * 
     */
	public int getyAxis() {
		return yAxis;
	}
	
	/**
     * A mutator method to set the y-axis
     * 
     * @param yAxis - the y-axis of the coordinate
     * 
     */
	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}
	
	@Override
	public String toString() {
		return "(" + xAxis + ", " + yAxis + ")";
	}
	
	
}
