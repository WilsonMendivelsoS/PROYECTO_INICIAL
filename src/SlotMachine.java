import java.util.ArrayList;
import java.lang.Math;
import javax.swing.JOptionPane;

/**
* Represents a slotMachine, it can add wheels and spin them, if you wanna win, you will need a jackpot.<br>
* <b>(symbolsQuantity, wheelsQuantity, lever, rectangleBodyParts, symbols, wheels)</b><br>
* <b>Inv: symbols</b> > 0 and <b>wheels</b> > 0 
* @author David Garzon, Wilson Mendivelso
* @version 1
*/
public class SlotMachine{
    private Rectangle[] rectangleBodyParts;
    private ArrayList<Figure> symbolsFigure;
    private ArrayList<Wheel> wheels;
    private Circle handle;
    private boolean isVisual;
    
    /** 
     * 
     * Creates a slotMachine with a initial composition.
     */
    public SlotMachine(){
        this(true);
    }

    /**
     * Creates a slotMachine with optional visualization
     * @param visible true to show the machine, false is for tests
     */
    public SlotMachine(boolean isVisual){
        this.isVisual = isVisual;
        wheels = new ArrayList<>();
        rectangleBodyParts = new Rectangle[6];
        handle = new Circle("red");
        handle.changeSize(30);
        
        for(int i = 0; i < rectangleBodyParts.length; i++){
            rectangleBodyParts[i] = new Rectangle();
        }
        
        prepareMachine();
        prepareSymbols();
        prepareWheels();
        if (distinctSymbols()==1){
            int temp1=randomNumGenerator(0, wheels.size()-1);
            spin(temp1);
        }
        makeVisible();
    }
    
    /**
     * Creates the Machine where the Wheels will be putted.
     */
    private void prepareMachine(){
        rectangleBodyParts[0].moveHorizontal(-rectangleBodyParts[0].getPosition()[0]+50);
        rectangleBodyParts[0].moveVertical(-rectangleBodyParts[0].getPosition()[1]+50);
        rectangleBodyParts[0].changeColor("gray");
        rectangleBodyParts[0].changeSize(200, 400);
        //Handle body
        rectangleBodyParts[1].changeColor("black");
        rectangleBodyParts[1].changeSize(10,70);
        //Handle body up
        rectangleBodyParts[2].changeColor("black");
        rectangleBodyParts[2].changeSize(200, 10);
        //False handle
        rectangleBodyParts[3].changeColor("white");
        rectangleBodyParts[3].changeSize(100, 10);
        //False down
        rectangleBodyParts[4].changeColor("white");
        rectangleBodyParts[4].changeSize(100, 10);
        changeBodyPartsPosition();
        
        //Base
        rectangleBodyParts[5].changeSize( 25 , rectangleBodyParts[0].getWidth()+50);
        rectangleBodyParts[5].changeColor("black");
    }
    
    /**
     * Creates all the starter symbols that the wheels will use.
     */
    private void prepareSymbols(){
        symbolsFigure = new ArrayList<>();
        String[] colors = {"green", "red", "blue"};
        for(int i = 0; i < 3; i++){
            int ran = randomNumGenerator(0,3);
            if(ran == 0){
                symbolsFigure.add(new Triangle(colors[i]));
            }
            else if(ran == 1){
                symbolsFigure.add(new Rectangle(colors[i]));
            }
            else if(ran == 2){
                symbolsFigure.add(new Circle(colors[i]));
            }
        }
    }
    
    /**
     * Creates started wheels that will use the created symbols.
     */
    private void prepareWheels(){
        wheels.add(new Wheel());
        wheels.add(new Wheel());
        wheels.add(new Wheel());
        
        for(Figure f: symbolsFigure){
            for(Wheel w: wheels){
                w.addSymbol(new Symbol(f.getFigureName(), f.getColor()));
            }
        }
        for(Wheel w: wheels){
                w.randomizeSymbol();
            }

    }
    
