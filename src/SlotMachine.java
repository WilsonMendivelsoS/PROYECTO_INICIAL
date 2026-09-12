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
    private static ArrayList<Figure> symbols;
    private ArrayList<Wheel> wheels;
    private Lever lever;
    private boolean isVisual;
    private boolean ok;
    
    /** 
     * 
     * Creates a slotMachine with a initial composition.
     */
    public SlotMachine(){
        isVisual = false;
        wheels = new ArrayList<>();
        rectangleBodyParts = new Rectangle[2];
        lever = new Lever();
        ok = true;
        
        for(int i = 0; i < rectangleBodyParts.length; i++){
            rectangleBodyParts[i] = new Rectangle();
        }
        prepareMachine();
        prepareSymbols(); //Borrar y revisar qué pasa, porque ahora todo debe iniciar en 0
        prepareWheels(); //Borrar y revisar qué pasa, porque ahora todo debe iniciar en 0

    }

    /**
     * Creates the Machine where the Wheels will be putted.
     */
    private void prepareMachine(){
        rectangleBodyParts[0].moveHorizontal(-rectangleBodyParts[0].getPosition()[0]+50);
        rectangleBodyParts[0].moveVertical(-rectangleBodyParts[0].getPosition()[1]+50);
        rectangleBodyParts[0].changeColor("gray");
        rectangleBodyParts[0].changeSize(200, 400);
        
        changeBodyPartsPosition();
        
        //Base
        rectangleBodyParts[1].changeSize( 25 , rectangleBodyParts[0].getWidth()+50);
        rectangleBodyParts[1].changeColor("black");
    }
    
    /**
     * Creates all the starter symbols that the wheels will use.
     */
    private void prepareSymbols(){//Borrar y revisar qué pasa, porque ahora todo debe iniciar en 0
        symbols = new ArrayList<>();
        String[] colors = {"green", "red", "blue"};
        for(int i = 0; i < 3; i++){
            int ran = randomNumGenerator(0,3);
            if(ran == 0){
                symbols.add(new Triangle(colors[i]));
            }
            else if(ran == 1){
                symbols.add(new Rectangle(colors[i]));
            }
            else if(ran == 2){
                symbols.add(new Circle(colors[i]));
            }
        }
    }
    
    /**
     * Creates started wheels that will use the created symbols.
     */
    private void prepareWheels(){//Borrar y revisar qué pasa, porque ahora todo debe iniciar en 0
        wheels.add(new Wheel());
        wheels.add(new Wheel());
        wheels.add(new Wheel());

        for(Wheel w: wheels){
            w.randomizeSymbol();
        }

    }
    
    /**
     * Makes visible the machine, its wheels and symbols.
     */
    public void makeVisible(){
        if(!isVisual){
            isVisual = true;
        }
        for(Rectangle r: rectangleBodyParts){
            if(!r.isVisible()){
                r.makeVisible();    
            }
        }
        lever.makeVisible();
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
        lever.makeInvisible();
    }
    
    
    /**
     * It returns all the symbol's colors.
     * @return Symbol's colors in order starting by one.
     */
    public String[] symbols(){
        String[] names = new String[symbols.size()];
        for (int i = 0; i < symbols.size(); i++){
            names[i] = symbols.get(i).getColor();
        }
        return names;
    }

    /**
     * Add a new wheel in a specific position.
     * @param pos is the position of the new wheel.
     */
    public void addWheel(int pos){
        if(wheels.size()!= 50){
            pos = Math.min(Math.max(0, pos-1), wheels.size());

            wheels.add(pos, new Wheel());

            makeInvisible();
            wheels.get(pos).randomizeSymbol();
            
            // If wheels are 13 or 26 or 39, slotMachine will be higher.  
            if(wheels.size() <14 ){
                rectangleBodyParts[0].changeSize(200, rectangleBodyParts[0].getWidth()+60);
            }
            else{
                rectangleBodyParts[0].changeSize(((wheels.size()/13)+1)*150, rectangleBodyParts[0].getWidth());
            }
            rectangleBodyParts[1].changeSize(25, rectangleBodyParts[0].getWidth()+50);
            
            changeBodyPartsPosition();
            ok = true;
            if(isVisual){
                makeVisible();  
            }   
        }else{
            JOptionPane.showMessageDialog(null, "Accion Invalida, se ha alcanzado el máximo número de ruedas.");
            ok = false;
        }
    }
    
    /**
     * Changes bodyParts position adapting all the body parts to the slotMachine window
     */
    private void changeBodyPartsPosition(){
        lever.changeBodyPartsPosition(rectangleBodyParts[0].getWidth(), rectangleBodyParts[0].getHeight());
        
        rectangleBodyParts[1].moveHorizontal(-rectangleBodyParts[1].getPosition()[0]+rectangleBodyParts[0].getPosition()[0]-25);
        rectangleBodyParts[1].moveVertical(-rectangleBodyParts[1].getPosition()[1] +rectangleBodyParts[0].getPosition()[1]+rectangleBodyParts[0].getHeight());
    }
    
    /**
     * Deletes a specific wheel
     * @param pos is the position of thw wheel that we wanna delete
     */
    
    public void delWheel(int pos){ 
        if(wheels.size()>0){
            pos = Math.min(Math.max(0, pos-1), wheels.size()-1);
            makeInvisible();
            wheels.remove(pos);
            isJackpot();

            
            // If wheels are 13 or 26 or 39, slotMachine will be lower.  
            if(wheels.size() <13 ){
                rectangleBodyParts[0].changeSize(200, rectangleBodyParts[0].getWidth()-60);
            }
            else{
                rectangleBodyParts[0].changeSize(((wheels.size()/13)+1)*150, rectangleBodyParts[0].getWidth());
            }
            
            rectangleBodyParts[1].changeSize(25, rectangleBodyParts[0].getWidth()+50);
            
            changeBodyPartsPosition();
            ok = true;
            if(isVisual){
                makeVisible();  
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Accion Invalida, no puedes tener menos de 3 ruedas."); //Cambiar esto, si se pueden tener menos de 3, pero no negativas.
        
            ok = false;
        }
    }
    
    /**
     * Moves all the wheels to its next symbol.
     */
    public void spin(){ //Sean 0 simbolos o 0 ruedas
        lever.animation();
        for(Wheel w: wheels){
            w.spin();
            if(isVisual){
                w.makeVisible();
                Canvas.getCanvas().wait(150);
            }
        }
        isJackpot();
    }
    
    /**
     * Moves a specific wheel to its next symbol.
     */
    public void spin(int wheel){ //Sean 0 simbolos o 0 ruedas
        
        int a= Math.max(0, wheel-1);
        int b = Math.min(a, wheels.size()-1);
        lever.animation();
        wheels.get(b).spin();
           
    
        if(isVisual){
            wheels.get(b).makeVisible();
            isJackpot();
        }
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
        //Looks if symbol exists
        if(!colorExists(symbol)){
            ok = false;
            return;
        }
        int idxSymbol = 0;
        for(Figure f: symbols){
            if(symbol.equals(f.getColor())){
                idxSymbol = symbols.indexOf(f);
                break;
            }
        }
        lever.animation();
        int idxWheel = Math.min(Math.max(0, wheel-1), wheels.size()-1);
        wheels.get(idxWheel).setCurrentSymbol(idxSymbol);
        ok = true;
        if(isVisual){
            wheels.get(idxWheel).makeVisible();
            isJackpot();
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
    public boolean isJackpot(){
        if(distinctSymbols()==1 && symbols().length > 1){
            if(!rectangleBodyParts[0].getColor().equals("yellow")){
                rectangleBodyParts[0].changeColor("yellow");
                if(isVisual){ 
                    makeVisible();
                    JOptionPane.showMessageDialog(null, "Ganaste");  
                    winningAnimation();
                }       
            }
            return true;
        }
        else{
            if(rectangleBodyParts[0].getColor().equals("yellow")){
                rectangleBodyParts[0].changeColor("gray");  
                if(isVisual){
                    makeVisible();
                }
            }
        }
        return false;
    } 
    
    /**
     * Adds a symbol in a specific position, this symbol is also added to all the wheels
     */
    public void addSymbol(int pos, String color){
        if(!colorExists(color)){
            ok = false;
            return;
        }
        for(Figure f: symbols){
            if(f.getColor().equals(color)){
                return;
            }
        }
        pos = Math.min(Math.max(0, pos-1), symbols.size());
        int ran = randomNumGenerator(0,3);
        if(ran == 0){
            symbols.add(pos, new Triangle(color));
        }
        else if(ran == 1){
            symbols.add(pos, new Rectangle(color));
        }
        else if(ran == 2){
            symbols.add(pos,new Circle(color));
        }
        
        for(Wheel w: wheels){
            w.addSymbol(pos+1, symbols.get(pos));
        }            
        ok = true;
    }
    /**
     * Deletes a specific symbol
     */
    public void delSymbol(String symbolColor){
        if(symbols.size()>0){
            for(int i = 0; i < symbols.size(); i++){
                if(symbols.get(i).getColor().equals(symbolColor)){
                    symbols.remove(i);
                }
            }
            ok = true;
        }else{
            JOptionPane.showMessageDialog(null, "Accion Invalida");
            ok = false;
        }
    }

    /**
     * The program ends with a message and make invisible all wheels and symbols.
     */
    public void exit(){
        makeInvisible();
        JOptionPane.showMessageDialog(null, "Adios");
        System.exit(0);
    }

    
    /**
     * check if the last action can be performed
     * @return true if the last action can be performed
     */
    public boolean ok(){
        return ok;
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
    
    /**
     * Checks if a symbol exists
     * @param color is the symbol's color
     * @return if that symbols exists
     */
    private boolean colorExists(String color){
        for(String c: Canvas.colors){
            if(color.equals(c)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * swap the positions of two wheels given their positions
     * @param wheel1 Position in ArrayList wheels of wheel number 1
     * @param wheel2 Position in Arraylist wheels of wheel number 2
     */
    public void swap(int wheel1, int wheel2){ //Mirar que pasa si hay 0 wheels
        int b = Math.max(wheel1-1, 0);
        int a = Math.min(b, wheels.size()-1);
        int d = Math.max(wheel2-1, 0);
        int c = Math.min(d, wheels.size()-1);
        
        Wheel wheelOne = wheels.get(a);
        Wheel wheelTwo = wheels.get(c);
        if(wheelOne.getIsLocked() == false && wheelTwo.getIsLocked() == false){
            wheels.set(a, wheelTwo);
            wheels.set(c, wheelOne);
            ok = true;
        }
        else{
            ok = false;
        }
        if(isVisual){
            wheelOne.makeVisible(); 
            wheelTwo.makeVisible();
        }
    }
    
    /**
     * lock a wheel, if is negative, get position 0, if is larger than the wheel size, it adopts that size
     * @param wheel in wheels( position )
     */
    public void lock(int wheel){ //Mirar que pasa si hay 0 wheels
        int a = Math.max(wheel-1, 0);
        int b = Math.min(a, wheels.size()-1);
        Wheel wheelact = wheels.get(b);
        if(wheelact.getIsLocked() == false){
            wheelact.setIsLocked(true);
        }
    }
    
    /**
     * unlock a wheel, if is negative, get position 0, if is larger than the wheel size, it adopts that size
     * @param wheel in wheels ( position )
     */
    public void unlock(int wheel){ //Mirar que pasa si hay 0 wheels
        int a = Math.max(wheel-1, 0);
        int b = Math.min(a, wheels.size()-1);
        Wheel wheelact = wheels.get(b);
        if(wheelact.getIsLocked() == true){
            wheelact.setIsLocked(false);
        }        
    }
    
    /**
     * Moves a specific wheel specific steps. Steps can be negative.
     * @param wheel is the wheel number.
     * @param steps are the steps it will move.
     */
    public void spin(int wheel, int steps){ //Mirar que pasa si hay 0 wheels y 0 simbolos
        int a = Math.max(wheel-1, 0);
        int b = Math.min(a, wheels.size()-1);
        lever.animation();
        wheels.get(b).spin(steps);
        isJackpot();
    }
    
    /**
     * Change the symbol's color that are given
     * @param setSymbols are the symbols that are given
     */
    public void spin(String[] setSymbols){
        if(setSymbols.length == wheels.size()){
            for(int i = 0; i< wheels.size();i++){
                if(colorExists(setSymbols[i])){
                    while(wheels.get(i).colorCurrentSymbol() != setSymbols[i]){
                        wheels.get(i).spin();
                    }    
                }
            }
            if(isVisual){
                makeVisible(); 
                isJackpot();
            }
            ok = true;
        }
        else{
            ok = false;
        }
    }
    
    /**
     * If you win you will see this animation, where all the symbols changes.
     */
    private void winningAnimation(){
        for(int i = 0; i < 2*symbols.size(); i++){
            for(Wheel w: wheels){
                w.spin();
                w.makeVisible();
            }
            Canvas.getCanvas().wait(300);
        }
    }
    /**
     * Gives all the current figures.
     * @return all current figures.
     */
    public static ArrayList<Figure> getSymbols(){
        return symbols;
    }
}
