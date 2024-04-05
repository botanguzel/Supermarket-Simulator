package snabbköp;

import randomNum.ExponentialRandomStream;
import randomNum.UniformRandomStream;

/**
 * Klassen hanterar tidberäkningar för ankomst, plockning av varor och betalningstider
 * i snabbköpssimuleringen. Använder sig av exponentialfördelning för ankomsttider och
 * uniform fördelning för plock- och betalningstider. 
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class TimeCalculations {
    private ExponentialRandomStream ankomsttidERS; // Hanterar ankomsttider med exponentialfördelning
    private UniformRandomStream betalningstidURS; // Hanterar betalningstider med uniform fördelning
    private UniformRandomStream plocktidURS;      // Hanterar plocktider med uniform fördelning

    /**
     * Skapar en instans av TimeCalculations med specificerade parametrar för tidberäkningar.
     * 
     * @param lambda Parametern för hur ofta ankomsstiderna kommer utifrån exponentialfördelningen.
     * @param seed Startvärde för slumpmässigheten i tidberäkningar.
     * @param minKassaTid Minsta tid för betalning vid kassan.
     * @param maxKassaTid Maximal tid för betalning vid kassan.
     * @param minPlockTid Minsta tid för att plocka varor.
     * @param maxPlockTid Maximal tid för att plocka varor.
     */
    public TimeCalculations(double lambda, long seed, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid) {
        this.ankomsttidERS = new ExponentialRandomStream(lambda, seed);
        this.plocktidURS = new UniformRandomStream(minPlockTid, maxPlockTid, seed);
        this.betalningstidURS = new UniformRandomStream(minKassaTid, maxKassaTid, seed);
    }

    /**
     * Slumpar fram nästa ankomsttid baserat på nuvarande tid och exponentialfördelningen.
     * 
     * @param currTime Nuvarande tid i simuleringen.
     * @return Nästa ankomsttid.
     */
    public double calculateAnkomst(double currTime) {
        return this.ankomsttidERS.next() + currTime;
    }

    /**
     * Slumpar fram nästa plocktid baserat på nuvarande tid och uniform fördelning.
     * 
     * @param currTime Nuvarande tid i simuleringen.
     * @return Tid för att plocka varor.
     */
    public double calculatePlock(double currTime) {
        return this.plocktidURS.next() + currTime;
    }

    /**
     * Slumpar fram nästa betalningstid baserat på nuvarande tid och uniform fördelning.
     * 
     * @param currTime Nuvarande tid i simuleringen.
     * @return Betalningstid vid kassan.
     */
    public double calculateBetalnings(double currTime) {
        return this.betalningstidURS.next() + currTime;
    }
}
