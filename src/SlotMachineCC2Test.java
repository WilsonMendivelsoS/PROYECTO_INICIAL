import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class SlotMachineCC2Test.
 *
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class SlotMachineCC2Test
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
    }
    /**
     * If a symbol doesn't exists, wheels should change their symbols except 
     * that one with the strange symbol.
     */
    @Test
    public void accordingGrMsShouldNotSetSymbolsThatDoesNotExists(){
        SlotMachine slotMachine = new SlotMachine();
        slotMachine.addSymbol(1,"red");
        slotMachine.addSymbol(2,"blue");
        slotMachine.addSymbol(3,"green");
        while(slotMachine.configuration().length < 3){
             slotMachine.addWheel(1);   
        }

        
        String[] begin = {"red", "blue", "green"};
        slotMachine.spin(begin);
        
        String[] strangeSymbols = {"red", "null", "blue"};
        slotMachine.spin(strangeSymbols);
        
        String[] waited = {"red", "blue", "blue"};
        String[] afterSpin = slotMachine.configuration();
        
        assertEquals(waited, afterSpin);
    }
    /**
     * If a symbol doesn't exists it shouldn't be added and 
     * you shouldn't win if you try to set the machine with that symbol.
     */
    @Test
    public void accordingGrMsShouldNotAddStrangeSymbolsAndWinWithThatSymbol(){
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addSymbol(3, "green");
        while(slotMachine.configuration().length < 3){
            slotMachine.addWheel(1);   
        }
        
        slotMachine.spin(new String[]{"red", "blue", "green"});
    
        slotMachine.addSymbol(4, "strange");
        assertFalse(slotMachine.ok());
    
        String[] strangeCombination = {"strange", "strange", "strange"};
        slotMachine.spin(strangeCombination); 
        
        assertFalse(slotMachine.isJackpot());
    }
    
    
    /**
     * Verifies that attempting to swap two wheels fails when one of
     * them is locked, setting the machine status to not ok.
     */
    @Test
    public void accordingIcPgShouldNotSwap() {
        // Add some symbols
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
       
        // Add some wheels
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
       
        // Lock the first one
        slotMachine.lock(1);
       
        // Try to swap the 1st and the 3rd
        slotMachine.swap(1, 3);
       
        // Check that the action wasn't succesful due to the wheel to spin is locked
        assertFalse(slotMachine.ok());
    }
 
    /**
     * Verifies that attempting to swap two wheels fails when one of
     * them is locked, setting the machine status to not ok.
     */
    @Test
    public void shouldNotSwap() {
        // Add some symbols
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
        
        // Add some wheels
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        
        // Lock the first one
        slotMachine.lock(1);
        
        // Try to swap the 1st and the 3rd
        slotMachine.swap(1, 3);
        
        // Check that the action wasn't succesful due to the wheel to spin is locked
        assertFalse(slotMachine.ok());
    }
    
    /**
     * A locked wheel does not advance when the machine spins.
     */
    @Test
    public void accordingCcGbShouldNotSpinLockedWheel()
    {
        SlotMachine machine = new SlotMachine();
        machine.addWheel(1);
        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");

        machine.lock(1);
        machine.spin(1);

        assertEquals("red", machine.configuration()[0]);
        assertFalse(machine.ok());
    }
    /**
     * A wheel that was locked resumes spinning after unlock.
     */
    @Test
    public void accordingCcGbShouldSpinAfterUnlock()
    {
        SlotMachine machine = new SlotMachine();
        machine.addWheel(1);
        machine.addSymbol(1, "red");
        machine.addSymbol(1, "blue");

        machine.lock(1);
        machine.unlock(1);
        machine.spin(1);

        assertEquals("blue", machine.configuration()[0]);
        assertTrue(machine.ok());
    }
    
    /** Prueba que una rueda existente pueda ser bloqueada correctamente.
    * La maquina debe permitir bloquear una rueda que existe.
    * La operacion debe realizarse correctamente y ok() debe retornar true.
    */
        
    @Test
    public void accordingBaGqShouldLockWheel() {
        SlotMachine maquinaTraga = new SlotMachine();
        maquinaTraga.addWheel(1);
        maquinaTraga.lock(1);
        assertTrue(maquinaTraga.ok());
    }
    /** * Prueba que una rueda bloqueada no pueda girar.
    * ¿Que no deberia hacer? * Una rueda que se encuentra bloqueada no debe avanzar
    * cuando se intenta realizar un spinStep.*/
    
    @Test
    public void accordingBaGqShouldNotSpinLockedWheel() {
        SlotMachine maq = new SlotMachine();
        maq.addWheel(1);
        maq.addSymbol(1, "red");
        maq.addSymbol(2, "blue");
        maq.placeSymbol(1, "red");
        maq.lock(1);
        maq.spin(1, 1);
        assertFalse(maq.ok());
        String[] config = maq.configuration();
        assertEquals("red", config[0]);
    }
         /**
     * Verifies that a wheel can be spun successfully once it has been
     * locked and then unlocked, leaving the machine status as ok.
     */
    @Test
    public void accordingIcPgshouldSpinAWheel() {
        // Add some symbols
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
        
        // Add some wheels
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);

        // Lock the first one
        slotMachine.lock(1);
        
        // Unlock it then
        slotMachine.unlock(1);
        
        // Spin the second wheel which is not locked
        slotMachine.spin(1);
        
        // Check that the action was succesful due to the wheel to spin is locked
        assertTrue(slotMachine.ok());
    }

    /**
     * Verifies that after locking a wheel with lock(), calling
     * spin(wheel, steps) does not alter what that wheel shows in
     * configuration().
     */
    @Test
    public void accordingCgCpShouldNotChangeConfigurationWhenWheelIsLocked()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
        slotMachine.placeSymbol(1, "red");

        slotMachine.lock(1);
        String before = slotMachine.configuration()[0];
        slotMachine.spin(1, 1);

        assertEquals(before, slotMachine.configuration()[0]);

        slotMachine.unlock(1);
    }

    /**
     * Verifies that after locking and then unlocking a wheel
     * (lock() followed by unlock()), spin(wheel, steps) can
     * change what that wheel shows.
     */
    @Test
    public void accordingCgCpShouldChangeConfigurationWhenWheelIsUnlockedAfterLock()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
        slotMachine.placeSymbol(1, "red");

        slotMachine.lock(1);
        slotMachine.unlock(1);
        slotMachine.spin(1, 1);

        assertTrue(slotMachine.ok());
    }

    /* Adding a color that already exists on a wheel should fail */
    @Test
    public void accordingCgHnAddSymbolShouldFailWhenColorAlreadyExists()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "red");
        assertFalse(slotMachine.ok());
    }


    /**
     * A locked wheel should not be able to spin.
     */
    @Test
    public void accordingGmLaShouldNotAllowSpinningALockedWheel()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);

        slotMachine.lock(1);
        slotMachine.spin(1);
        assertFalse(slotMachine.ok());

        slotMachine.unlock(1);
    }
    
    /**
     * Verifies that swapping two valid wheels with different symbols
     * using swap(int, int) leaves each one showing the symbol the
     * other previously had.
     */
    @Test
    public void accordingMsRhShouldSwapSymbolsBetweenTwoValidWheels()
    {
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        slotMachine.swap(1, 2);
        assertEquals("blue", slotMachine.configuration()[0]);
        assertEquals("red", slotMachine.configuration()[1]);
    }
    
    /**
     * Verifies that a wheel locked with lock(int) does not change its
     * visible symbol when attempting to spin it with spin(int).
     */
    @Test
    public void accordingMsRhShouldNotChangeLockedWheelWhenSpinning()
    {
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(1, "red");
        slotMachine.lock(1);
        slotMachine.spin(1);
        assertEquals("red", slotMachine.configuration()[0]);

        slotMachine.unlock(1);
    }
    
    /**
     * Collectively verifies that forcing a configuration where all
     * wheels end up with the same symbol via spin(String[]) changes
     * the system's state and successfully detects the jackpot.
     */
    @Test
    public void accordingMsRhShouldDetectJackpotAfterForcedSpin()
    {
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.spin(new String[]{"red", "red"});
        assertTrue(slotMachine.isJackpot());
    }
    
    /**
     * Verifies that delWheel(int pos) removes the last wheel when the
     * given position is greater than the number of wheels.
     */
    @Test
    public void accordingMsRhShouldDeleteLastWheelWhenPositionGreaterThanSize()
    {
        slotMachine.addSymbol(1, "red"); // configuration() needs at least one symbol
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.delWheel(10);
        assertEquals(1, slotMachine.configuration().length);
    }
    
    /**
     * A jackpot should occur when all wheels show the same color.
     */
    @Test
    public void accordingDrRmShouldBeJackpotWhenAllWheelsMatch()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.spin(new String[]{"red", "red", "red"});
        assertTrue(slotMachine.isJackpot());
    }
    
    /**
     * The number of wheels should remain correct after an add and a
     * delete.
     */
    @Test
    public void accordingDrRmShouldKeepCorrectWheelCountAfterAddAndDelete()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.delWheel(2);
        slotMachine.addSymbol(1, "red");
        slotMachine.spin(new String[]{"red", "red"});
        assertEquals(2, slotMachine.configuration().length);
    }
    
    /**
     * WHAT IT SHOULD DO: swapping two wheels should not alter the
     * number of distinct symbols reported for the machine (swap
     * moves the content, it does not modify it).
     */
    @Test
    public void accordingFsGcShouldKeepDistinctSymbolCountAfterSwap()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
    
        int before = slotMachine.distinctSymbols();
        slotMachine.swap(1, 2);
    
        assertTrue(slotMachine.ok());
        assertEquals(before, slotMachine.distinctSymbols());
    }

    
    /**
     * EXAMPLE 2 — "what it should NOT do": leaving a given
     * configuration (spin with array) should not report a jackpot if
     * the assigned symbols do not match each other.
     */
    @Test
    public void accordingClPcShouldNotReportJackpotWhenSetConfigurationDiffers()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
    
        slotMachine.spin(new String[]{"red", "blue"});
    
        assertTrue(slotMachine.ok());
        assertFalse(slotMachine.isJackpot());
    }
    
    /**
     * Tests that two wheels can be swapped.
     */
    @Test
    public void accordingJcSrShouldExchangeWheels()
    {
        // Two symbols and different ones placed, so the swap can be observed
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);

        slotMachine.placeSymbol(3, "blue");

        String[] before = slotMachine.configuration();
        slotMachine.swap(1, 3);
        String[] after = slotMachine.configuration();
        assertEquals(before[0], after[2]);
        assertEquals(before[2], after[0]);
    }
    
    /**
     * Tests that configuration() reports each wheel's current color
     */
    @Test
    public void accordingFmSnShouldShowCorrectConfiguration()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);

        slotMachine.addSymbol(1, "red");   // Add a new symbol [red] - with black being the default.
        slotMachine.addSymbol(1, "green"); // Add another symbol [green,red].
        slotMachine.addSymbol(1, "black"); //Add another symbol [black, green,red].
        slotMachine.placeSymbol(1, "red"); // Change the first wheel's symbol to red.
        slotMachine.placeSymbol(2, "green"); // Change the second wheel's symbol to green.
        slotMachine.placeSymbol(3, "black"); // Change the third wheel's symbol to black.
    
        String[] config = slotMachine.configuration(); // Current configuration.
    
        assertEquals("red", config[0]);
        assertEquals("green", config[1]);
        assertEquals("black", config[2]);
    }
    
    /**
     * spin(setSymbols) should leave the machine in exactly those
     * colors.
     */
    @Test
    public void accordingGmLaShouldReflectExactConfigurationAfterSpinWithGivenSymbols()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);


        slotMachine.addSymbol(1, "yellow");
        slotMachine.addSymbol(2, "red");
        slotMachine.addSymbol(3, "blue");

        slotMachine.spin(new String[] { "yellow", "red", "blue" });
        assertTrue(slotMachine.ok());
        assertArrayEquals(new String[] { "yellow", "red", "blue" }, slotMachine.configuration());
    }

    /**
     * Verifies that spinning a wheel advances it to the next symbol
     * in the machine's symbol list.
     */
    @Test
    public void accordingCxLxShouldAdvanceToNextSymbol()
    {
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addWheel(1);
        slotMachine.placeSymbol(1, "red");
        slotMachine.spin(1);
        assertEquals("blue", slotMachine.configuration()[0]);
        assertTrue(slotMachine.ok());
    }
    
    /**
     * Should: a locked wheel should not move when a spin is
     * attempted, and should be able to spin again after an unlock.
     */
    @Test
    public void accordingMrSeShouldKeepLockedWheelFixedAndAllowSpinAfterUnlock()
    {   
        
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
        slotMachine.placeSymbol(1, "red"); // known state
    
        slotMachine.lock(1);
        slotMachine.spin(1, 2); // attempt to spin the locked wheel
    
        assertFalse(slotMachine.ok());
        assertEquals("red", slotMachine.configuration()[0]); // should not have moved
    
        slotMachine.unlock(1);
        slotMachine.spin(1, 1); // now it should be able to spin
    
        assertTrue(slotMachine.ok());
    }
    
    /**
     * Should NOT: apply any change if one of the requested colors
     * does not exist on its corresponding wheel.
     */
    @Test
    public void accordingMrSeShouldRejectSpinSetSymbolsWhenColorMissing()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "green");
    
        String[] before = slotMachine.configuration();
        slotMachine.spin(new String[]{"red", "strange"}); // "strange" does not exist
    
        assertArrayEquals(before, slotMachine.configuration()); // nothing should have moved
    }
    
    /**
     * Verifies that a locked wheel cannot spin.
     */
    @Test
    public void accordingRlBbShouldNotSpinWhenLocked()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(1, "blue");
        slotMachine.addSymbol(1, "green");
    
        slotMachine.lock(1);
    
        String[] before = slotMachine.configuration();
        slotMachine.spin(1, 1);
        String[] after = slotMachine.configuration();
    
        assertTrue(Arrays.equals(before, after));

        slotMachine.unlock(1);
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