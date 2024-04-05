package snabbköp;


/**
 * Representerar en kund i snabbköpssimuleringen. Varje kund har ett unikt ID som
 * tilldelas när kunden ankommer till snabbköpet.
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class Kund {
    private int kundID; // Kundens unika identifierare

    /**
     * Sätter ett ID till kund
     * @param id
     */
    public Kund(int id) {
        this.kundID = id;
    }

    /**
     * Returnerar kundens ID
     * @return kundID
     */
    public int getKundID() {
        return kundID;
    }
    
    
}
