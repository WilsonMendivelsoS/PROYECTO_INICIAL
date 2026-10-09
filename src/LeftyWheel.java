
/**
 * Write a description of class LeftyWheel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeftyWheel extends Wheel
{
    /**
     * Constructor for objects of class LeftyWheel
     */
    public LeftyWheel()
    {
        super();
        this.rectangleBodyPart.changeColor("Lefty");
    }

    /**
     * 
     */
    @Override
    public boolean canCopyTheColorFromLeft(){
        return true;
    }
}