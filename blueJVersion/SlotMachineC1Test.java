import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SlotMachineC1Test.
 * Pruebas para el Ciclo 1: Gestión básica de la máquina tragamonedas.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class SlotMachineC1Test {
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
     * If a wheel is added, the number of wheels should be greater
     */
    @Test
    public void shouldWheelNumberBeGreaterIfWheelAdded(){
        slotMachine.addWheel(1);
        assertEquals(4, slotMachine.configuration().length);
    }

    /**
     * If we put a wheel in a position bigger than the number of wheels it should be placed at the end.
     */
    @Test
    public void shouldAddWheelAtEndIfPosTooBig(){
        String[] before = slotMachine.configuration();
        slotMachine.addWheel(1000);
        String[] after = slotMachine.configuration();
        assertTrue(before[0].equals(after[0]) && before[1].equals(after[1]) && before[2].equals(after[2]));
    }

    /**
     * If we put a wheel in a position lower than the number of wheels it should be placed at the start.
     */
    @Test
    public void shouldAddWheelAtStartIfPosTooLittle(){
        String[] before = slotMachine.configuration();
        slotMachine.addWheel(-1000);
        String[] after = slotMachine.configuration();
        assertTrue(before[0].equals(after[1]) && before[1].equals(after[2]) && before[2].equals(after[3]));
    }
    
    /**
     * If a wheel is deleted, the number of wheels should be lower
     */
    @Test
    public void shouldWheelNumberBeLessIfWheelDeleted(){
        slotMachine.delWheel(1);
        assertEquals(2, slotMachine.configuration().length);
    }

    /**
     * If we delete a wheel in a position bigger than the number of wheels it should be deleted at the end.
     */
    @Test
    public void shouldDeleteWheelAtEndIfPosTooBig(){
        String[] before = slotMachine.configuration();
        slotMachine.delWheel(1000);
        String[] after = slotMachine.configuration();
        assertTrue(before[0].equals(after[0]) && before[1].equals(after[1]) && before.length > after.length);
    }

    /**
     * If we delete a wheel in a position lower than the number of wheels it should be deleted at the start.
     */
    @Test
    public void shouldDeleteWheelAtEndIfPosTooLittle(){
        String[] before = slotMachine.configuration();
        slotMachine.delWheel(-1000);
        String[] after = slotMachine.configuration();
        assertTrue(before[1].equals(after[0]) && before[2].equals(after[1]) && before.length > after.length);
    }
    
    /**
     * If we add a new symbol correctly the number of symbols should be greater.
     */
    @Test
    public void shouldSymbolNumberBeGreaterIfSymbolAdded(){
        slotMachine.addSymbol(2, "pink");
        assertEquals(4, slotMachine.symbols().length);
    }

    /**
     * If we add a symbol in a specific position the symbol should be there.
     */
    @Test
    public void shouldSymbolNumberBeInTheSpecificPosition(){
        slotMachine.addSymbol(2, "pink");
        assertEquals("pink", slotMachine.symbols()[1]);
    }

    /**
     * If we add a repeated symbol it shouldn't change the number of symbols, because it was already there.
     */
    @Test
    public void shouldNotAddRepeatedSymbols(){
        slotMachine.addSymbol(2, "red");
        assertEquals(3, slotMachine.symbols().length);
    }

    /**
     * If we add a symbol in a position bigger than the number of symbols it should be added at the end.
     */
    @Test
    public void shouldAddSymbolAtEndIfPosTooBig(){
        slotMachine.addSymbol(1000, "pink");
        assertEquals("pink", slotMachine.symbols()[3]);
    }

    /**
     * If we add a symbol in a position lower than the number of symbols it should be added at the start.
     */
    @Test
    public void shouldAddSymbolAtStartIfPosTooLittle(){
        slotMachine.addSymbol(-1000, "pink");
        assertEquals("pink", slotMachine.symbols()[0]);
    }

    /**
     * If a symbol is not in the list of possible symbols it won't be added.
     */
    @Test
    public void shouldNotAddStrangeSymbols(){
        slotMachine.addSymbol(2, "strange");
        assertEquals(3, slotMachine.symbols().length);
    }
    
    /**
     * If delete a symbol the number of symbols should be less.
     */
    @Test
    public void shouldSymbolNumberBeLessIfSymbolWasDeleted(){
        slotMachine.delSymbol("red");
        assertEquals(2, slotMachine.symbols().length);
    }

    /**
     * If the symbol doesn't exist you shouldn't delete anything.
     */
    @Test
    public void shouldNotDeleteIfTheSymbolNotExists(){
        slotMachine.delSymbol("pink");
        assertEquals(3, slotMachine.symbols().length);
    }
    
    /**
     * Should change the symbols of the wheels.
     */
    @Test
    public void shouldPlaceWheelSymbol(){
        slotMachine.addSymbol(1000, "pink");
        slotMachine.placeSymbol(1, "pink");
        slotMachine.placeSymbol(2, "pink");
        assertTrue(slotMachine.configuration()[0].equals("pink") && slotMachine.configuration()[1].equals("pink"));
    }

    /**
     * Should not change the symbols of the wheels if the symbol doesn't exist.
     */
    @Test
    public void shouldStayIfSymbolDoesNotExists(){
        String[] before = slotMachine.configuration();
        slotMachine.placeSymbol(1, "strange");
        slotMachine.placeSymbol(2, "strange2");
        assertArrayEquals(before, slotMachine.configuration());
    }
    
    /**
     * Should spin a specific wheel's symbol.
     */
    @Test
    public void shouldSpinJustASpecificWheel(){
        String[] before = slotMachine.configuration();
        slotMachine.spin(1);
        String[] after = slotMachine.configuration();
        assertTrue(!before[0].equals(after[0]) && before[1].equals(after[1]) && before[2].equals(after[2]));
    }

    /**
     * If we spin a specific wheel in a position bigger than the number of wheels, we should spin the last one.
     */
    @Test
    public void shouldSpinLastWheelIfPosTooBig() {
        String[] before = {"red", "blue", "green"};
        slotMachine.spin(before);
        slotMachine.spin(1000);
        String[] after = slotMachine.configuration();
        assertTrue(!before[2].equals(after[2]) && before[1].equals(after[1]) && before[0].equals(after[0]));
    }

    /**
     * If we spin a specific wheel in a position lower than the number of wheels, we should spin the first one.
     */
    @Test
    public void shouldSpinFirstWheelIfPosTooShort(){
        String[] before = slotMachine.configuration();
        slotMachine.spin(-1000);
        String[] after = slotMachine.configuration();
        assertTrue(!before[0].equals(after[0]) && before[1].equals(after[1]) && before[2].equals(after[2]));
    }   
    
    /**
     * If we do spin to all the wheels it should change their symbols.
     */
    @Test
    public void shouldChangeAllWheelsSymbols(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        slotMachine.spin();
        String[] afterSymbols = slotMachine.configuration();
        assertTrue(!setSymbols[0].equals(afterSymbols[0]) && !setSymbols[1].equals(afterSymbols[1]) && !setSymbols[2].equals(afterSymbols[2]));
    }
    
    /**
     * If we put a set of symbols it should give us how many different symbols are.
     */
    @Test
    public void shouldGiveCorrectDistinctSymbolsNumber(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        int result = slotMachine.distinctSymbols();
        assertEquals(3, result);
    }
    
    /**
     * If we put a set of symbols configuration should give us those current symbols.
     */
    @Test
    public void shouldGiveCorrectCurrentSymbols(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        assertArrayEquals(setSymbols, slotMachine.configuration());
    }
    
    /**
     * If all symbols are equals we should win.
     */
    @Test
    public void shouldBeJackpotIfAllSymbolsAreEquals(){
        String[] setSymbols = {"red", "red", "red"};
        slotMachine.spin(setSymbols);
        assertTrue(slotMachine.isJackpot());
    }

    /**
     * If at least two symbols are different we shouldn't win.
     */
    @Test
    public void shouldNotBeJackpotIfAtLeastTwoSymbolsAreDifferent(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        assertFalse(slotMachine.isJackpot());
    }

    /**
     * Tears down the test fixture.
     */
    @After
    public void tearDown(){
    }
}