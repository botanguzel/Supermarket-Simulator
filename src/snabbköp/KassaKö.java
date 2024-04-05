package snabbköp;

import java.util.ArrayList;
import java.util.List;

/**
 * Kassakön för snabbköpet somo fungerar som en fifo.
 * Klassen kunder (Kund) som väntar på att betala,
 * och tillåter att lägga till och ta bort kunder från kön.
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class KassaKö {
    private List<Kund> kö = new ArrayList<>();

    /**
     * Skapar en tom kassakö.
     */
    public KassaKö() {
    }

    /**
     * Lägger till en kund i slutet av kön.
     * 
     * @param kund Kunden som ska läggas till i kön.
     */
    public void läggTillIKö(Kund kund) {
        kö.add(kund);
    }

    /**
     * Tar bort och returnerar nästa kund från kön.
     * Om kön är tom, returneras {@code null}.
     * 
     * @return Nästa kund i kön, eller {@code null} om kön är tom.
     */
    public Kund taNästaFrånKö() {
        return kö.isEmpty() ? null : kö.remove(0);
    }

    /**
     * Kontrollerar om kön är tom.
     * 
     * @return {@code true} om kön är tom, annars {@code false}.
     */
    public boolean isEmpty() {
        return kö.isEmpty();
    }

    /**
     * Skapar ett tillfälligt ArrayList för att spara kundID.
     * <br>
     * Används för att visa kundID i vyn, istället för kund objektet
     * @return tempKö
     */
    public ArrayList<Integer> SkapaTillfälligtKö() {
        ArrayList<Integer> tempKö = new ArrayList<Integer>();

        for (Kund kund : this.kö) {
            tempKö.add(((Kund) kund).getKundID());
        }
        return tempKö;
    }
    
    public int köStorlek() {
        return this.kö.size();
    }
    
}
