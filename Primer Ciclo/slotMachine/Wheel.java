import java.util.ArrayList;

/**
 * Represents a wheel that can have a lot of symbols.
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 0.1
 */
public class Wheel{
    private ArrayList<Symbol> symbols;
    private Rectangle rectangleBodyPart;
    private int currentSymbol;
    private boolean isVisible;
    
    /**
     * Create a new wheel with color gray.
     */
    public Wheel(){
        symbols = new ArrayList<>();
        currentSymbol = 0;
        rectangleBodyPart = new Rectangle("white");
        rectangleBodyPart.changeSize(100,50);
    }
    
    /**
     * Make this wheel visible.
     */
    public void makeVisible(){
        rectangleBodyPart.makeVisible();
        symbols.get(currentSymbol).place(rectangleBodyPart.getPosition()[0]+rectangleBodyPart.getHeight()/10 , rectangleBodyPart.getPosition()[1] + 7*rectangleBodyPart.getHeight()/20);
        symbols.get(currentSymbol).makeVisible();
        isVisible = true;
    }
    
    /**
     * Make this wheel invisible.
     */
    public void makeInvisible(){
        rectangleBodyPart.makeInvisible();
        symbols.get(currentSymbol).makeInvisible();
        isVisible = false;
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
    
    /**
     * It changes the current symbol to any one.
     */
    public void randomizeSymbol(){
        currentSymbol = SlotMachine.randomNumGenerator(0, symbols.size());
        if(isVisible){
            makeVisible();
        }
    }
}