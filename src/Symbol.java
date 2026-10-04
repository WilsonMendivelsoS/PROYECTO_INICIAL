/**
 * Represets a Symbol.
 * @author David Garzon, Wilson Mendivelso
 * @version 1
 */
public class Symbol{
    private Figure figure;
    private int figureNum;
    
    /**
     * Creates a Symbol
     * @param color is the figure's color
     * @param figureNum determinates which figure will we use. If figureNum is 1 it will do a Circle,
     * if it is two it will do a Rectangle, if it is other it will do a Triangle
     */
    public Symbol(String color, int figureNum){
        this.figureNum = figureNum;
        
        if(figureNum == 1) figure= new Circle(color);
        else if(figureNum == 2) figure = new Rectangle(color);
        else figure = new Triangle(color);
    }
    /**
     * Makes a copy of this symbol cloning it
     * @return a symbol with the same atributes.
     */
    public Symbol copy(){
        return new Symbol(figure.getColor(), figureNum);
    }
    
    /**
     * Makes visible the figure
     */
    public void makeVisible(){
        figure.makeVisible();
    }
    
    /**
     * Makes invisible the figure
     */
    public void makeInvisible(){
        figure.makeInvisible();
    }
    
    /**
     * Puts the Symbol in a specific position.
     * @param x is the x position
     * @param y is the y position
     */
    public void place(int x, int y){
        figure.place(x,y);
    }
    
    /**
     * Return Symbols's color.
     * @return Symbols's color.
     */
    public String getColor(){
        return figure.getColor();
    }
}