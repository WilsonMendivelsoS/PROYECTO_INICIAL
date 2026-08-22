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
    private int symbolsQuantity;
    private int wheelsQuantity;
    private Circle lever;
    private Rectangle[] rectangleBodyParts;
    private ArrayList<Symbol> symbols;
    private ArrayList<Wheel> wheels;
    
    public SlotMachine(){
        symbolsQuantity = 0;
        wheelsQuantity = 0;
        
        lever = new Circle();
        rectangleBodyParts = new Rectangle[2];
        
        for(int i = 0; i < rectangleBodyParts.length; i++){
            rectangleBodyParts[i] = new Rectangle();
        }
        
        symbols.add(new Symbol("black"));
        symbols.add(new Symbol("green"));
        symbols.add(new Symbol("red"));
        symbols.add(new Symbol("blue"));

        symbolsQuantity = 3;
        
        wheels.add(new Wheel());
        wheels.add(new Wheel());
        wheels.add(new Wheel());
        
        for(int i = 1; i < symbols.size(); i++){
            wheels.get(1).addSymbol(symbols.get(i));
            wheels.get(1).addSymbol(symbols.get(i));
            wheels.get(1).addSymbol(symbols.get(i));

        }
    }
    
    public static int randomNumGenerator(int infLimit, int supLimit){
        int numeroRand = infLimit + (int)(Math.random()* supLimit);
        return numeroRand;
    }
    
}
