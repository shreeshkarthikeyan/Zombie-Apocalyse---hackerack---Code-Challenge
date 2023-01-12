package zombie;

import java.util.ArrayList;

/**
 * Description
 * ===========
 * ZombieApocalyse class holds the information of the position and final creatures' positions
 * and final zombies' positions and the world dimension and tabular display of world.
 * 
 * @author - Shreesh Karthikeyan
 * @version - 24/07/2022
 */
public class ZombieApocalyse {

	private int dimensionGrid; // World size
	private String[][] worldDimension; // Tabular display of world
	public ArrayList<Position> creaturePosition; // Creature positions
	private ArrayList<Position> creatureAffectedPosition; // Affected Creature position (to track of zombies)
	public ArrayList<Position> zombiePosition; // Final zombie positions
	private Position zombie0Position; // Zombie 0 position
	public int zombieCount; // To track number of zombies
	
	/**
     * Default constructor
     * 
     */
	public ZombieApocalyse(int dimensionGrid) {
		
		this.dimensionGrid = dimensionGrid;
		worldDimension = new String[dimensionGrid][dimensionGrid];
		creatureAffectedPosition = new ArrayList<Position>();
		zombiePosition = new ArrayList<Position>();
		worldDimensionBoardInitialization();
	}
	
	/**
     * This method is used to initialize the tabular display of the world dimension
     *
     */
	public void worldDimensionBoardInitialization() {
		for(int i = 0; i < worldDimension.length; i++) {
			for(int j = 0; j < worldDimension[i].length; j++) {
				worldDimension[i][j] = "-";
			}
		}
	}
	
	/**
     * This method is used to visualize the tabular display of the world dimension
     *
     */
    public void worldDimensionBoardDisplay() {
        int spaceGap = 10;   //Space between next elements
        System.out.println("World Representation");
        System.out.println("====================");
        System.out.println();
        for (int i = 0; i < worldDimension.length; i++) {
            for (int j = 0; j < worldDimension[i].length; j++) {
               System.out.print(worldDimension[i][j]);
               for(int k = worldDimension[i][j].length(); k <= spaceGap;k++) {
            	   System.out.print(" ");
               }
            }
            System.out.println();
        }
    }
  
