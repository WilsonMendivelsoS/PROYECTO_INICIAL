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
public class SlotMachineCC4Test
{
    private SlotMachine slotMachine;

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    
    }
    
    /**
     * 
     */
    @Test
    public void accordingGrMsshouldBeTheSameColorAsTheWheelOnTheLeft(){
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
        
        slotMachine.spin();
        String[] a = slotMachine.configuration();
    
        assertEquals(a[5], a[4]);
        
    }
    
    
    /**
     * 
     */
    @Test
    public void accordingGrMsshouldNotDeleteARebelWheel(){
        slotMachine = new SlotMachine();
        slotMachine.addSymbol(1, "red");
        slotMachine.addSymbol(2, "blue");
        slotMachine.addSymbol(3, "green");
        
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.addWheel("Rebel", 4);
        slotMachine.delWheel(4);
        String[] a = slotMachine.configuration();
        assertEquals(4, a.length);
    }
    
    /**
     * Verifica que los simbolos de tipo normal, ephemeral y shy
     * puedan coexistir en una misma rueda.
     */
    @Test
    public void accordingGbShouldAllowDifferentSymbolTypesInSameWheel(){
        SlotMachine machine = new SlotMachine();
        machine.addWheel("Normal", 1);
        machine.addSymbol("Normal",1, "red");
        machine.addSymbol("Ephemeral", 2,"blue");
        machine.addSymbol("Shy", 3,"green");
        assertTrue(machine.ok());
        assertArrayEquals(new String[]{"red", "blue", "green"}, machine.symbols());
    }
    
    /**
     * Verifica que diferentes tipos de simbolos no puedan
     * introducir colores duplicados en la misma rueda.
     */
    @Test
    public void accordingGbShouldNotAllowDuplicateColorAcrossSymbolTypes() {
        SlotMachine machine = new SlotMachine();
        machine.addWheel("Normal", 1);
        machine.addSymbol("Normal", 1,"red");
        assertTrue(machine.ok());
        
        
        machine.addSymbol("Shy", 1, "red");
        assertFalse(machine.ok());
        assertArrayEquals(new String[]{"red"},machine.symbols());
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