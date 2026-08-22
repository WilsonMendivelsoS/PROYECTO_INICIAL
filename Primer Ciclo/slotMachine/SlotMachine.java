import java.util.ArrayList;
import java.lang.Math;

/**
* Represents a slotMachine, it can add wheels and spin them, if you wanna win, you will need a jackpot.<br>
* <b>(symbolsQuantity, wheelsQuantity, lever, rectangleBodyParts, symbols, wheels)</b><br>
* <b>Inv: symbols</b> > 0 and <b>wheels</b> > 0 
* @author David Garzon, Wilson Mendivelso
* @version 0.1
*/
public class SlotMachine{
    private Rectangle[] rectangleBodyParts;
    private ArrayList<String> symbolsColors;
    private ArrayList<Wheel> wheels;
    
    /**
     * Creates a slotMachine with a initial composition.
     */
    public SlotMachine(){
        wheels = new ArrayList<>();
        rectangleBodyParts = new Rectangle[1];
        
        for(int i = 0; i < rectangleBodyParts.length; i++){
            rectangleBodyParts[i] = new Rectangle();
        }
        
        prepareMachine();
        prepareSymbols();
        prepareWheels();
        
        makeVisible();
    }
    
    
    /**
     * Creates the Machine where the Wheels will be putted.
     */
    public void prepareMachine(){
        rectangleBodyParts[0].moveHorizontal(-rectangleBodyParts[0].getPosition()[0]+50);
        rectangleBodyParts[0].moveVertical(-rectangleBodyParts[0].getPosition()[1]+150);
        rectangleBodyParts[0].changeColor("gray");
        rectangleBodyParts[0].changeSize(200, 400);
    }
    
    /**
     * Creates all the starter symbols that the wheels will use.
     */
    public void prepareSymbols(){
        symbolsColors = new ArrayList<>();
        symbolsColors.add("green");
        symbolsColors.add("red");
        symbolsColors.add("blue");
    }
    
    /**
     * Creates started wheels that will use the created symbols.
     */
    public void prepareWheels(){
        wheels = new ArrayList<>();
        wheels.add(new Wheel());
        wheels.add(new Wheel());
        wheels.add(new Wheel());
        
        for(String s: symbolsColors){
            for(Wheel w: wheels){
                w.addSymbol(new Symbol(s));
            }
        }
        for(Wheel w: wheels){
                w.randomizeSymbol();
            }

    }
    
    /**
     * Makes visible the machine, its wheels and symbols.
     */
    public void makeVisible(){
        for(Rectangle r: rectangleBodyParts){
            r.makeVisible();
        }
        
        int espacioInterm = (rectangleBodyParts[0].getWidth() - 50*wheels.size())/(wheels.size() + 1);
        
        for(int i = 0; i < wheels.size(); i++){
            wheels.get(i).place(rectangleBodyParts[0].getPosition()[0] + espacioInterm*(1 + i) +50*i, rectangleBodyParts[0].getPosition()[1] + 50);
            wheels.get(i).makeVisible();
        } 
    }
    
    
    /**
     * Makes invisible the machine, its wheels and symbols.
     */
    
    public void makeInvisible(){
        for(Rectangle r: rectangleBodyParts){
            r.makeInvisible();
        }
        for(Wheel w: wheels){
            w.makeInvisible();
        }
    }
    
    
    /**
     * It returns all the symbol's colors.
     * @return Symbol's colors in order starting by one.
     */
    public String[] symbols(){
        String[] names = new String[symbolsColors.size()];
        for (int i = 0; i < symbolsColors.size(); i++){
            names[i] = symbolsColors.get(i);
        }
        return names;
    }

    /**
     * Add a new wheel in a specific position.
     * @param pos is the position of the new wheel.
     */
    public void addWheel(int pos){
        pos --;
        if(pos <= 0){
            pos = 0;
        }
        else if(pos > wheels.size()){
            pos = wheels.size();
        }
        
        wheels.add(pos, new Wheel());
        for(String color: symbols()){
            wheels.get(pos).addSymbol(new Symbol(color));
        }
        makeInvisible();
        wheels.get(pos).randomizeSymbol();
        rectangleBodyParts[0].changeSize(200, rectangleBodyParts[0].getWidth()+50);
        
        makeVisible();
    }
    
    /**
     * Deletes a specific wheel
     * @param pos is the position of thw wheel that we wanna delete
     */
    
    public void delWheel(int pos){
        pos --;
        if(pos <= 0){
            pos = 0;
        }
        else if(pos > wheels.size()){
            pos = wheels.size();
        }
        makeInvisible();
        wheels.remove(pos);
        makeVisible();
        
    }
    
    /**
     * Moves all the wheels to its next symbol.
     */
    public void spin(){
        for(Wheel w: wheels){
            w.spin();
        }
        makeVisible();
    }
    
    /**
     * Moves a specific wheel to its next symbol.
     */
    public void spin(int wheel){
        wheel --;
        if(wheel <= 0){
            wheel = 0;
        }
        else if(wheel > wheels.size()){
            wheel = wheels.size();
        }
        wheels.get(wheel).spin();
        wheels.get(wheel).makeVisible();
    }
    
    /**
     * Adds a symbol in a specific position, this symbol is also added to all the wheels
     */
    public void addSymbol(int pos, String color){
        pos --;
        if(pos <= 0){
            pos = 0;
        }
        if(pos > symbolsColors.size()+1){
            symbolsColors.add(color);
        }
        else{
            symbolsColors.add(pos, color);
        }
        
        for(Wheel w: wheels){
            w.addSymbol(pos+1, color);
        }
    }
    
    public void delSymbol(String symbol){
        symbolsColors.remove(symbol);
        for(Wheel w: wheels){
            w.delSymbol(symbol);
        }
    }
    
    /**
     * Generates a random number.
     * @param infLimit is the minimum number that can have.
     * @param supLimit - 1 is the maximum number that can have.
     * @return a random number between infLimit and supLimit-1
     */
    public static int randomNumGenerator(int infLimit, int supLimit){
        int numeroRand = infLimit + (int)(Math.random()* supLimit);
        return numeroRand;
    }
    
}
