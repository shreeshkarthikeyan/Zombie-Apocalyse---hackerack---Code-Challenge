package zombie;



import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZombieApocalypseApplicationTest {

	ZombieApocalypseApplication zombieApocalypseApplication;
	ZombieApocalyse zombieApocalyse;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    	zombieApocalypseApplication = new ZombieApocalypseApplication();
    	zombieApocalyse = new ZombieApocalyse(4);
    }

    /** 
     * Test case 1
     * 
     * Input
     * =====
     * 
     * Zombie Initial Position -> (3,1)
     * Creature Intial Position -> (0,1) (1,2) (1,1)
     * Movement -> RDRU
     * 
     * Output
     * ======
     * 
     * zombies’ positions:
	 * (1,1)(2,1)(3,2)(3,1)
	 * creatures’ positions:
	 * none 
     */
    @Test
    public void testCase1()
    {
    	Position zombie = zombieApocalyse.initializeZombiePosition("(3,1)");
    	zombieApocalyse.initializeCreaturePosition("(0,1) (1,2) (1,1)");
    	zombieApocalyse.zombieMovement("RDRU", zombie);
    	zombieApocalyse.checkForNewZombies("RDRU");
    	String expectedResult = "zombies' positions:\n";
    	expectedResult += "(1, 1)(2, 1)(3, 2)(3, 1)\n";
    	expectedResult += "creatures' positions:\n";
    	expectedResult += "none";
        assertEquals(expectedResult, zombieApocalyse.printResult());
    }
    
    /** 
     * Test case 2
     * 
     * Input
     * =====
     * 
     * Zombie Initial Position -> (3,1)
     * Creature Intial Position -> (2,2)
     * Movement -> RDRU
     * 
     * Output
     * ======
     * 
     * zombies’ positions:
	 * (1,1)
	 * creatures’ positions:
	 * (2,2) 
     */
    @Test
    public void testCase2()
    {
    	Position zombie = zombieApocalyse.initializeZombiePosition("(3,1)");
    	zombieApocalyse.initializeCreaturePosition("(2,2)");
    	zombieApocalyse.zombieMovement("RDRU", zombie);
    	zombieApocalyse.checkForNewZombies("RDRU");
    	String expectedResult = "zombies' positions:\n";
    	expectedResult += "(1, 1)\n";
    	expectedResult += "creatures' positions:\n";
    	expectedResult += "(2, 2)";
        assertEquals(expectedResult, zombieApocalyse.printResult());
    }
    
    /** 
     * Test case 3
     * 
     * Input
     * =====
     * 
     * Zombie Initial Position -> (3,1)
     * Creature Intial Position -> (3,1) (0,1) (1,2) (1,1)
     * Movement -> RDRU
     * 
     * Output
     * ======
     * 
     * zombies’ positions:
	 * (1, 1)(2, 1)(2, 1)(3, 2)(3, 1)
	 * creatures’ positions:
	 * none 
     */
    @Test
    public void testCase3()
    {
    	Position zombie = zombieApocalyse.initializeZombiePosition("(3,1)");
    	zombieApocalyse.initializeCreaturePosition("(3,1) (0,1) (1,2) (1,1)");
    	zombieApocalyse.zombieMovement("RDRU", zombie);
    	zombieApocalyse.checkForNewZombies("RDRU");
    	String expectedResult = "zombies' positions:\n";
    	expectedResult += "(1, 1)(1, 1)(2, 1)(3, 2)(3, 1)\n";
    	expectedResult += "creatures' positions:\n";
    	expectedResult += "none";
        assertEquals(expectedResult, zombieApocalyse.printResult());
    }
    
    /** 
     * Test case 4
     * 
     * Input
     * =====
     * 
     * Zombie Initial Position -> (3,1)
     * Creature Intial Position -> (3,1) (3,1) (0,1) (0,1) (1,2) (1,1) (2,2)
     * Movement -> RDRU
     * 
     * Output
     * ======
     * 
     * zombies’ positions:
	 * (1, 1)(1, 1)(1, 1)(2, 1)(2, 1)(3, 2)(3, 1)(0, 2)
	 * creatures’ positions:
	 * none 
     */
    @Test
    public void testCase4()
    {
    	Position zombie = zombieApocalyse.initializeZombiePosition("(3,1)");
    	zombieApocalyse.initializeCreaturePosition("(3,1) (3,1) (0,1) (0,1) (1,2) (1,1) (2,2)");
    	zombieApocalyse.zombieMovement("RDRU", zombie);
    	zombieApocalyse.checkForNewZombies("RDRU");
    	String expectedResult = "zombies' positions:\n";
    	expectedResult += "(1, 1)(1, 1)(1, 1)(2, 1)(2, 1)(3, 2)(3, 1)(0, 2)\n";
    	expectedResult += "creatures' positions:\n";
    	expectedResult += "none";
        assertEquals(expectedResult, zombieApocalyse.printResult());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
