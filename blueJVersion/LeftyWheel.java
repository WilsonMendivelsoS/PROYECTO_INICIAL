import java.util.ArrayList;
/**
 * Represents a LeftyWheel
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
    
    /*
     * Move the wheel and takes the state of the left wheel, if it is not the first wheel.
     */
    @Override
    public void spin(){
        ArrayList<Wheel> wheels = SlotMachine.getWheels();
        if(wheels.get(0) ==this ){
            super.spin();
        }
        else{
            for(Wheel w: wheels){
                if(w == this){
                    int posThis = wheels.indexOf(w);
                    int newCurrentSymbol = wheels.get(posThis-1).getCurrentSymbol(); 
                    setCurrentSymbol(newCurrentSymbol);
                }
            }
        }
    }
    
    /*
     * Move the wheel n steps 
     * @param steps number of wheel will advance 
     */
    @Override
    public void spin(int steps){
        ArrayList<Wheel> wheels = SlotMachine.getWheels();
        if(wheels.get(0) ==this ){
            super.spin(steps);
        }
        else{
            spin();
        }
    }
}