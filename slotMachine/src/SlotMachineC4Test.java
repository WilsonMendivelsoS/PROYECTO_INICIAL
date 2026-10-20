import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SlotMachineC4Test.
 * Pruebas para el Ciclo 4: Validación extendida de nuevas tipologías y casos límite.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 2
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
    
    /*
     * Should be the same color as the wheel on the left when it spins.
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

    /*
     * Should Lefty act as a normal wheel and not crash if it is the first wheel.
     */
    @Test
    public void shouldLeftyActAsNormalWheelIfFirst(){
        slotMachine.addWheel("Lefty", 1);
        
        String[] before = slotMachine.configuration();
        slotMachine.spin(1);
        String[] after = slotMachine.configuration();
        
        /* It shouldn't crash trying to access a left wheel, it should just spin normally */
        assertTrue(!before[0].equals(after[0]));
    }
    
    /*
     * Should always return true for getIsLocked intrinsically.
     */
    @Test
    public void shouldRebelBeAlwaysLocked(){
        slotMachine.addWheel("Rebel", 1);
        assertTrue(slotMachine.getWheel(1).getIsLocked());
    }

    /*
     * Should force itself to unlock if the system tries to lock it.
     */
    @Test
    public void shouldRebelUnlockItSelfIfForced(){
        slotMachine.addWheel("Rebel", 1);
        slotMachine.getWheel(1).setIsLocked(true);
        
        /* The overridden method forces it to stay false internally */
        assertTrue(slotMachine.getWheel(1).getIsLocked());
    }
    
    /*
     * Should not allow swapping actions for rebel wheels.
     */
    @Test
    public void shouldRebelNotSwap(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Rebel", 2);
        
        slotMachine.swap(1, 2);
        assertFalse(slotMachine.getWheel(2).canSwap());
    }
    
    /*
     * Should not be possible to delete rebel wheels.
     */
    @Test
    public void shouldRebelNotBeDelete(){
        slotMachine.addWheel("Rebel", 1);
        
        slotMachine.delWheel(1);
        assertFalse(slotMachine.getWheel(1).canBeDelete());
    }
    
    /*
     * Should Splatty move the wheel exactly to its left when it spins.
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

    /*
     * Should Splatty move the left wheel backwards if steps are negative.
     */
    @Test
    public void shouldSplattyMoveLeftWheelNegativeSteps(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        
        String[] beforeSpin = slotMachine.configuration();
        slotMachine.spin(2, -2);
        String[] afterSpin = slotMachine.configuration();
        
        /* Both the Splatty and the left wheel should have changed state backward */
        assertTrue(!beforeSpin[0].equals(afterSpin[0]));
        assertTrue(!beforeSpin[1].equals(afterSpin[1]));
    }

    /*
     * Should Splatty act as a normal wheel and not crash if it is the first wheel.
     */
    @Test
    public void shouldSplattyActAsNormalWheelIfFirst(){
        slotMachine.addWheel("Splatty", 1);
        
        String[] before = slotMachine.configuration();
        slotMachine.spin(1);
        String[] after = slotMachine.configuration();
        
        assertTrue(!before[0].equals(after[0]));
    }
    
    /*
     * Should Splatty wheel trigger a chain reaction moving other Splatties on the left.
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
        
        assertTrue(!beforeSpin[0].equals(afterSpin[0]));
        assertTrue(!beforeSpin[1].equals(afterSpin[1]));
        assertTrue(!beforeSpin[2].equals(afterSpin[2]));
    }

    /*
     * Should accept new types of symbols seamlessly without crashing.
     */
    @Test
    public void shouldAddNewSymbolTypesCorrectly(){
        slotMachine.addSymbol("Ephemeral", 4, "yellow");
        slotMachine.addSymbol("Shy", 5, "purple");
        
        assertEquals(5, slotMachine.symbols().length);
    }

    /*
     * Should Shy symbol survive multiple spins without crashing the configuration.
     * This tests the action() trigger hidden inside the spin flow.
     */
    @Test
    public void shouldShySymbolSurviveMultipleSpins(){
        slotMachine.addSymbol("Shy", 4, "purple");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(1, "purple");
        
        slotMachine.spin(1, 4);
        
        assertNotNull(slotMachine.configuration()[0]);
    }

    /*
     * Should Ephemeral symbol survive multiple spins while shrinking.
     */
    @Test
    public void shouldEphemeralSymbolSurviveMultipleSpins(){
        slotMachine.addSymbol("Ephemeral", 4, "yellow");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(1, "yellow");
        
        slotMachine.spin(1, 10);
        
        assertNotNull(slotMachine.configuration()[0]);
    }
    /*
     * Should Splatty not change the left wheel if it is explicitly locked.
     */
    @Test
    public void shouldSplattyRespectLockedWheels(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        
        slotMachine.lock(1);
        String[] beforeSpin = slotMachine.configuration();
        slotMachine.spin(2, 1);
        String[] afterSpin = slotMachine.configuration();
        
        assertTrue(!beforeSpin[1].equals(afterSpin[1]));
        assertEquals(beforeSpin[0], afterSpin[0]);
    }

    /*
     * Should Splatty with 0 steps keep both itself and the left wheel in the exact same position.
     */
    @Test
    public void shouldSplattyNotMoveAnythingWithZeroSteps(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        
        String[] beforeSpin = slotMachine.configuration();
        slotMachine.spin(2, 0);
        String[] afterSpin = slotMachine.configuration();
        
        assertArrayEquals(beforeSpin, afterSpin);
    }

    /*
     * Should Splatty spinning a LeftyWheel trigger the Lefty behavior on the left.
     */
    @Test
    public void shouldSplattyTriggerLeftyLeftOfIt(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Lefty", 2);
        slotMachine.addWheel("Splatty", 3);
        
        String[] beforeSpin = slotMachine.configuration();
        
        slotMachine.spin(3, 1);
        String[] afterSpin = slotMachine.configuration();
        
        assertEquals(afterSpin[0], afterSpin[1]);
        assertTrue(!beforeSpin[2].equals(afterSpin[2]));
    }

    /*
     * Should ShySymbol alternate its visibility state internally without breaking the wheel configuration.
     */
    @Test
    public void shouldShySymbolAlternateVisibilityOnAction(){
        slotMachine.addSymbol("Shy", 4, "purple");
        slotMachine.addWheel(1);
        
        slotMachine.placeSymbol(1, "purple");
        
        slotMachine.spin(1, 1);
        
        slotMachine.spin(1, 1);
        
        assertEquals("blue", slotMachine.configuration()[0]);
    }
    /*
     * Should only affect the exact wheel on its left, totally ignoring wheels on its right.
     */
    @Test
    public void shouldSplattyOnlyMoveLeftWheelNotRight(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        slotMachine.addWheel(3);
        
        String[] beforeSpin = slotMachine.configuration();
        slotMachine.spin(2, 1);
        String[] afterSpin = slotMachine.configuration();
        

        assertTrue(!beforeSpin[0].equals(afterSpin[0]));
        assertEquals(beforeSpin[2], afterSpin[2]);
    }

    /*
     * Should preserve the visibility state of the left wheel after spinning it.
     * This tests the specific visibility logic inside SplattyWheel's overridden spin methods.
     */
    @Test
    public void shouldSplattyPreserveLeftWheelVisibility(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        
        slotMachine.makeVisible();
        
        slotMachine.spin(2, 1);
        
        assertTrue(slotMachine.getWheel(1).isVisible());
    }

    /*
     * Should handle a massive amount of steps accurately without crashing.
     */
    @Test
    public void shouldSplattyHandleLargeNumberOfSteps(){
        slotMachine.addWheel(1);
        slotMachine.addWheel("Splatty", 2);
        
        String[] beforeSpin = slotMachine.configuration();
        
        slotMachine.spin(2, 100);
        String[] afterSpin = slotMachine.configuration();
        
        assertTrue(!beforeSpin[0].equals(afterSpin[0]));
        assertTrue(!beforeSpin[1].equals(afterSpin[1]));
    }
    /**
     * Tears down the test fixture.
     */
    @After
    public void tearDown(){
    }
}