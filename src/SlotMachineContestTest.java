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
public class SlotMachineContestTest
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
    public void shouldNotAcceptNEqual2(){
        int[][] a= slotMachine.solve(2);
        int[][] b= new int[0][0];
        assertEquals(a, b);
        
    }
    
    @Test
    /**
     * SlotMachineContest must not accept n greater than 51
     */
    public void shouldNotAcceptNEqual51(){
        int[][] a= slotMachine.solve(51);
        int[][] b= new int[0][0];
        assertEquals(a, b);
        
    }
    
    @Test
    /**
     * the method solve must complete the contest
     */
    public void shouldCompleteContest(){
        int[][] solution = slotMachine.solve(50);
        assertTrue(slotMachine.slotMachine.isJackpot());
    }
    
    @Test
    /**
     * SlotMachine must have the same number of symbols and wheels.
     */
    public void shouldHaveTheSameNumberOfSymbolsAndWheels(){
        SlotMachine slotMachine = new SlotMachine(5);
        assertEquals(slotMachine.configuration().length, 5);
        assertEquals(slotMachine.symbols().length, 5);
    }
    
    @Test
    /**
     * 
     */
    public void shouldNotAcceptNumberNegative(){
        SlotMachine slotMachine = new SlotMachine(-1);
        assertEquals(slotMachine.configuration().length, 0);
        assertEquals(slotMachine.symbols().length, 0);
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