package snabbköp.händelser;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.SnabbköpTillstånd;

/**
 * Representerar stopphändelsen i snabbköpssimuleringen och används när simuleringen ska avslutas. 
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class Stophändelse extends Event {
    private SnabbköpTillstånd tillstånd;

    /**
     * Skapar en ny stopphändelse.
     * 
     * @param tillstånd Det aktuella tillståndet i snabbköpet.
     * @param eQ Händelsekön där denna och andra händelser hanteras.
     * @param timeOfEvent Tiden då händelsen sker.
     */
    public Stophändelse(SnabbköpTillstånd tillstånd, EventQueue eQ, double timeOfEvent) {
        super(tillstånd, eQ, timeOfEvent);
        this.tillstånd = tillstånd;
    }

    /**
     * Utför stopphändelsen. 
     */
    @Override
    public void executeEvent() {
        this.tillstånd.stopSimulation();
    }

    /**
     * Returnerar namnet på händelsen, i detta fall "Stopp".
     * 
     * @return En sträng som representerar händelsens namn.
     */
    @Override
    public String getName() {
        return "Stopp";
    }
}
