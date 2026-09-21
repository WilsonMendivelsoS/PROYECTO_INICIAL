import java.util.ArrayList;
/**
 * Represents a slotMachineContest
 * 
 * @author David Garzon, Wilson Mendivelso
 * @version 3
 */
public class SlotMachineContest
{
    public static SlotMachine slotMachine;

    /**
     * Constructor for objects of class SlotMachineContest
     */
    public SlotMachineContest()
    {
    }
    
    /**
     * Solves the problem, returning all the steps 
     */
    public static int[][] solve(int n){
        if(n < 3 || n > 50){
            return new int[0][0];
        }
        if(slotMachine==null){
            slotMachine = new SlotMachine(n);
        }
        
        ArrayList<int[]> movimientos = new ArrayList<>();
 
        //Fase 1:
        for(int i = 2; i <= n; i++){
            int bestK = slotMachine.distinctSymbols();
            int bestStep = 0; 
            
            
            if(bestK < n){
                for(int step = 1; step <= n - 1; step++){ 
                    slotMachine.spin(i, 1);
                    movimientos.add(new int[]{i, 1});
                    int kActual = slotMachine.distinctSymbols();
 
                    if(kActual > bestK){
                        bestK = kActual;
                        bestStep = step;
                    }
                }
                int back = bestStep - (n - 1);
                if(back != 0){
                    slotMachine.spin(i, back);
                    movimientos.add(new int[]{i, back});
                }
            }
        }
        
        //fase 2:
        int[] distancia = new int[n + 1]; 
        boolean[] yaSabemos = new boolean[n + 1];
        yaSabemos[1] = true;
         
        int distanciaRueda1 = 0;
 
        for(int buscado = 1; buscado <= n - 1; buscado++){
 
            slotMachine.spin(1, 1);
            movimientos.add(new int[]{1, 1});
            distanciaRueda1 = buscado;
            boolean encontrada = false;
 
            for(int rueda = 2; rueda <= n && !encontrada; rueda++){
                if(yaSabemos[rueda]==false){
                    slotMachine.spin(rueda, -1);
                    movimientos.add(new int[]{rueda, -1});
                    int kPrueba = slotMachine.distinctSymbols();
                    slotMachine.spin(rueda, 1);
                    movimientos.add(new int[]{rueda, 1});
 
                    if(kPrueba >= n - 1){
                        distancia[rueda] = buscado;
                        yaSabemos[rueda] = true;
                        encontrada = true;
                    }
                }
            }
        }
        distancia[1] = distanciaRueda1;
 
        //fase 3:
        for(int rueda = 1; rueda <= n; rueda++){
            if(distancia[rueda] != 0){
                slotMachine.spin(rueda, -distancia[rueda]);
                movimientos.add(new int[]{rueda, -distancia[rueda]});
            }
        }
        int[][] solution = new int[movimientos.size()][2];
        for(int i = 0; i < movimientos.size(); i++){
            solution[i] = movimientos.get(i);
        }
                
        return solution;
    }
 
    /**
     * Simulates the solution, showing the machine.
     * @param n is the number of wheels and symbols.
     */
    public static void simulate(int n){
        slotMachine = new SlotMachine(n);
        slotMachine.makeVisible();
        int[][] a = solve(n);
        
    }
        
}