    /**
     * Makes visible the machine, its wheels and symbols.
     */
    public void makeVisible(){
        if(!isVisual){
            return;
        }
        for(Rectangle r: rectangleBodyParts){
            if(!r.isVisible()){
                r.makeVisible();    
            }
        }
        handle.makeVisible();
        int espacioInterm = (rectangleBodyParts[0].getWidth() - 50*Math.min(wheels.size(),13))/(Math.min(wheels.size(),13) + 1);
        
        for(int j = 0; j < wheels.size(); j++){
            int posX = j % 13;
            int posY = j/13;
            wheels.get(j).place(rectangleBodyParts[0].getPosition()[0] + espacioInterm*(1 + posX) + 50 * posX, rectangleBodyParts[0].getPosition()[1] +50 + 125*posY);
            wheels.get(j).makeVisible();
        }
    }
    
    
    /**
     * Makes invisible the machine, its wheels and symbols.
     */
    
    public void makeInvisible(){
        for(Rectangle r: rectangleBodyParts){
            r.makeInvisible();
        }
        for(Wheel w: wheels){
            w.makeInvisible();
        }
        handle.makeInvisible();
    }
    
    
    /**
     * It returns all the symbol's colors.
     * @return Symbol's colors in order starting by one.
     */
    public String[] symbols(){
        String[] names = new String[symbolsFigure.size()];
        for (int i = 0; i < symbolsFigure.size(); i++){
            names[i] = symbolsFigure.get(i).getColor();
        }
        return names;
    }

    /**
     * Add a new wheel in a specific position.
     * @param pos is the position of the new wheel.
     */
    public void addWheel(int pos){
        if(ok()){
            pos --;
            if(pos <= 0){
                pos = 0;
            }
            else if(pos > wheels.size()){
                pos = wheels.size();
            }
                
            wheels.add(pos, new Wheel());
            for(Figure f: symbolsFigure){
                wheels.get(pos).addSymbol(new Symbol(f.getFigureName(), f.getColor()));
            }
            makeInvisible();
            wheels.get(pos).randomizeSymbol();
            
            // If wheels are 13 or 26 or 39, slotMachine will be higher.  
            if(wheels.size() <14 ){
                rectangleBodyParts[0].changeSize(200, rectangleBodyParts[0].getWidth()+60);
            }
            else{
                rectangleBodyParts[0].changeSize(((wheels.size()/13)+1)*150, rectangleBodyParts[0].getWidth());
            }
            rectangleBodyParts[5].changeSize(25, rectangleBodyParts[0].getWidth()+50);
            
            changeBodyPartsPosition();
        
            makeVisible();
        }else{
            JOptionPane.showMessageDialog(null, "Accion Invalida");
        }
    }
    
    /**
     * Changes bodyParts position adapting all the body parts to the slotMachine window
     */
    private void changeBodyPartsPosition(){
        handle.moveHorizontal(-handle.getPosition()[0] + rectangleBodyParts[0].getPosition()[0] + rectangleBodyParts[0].getWidth()+70-2*10);
        handle.moveVertical(-handle.getPosition()[1]+ rectangleBodyParts[0].getPosition()[1]-100+rectangleBodyParts[0].getHeight()/2);
        
        rectangleBodyParts[1].moveHorizontal(-rectangleBodyParts[1].getPosition()[0]+rectangleBodyParts[0].getPosition()[0]+rectangleBodyParts[0].getWidth());
        rectangleBodyParts[1].moveVertical(-rectangleBodyParts[1].getPosition()[1]+rectangleBodyParts[0].getPosition()[1]+rectangleBodyParts[0].getHeight()/2);
        
        rectangleBodyParts[2].moveHorizontal(-rectangleBodyParts[2].getPosition()[0]+rectangleBodyParts[0].getPosition()[0]+rectangleBodyParts[0].getWidth()+70-10);
        rectangleBodyParts[2].moveVertical(-rectangleBodyParts[2].getPosition()[1]+handle.getPosition()[1]+10);
        
        rectangleBodyParts[3].moveHorizontal(-rectangleBodyParts[3].getPosition()[0]+handle.getPosition()[0]+10);
        rectangleBodyParts[3].moveVertical(-rectangleBodyParts[3].getPosition()[1]+handle.getPosition()[1]-100);
        
        rectangleBodyParts[4].moveHorizontal(-rectangleBodyParts[4].getPosition()[0]+handle.getPosition()[0]+10);
        rectangleBodyParts[4].moveVertical(-rectangleBodyParts[4].getPosition()[1]+handle.getPosition()[1]+100+10);
        
        rectangleBodyParts[5].moveHorizontal(-rectangleBodyParts[5].getPosition()[0]+rectangleBodyParts[0].getPosition()[0]-25);
        rectangleBodyParts[5].moveVertical(-rectangleBodyParts[5].getPosition()[1] +rectangleBodyParts[0].getPosition()[1]+rectangleBodyParts[0].getHeight());
    }
    
