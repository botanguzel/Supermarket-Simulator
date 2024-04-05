package snabbköp.händelser;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.Kund;
import snabbköp.SnabbköpTillstånd;

/**
 * Hanterar öppningen av snabbköpet i simuleringen.
 * Denna händelse aktiveras vid starttiden för snabbköpet och initierar processen
 * att ta emot kunder.
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class Starthändelse extends Event {
    private SnabbköpTillstånd tillstånd;

    public Starthändelse(SnabbköpTillstånd tillstånd, EventQueue eQ, double timeOfEvent) {
        super(tillstånd, eQ, timeOfEvent);
        this.tillstånd = tillstånd;
    }
    @Override
    /**
     * Exekvierar/startar snabbköpet. En ny kund skapas och även en ny ankomsstid för denna kund. 
     * Likt de övriga händelseklasserna läggs starthändelsen i eventkön. 
     */
    public void executeEvent() {
        this.tillstånd.öppnaSnabbköp();
        Kund nyKund = this.tillstånd.skapaKund();

        double nästaAnkomstTid = this.tillstånd.getNästaAnkomstTid(this.getTimeOfEvent()); //Skapar en ny ankomsstid för kunden
        this.eQ.addEvent(new Ankomsthändelse(this.tillstånd, this.eQ, nästaAnkomstTid, nyKund));
        this.eQ.addEvent(new Stängningshändelse(this.tillstånd, this.eQ, this.tillstånd.getTidentSnabbköpetStänger()));
    }

    @Override
    /**
     * Returnerar namnet på händelsen, i detta fall "Start" (som dock inte används). 
     * 
     * @return En sträng som representerar händelsens namn.
     */
    public String getName() {
        return "Start";
    }
}