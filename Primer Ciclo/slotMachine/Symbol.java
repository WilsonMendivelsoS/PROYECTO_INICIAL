
/**
 * Represents a symbol with a specefic shape and color.<br>
 * <b>circleBody, triangleBody, rectangleBody</b><br>
 * <b>Inv: circleBody != null</b> or <b> triangleBody != null</b> or <b> rectangleBody != null </b>
 * @author David Garzon, Wilson Mendivelso
 * @version 0.1
 */
public class Symbol
{
    private Circle circleBody;
    private Triangle triangleBody;
    private Rectangle rectangleBody;
    
    /**
     * Create a new symbol with a specific color and a random shape.
     */
    public Symbol(String color){        
        int shape = SlotMachine.randomNumGenerator(0,3);
        if(shape == 0){
            circleBody = new Circle(color);
        }
        else if(shape == 1){
            triangleBody = new Triangle(color);
        }
        else if(shape == 2){
            rectangleBody = new Rectangle(color);
        }
    }
    
    /**
     * Make this symbol visible.
     */
    public void makeVisible(){
        if (circleBody != null){
            circleBody.makeVisible();
        }
        else if(triangleBody != null){
            triangleBody.makeVisible();
        }
        else{
            rectangleBody.makeVisible();
        }
    }
    
    /**
     * Make this symbol invisible.
     */
    public void makeInvisible(){
        if (circleBody != null){
            circleBody.makeInvisible();
        }
        else if(triangleBody != null){
            triangleBody.makeInvisible();
        }
        else{
            rectangleBody.makeInvisible();
        }
    }
    
    /**
     * Puts the Symbol in a specific position.
     */
    public void place(int x, int y){
        makeInvisible();
        if(circleBody != null){
            circleBody.moveHorizontal(-circleBody.getPosition()[0]+x);
            circleBody.moveVertical(-circleBody.getPosition()[1]+y);
        }
        else if(triangleBody != null){
            triangleBody.moveHorizontal(-triangleBody.getPosition()[0]+x);
            triangleBody.moveVertical(-triangleBody.getPosition()[1]+y);
        }
        else{
            rectangleBody.moveHorizontal(-rectangleBody.getPosition()[0]+x);
            rectangleBody.moveVertical(-rectangleBody.getPosition()[1]+y);
        }
        makeVisible();
    }
    
    /**
     * Get the symbols color.
     * @return the color's name.
     */
    public String getColor(){
        if (circleBody != null){
            return circleBody.getColor();
        }
        else if(triangleBody != null){
            return triangleBody.getColor();
        }
        else{
            return rectangleBody.getColor();
        }
    }

}