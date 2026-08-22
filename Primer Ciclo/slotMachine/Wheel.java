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
     * @param x is the position in x.
     * @param y is the position in y.
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
     * Adds a symbol to the wheel in a specific position.
     */
    public void addSymbol(int pos, String color){
        pos --;
        if(pos <= 0){
            pos = 0;
        }
        else if(pos > symbols.size()){
            pos = symbols.size();
        }
        symbols.add(pos, new Symbol(color));
    }
    
    /**
     * Deletes a symbol in the wheel.
     */
    public void deleteSymbol(Symbol s){
        symbols.remove(s);
        if(currentSymbol > symbols.size()){
            currentSymbol = symbols.size();
        }
        if(isVisible){
            makeVisible();
        }
    }
    
    /**
     * Moves to the next symbol.
     */
    public void spin(){
        currentSymbol = (currentSymbol+1)% symbols.size();
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