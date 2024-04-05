package snabbköp.händelser;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.Kund;
import snabbköp.SnabbköpTillstånd;

/**
 * Ankomsthändelse i snabbköpssimuleringen. Denna händelse skapar en ny kund
 * och hanterar kundens ankomst till snabbköpet baserat på det aktuella tillståndet i snabbköpet.
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class Ankomsthändelse extends Event implements KundHändelse{
    private SnabbköpTillstånd tillstånd;
    private Kund kund;

    /**
     * Skapar en ankomsthändelse med specificerat tillstånd, händelsekö, tidpunkt för händelsen och kunden.
     * 
     * @param tillstånd     Det aktuella tillståndet i snabbköpet.
     * @param eQ            Händelsekön där denna och andra händelser hanteras.
     * @param timeOfEvent   Tiden då händelsen sker.
     * @param kund        Kunden som är involverad i händelsen.
     */
    public Ankomsthändelse(SnabbköpTillstånd tillstånd, EventQueue eQ, double timeOfEvent, Kund kund) {
        super(tillstånd, eQ, timeOfEvent);
        this.tillstånd = tillstånd;
        this.kund = kund;
    }

    /**
     * Hämta KundID 
     * 
     * @return Kund ID 
     */
    public int getKundID() {
        return this.kund.getKundID();
    }

    /**
     * Utför ankomsthändelsen. Skapa en plocktid för kunden och skapa även en ankomsthändelse för en ny kund om snabbköpet är öppet och inte
     * är fullt. Om snabbköpet är öppet men fullt lägg till missad kund. Om snabbköpet är stängt exekviera inte ankomsthändelsen. 
     */
    public void executeEvent() {    	
    	if (this.tillstånd.ärSnabbköpÖppet()) { //Kollar om snabbköpet är öppet
    		 //Skapa en ankomsttid för nästa kund
            double nästaAnkomstTid = this.tillstånd.getNästaAnkomstTid(this.getTimeOfEvent()); 
            this.eQ.addEvent(new Ankomsthändelse(this.tillstånd, this.eQ, nästaAnkomstTid, this.tillstånd.skapaKund()));
            if (this.tillstånd.getAntalKunderISnabbköpet() < this.tillstånd.getMaxAntalKunder()) { //Kollar om snabbköpet är fullt
        		this.tillstånd.ökaAntalKunderISnabbköpet();
                //Skapa en ny plocktid för kunden som har ankommit
                double plockTid = this.tillstånd.getNästaPlockTid(this.getTimeOfEvent()); //Skapa en ny plocktid
                this.eQ.addEvent(new Plockhändelse(this.tillstånd, this.eQ, plockTid, this.kund)); //Lägga till plocktid till eventet
	        } else {
	            this.tillstånd.läggTillMissadKund();
            }
        }
    }

    /**
     * Returnerar namnet på händelsen, i detta fall "Ankomst".
     * 
     * @return En sträng som representerar händelsens namn.
     */
    @Override
    public String getName() {
        return "Ankomst";
    }
}
