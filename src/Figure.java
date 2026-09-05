import java.awt.*;

/**
 * Represents a Figure
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class Figure
{
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;
    /**
     * Creates a new Figure
     */
    public Figure(){
        xPosition = 0;
        yPosition = 0;
        color = "black";
        isVisible = false;
    }
    /*
     * Draws the figure on screen
     */
    protected void draw(){
        
    }
    
    /*
     * Erase the figure on screen.
     */
    protected void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /*
     * Draws quickly the figure
     */
    protected void fastDraw(){
        
    }
    
    /**
     * Slowly move the figure vertically.
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
    
    /**
     * Slowly move the figure horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }
    
    /**
     * Fastly move the figure vertically
     * @param distance the desired distance in pixels
     * @param speed is speed drawing
     */
    public void fastMoveVertical(int distance, int speed){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i = i+speed){
            yPosition += speed*delta;
            fastDraw();
        }
    }
    
    /**
     * Returns figure's position
     * @return the xPosition and the yPosition
     */
    public int[] getPosition(){
        int[] temp = {xPosition, yPosition};
        return temp;
    }
    
    /**
     * Return figure's color.
     * @return figure's color.
     */
    public String getColor(){
        return color;
    }
    
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green", "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
    
    /**
     * Gives the Figure's name.
     * @return figure's name
     */
    public String getFigureName(){
        return "";
    }
    
    /**
     * Returns if figure is visible.
     * @return if it is visible
     */
    public boolean isVisible(){
        return isVisible;
    }
    
    /**
     * Make this figure visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this figure invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * Move the figure horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the figure vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
}