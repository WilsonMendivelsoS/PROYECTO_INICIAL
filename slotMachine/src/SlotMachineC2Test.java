import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SlotMachineC2Test.
 * Pruebas para el Ciclo 2: Extensiones como swap, lock, unlock, configuraciones por arreglos y pasos de giro.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class SlotMachineC2Test {
    private SlotMachine slotMachine;
    
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        slotMachine = new SlotMachine();
        
        slotMachine.addSymbol(1, "green");
        slotMachine.addSymbol(2, "red");
        slotMachine.addSymbol(3, "blue");
        
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
    }
    
    /**
     * If we do a swap between two symbols with different colors configuration should change.
     */
    @Test
    public void shouldSwapWheelsWillChangeColorsOrder(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(2, 3);
        String[] waited = {"red", "green", "blue"};
        assertArrayEquals(slotMachine.configuration(), waited);
    }

    /**
     * If the swap is with the same wheel it should not change the configuration.
     */
    @Test
    public void shouldNotChangeIfSwapWithTheSameWheel(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(2, 2);
        String[] waited = {"red", "blue", "green"};
        assertArrayEquals(slotMachine.configuration(), waited);
    }

    /**
     * If one of the wheels position is too short we should take the first wheel.
     */
    @Test
     public void shouldSwapIfWheelPosIsTooShort(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(-1000, 2);
        String[] waited = {"blue", "red", "green"};
        assertArrayEquals(slotMachine.configuration(), waited);
    }

    /**
     * If one of the wheels position is too big we should take the last wheel.
     */
    @Test
    public void shouldSwapIfWheelPosIsTooBig(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(1000, 2);
        String[] waited = {"red", "green", "blue"};
        assertArrayEquals(slotMachine.configuration(), waited);
    }
    
    /**
     * If a wheel is locked, it should not change the symbols.
     */
    @Test
    public void shouldNotChangeWheelsSymbolIfItIslocked(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.lock(1);
        slotMachine.spin(1);
        String[] waited = {"red", "blue", "green"};
        assertArrayEquals(slotMachine.configuration(), waited);
    }

    /**
     * If wheel position is too short it should lock the first wheel.
     */
    @Test
    public void shouldLockIfWheelPosIsTooShort(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.lock(-1000);
        slotMachine.spin(1);
        String[] waited = {"red", "blue", "green"};
        assertArrayEquals(slotMachine.configuration(), waited);
    }

    /**
     * If wheel position is too big it should lock the last wheel.
     */
    @Test
    public void shouldLockIfWheelPosIsTooBig(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.lock(1000);
        slotMachine.spin(3);
        String[] waited = {"red", "blue", "green"};
        assertArrayEquals(slotMachine.configuration(), waited);
    }
    
    /**
     * If a wheel is unlocked, it can change its symbols.
     */
    @Test
    public void shouldChangeWheelsSymbolsIfItIsUnlocked(){
        String beforeLockSymbol = slotMachine.configuration()[0];
        slotMachine.lock(1);
        slotMachine.unlock(1);
        slotMachine.spin(1);
        assertTrue(!slotMachine.configuration()[0].equals(beforeLockSymbol));
    }

    /**
     * If wheel position is too short it should unlock the first wheel.
     */
    @Test
    public void shouldUnlockIfWheelPosIsTooShort(){
        String beforeLockSymbol = slotMachine.configuration()[0];
        slotMachine.lock(1);
        slotMachine.unlock(-1000);
        slotMachine.spin(1);
        assertTrue(!slotMachine.configuration()[0].equals(beforeLockSymbol));
    }

    /**
     * If wheel position is too big it should unlock the last wheel.
     */
    @Test
    public void shouldUnlockIfWheelPosIsTooBig(){
        String beforeLockSymbol = slotMachine.configuration()[2];
        slotMachine.lock(3);
        slotMachine.unlock(3000);
        slotMachine.spin(3);
        assertTrue(!slotMachine.configuration()[2].equals(beforeLockSymbol));
    }

    /**
     * If steps are negative we should spin back.
     */
    @Test
    public void shouldSpinBackIfStepsAreNegatives(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(3, "pink");
        slotMachine.placeSymbol(1, "pink");
        slotMachine.spin(1, -2);
        assertEquals("black", slotMachine.configuration()[0]);
    }

    /**
     * If steps are zero it should be at the same place.
     */
    @Test
    public void shoultNotSpinIfStepsAreZero(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(3, "pink");
        slotMachine.placeSymbol(1, "pink");
        slotMachine.spin(1, 0);
        assertEquals("pink", slotMachine.configuration()[0]);
    }

    /**
     * If steps are given it should spin correctly.
     */
    @Test
    public void shouldSpinNPositionsCorrectly(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(3, "pink");
        slotMachine.placeSymbol(1, "black");
        slotMachine.spin(1, 2);
        assertEquals("pink", slotMachine.configuration()[0]);
    }

    /**
     * If the number of wheel is too big it will spin the last wheel.
     */
    @Test
    public void shouldSpinLastWheelIfWheelNumIsTooBig(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(3, "pink");
        slotMachine.placeSymbol(3, "black");
        slotMachine.spin(1000, 2);
        assertEquals("pink", slotMachine.configuration()[2]);
    }

    /**
     * If the number of wheel is too short it will spin the first wheel.
     */
    @Test
    public void shouldSpinFirstWheelIfWheelNumIsTooShort(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(3, "pink");
        slotMachine.placeSymbol(1, "pink");
        slotMachine.spin(-1000, -2);
        assertEquals("black", slotMachine.configuration()[0]);
    }
    
    /**
     * If symbols are given all the wheels must be in the position of the setSymbols.
     */
    @Test
    public void shouldSetAllSymbols(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        assertArrayEquals(setSymbols, slotMachine.configuration());
    }

    /**
     * If there are incorrect symbols, those wheels should ignore the spin.
     */
    @Test
    public void shouldStayAWheelIfItsSymbolIsIncorrect(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        String[] incorrectSymbols = {"good", "no", "red"};
        slotMachine.spin(incorrectSymbols);
        String[] waited = {"green", "red", "red"};
        assertArrayEquals(waited, slotMachine.configuration());
    }
    
    /**
     * This is the first acceptation test.
     */
    @Test
    public void shouldPassFirstAcceptationTest(){
        /* 1. User adds three wheels but tries to break the machine putting strange positions. */
        slotMachine.addWheel(-1000);
        slotMachine.addWheel(1000);
        slotMachine.addWheel(0);
        
        /* 2. User adds symbols. */
        slotMachine.addSymbol(1, "orange");
        slotMachine.addSymbol(1, "cyan");
        
        /* 3. Machine sets initial configuration with array */
        String[] setSymbols = {"green", "red", "blue", "orange", "orange", "cyan"};
        slotMachine.spin(setSymbols);
        
        /* 4. Moves first wheel 4 steps to get orange there */
        slotMachine.spin(1, 4);
        
        /* 5. Moves last wheel to get orange there. */
        slotMachine.spin(6);
        
        /* 6. Deletes second wheel to win easier. */
        slotMachine.delWheel(2);
        
        /* 7. Moves second wheel two times to win. */
        slotMachine.spin(2, 2);
        
        /* 8. Checks jackpot */
        assertTrue(slotMachine.isJackpot());
    }
    
    /**
     * This is the Second acceptation test.
     */
    @Test
    public void shouldSecondAcceptationTestPass(){
        /* 1. User adds a wheel */
        slotMachine.addWheel(2);
        
        /* 2. User adds a symbol. */
        slotMachine.addSymbol(1, "cyan");
        
        /* 3. Machine has next symbols configured: */
        String[] setSymbols = {"cyan", "red", "blue", "blue"};
        slotMachine.spin(setSymbols);
        
        /* 4. Locks the wheels number 3 and 4 */
        slotMachine.lock(3);
        slotMachine.lock(4);
        
        /* 5. Move first wheel to get blue there. */
        slotMachine.spin(1, 3);
        
        /* 6. Unlocks the wheels number 3 and 4 */
        slotMachine.unlock(3);
        slotMachine.unlock(4);
        
        /* 7. Moves second wheel once to win. */
        slotMachine.spin(2, 1);
        
        /* 8. Checks jackpot */
        assertTrue(slotMachine.isJackpot());
    }

    /**
     * Tears down the test fixture.
     */
    @After
    public void tearDown(){
    }
}