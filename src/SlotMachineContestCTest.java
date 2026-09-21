import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SlotMachineCC2Test.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class SlotMachineContestCTest
{
    private SlotMachineContest slotMachine;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        slotMachine = new SlotMachineContest();
    }
    
    @Test
    /**
     * SlotMachineContest must not accept n less than 2
     */
    public void accordingGrMsShouldNotAcceptNEqual2(){
        int[][] a= slotMachine.solve(2);
        int[][] b= new int[0][0];
        assertEquals(a, b);
        
    }
    
    @Test
    /**
     * SlotMachineContest must not accept n greater than 51
     */
    public void accordingGrMsShouldNotAcceptNEqual51(){
        int[][] a= slotMachine.solve(51);
        int[][] b= new int[0][0];
        assertEquals(a, b);
        
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
        
    }
}