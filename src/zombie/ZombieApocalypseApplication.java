package zombie;
import java.util.*;
/**
 * Description
 * ===========
 * ZombieApocalypseApplication class includes main method and acts like a user interface class.
 *
 *
 * @author - Shreesh Karthikeyan
 * @version - 24/07/2022
 */
public class ZombieApocalypseApplication {

	/**
     * This main method starts the execution
     * 
     */
    public static void main(String []args) {
         Scanner sc = new Scanner(System.in);

         System.out.println("Zombie Apocalyse Code Challenge");
         System.out.println("===============================");
         System.out.println();

         int dimension;
         String zombieInputPosition;
         String creaturePositions;
         String zombieMovement;
	     
         System.out.println("Please enter the dimension of the grid: ");  
	     dimension = sc.nextInt();
	     sc.nextLine();
	     ZombieApocalyse zombieApocalyse = new ZombieApocalyse(dimension);
	     System.out.println("Please enter the initial position of the zombie: ");
	     zombieInputPosition = sc.nextLine();
	     Position zombiePosition = zombieApocalyse.initializeZombiePosition(zombieInputPosition);
	     System.out.println("Please enter the list of initial positions of the creature: ");
	     creaturePositions = sc.nextLine();
	     zombieApocalyse.initializeCreaturePosition(creaturePositions);
	     do {
	     System.out.println("Please enter the list of moves the zombie will make: ");
	     zombieMovement = sc.nextLine();
	     } while(!validationCheckOnMovement(zombieMovement));
	     zombieApocalyse.zombieMovement(zombieMovement, zombiePosition);
	     if(zombieApocalyse.zombieCount > 1) {
	    	 zombieApocalyse.checkForNewZombies(zombieMovement);
	     }
	     zombieApocalyse.printResult();
    }
    
    /**
     * This method is used to validate the Zombie Movement is with right keywords (RDLU)
     *
     * @param  zombieMovement - Zombie movement
     * @return  boolean - whether validMovement
     * 
     */
    private static boolean validationCheckOnMovement(String zombieMovement) {
    	boolean validMovement = false;
    	char[] movements = zombieMovement.toCharArray();
    	for(char c: movements) {
    		validMovement = false;
    		if(c == 'R' || c == 'r' || c == 'L' || c == 'l' ||
    		   c == 'U'	|| c == 'u' || c == 'D' || c == 'd') {
    			validMovement = true;
    		}
    		if(!validMovement) {
    			System.out.println("Please enter a valid movement which accepts only R(Right) L(Left) D(Down) U(Up)");
    		}
    	}
    	return validMovement;
    }
	
}
