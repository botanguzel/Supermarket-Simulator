package snabbköp;



/**
 * Klassen fungerar fför att skapa nya kunder till snabbköpet. 
 * 
 * @author Botan Güzel, Sergij Wennströmm, Ludvig Lidén
 *  
 */
public class KundFabrik {
    private int senasteKundID = -1;

    public Kund skapaNyKund() {
        senasteKundID++;
        return new Kund(senasteKundID);
    }
}
