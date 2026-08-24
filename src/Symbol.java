
/**
 * Represents a symbol with a specefic shape and color.<br>
 * <b>circleBody, triangleBody, rectangleBody</b><br>
 * <b>Inv: circleBody != null</b> or <b> triangleBody != null</b> or <b> rectangleBody != null </b>
 * @author David Garzon, Wilson Mendivelso
 * @version 0.1
 */
public class Symbol
{
    private Rectangle rectangleBody;
    
    /**
     * Create a new symbol with a specific color and a random shape.
     */
    public Symbol(String color){   
        rectangleBody = new Rectangle(color);
    }
    
    /**
     * Make this symbol visible.
     */
    public void makeVisible(){
        rectangleBody.makeVisible();
    }
    
    /**
     * Make this symbol invisible.
     */
    public void makeInvisible(){
        rectangleBody.makeInvisible();
    }
    
    /**
     * Puts the Symbol in a specific position.
     */
    public void place(int x, int y){
        makeInvisible();
        rectangleBody.moveHorizontal(-rectangleBody.getPosition()[0]+x);
        rectangleBody.moveVertical(-rectangleBody.getPosition()[1]+y);
        makeVisible();
    }
    
    /**
     * Get symbol's color.
     * @return the color's name.
     */
    public String getColor(){
        return rectangleBody.getColor();
    }
}