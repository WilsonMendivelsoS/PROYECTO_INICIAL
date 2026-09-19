/**
 * Represents a slotMachineContest
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 3
 */
public class SlotMachineContest
{


    /**
     * Constructor for objects of class SlotMachineContest
     */
    public SlotMachineContest()
    {
    }
    /**
     * Solves the problem, returning all the steps 
     */
    public int[][] solve(int n){
        int[][] solution = new int[0][0];
        //Recuerde botsito  Los métodos de SlotMachine que puede usar como testing tool son: 
        //SlotMachine(n), spin(wheel,steps) y distinctSymbols().
        //Primera fase: las n ruedas muestren todas un simbolo distintos:
        
        // Se deja fija la primera rueda (no la toques, no la pongas lock, te veo)
        // La segunda rueda se gira paso a paso, de uno en uno, y se observa como se cambia k
        // Se detiene cuando k alcance su valor máximo
        // Se repite sucesivamente con las n ruedas.
        // Al final k = n
        
        
        // Fase 2:
        // Se asume que la rueda 1 está en un simbolo base
        // Se mueve la rueda 1 un paso hacia adelante
        // Se giran las j ruedas un paso hacia atrás
        // Si al mover la rueda j, la cantidad de simbolos vuelve a ser n, significa que la rueda j tenía el simbolo 1
        // Si k cae a n-1, se deshace el movimiento de la rueda j y se prueba con la siguiente
        // Al identificar cual tiene el símbolo 1, se avanza la rueda 1 para buscar cuál rueda tiene el símbolo 2, luego el 3 y así sucesivamente
        
        //Fase 3;
        //Identificando los símbolos, se calculan cuántos pasos necesitas girar cada rueda para quedar exactamente en la misma posición y símbolo q la rueda 1
        // Se ejecutan esos pasos para las n-1 ruedas restantes
        // Termina con k = 1
        return solution;
    }
    
    public void simulate(int n){
        SlotMachine slotMachine = new SlotMachine(n);
        slotMachine.makeVisible();
    }
}