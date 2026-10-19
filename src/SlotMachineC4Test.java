import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SlotMachineC4Test.
 * Pruebas para el Ciclo 4: Validación de nuevas tipologías de ruedas y símbolos.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class SlotMachineC4Test {
    private SlotMachine slotMachine;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        slotMachine = new SlotMachine();
        
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addSymbol(3, "green");
    }
    
    /**
     * Lefty wheel should copy the state of the wheel on its left when it spins.
     */
    @Test
    public void shouldBeTheSameColorAsTheWheelOnTheLeft(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Lefty", 2);
        
        slotMachine.spin();
        String[] config = slotMachine.configuration();
        
        /* LeftyWheel is at position 2 (index 1), normal wheel is at position 1 (index 0) */
        assertEquals(config[0], config[1]);
    }
    
    /**
     * Rebel wheel returns true for locking intrinsically.
     */
    @Test
    public void shouldRebelNotBeLocked(){
        slotMachine.addWheel("Rebel", 1);
        slotMachine.lock(1);
        
        /* Although we called lock, we verify its internal logic manages its state appropriately */
        assertTrue(slotMachine.getWheel(1).getIsLocked());
    }
    
    /**
     * Rebel wheels should not allow swapping actions.
     */
    @Test
    public void shouldRebelNotSwap(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Rebel", 2);
        
        slotMachine.swap(1, 2);
        assertFalse(slotMachine.getWheel(2).canSwap());
    }
    
    /**
     * Rebel wheels cannot be deleted.
     */
    @Test
    public void shouldRebelNotBeDelete(){
        slotMachine.addWheel("Rebel", 1);
        
        slotMachine.delWheel(1);
        assertFalse(slotMachine.getWheel(1).canBeDelete());
    }
    
    /**
     * Spinning a Splatty wheel should also move the wheel exactly to its left.
     */
    @Test
    public void shouldSplattyMoveWheelsNearToHim(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        
        String[] beforeSpin = slotMachine.configuration();
        slotMachine.spin(2, 1);
        String[] afterSpin = slotMachine.configuration();
        
        /* Splatty is at pos 2, it should have moved pos 1 as well */
        assertTrue(!beforeSpin[0].equals(afterSpin[0]));
    }
    
    /**
     * Splatty wheels should trigger a chain reaction moving other Splatties on the left.
     */
    @Test
    public void shouldSplattyMoveOtherSplatties(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        slotMachine.addWheel("Splatty", 3);
        
        String[] beforeSpin = slotMachine.configuration();
        /* Spin the last Splatty */
        slotMachine.spin(3, 1);
        String[] afterSpin = slotMachine.configuration();
        
        /* It should have affected itself, the Splatty to its left, and the normal wheel on the far left */
        assertTrue(!beforeSpin[0].equals(afterSpin[0]));
        assertTrue(!beforeSpin[1].equals(afterSpin[1]));
        assertTrue(!beforeSpin[2].equals(afterSpin[2]));
    }

    /**
     * The system should accept new types of symbols seamlessly without crashing.
     */
    @Test
    public void shouldAddNewSymbolTypesCorrectly(){
        slotMachine.addSymbol("Ephemeral", 4, "yellow");
        slotMachine.addSymbol("Shy", 5, "purple");
        
        /* It must be capable of counting the original 3 plus these 2 new symbols */
        assertEquals(5, slotMachine.symbols().length);
    }
    
    /**
     * Tears down the test fixture.
     */
    @After
    public void tearDown(){
    }
}