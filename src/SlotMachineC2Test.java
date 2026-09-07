import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SlotMachineC2Test.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class SlotMachineC2Test{
    private SlotMachine slotMachine;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
        slotMachine = new SlotMachine();

    }
    /**
     * If a wheel is added, the number of wheels should be greater
     */
    @Test
    public void shouldWheelNumberBeGreaterIfWheelAdded(){
        slotMachine.addWheel(1);
        assertEquals(4,slotMachine.configuration().length);
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
        assertEquals(2,slotMachine.configuration().length);
    }
    /**
     * If we delete a wheel in a position bigger than the number of wheels it should be deleted at the end.
     */
    @Test
    public void shouldDeleteWheelAtEndIfPosTooBig(){
        String[] before = slotMachine.configuration();
        slotMachine.delWheel(1000);
        String[] after = slotMachine.configuration();
        assertTrue(before[0].equals(after[0]) && before[1].equals(after[1]) && before.length> after.length);
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
     * If we do a swap between to symbols with different colors configuration should change.
     */
    @Test
    public void shouldSwapWheelsWillChangeColorsOrder(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(2,3);
        String[] waited = {"red", "green", "blue"};
        assertEquals(slotMachine.configuration(), waited);
    }
    /**
     * If the swap is with the same wheel is should not change the configuration.
     */
    @Test
    public void shouldNotChangeIfSwapWithTheSameWheel(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(2,2);
        String[] waited = {"red", "blue", "green"};
        assertEquals(slotMachine.configuration(), waited);
    }
    /**
     * If one of the wheels position is too short we should take the first wheel.
     */
    @Test
     public void shouldSwapIfWheelPosIsTooShort(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(-1000,2);
        String[] waited = {"blue", "red", "green"};
        assertEquals(slotMachine.configuration(), waited);
    }
    /**
     * If one of the wheels position is too big we should take the last wheel.
     */
    @Test
    public void shouldSwapIfWheelPosIsTooBig(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.swap(1000,2);
        String[] waited = {"red", "green", "blue"};
        assertEquals(slotMachine.configuration(), waited);
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
        assertEquals(slotMachine.configuration(), waited);
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
        assertEquals(slotMachine.configuration(), waited);
    }
    /**
     * If wheel position is too short it should lock the first wheel.
     */
    @Test
    public void shouldLockIfWheelPosIsTooBig(){
        String[] symbols = {"red", "blue", "green"};
        slotMachine.spin(symbols);
        slotMachine.lock(1000);
        slotMachine.spin(3);
        String[] waited = {"red", "blue", "green"};
        assertEquals(slotMachine.configuration(), waited);
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
     * If wheel position is too short it should unlock the first wheel.
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
     * If we add a new symbol correctly the number of symbols should be greater.
     */
    @Test
    public void shouldSymbolNumberBeGreaterIfSymbolAdded(){
        slotMachine.addSymbol(2,"pink");
        assertEquals(4, slotMachine.symbols().length);
    }
    /**
     * If we add a symbol in a specific position the symbol should be there.
     */
    @Test
    public void shouldSymbolNumberBeInTheSpecificPosition(){
        slotMachine.addSymbol(2,"pink");
        assertEquals("pink", slotMachine.symbols()[1]);
    }
    /**
     * If we add a repeated symbol it shouldn't be change the number of symbols, because it was already there.
     */
    @Test
    public void shouldNotAddRepeatedSymbols(){
        slotMachine.addSymbol(2,"red");
        assertEquals(3, slotMachine.symbols().length);
    }
    /**
     * If we add a symbol in a position bigger than the number of symbols it should be deleted at the end.
     */
    @Test
    public void shouldAddSymbolAtEndIfPosTooBig(){
        slotMachine.addSymbol(1000,"pink");
        assertEquals("pink", slotMachine.symbols()[3]);
    }
    /**
     * If we add a symbol in a position lower than the number of symbols it should be deleted at the start.
     */
    @Test
    public void shouldAddSymbolAtStartIfPosTooLittle(){
        slotMachine.addSymbol(-1000,"pink");
        assertEquals("pink", slotMachine.symbols()[0]);
    }
    /**
     * If a symbol is not in the list of possible symbols it won't we added
     */
    @Test
    public void shouldNotAddStrangeSymbols(){
        slotMachine.addSymbol(2,"strange");
        assertEquals(3, slotMachine.symbols().length);
    }
    
    /**
     * If delete a symbol the number of symbols should be less
     */
    @Test
    public void shouldSymbolNumberBeLessIfSymbolWasDeleted(){
        slotMachine.delSymbol("red");
        assertEquals(2, slotMachine.symbols().length);
    }
    /**
     * If the symbol doesn't exists you shouldn't delete something
     */
    @Test
    public void shouldNotDeleteIfTheSymbolNotExists(){
        slotMachine.delSymbol("pink");
        assertEquals(3, slotMachine.symbols().length);
    }
    
    /**
     * Should change the symbols of the wheels
     */
    @Test
    public void shouldPlaceWheelSymbol(){
        slotMachine.addSymbol(1000,"pink");
        slotMachine.placeSymbol(1,"pink");
        slotMachine.placeSymbol(2,"pink");
        assertTrue(slotMachine.configuration()[0].equals("pink") && slotMachine.configuration()[1].equals("pink"));
    }
    /**
     * Should not change the symbols of the wheels if the symbol doesn't exists.
     */
    @Test
    public void shouldStayIfSymbolDoesNotExists(){
        String[] before = slotMachine.configuration();
        slotMachine.placeSymbol(1, "strange");
        slotMachine.placeSymbol(2, "strange2");
        assertEquals(before, slotMachine.configuration());
    }
    
    /**
     * Should spin a specific wheel's symbol
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
     * If steps are negative we should spin back.
     */
    @Test
    public void shouldSpinBackIfStepsAreNegatives(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(3, "pink");
        slotMachine.placeSymbol(1, "pink");
        slotMachine.spin(1,-2);
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
        slotMachine.spin(1,0);
        assertEquals("pink", slotMachine.configuration()[0]);
    }
    /**
     * If steps are given it should spin correctly
     */
    @Test
    public void shouldSpinNPositionsCorrectly(){
        slotMachine.addSymbol(1, "black");
        slotMachine.addSymbol(3, "pink");
        slotMachine.placeSymbol(1, "black");
        slotMachine.spin(1,2);
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
        slotMachine.spin(1000,2);
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
        slotMachine.spin(-1000,-2);
        assertEquals("black", slotMachine.configuration()[0]);
    }
    
    /**
     * If symbols are given all the wheels must be in the position of the setSymbols.
     */
    @Test
    public void shouldSetAllSymbols(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        assertEquals(setSymbols, slotMachine.configuration());
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
        assertEquals(waited, slotMachine.configuration());
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
     * If we put a set of symbols configuration should give us those current symbols
     */
    @Test
    public void shouldGiveCorrectCurrentSymbols(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        assertEquals(setSymbols, slotMachine.configuration());
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
     * If at least two symbols are differents we shouldn't win.
     */
    @Test
    public void shouldNotBeJackpotIfAtLeastTwoSymbolsAreDifferent(){
        String[] setSymbols = {"green", "red", "blue"};
        slotMachine.spin(setSymbols);
        assertFalse(slotMachine.isJackpot());
    }
    
    
    
    
    /**
     * This is the first acceptation test.
     */
    @Test
    public void shouldPassFirstAcceptationTest(){
        //1. User adds three wheels but tryes to break the machine putting strange positions.
        slotMachine.addWheel(-1000);
        slotMachine.addWheel(1000);
        slotMachine.addWheel(0);
        //2. User add three symbols.
        slotMachine.addSymbol(1,"orange");
        slotMachine.addSymbol(1,"cyan");
        
        //3. Machine has next symbols:
        String[] setSymbols = {"green", "red", "blue", "orange", "orange", "cyan"};
        slotMachine.spin(setSymbols);
        
        //4. Moves first wheel 4 steps to get orange there
        slotMachine.spin(1,4);
        
        //5. Moves last wheel to get orange there.
        slotMachine.spin(6);
        
        //6. Deletes second wheel to win easier.
        slotMachine.delWheel(2);
        
        //7. Moves second wheel two times to win.
        slotMachine.spin(2,2);
        
        //8. He got jackpot
        assertTrue(slotMachine.isJackpot());
        
    }
    
    /**
     * This is the Second acceptation test.
     */
    @Test
    public void shouldSecondAcceptationTestPass(){
        //1. User add a wheel
        slotMachine.addWheel(2);
        //2. User add a symbol.
        slotMachine.addSymbol(1,"cyan");
        
        //3. Machine has next symbols:
        String[] setSymbols = {"cyan", "red", "blue", "blue"};
        slotMachine.spin(setSymbols);
        
        //4. Lock the wheels number 3 and 4
        slotMachine.lock(3);
        slotMachine.lock(4);
        
        //5. Move first wheel to get blue there.
        slotMachine.spin(1, 3);
        
        //6. unLock the wheels number 3 and 4
        slotMachine.unlock(3);
        slotMachine.unlock(4);
        
        //7. Moves second wheel once to win.
        slotMachine.spin(2,1);
        
        //8. He got jackpot
        assertTrue(slotMachine.isJackpot());
        
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