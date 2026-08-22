import java.util.ArrayList;

/**
 * Represents a wheel that can have a lot of symbols.
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 0.1
 */
public class Wheel
{
    private ArrayList<Symbol> symbols;
    private Rectangle rectangleBodyPart;
    private int currentSymbol;
    
    /**
     * Create a new wheel with color gray.
     */
    public Wheel(){
        symbols = new ArrayList<>();
        symbols.add(new Symbol("black"));
        rectangleBodyPart = new Rectangle("gray");
        currentSymbol = 0;
        rectangleBodyPart.changeSize(100,50);
    }
    
    /**
     * Make this wheel visible.
     */
    public void makeVisible(){
        rectangleBodyPart.makeVisible();
        if(currentSymbol == 0 && symbols.size()>1){
            currentSymbol = SlotMachine.randomNumGenerator(1,symbols.size());
            symbols.get(currentSymbol).place(4*rectangleBodyPart.getPosition()[0]/3,8*rectangleBodyPart.getPosition()[1]/3);
            symbols.get(currentSymbol).makeVisible();
        }
    }
    
    /**
     * Make this wheel invisible.
     */
    public void makeInvisible(){
        rectangleBodyPart.makeInvisible();
        symbols.get(currentSymbol).makeInvisible();
    }
    
    /**
     * Puts the Wheel in a specific position.
     */
    public void place(int x, int y){
        makeInvisible();
        rectangleBodyPart.moveHorizontal(-rectangleBodyPart.getPosition()[0]+x);
        rectangleBodyPart.moveVertical(-rectangleBodyPart.getPosition()[1]+y);
        makeVisible();
    }
        
    /**
     * Adds a symbol to the wheel.
     */
    public void addSymbol(Symbol s){
        symbols.add(s);
    }
    
    /**
     * Deletes a symbol in the wheel.
     */
    public void deleteSymbol(Symbol s){
        symbols.remove(s);
        if(currentSymbol > symbols.size()){
            currentSymbol = symbols.size();
        }
    }
}