    /**
     * This method is used to initialize the Zombie 0 position from the input (the initial position of the zombie)
     *
     * @param  zombiePosition - the initial position of the zombie
     * @return  zombie0Position - the initial position of the zombie
     * 
     */
	public Position initializeZombiePosition(String zombiePosition) {
		zombie0Position = null;
		try {
			String zombieName = "Z0";
			Coordinates zombie0Coordinates = convertToCoordinateFromInput(zombiePosition);
			worldDimension[zombie0Coordinates.getyAxis()][zombie0Coordinates.getxAxis()] = zombieName;		
			zombie0Position = new Position(zombieName, zombie0Coordinates);
			zombieCount++;
			worldDimensionBoardDisplay();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return zombie0Position;
	}
	
	/**
     * This method is used to initialize the creatures positions' from the input (a list of initial positions of the creatures)
     *
     * @param  zombiePosition - a list of initial positions of the creatures
     * 
     */
	public void initializeCreaturePosition(String creaturePositions) {
		
		try {
			creaturePosition = new ArrayList<Position>();
			String[] creaturePositionList = creaturePositions.split(" ");
			for(int i=0;i<creaturePositionList.length;i++) {
				String creatureName = "C"+(i+1);
				
				Coordinates creatureCoordinates = convertToCoordinateFromInput(creaturePositionList[i]);
				creaturePosition.add(new Position(creatureName, creatureCoordinates));
				
				//Empty Coordinate
				if(worldDimension[creatureCoordinates.getyAxis()][creatureCoordinates.getxAxis()] == "-") {
					worldDimension[creatureCoordinates.getyAxis()][creatureCoordinates.getxAxis()] = creatureName;
				} else if(worldDimension[creatureCoordinates.getyAxis()][creatureCoordinates.getxAxis()].contains("Z0")) { // Coordinate with zombie
					String zombieName = "Z"+zombieCount;
					Position pos = new Position(zombieName, creatureCoordinates);
					
					creatureAffected(zombie0Position, pos);
					worldDimension[creatureCoordinates.getyAxis()][creatureCoordinates.getxAxis()] += ","+zombieName;
					zombieCount++;
				} else {
					worldDimension[creatureCoordinates.getyAxis()][creatureCoordinates.getxAxis()] += ","+creatureName;
				}
				
			}
			worldDimensionBoardDisplay();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to convert the input into Coordinate object
     *
     * @param input  - String - the position of the zombie
     * @return Coordinates the coordinates object from the input
     * 
     */
    private Coordinates convertToCoordinateFromInput(String input) {
		Coordinates result = null; 
    	try {
			input = input.replace("(", "").replace(")", "");
			int xAxis = Integer.parseInt(input.split(",")[0]);
			int yAxis = Integer.parseInt(input.split(",")[1]);
			result = new Coordinates(xAxis, yAxis);
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    	
    }
	
	/**
     * This method is used to add the the position of the the creature in 'creatureAffectedPosition' list
     * when the zombie comes to the creature position and removes the coordinates of the creature affected.
     *
     * @param zombie  - Position - the position of the zombie
     * @param positionToAddInCreatureAffected  - Position - the position of the creature that gets affected
     * 
     */
	private void creatureAffected(Position zombie, Position positionToAddInCreatureAffected) {
		creatureAffectedPosition.add(positionToAddInCreatureAffected);
		System.out.println(zombie.getName() + " infected the creature at "+ zombie.getCordinates());
		removeCreatureCoordinates(zombie.getCordinates());
	}
	
	/**
     * This method is used to remove the Coordinate of the creature affected from the 'creaturePosition' list
     *
     * @param coordinatesToDelete - Coordinates - the coordinates of the creature to remove
     * 
     */
    private void removeCreatureCoordinates(Coordinates coordinatesToDelete) {
    	
    	for(Position p : creaturePosition) {
			if(p.getCordinates() != null) {
				if((p.getCordinates().getxAxis() == coordinatesToDelete.getxAxis()) && 
						(p.getCordinates().getxAxis() == coordinatesToDelete.getxAxis())) {
					p.setCordinates(null);
					break;
				}
			}
		}
    }
	
	
	/**
     * This method is used to move the zombies with the list of moves the zombies provided from the input
     *
     * @param  zombieMovement - a list of moves the zombies will make
     * @param  zombiePos - A zombie, which takes its movement 
     * 
     */
	public void zombieMovement(String zombieMovement, Position zombiePos) {
		try {
			System.out.println("========");
			System.out.println("Zombie "+ zombiePos.getName() +" started its movement");
			char[] movement = zombieMovement.toCharArray();
			for(int i = 0; i < movement.length; i++) {
				Coordinates zombieCoordinates= zombiePos.getCordinates();
				
				if(worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].contains(",")) {
					worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] = 
						worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].replace(zombiePos.getName()+",", "").replace(","+zombiePos.getName(), "");
				} else {
					worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] = 
							worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].replace(zombiePos.getName(), "");
				}
				if(worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].length() == 0) {
					worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] = "-";
				}
				switch(movement[i]) {
					//Down
					case 'd':
					case 'D':
		            {	            	
		                if(!isZombieOnBoundary(zombieCoordinates ,"yAxis", worldDimension.length - 1)) { //Check wether zombie is on edge of the world
		                	zombieCoordinates.setyAxis(zombieCoordinates.getyAxis() + 1);
		                } else { //Zombie moved through the edge of the grid 
		                	zombieCoordinates.setyAxis(0);
		                }
		                break;
		            }
		            //Up
					case 'u':
		            case 'U':
		            {
		                if(!isZombieOnBoundary(zombieCoordinates ,"yAxis", 0)) { //Check wether zombie is on edge of the world
		                	zombieCoordinates.setyAxis(zombieCoordinates.getyAxis() - 1);
		                } else { //Zombie moved through the edge of the grid 
		                	zombieCoordinates.setyAxis(worldDimension.length - 1);
		                }
		                break;
		            }
		            // Right
		            case 'r' :
		            case 'R' :
		            {
		                if(!isZombieOnBoundary(zombieCoordinates, "xAxis", worldDimension[zombieCoordinates.getyAxis()].length - 1)) { //Check wether zombie is on edge of the world
		                	zombieCoordinates.setxAxis(zombieCoordinates.getxAxis() + 1);
		                } else { //Zombie moved through the edge of the grid 
		                	zombieCoordinates.setxAxis(0);
		                }
		                break;
		            }
		            //Left
		            case 'l' :
		            case 'L' :
		            {
		                if(!isZombieOnBoundary(zombieCoordinates, "xAxis", 0)) { //Check wether zombie is on edge of the world
		                	zombieCoordinates.setxAxis(zombieCoordinates.getxAxis() - 1);
		                } else { //Zombie moved through the edge of the grid 
		                	zombieCoordinates.setxAxis(worldDimension[zombieCoordinates.getyAxis()].length - 1);
		                }
		                break;
		            }
		        }
				