    public void addSomeWheels(int num){
        for(int i = 0; i < num; i++){
            addWheel(0);
        }
    }
    
    /**
     * Deletes a specific wheel
     * @param pos is the position of thw wheel that we wanna delete
     */
    
    public void delWheel(int pos){
        if(ok()){
            pos --;
            if(pos <= 0){
                pos = 0;
            }
            else if(pos >= wheels.size()){
                pos = wheels.size()-1;
            }
            makeInvisible();
            wheels.remove(pos);
            isJackPot();
            
            
            // If wheels are 13 or 26 or 39, slotMachine will be lower.  
            if(wheels.size() <13 ){
                rectangleBodyParts[0].changeSize(200, rectangleBodyParts[0].getWidth()-60);
            }
            else{
                rectangleBodyParts[0].changeSize(((wheels.size()/13)+1)*150, rectangleBodyParts[0].getWidth());
            }
            
            rectangleBodyParts[5].changeSize(25, rectangleBodyParts[0].getWidth()+50);
            
            changeBodyPartsPosition();
            
            
            makeVisible();
        }else{
            JOptionPane.showMessageDialog(null, "Accion Invalida");
        }
    }
    
    
    /**
     * Moves all the wheels to its next symbol.
     */
    private void spin(){
        animation();
        for(Wheel w: wheels){
            w.spin();
        }
        makeVisible();
    }
    
    /**
     * Moves a specific wheel to its next symbol.
     */
    public void spin(int wheel){
        if(ok()){
            wheel = Math.min(Math.max(0, wheel-1), wheels.size()-1);
            animation();
        }
        wheels.get(wheel).spin();
        isJackPot();
        makeVisible();
    }
    
    /**
     * Return a List with the color of the current symbols  arranged from left to right. of the slotmachine
     * @return String[] with the color of the current symbols
     */
    public String[] configuration(){
        String[] actualSym = new String[wheels.size()];
        int c =0;
        for(Wheel w: wheels){
            actualSym[c]=w.colorCurrentSymbol();
            c++;
        }
        return actualSym;
    }
    
    /**
     * Place a wheel in a specific symbol
     * @param wheel is the number of the wheel.
     * @param symbol is the name of the symbol
     */
    public void placeSymbol(int wheel, String symbol){
        boolean colorExists = false;
        for(String c: Canvas.colors){
            if(symbol.equals(c)){
                colorExists = true;
                break;
            }
        }
        if(!colorExists){
            return;
        }
        boolean canBePlaced = false;
        int idxSymbol = 0;
        for(Figure f: symbolsFigure){
            if(symbol.equals(f.getColor())){
                canBePlaced = true;
                idxSymbol = symbolsFigure.indexOf(f);
                break;
            }
        }
        
        if(canBePlaced){
            animation();
            wheels.get(Math.min(Math.max(0, wheel-1), wheels.size()-1)).setCurrentSymbol(idxSymbol);
            makeVisible();
        }
    }
    
    /**
     * Return the number of the distrincs Symbols of the currents wheels
     */
    public int distinctSymbols(){
        String[] actualColorSym = configuration();
        ArrayList<String> dif= new ArrayList<>();
        for(String color : actualColorSym){
            boolean encontrado = false;
            for( String colorIn : dif){
                if(color.equals(colorIn)){
                    encontrado = true;
                }
            }
            if(encontrado == false){
                dif.add(color);
            }
        }
        
        return dif.size();
    }
    
