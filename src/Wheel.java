import java.util.ArrayList;

/**
 * Represents a wheel that can have a lot of symbols.
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class Wheel{
    private ArrayList<Symbol> symbols; 
    private Rectangle rectangleBodyPart;
    private int currentSymbol;
    private boolean isVisible;
    private boolean isLocked;
    
    /**
     * Create a new wheel with color gray.
     */
    public Wheel(){
        symbols = new ArrayList<>();
        currentSymbol = 0;
        rectangleBodyPart = new Rectangle("white");
        rectangleBodyPart.changeSize(100,50);
        isLocked = false;
    }
    
    /**
     * Make this wheel visible.
     */
    public void makeVisible(){
        if(!isVisible){
            rectangleBodyPart.makeVisible();
        }
        int moverExtra = 0;
        if(symbols.get(currentSymbol).getBodyFigureName().equals("TRIANGLE")){
            moverExtra = 15;
        }
        else if(symbols.get(currentSymbol).getBodyFigureName().equals("CIRCLE")){
            moverExtra = -2;
        }
        symbols.get(currentSymbol).place(rectangleBodyPart.getPosition()[0]+10 + moverExtra, rectangleBodyPart.getPosition()[1] + 7*rectangleBodyPart.getHeight()/20);
        
        
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
    public void addSymbol(int pos, String color, String figure){
        symbols.add(Math.min(Math.max(0, pos-1), symbols.size()), new Symbol(figure, color));
        if(pos<= currentSymbol+1){
            currentSymbol =  (currentSymbol+1)%symbols.size(); 
        }
        
    }
    
    /**
     * Deletes a symbol in the wheel, if its symbol is deleted, then it moves to the next one.
     */
    public void delSymbol(String symbol){
        int idxDeleted = -1;
        for(Symbol s: symbols){ 
            idxDeleted ++;
            if(s.getColor().equals(symbol)){
                symbols.remove(s);
                break;
            }
        }
        if(idxDeleted < currentSymbol){
            currentSymbol = (currentSymbol-1)%symbols.size();
        }
        else{
            currentSymbol = (currentSymbol)%symbols.size(); 
        }
        if(isVisible){
            makeVisible();
        }
    }
    
    /**
     * Moves to the next symbol.
     */
    public void spin(){
        if(isLocked == false){
            symbols.get(currentSymbol).makeInvisible();
            currentSymbol = (currentSymbol+1)% symbols.size(); 
            return;
        }
    }

    /**
     * Moves n steps the symbol. It can be negative.
     * @steps are the steps, if it is negative it will go back.
     */
    public void spin(int steps){
        if(isLocked == false){
            if(steps>=0){
                for( int i = 0; steps>i ; i++){
                    spin();
                    if(isVisible){
                        makeVisible(); 
                    }                    
                }
            }
            if(steps<0){
                if(isLocked == false){
                    for(int i = 0; i < Math.abs(steps); i++){
                        symbols.get(currentSymbol).makeInvisible();
                        if(currentSymbol == 0){
                            currentSymbol = symbols.size()-1;
                        }
                        else{
                            currentSymbol--;
                        }
                        if(isVisible){
                            makeVisible();   
                        }
                    }
                }
            }
        } 
    }
    
    /**
     * Return the color of de current symbol
     * @return the symbol color's name
     */
    public String colorCurrentSymbol(){
        String color = new String();
        color=symbols.get(currentSymbol).getColor();
        return color;
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
    
    /**
     * Sets wheel's currentSymbol to the new symbol
     */
    public void setCurrentSymbol(int numSymbol){
        currentSymbol = numSymbol; 
    }
    
    /**
     * return if the wheel is locked
     * @return isLocked
     */
    public boolean getIsLocked(){
        return isLocked;
    }
    
    /**
     * modifies the wheel's locking state
     */
    public void setIsLocked(boolean isLocked){
        this.isLocked = isLocked;
    }
}