package mainSim;

import generellSim.EventQueue;
import snabbköp.SnabbköpSim;
import snabbköp.SnabbköpTillstånd;
import snabbköp.händelser.Starthändelse;
import snabbköp.händelser.Stophändelse;

/**
 * Huvudklassen för att starta och köra simuleringen av ett snabbköp.
 *
 * @author Botan Güzel, Sergij Wennström, Ludvig Lidén
 */
public class Main {
    /**
     * Startpunkten för programmet. Den här metoden skapar och initialiserar alla nödvändiga
     * objekt för att köra snabbköpssimuleringen och startar sedan själva simuleringen.
     * 
     * @param args Argumentet som i nuläget inte används. 
     */
    public static void main(String[] args) {
        // Skapa händelsekö
        EventQueue eQ = new EventQueue();
        
        // Starta simuleringstillstånd bestämda värden
        SnabbköpTillstånd tillstånd = new SnabbköpTillstånd(2, 7, 3, 13, 0.35, 0.6, 0.6, 0.9, 8);
        
        // Skapa simulatorinstansen med händelsekö och tillstånd
        SnabbköpSim snabbköpSim = new SnabbköpSim(eQ, tillstånd);
        
        // Lägg till start- och stopphändelser till händelsekön
        eQ.addEvent(new Starthändelse(tillstånd, eQ, 0.0));
        eQ.addEvent(new Stophändelse(tillstånd, eQ, 999.0));
        
        // Starta simuleringen
        snabbköpSim.runEvents();
    }
}