    /**
     * return true if the current number of symbols is 1 iand finish the game
     * @return true if the symbols currents in the wheels are equals 1
     */
    public boolean isJackPot(){
        if(distinctSymbols()==1 && symbols().length > 1){
            rectangleBodyParts[0].changeColor("yellow");
            makeVisible();
            if(isVisual){
                JOptionPane.showMessageDialog(null, "Ganaste");    
            }
            
            return true;
        }
        return false;
    } 
    
    /**
     * Adds a symbol in a specific position, this symbol is also added to all the wheels
     */
    public void addSymbol(int pos, String color){
        boolean colorExists = false;
        for(String c: Canvas.colors){
            if(color.equals(c)){
                colorExists = true;
                break;
            }
        }
        if(!colorExists){
            return;
        }
        for(Figure f: symbolsFigure){
            if(f.getColor().equals(color)){
                return;
            }
        }
        pos = Math.min(Math.max(0, pos-1), symbolsFigure.size());
        int ran = randomNumGenerator(0,3);
        if(ran == 0){
            symbolsFigure.add(pos, new Triangle(color));
        }
        else if(ran == 1){
            symbolsFigure.add(pos, new Rectangle(color));
        }
        else if(ran == 2){
            symbolsFigure.add(pos,new Circle(color));
        }
        
        for(Wheel w: wheels){
            w.addSymbol(pos+1, color, symbolsFigure.get(pos).getFigureName());
        }            
        
    }
    /**
     * Deletes a specific symbol
     */
    public void delSymbol(String symbolColor){
        if(ok()){
            for(int i = 0; i < symbolsFigure.size(); i++){
                if(symbolsFigure.get(i).getColor().equals(symbolColor)){
                    symbolsFigure.remove(i);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Accion Invalida");
        }
    }

    /**
     * The program ends with a message and make invisible all wheels and symbols.
     */
    public void exit(){
        makeInvisible();
        JOptionPane.showMessageDialog(null, "Adios");
    }

    
    /**
     * check if the last action can be performed
     * @return true if the last action can be performed
     */
    public boolean ok(){
        while(wheels.size() ==2|| wheels.size()==50){
            if (symbolsFigure.size()==2){
                return false;
            }else{
                break;
            }
        }
        return true;
    }
  
    /**
     * Animates the slotMachine
     */
    private void animation(){
        for(int i = 0; i <49; i++){
            handle.fastMoveVertical(2,2);
            rectangleBodyParts[3].fastMoveVertical(2,2);
            if (i>40){
                rectangleBodyParts[4].fastMoveVertical(2,2);
            }
        }

        for(int i = 0; i < 36; i++){
            rectangleBodyParts[4].fastMoveVertical(2,2);
            handle.fastMoveVertical(2,2);
        }
        
        
        for(int i = 0; i < 36; i++){
            handle.fastMoveVertical(-2,2);
            rectangleBodyParts[4].fastMoveVertical(-2,2);
        }
        for(int i = 0; i <49; i++){
            rectangleBodyParts[3].fastMoveVertical(-2,2);
            handle.fastMoveVertical(-2,2);       
            if(i<8){
                rectangleBodyParts[4].fastMoveVertical(-2,2);
            }
        }
    }
    
    
    /**
     * Generates a random number.
     * @param infLimit is the minimum number that can have.
     * @param supLimit - 1 is the maximum number that can have.
     * @return a random number between infLimit and supLimit-1
     */
    public static int randomNumGenerator(int infLimit, int supLimit){
        int numeroRand = infLimit + (int)(Math.random()* supLimit);
        return numeroRand;
    }
    
    
    //Llenar
    public void swap(int wheel1, int wheel2){
        
    }
    
    //Llenar
    public void lock(int wheel){
        
    }
    
    //Llenar
    public void unlock(int wheel){
        
    }
    
    //Llenar
    public void spin(int wheel, int steps){
        
    }
    
    //Llenar
    public void spin(String[] setSymbols){
        
    }
}
