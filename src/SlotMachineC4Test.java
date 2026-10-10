import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SlotMachineC4Test.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class SlotMachineC4Test
{
    private SlotMachine slotMachine;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        slotMachine = new SlotMachine();
        
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addSymbol(3, "green");
        slotMachine.addSymbol("ephemeral", 4, "yellow");
        slotMachine.addSymbol("shy", 5, "purple");
        
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.addWheel("Rebel", 4);
        slotMachine.addWheel(5);
        slotMachine.addWheel("Lefty", 6);
        
    }
    
    /**
     * 
     */
    @Test
    public void shouldBeTheSameColorAsTheWheelOnTheLeft(){
        slotMachine.spin();
        String[] a = slotMachine.configuration();
    
        assertEquals(a[5], a[4]);
        
    }
    
    /**
     * 
     */
    @Test
    public void shouldRebelNotBeLocked(){
        slotMachine.lock(4);
        assertTrue(slotMachine.getWheel(4).getIsLocked());
    }
    
    /**
     * 
     */
    @Test
    public void shouldRebelNotSwap(){
        slotMachine.swap(3, 4);
        assertFalse(slotMachine.getWheel(4).canSwap());
    }
    
    /**
     * 
     */
    @Test
    public void shouldRebelNotBeDelete(){
        slotMachine.delWheel(4);
        assertFalse(slotMachine.getWheel(4).canBeDelete());
    }
    
    /**
     * 
     */
    @Test
    public void shouldSplattyMoveWheelsNearToHim(){
        slotMachine.addWheel("Splatty", 4);
        String[] beforeSpin = slotMachine.configuration();
        slotMachine.spin(4,1);
        String[] afterSpin = slotMachine.configuration();
        
        assertTrue(beforeSpin[3] != afterSpin[3]);
    }
    
    @Test
    public void shouldSplattyMoveOtherSplatties(){
        slotMachine.addWheel("Splatty", 4);
        slotMachine.addWheel("Splatty", 5);
        slotMachine.spin(new String[]{"red", "red", "red", "red","blue", "green", "yellow", "red"});
        slotMachine.spin(5,1);
        String[] afterSpin = slotMachine.configuration();
        
        assertTrue(afterSpin[4].equals("green"));
        assertTrue(afterSpin[3].equals("blue"));
        assertTrue(afterSpin[2].equals("blue"));
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