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
        slotMachine.addSymbol(1,"red");
        slotMachine.addSymbol(2,"blue");
        slotMachine.addSymbol(3,"green");
        while(slotMachine.configuration().length < 3){
             slotMachine.addWheel(1);   
        }

        slotMachine.addSymbol(4, "strange");
        
        String[] strangeCombination = {"strange", "strange", "strange"};
        
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
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
        
    }
}