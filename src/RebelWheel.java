
/**
 * Write a description of class RebelWheel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RebelWheel extends Wheel
{
    /**
     * Constructor for objects of class RebelWheel
     */
    public RebelWheel()
    {   
        super();
        this.rectangleBodyPart.changeColor("Rebel");
    }

    /**
     * 
     */
    @Override
    public boolean getIsLocked(){
        return true;
    }
    
    /**
     * 
     */
    @Override 
    public void setIsLocked(boolean isLocked){
        super.setIsLocked(false);
    }
    
    /**
     * 
     */
    @Override
    public boolean canSwap(){
        return false;
    }
    
    /**
     * 
     */
    @Override
    public boolean canBeDelete(){
        return false;
    }
}