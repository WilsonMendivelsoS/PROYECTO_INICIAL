import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */

public class Circle extends Figure{
    public static final double PI=3.1416;
    
    private int diameter;
    
    /**
     * Create a new circle at default position with default color.
     */
    public Circle(){
        super();
        diameter = 30;
    }
    
    /**
     * Create a new circle at default position with a specific color.
     */
    public Circle(String color){
        this();
        this.color = color;
    }
    
    /*
     * Draw the circle with current specifications on screen.
     */
    @Override
    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }
    
    /*
     * Draw the circle very fast with current specifications on screen.
     */
    @Override
    protected void fastDraw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(1);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }


    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Gives the Figure's name.
     */
    @Override
    public String getFigureName(){
        return "CIRCLE";
    }
    
}
