
package snabbköp;

import generellSim.SimState;

/**
 * Representerar tillståndet för simulering av ett snabbköp och utökar SimState-klassen.
 * Denna klass sparar allt information om snabbköpstillstånd, inklusive händelse tider, kassa köer,
 * tillgänglighet för kassor och spårning av statistisk data.
 * 
 * @author Botan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class SnabbköpTillstånd extends SimState {
    private TimeCalculations tidBeräkningar;
    private int antalLedigaKassor;
    private double totalTidLedigaKassor;
    private double totalTidIKassaKö;
    private int totaltAntalKunderSomKöat;
    private int totaltAntalKunderSomFörsöktHandlat;
    private int antalKunderISnabbköpet;
    private int totaltAntalBetaldaKunder;
    private int totaltAntalMissadeKunder;
    private boolean ärSnabbköpÖppet;
    private KassaKö kassaKö;
    private int maxAntalKassor;
    private int maxAntalKunder;
    private double ankomstRate;
    private long frö;
    private double minKassaTid;
    private double maxKassaTid;
    private double minPlockTid;
    private double maxPlockTid;
    private double tidenSnabbköpetStänger;
    private KundFabrik kundFabrik;

    /**
     * Skapar ett nytt SnabbköpTillstånd-objekt med angivna parametrar och kund generator.
     *
     * @param antalKassor             Maximalt antal kassor i snabbköpet.
     * @param maxAntalKunder          Maximalt antal kunder som snabbköpet kan hantera samtidigt.
     * @param ankomstRate             Genomsnittlig ankomsthastighet av kunder till snabbköpet per enhet tid.
     * @param frö                     Frö för slumpgenerator för att generera händelsetider för kunder.
     * @param minKassaTid             Minsta tid det tar för en kassör att hantera en kund.
     * @param maxKassaTid             Högsta tid det tar för en kassör att hantera en kund.
     * @param minPlockTid             Minsta tid det tar för en kund att plocka varor.
     * @param maxPlockTid             Högsta tid det tar för en kund att plocka varor.
     * @param tidenSnabbköpetStänger  Tidpunkt då snabbköpet stänger.
     */
    public SnabbköpTillstånd(int antalKassor, int maxAntalKunder, double ankomstRate, long frö, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid, double tidenSnabbköpetStänger) {
        this.antalLedigaKassor = antalKassor;
        this.maxAntalKunder = maxAntalKunder;
        this.ankomstRate = ankomstRate;
        this.frö = frö;
        this.minKassaTid = minKassaTid;
        this.maxKassaTid = maxKassaTid;
        this.minPlockTid = minPlockTid;
        this.maxPlockTid = maxPlockTid;
        this.tidBeräkningar = new TimeCalculations(ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid);
        this.maxAntalKassor = antalKassor;
        this.kassaKö = new KassaKö();
        this.tidenSnabbköpetStänger = tidenSnabbköpetStänger;
        this.kundFabrik = new KundFabrik();
    }

    /**
     * Skapar en ny kund med hjälp av KundFabrik.
     * 
     * @return En ny Kund-instans med unikt ID.
     */
    public Kund skapaKund() { return this.kundFabrik.skapaNyKund(); }

    /**
     * Hämtar den aktuella kassakön.
     * 
     * @return Aktuell KassaKö-instans.
     */
    public KassaKö getKassaKö() { return this.kassaKö; }

    /**
     * Hämtar det totala antalet kunder som inte kunde handla på grund av fullt snabbköp.
     * 
     * @return Antalet missade kunder.
     */
    public int getTotaltAntalMissadeKunder() { return this.totaltAntalMissadeKunder; }

    /**
     * Hämtar det maximala antalet kassor som kan vara öppna samtidigt.
     * 
     * @return Maximalt antal kassor.
     */
    public int getMaxAntalKassor() { return this.maxAntalKassor; }

    /**
     * Hämtar antalet för tillfället lediga kassor.
     * 
     * @return Antalet lediga kassor.
     */
    public int getAntalLedigaKassor() { return this.antalLedigaKassor; }

    /**
     * Ökar antalet lediga kassor med ett.
     */
    public void ökaAntalLedigaKassor() { ++this.antalLedigaKassor; }

    /**
     * Minskar antalet lediga kassor med ett.
     */
    public void minskaAntalLedigaKassor() { --this.antalLedigaKassor; }

    /**
     * Hämtar den totala tiden kassor varit lediga.
     * 
     * @return Total tid kassor varit lediga.
     */
    public double getTotalTidLedigaKassor() { return this.totalTidLedigaKassor; }

    /**
     * Sätter den totala tiden kassor varit lediga.
     * 
     * @param value Den totala tiden kassor har varit lediga.
     */
    public void setTotalTidLedigaKassor(double value) { this.totalTidLedigaKassor = value; }

    /**
     * Hämtar genomsnittlig ankomsthastighet av kunder till snabbköpet per enhet tid.
     * 
     * @return AnkomstRate som en double.
     */
    public double getAnkomstRate() { return this.ankomstRate; }

    /**
     * Hämtar den minimala tiden det tar för en kassör att hantera en kund.
     * 
     * @return Minsta kassatid.
     */
    public double getMinKassaTid() { return this.minKassaTid; }

    /**
     * Hämtar den maximala tiden det tar för en kassör att hantera en kund.
     * 
     * @return Högsta kassatid.
     */
    public double getMaxKassaTid() { return this.maxKassaTid; }

    /**
     * Hämtar den minimala tiden det tar för en kund att plocka varor.
     * 
     * @return Minsta plocktid.
     */
    public double getMinPlockTid() { return this.minPlockTid; }

    /**
     * Hämtar den maximala tiden det tar för en kund att plocka varor.
     * 
     * @return Högsta plocktid.
     */
    public double getMaxPlockTid() { return this.maxPlockTid; }

    /**
     * Hämtar fröet som används för slumpmässig generering av händelsetider för kunder.
     * 
     * @return Fröet som en long.
     */
    public long getFrö() { return this.frö; }

    /**
     * Hämtar det totala antalet betalda kunder.
     * @return totala antalet betalda kunder.
     */
    public int getTotaltAntalBetaldaKunder() { return this.totaltAntalBetaldaKunder; }

    /**
     * Ökar antalet betalda kunder med ett.
     */
    public void ökaTotaltAntalBetaldaKunder() { ++this.totaltAntalBetaldaKunder; }

    /**
     * Hämtar det totala antalet kunder som försökt handla.
     * @return totala antalet kunder som försökt handla.
     */
    public int getTotaltAntalKunderSomFörsöktHandlat() { return this.totaltAntalKunderSomFörsöktHandlat; }

    /**
     * Ökar antalet kunder som försökt handla med ett.
     * @return det nya totala antalet kunder som försökt handla efter ökningen.
     */
    public int ökaTotaltAntalKunderSomFörsöktHandlat() { return this.totaltAntalKunderSomFörsöktHandlat++; }

    /**
     * Hämtar det aktuella antalet kunder i snabbköpet.
     * @return antalet kunder i snabbköpet.
     */
    public int getAntalKunderISnabbköpet() { return this.antalKunderISnabbköpet; }

    /**
     * Ökar antalet kunder i snabbköpet med ett.
     */
    public void ökaAntalKunderISnabbköpet() { ++this.antalKunderISnabbköpet; }

    /**
     * Minskar antalet kunder i snabbköpet med ett.
     */
    public void minskaAntalKunderISnabbköpet() { --this.antalKunderISnabbköpet; }

    /**
     * Hämtar det maximala antalet kunder som snabbköpet kan hantera samtidigt.
     * @return maximala antalet kunder.
     */
    public int getMaxAntalKunder() { return this.maxAntalKunder; }

    /**
     * Ökar antalet missade kunder med ett.
     */
    public void läggTillMissadKund() { ++this.totaltAntalMissadeKunder; }

    /**
     * Hämtar det totala antalet kunder som köat.
     * @return totala antalet kunder som köat.
     */
    public int getTotaltAntalKunderSomKöat() { return this.totaltAntalKunderSomKöat; }

    /**
     * Ökar antalet kunder som har köat med ett.
     */
    public void ökaTotaltAntalKunderSomKöat() { this.totaltAntalKunderSomKöat++; }

    /**
     * Hämtar den totala tiden kunder har tillbringat i kassakön.
     * @return totala tiden i kassakön.
     */
    public double getTotalTidIKassaKö() { return this.totalTidIKassaKö; }

    /**
     * Sätter den totala tiden kunder har tillbringat i kassakön.
     * @param value den nya totala tiden i kassakön.
     */
    public void setTotalTidIKassaKö(double value) { this.totalTidIKassaKö = value;}

    /**
     * Beräknar genomsnittstiden en kassa varit ledig.
     * @return genomsnittlig ledig kassatid.
     */
    public double getGenomsnittligLedigKassatid() {
        return (double)this.totalTidLedigaKassor / (double)this.maxAntalKassor;
    }

    /**
     * Beräknar genomsnittstiden kunder har tillbringat i kö.
     * @return genomsnittlig kötid.
     */
    public double getGenomsnittligKöTid() {
        return this.totaltAntalKunderSomKöat > 0 ? (double)this.totalTidIKassaKö / (double)this.totaltAntalKunderSomKöat : 0.0;
    }
    /**
     * Beräknar nästa ankomsttid för en kund.
     * @param nuvarandeTid den nuvarande tiden i simuleringen.
     * @return tiden för nästa kunds ankomst.
     */
    public double getNästaAnkomstTid(double nuvarandeTid) { return this.tidBeräkningar.calculateAnkomst(nuvarandeTid); }

    /**
     * Beräknar nästa plocktid för en kund.
     * @param nuvarandeTid den nuvarande tiden i simuleringen.
     * @return tiden det tar för nästa kund att plocka sina varor.
     */
    public double getNästaPlockTid(double nuvarandeTid) { return this.tidBeräkningar.calculatePlock(nuvarandeTid); }

    /**
     * Beräknar nästa betalningstid för en kund.
     * @param nuvarandeTid den nuvarande tiden i simuleringen.
     * @return tiden det tar för nästa kund att betala.
     */
    public double getNästaBetalningsTid(double nuvarandeTid) { return this.tidBeräkningar.calculateBetalnings(nuvarandeTid); }

    /**
     * Kontrollerar om simuleringen fortfarande pågår.
     * @return true om simuleringen pågår, annars false.
     */
    public boolean simulationRunning() { return super.simulationRunning(); }

    /**
     * Startar simuleringen.
     */
    public void startSimulation() { super.startSimulation(); }

    /**
     * Stannar simuleringen.
     */
    public void stopSimulation() { super.stopSimulation(); }

    /**
     * Stänger snabbköpet och avslutar verksamheten.
     */
    public void stängSnabbköp() { this.ärSnabbköpÖppet = false; }

    /**
     * Öppnar snabbköpet och startar verksamheten.
     */
    public void öppnaSnabbköp() {
        this.ärSnabbköpÖppet = true;
        this.startSimulation();
        this.setChanged();
        this.notifyObservers("Snabbköpet är öppet");
    }

    /**
     * Kontrollerar om snabbköpet för närvarande är öppet.
     * @return true om snabbköpet är öppet, annars false.
     */
    public boolean ärSnabbköpÖppet() { return this.ärSnabbköpÖppet; }

    /**
     * Hämtar tiden då snabbköpet stänger.
     * @return tiden då snabbköpet stänger.
     */
    public double getTidentSnabbköpetStänger() { return this.tidenSnabbköpetStänger; }
}