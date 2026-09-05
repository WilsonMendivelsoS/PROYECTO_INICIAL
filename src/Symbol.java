
/**
 * Represents a symbol with a specefic shape and color.<br>
 * <b>circleBody, triangleBody, rectangleBody</b><br>
 * <b>Inv: circleBody != null</b> or <b> triangleBody != null</b> or <b> rectangleBody != null </b>
 * @author David Garzon, Wilson Mendivelso
 * @version 0.1
 */
public class Symbol
{
    private Figure body;
    
    /**
     * Create a new symbol with a specific color and an specific shape.
     */
    public Symbol(String figure, String color){   
        if(figure == "RECTANGLE"){
            body = new Rectangle(color);
        }
        else if(figure == "TRIANGLE"){
            body = new Triangle(color);
        }
        else{
            body = new Circle(color);
        }
    }
    
    /**
     * Make this symbol visible.
     */
    public void makeVisible(){
        body.makeVisible();
    }
    
    /**
     * Make this symbol invisible.
     */
    public void makeInvisible(){
        body.makeInvisible();
    }
    
    /**
     * Puts the Symbol in a specific position.
     */
    public void place(int x, int y){
        makeInvisible();
        body.moveHorizontal(-body.getPosition()[0]+x);
        body.moveVertical(-body.getPosition()[1]+y);
        makeVisible();
    }
    
    /**
     * Get symbol's color.
     * @return the color's name.
     */
    public String getColor(){
        return body.getColor();
    }
    
    /**
     * Get symbol's body name.
     * @return body's figure.
     */
    public String getBodyFigureName(){
        return body.getFigureName();
    }
}