				//Zombie moving to an empty coordinate
				if(worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] == "-") {
					worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] = zombiePos.getName();
				}
				//Zombie moving into a creature coordinate
				else if(worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].contains("C")) {
					//Multiple creatures can be present in the position
					if(worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].contains(",")) {
					
						worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] += ","+zombiePos.getName();
						
						String[] positions = worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].split(",");
						for(String s: positions) {
							if(s.contains("C")) {
								String zombieName = "Z"+zombieCount;
								Coordinates c= new Coordinates(zombieCoordinates.getxAxis(), zombieCoordinates.getyAxis());
								Position pos = new Position(zombieName, c);
								creatureAffected(zombiePos, pos);
								worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] = 
										worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].replace(s, zombieName);
								zombieCount++;
							}
						}
					}
					//Single creature present in the position
					else {
						String zombieName = "Z"+zombieCount;
						Coordinates c= new Coordinates(zombieCoordinates.getxAxis(), zombieCoordinates.getyAxis());
						Position pos = new Position(zombieName, c);
						creatureAffected(zombiePos, pos);
						worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] = 
								zombiePos.getName()+","+zombieName;
						zombieCount++;
					}		
				}
				//Zombie moving into another zombie coordinate
				else if(worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()].contains("Z")) {
					worldDimension[zombieCoordinates.getyAxis()][zombieCoordinates.getxAxis()] += ","+zombiePos.getName();
				}
				//worldDimensionBoardDisplay();
	            System.out.println(zombiePos);
			}
			zombiePosition.add(zombiePos);
			System.out.println("Zombie "+ zombiePos.getName() +" finished its movement");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to validate whether the the zombie is at the edge of the world
     *
     * @param c - Coordinate - coordinate to check with the number
     * @param axis - String - Whether x-Axis or y-Axis 
     * @param number - int - validation number
     * @return - boolean
     */
    public boolean isZombieOnBoundary(Coordinates c, String axis, int number){
        if(axis.equals("yAxis")) {
            if(c.getyAxis() == number){
                return true;
            }
        }
        else {
            if(c.getxAxis() == number){
                return true;
            }
        }
        return false;
    }

    /**
     * This method is used to check whether any new zombies are yet to move
     *
     * @param zombieMovement  - String - a list of moves the zombies will make
     * 
     */
	public void checkForNewZombies(String zombieMovement) {	
		while(true) {
			if(creatureAffectedPosition.size() == 0) {
				break;
			}
			Position newZom = creatureAffectedPosition.get(0);
			zombieMovement(zombieMovement, newZom);
			creatureAffectedPosition.remove(newZom);
		}	
	}
    
    /**
     * This method is used to return the final list of the zombies' position and creatures' positions
     *
     * @return result - String - displays the zombies' position with creatures' position after their movement
     * 
     */
	public String printResult() {
		String result = "";
		try {
			result += "zombies' positions:\n";
			for(Position p: zombiePosition)
				result += p.getCordinates();
			result += "\ncreatures' positions:\n";
			boolean isAnyCreatureFound = false;
			for(Position p: creaturePosition) {
				if(p.getCordinates() != null) {
					isAnyCreatureFound = true;
					result += p.getCordinates();
				}
			}
			if(!isAnyCreatureFound)
				result += "none";
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
