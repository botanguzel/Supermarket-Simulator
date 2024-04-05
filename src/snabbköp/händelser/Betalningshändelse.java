package snabbköp.händelser;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.Kund;
import snabbköp.SnabbköpTillstånd;

/**
 * Hanterar betalningsprocessen för en kund i snabbköpssimuleringen.
 * Denna händelse triggar när en kund är redo att betala för sina varor.
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class Betalningshändelse extends Event implements KundHändelse{
    private SnabbköpTillstånd tillstånd;
    private Kund kund;

    /**
     * Konstruerar en betalningshändelse med given snabbköpstillstånd, händelsekö, tidpunkt för händelsen, och kunden som ska betala.
     * 
     * @param tillstånd Det aktuella tillståndet i snabbköpet.
     * @param eQ Händelsekön där händelsen hanteras.
     * @param timeOfEvent Tidpunkten då händelsen inträffar.
     * @param kund Kunden som utför betalningen.
     */
    public Betalningshändelse(SnabbköpTillstånd tillstånd, EventQueue eQ, double timeOfEvent, Kund kund) {
        super(tillstånd, eQ, timeOfEvent);
        this.tillstånd = tillstånd;
        this.kund = kund;
    }

    public int getKundID() {
        return this.kund.getKundID();
    }

    /**
     * Exekvierar betalningsprocessen för kunden och hanterar köhantering vid kassan.
     */
    public void executeEvent() {
    	this.tillstånd.ökaTotaltAntalBetaldaKunder();
        this.tillstånd.minskaAntalKunderISnabbköpet();
        if (!this.tillstånd.getKassaKö().isEmpty()) { //Kollar om kassakön är tom
            Kund nästaKund = this.tillstånd.getKassaKö().taNästaFrånKö(); //Ta nästa kund från kassan
            double betalningsTid = this.tillstånd.getNästaBetalningsTid(this.getTimeOfEvent());
            this.eQ.addEvent(new Betalningshändelse(this.tillstånd, this.eQ, betalningsTid, nästaKund));
        } else { //Om det inte är någon kund i kö, öka antalet lediga kassor.
            this.tillstånd.ökaAntalLedigaKassor(); 
        }
    }

    /**
     * Returnerar namnet på händelsen.
     * 
     * @return En sträng som representerar händelsens namn, i detta fall "Betalning".
     */
    @Override
    public String getName() {
        return "Betalning";
    }
}
