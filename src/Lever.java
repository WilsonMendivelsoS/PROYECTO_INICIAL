
/**
 * Represents a Lever.
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 2
 */
public class Lever
{
    private Circle head;
    private Rectangle[] body;
    
    /**
     * Creates a new Lever
     */
    public Lever(){
        head = new Circle("red");
        head.changeSize(30);
        body = new Rectangle[4];
        for(int i = 0; i < body.length; i++){
            body[i] = new Rectangle();
        }
        prepareBody();
    }
    
    private void prepareBody(){
        //Handle body
        body[0].changeColor("black");
        body[0].changeSize(10,70);
        //Handle body up
        body[1].changeColor("black");
        body[1].changeSize(200, 10);
        //False handle
        body[2].changeColor("white");
        body[2].changeSize(100, 10);
        //False down
        body[3].changeColor("white");
        body[3].changeSize(100, 10);
    }
    
    /**
     * Changes bodyParts position adapting all the body parts to the slotMachine window
     * @param machineWidth is the machine's width
     * @param machineHeight is the machine's height.
     */
    public void changeBodyPartsPosition(int machineWidth, int machineHeight){
        //Move head
        head.moveHorizontal(-head.getPosition()[0] + 50 + machineWidth + 70 - 2 *10);
        head.moveVertical(-head.getPosition()[1] + 50 -100+ machineHeight/2);
        
        body[0].moveHorizontal(-body[0].getPosition()[0]+50+ machineWidth);
        body[0].moveVertical(-body[0].getPosition()[1]+50+machineHeight/2);
        
        body[1].moveHorizontal(-body[1].getPosition()[0] + 50+machineWidth + 70 -10);
        body[1].moveVertical(-body[1].getPosition()[1] + head.getPosition()[1]+10);
        
        body[2].moveHorizontal(-body[2].getPosition()[0]+head.getPosition()[0]+10);
        body[2].moveVertical(-body[2].getPosition()[1]+head.getPosition()[1]-100);
        
        body[3].moveHorizontal(-body[3].getPosition()[0]+head.getPosition()[0]+10);
        body[3].moveVertical(-body[3].getPosition()[1]+head.getPosition()[1]+111);
    }
    
    public void makeVisible(){
        for(Rectangle r: body){
            r.makeVisible();
        }
        head.makeVisible();
    }
    
    public void makeInvisible(){
        head.makeInvisible();
        for(Rectangle r: body){
            r.makeInvisible();
        }
    }
    
    /**
     * Animates the lever
     */
    public void animation(){
        int speed = 3;
        for(int i = 0; i <99/speed; i++){
            if (i>81/speed){
                body[3].fastMoveVertical(speed,speed);
            }
            body[2].fastMoveVertical(speed,speed); 
            head.fastMoveVertical(speed,speed);  
        }

        for(int i = 0; i < 72/speed; i++){
            body[3].fastMoveVertical(speed,speed);
            head.fastMoveVertical(speed,speed);
        }
        
        for(int i = 0; i < 72/speed; i++){
            head.fastMoveVertical(-speed,speed);  
            body[3].fastMoveVertical(-speed,speed);
        }
        for(int i = 0; i <99/speed; i++){
            head.fastMoveVertical(-speed,speed);     
            if(i<15/speed){
                body[3].fastMoveVertical(-speed,speed);
            }
            body[2].fastMoveVertical(-speed,speed); 
        }
    }
}