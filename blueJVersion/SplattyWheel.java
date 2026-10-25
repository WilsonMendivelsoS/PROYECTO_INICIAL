import java.util.ArrayList;
/**
 * Represents splattyWheel if he moves, he will move the wheel in his left.
 */
public class SplattyWheel extends Wheel
{
    /**
     * Constructor for objects of class LeftyWheel
     */
    public SplattyWheel()
    {
        super();
        this.rectangleBodyPart.changeColor("brick");
    }
    
    /**
     * 
     */
    @Override
    public void spin(){
        ArrayList<Wheel> wheels = SlotMachine.getWheels();
        int me = 0;
        
        for(Wheel w: wheels){
            if(w == this){
                me = wheels.indexOf(w);
            }
        }
        if(me!= 0){
            Wheel leftWheel = wheels.get(me-1);
            boolean isVisible = leftWheel.isVisible();
            if(isVisible){
                leftWheel.makeInvisible();
            }
            leftWheel.spin();
            if(isVisible){
                leftWheel.makeVisible();
            }
        }
        super.spin();
    }
    
    /**
     * 
     */
    @Override
    public void spin(int steps){
        ArrayList<Wheel> wheels = SlotMachine.getWheels();
        int me = 0;
        for(Wheel w: wheels){
            if(w == this){
                me = wheels.indexOf(w);
            }
        }
        if(me!= 0){
            Wheel leftWheel = wheels.get(me-1);
            boolean isVisible = leftWheel.isVisible();
            if(isVisible){
                leftWheel.makeInvisible();
            }
            leftWheel.spin(steps);
            if(isVisible){
                leftWheel.makeVisible();
            }
        }
        super.spin(steps);
    }
}