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
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
    
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
        SlotMachine slotMachine = new SlotMachine();
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
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown(){
        
    }
}