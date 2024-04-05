package snabbköp.händelser;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.SnabbköpTillstånd;

/**
 * Representerar en händelse som indikerar stängning av snabbköpet i simuleringen.
 * Händelsen ansvarar över om snabbköpet ska accpetera att nya ankomsthändelser kan 
 * skapas eller ej. 
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class Stängningshändelse extends Event{
    private SnabbköpTillstånd tillstånd;

    /**
     * Skapar en ny stängningshändelse med givet tillstånd, händelsekö och tiden då
     * snabbköpet ska stängas.
     * 
     * @param tillstånd Det aktuella tillståndet i snabbköpet där händelsen inträffar.
     * @param eQ Händelsekön där denna och andra händelser hanteras.
     * @param timeOfEvent Tiden då snabbköpet ska stängas.
     */
    public Stängningshändelse(SnabbköpTillstånd tillstånd, EventQueue eQ, double timeOfEvent) {
        super(tillstånd, eQ, timeOfEvent);
        this.tillstånd = tillstånd;
    }

    /**
     * Stänger snabbköpet
    */
    @Override
    public void executeEvent() {
        this.tillstånd.stängSnabbköp();
    }
    @Override
    public String getName() {
        return "Stänger";
    }

	
}
