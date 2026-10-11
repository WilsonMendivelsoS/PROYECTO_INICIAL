
/**
 * Represents a RebelWheel
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
     * return if get is locked
     * @return true
     */
    @Override
    public boolean getIsLocked(){
        return true;
    }
    
    /**
     * set false in isLocked 
     * @param isLocked
     */
    @Override 
    public void setIsLocked(boolean isLocked){
        super.setIsLocked(false);
    }
    
    /**
     * return if can swap
     * @return false
     */
    @Override
    public boolean canSwap(){
        return false;
    }
    
    /**
     * return if can be delete
     * @return false
     */
    @Override
    public boolean canBeDelete(){
        return false;
    }
}