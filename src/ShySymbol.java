/**
 * Represents a ShySymbol that is a special type of symbol
 */
public class ShySymbol extends Symbol{
    
    /**
     * Creates a ShySymbol
     */
    public ShySymbol(String color, int figureNum){
        super(color, figureNum);
    }
    
    /**
     * Creates a copy of this EphemeralSymbol
     * @return a EphemeralSymbol with the same atributes.
     */
    @Override
    public ShySymbol copy(){
        return new ShySymbol(getFigure().getColor(), getFigureNum());
    }
    
    /**
     * Makes visible its figure
     */
    @Override
    public void makeVisible(){
        Figure f = getFigure();
        f.makeVisible();
        Canvas.getCanvas().wait(300);
        f.makeInvisible();
    }
    
}