/**
 * Represents a EphemeralSymbol that is a special type of symbol
 */
public class EphemeralSymbol extends Symbol{
    
    /**
     * Creates a EphemeralSymbol
     */
    public EphemeralSymbol(String color, int figureNum){
        super(color, figureNum);
    }
    
    /**
     * Creates a copy of this EphemeralSymbol
     * @return a EphemeralSymbol with the same atributes.
     */
    @Override
    public EphemeralSymbol copy(){
        return new EphemeralSymbol(getFigure().getColor(), getFigureNum());
    }
    
    /**
     * Does an action. In this case the figure will be smaller.
     */
    @Override
    public void action(){
        getFigure().decreaseSize();
    }
}