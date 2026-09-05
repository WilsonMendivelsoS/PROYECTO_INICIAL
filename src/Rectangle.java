import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class Rectangle extends Figure{

    public static int EDGES = 4;
    
    private int height;
    private int width;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        super();
        height = 30;
        width = 30;
    }
    
    /**
     * Create a new rectangle at default position with a specific color.
     */
    public Rectangle(String color){
        this();
        this.color = color;
    }

    /**
     * Move the rectangle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the rectangle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the rectangle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the rectangle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }


    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidht the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    

    /*
     * Draw the rectangle with current specifications on screen.
     */
    @Override
    protected void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }
    
    /**
     * Return rectangle's height.
     * @return rectangle's height.
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Return rectangle's width.
     * @return rectangle's width.
     */
    public int getWidth(){
        return width;
    }
    

    /*
     * Draw the rectangle very fast with current specifications on screen.
     */
    @Override
    protected void fastDraw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(1);
        }
    }
    
    
    /**
     * Gives the Figure's name.
     */
    @Override
    public String getFigureName(){
        return "RECTANGLE";
    }
}

