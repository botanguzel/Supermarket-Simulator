package mainSim;
import generellSim.EventQueue;
import snabbköp.SnabbköpSim;
import snabbköp.SnabbköpTillstånd;
import snabbköp.händelser.Starthändelse;
import snabbköp.händelser.Stophändelse;
import randomNum.K;

import java.util.Random;

/**
 * Klassen letar efter optimalt antal kassor som behövs för att inte få några missade kunder
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class Optimize {
    /**
     * OptimizeMain returnerar det totala antalet missade kunder med givna parametrar.
     *
     * @param antalKassor Antalet kassor i snabbköpet.
     * @param maxAntalKunder Maximalt antal kunder som får vara i butiken.
     * @param ankomstRate Genomsnittlig ankomsthastighet för kunder.
     * @param frö Frö för slumpgeneratorn.
     * @param minKassaTid Minsta tid det tar att betjäna en kund vid en kassa.
     * @param maxKassaTid Största tid det tar att betjäna en kund vid en kassa.
     * @param minPlockTid Minsta tid det tar för en kund att plocka sina varor.
     * @param maxPlockTid Största tid det tar för en kund att plocka sina varor.
     * @param tidenSnabbköpetStänger Tiden när snabbköpet stänger.
     * @return Det totala antalet missade kunder under simuleringen.
     */
    public int OptimizeMain(int antalKassor, int maxAntalKunder, double ankomstRate, long frö, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid, double tidenSnabbköpetStänger) {
        EventQueue eQ = new EventQueue();
        SnabbköpTillstånd tillstånd = new SnabbköpTillstånd(antalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger);
        SnabbköpSim snabbköpSim = new SnabbköpSim(eQ, tillstånd, true);
        eQ.addEvent(new Starthändelse(tillstånd, eQ, 0.0));
        eQ.addEvent(new Stophändelse(tillstånd, eQ, 999.0));
        snabbköpSim.runEvents();
        return tillstånd.getTotaltAntalMissadeKunder();
    }

    /**
     * Simulerar en ökning av antalet kassor med en enda kassa och returnerar det optimala antalet kassor för att minimera antalet missade kunder.
     *
     * @param frö Frö för slumpgeneratorn.
     * @return Det optimala antalet kassor för att minimera antalet missade kunder.
     */
    public int metod2LowerOne(int maxAntalKunder, double ankomstRate, long frö, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid, double tidenSnabbköpetStänger){
        int antalKassor = maxAntalKunder;
        int maxMissadeKunder = OptimizeMain(antalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger); // Antalet missade kunder när man kör OptimizeMain med givna parametrar
        
        while(antalKassor >= 1) { //Antalet kassor kan vara minst 1, while-loop börjar från högsta antalet kassor och minskar med ett.
            int nyaAntalMissadeKunder = OptimizeMain(antalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger); // Kör OptimizeMain med nya antalet kassor
            if (maxMissadeKunder != nyaAntalMissadeKunder){ // När vi når till maxMissadeKunder avbryter programmet
                return antalKassor+1;
            }
            antalKassor--;
        }
        return antalKassor;
    }
    public int Metod2RaiseOne(int maxAntalKunder, double ankomstRate, long frö, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid, double tidenSnabbköpetStänger){
        int antalKassor = 1;
        while(antalKassor <= maxAntalKunder) { //Antalet kassor kan vara minst 1, while-loop börjar från lägsta antalet kassor och ökar med ett.
            int oldAntalMissadeKunder = OptimizeMain(antalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger); // Kör OptimizeMain med old antalet kassor
            antalKassor++;
            int nyaAntalMissadeKunder = OptimizeMain(antalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger); // Kör OptimizeMain med nya antalet kassor
            if (oldAntalMissadeKunder == nyaAntalMissadeKunder){ // När vi når till nyaMissadeKunder = oldMissadeKunder avbryter programmet
                return antalKassor-1;
            }
        }
        return 0;
    }

    public int binarySearchMetod(int maxAntalKunder, double ankomstRate, long frö, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid, double tidenSnabbköpetStänger) {
        int maxAntalKassor = 1;
        int minAntalKassor = maxAntalKunder;
        int result = -1;
        int oldMissedCustomers = OptimizeMain(minAntalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger);

        while (maxAntalKassor <= minAntalKassor) {
            int optimalAntalKassor = maxAntalKassor + (minAntalKassor - maxAntalKassor) / 2;
            int currentMissedCustomers = OptimizeMain(optimalAntalKassor, maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger);
            if (currentMissedCustomers <= 0 || (currentMissedCustomers == oldMissedCustomers)) {
                result = optimalAntalKassor;
                minAntalKassor = optimalAntalKassor - 1;
            } else {
                maxAntalKassor = optimalAntalKassor + 1;
            }
            oldMissedCustomers = currentMissedCustomers;
        }
        return result;
    }

    /**
     * Beräknar det optimala antalet kassor för att minimera antalet missade kunder genom att iterativt öka antalet kassor.
     * Fortsätt tills vi antalet kassor som behövs inte ökat för 100 iterationer i rad.
     *
     * @param frö Frö för slumpgeneratorn.
     * @return Det optimala antalet kassor för att minimera antalet missade kunder.
     */

    public int OptimizeSim(int maxAntalKunder, double ankomstRate, long frö, double minKassaTid, double maxKassaTid, double minPlockTid, double maxPlockTid, double tidenSnabbköpetStänger){
        int counter = 0;
        int antalKassor = 1; // minsta antalet kassor som behövs för att få 0 missade kunder (Från tidigare tester:167)
        int nyAntalKassor = -1;
        
        while(counter < 100){
            Random rand = new Random(frö);
            nyAntalKassor = Metod2RaiseOne(maxAntalKunder, ankomstRate, rand.nextLong(), minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger); //10
            System.out.println("before IFELSE"+nyAntalKassor);
            System.out.println(Math.max(antalKassor, nyAntalKassor) + " : "+ antalKassor);
            if(antalKassor != Math.max(antalKassor, nyAntalKassor)){ // Kollar om gamla antalet kassor lika stort eller störra än nyaAntaletKassor: öka counter
                counter = 0;
                System.out.println("IF"+nyAntalKassor);
            }
            else{
                System.out.println("ELSE"+nyAntalKassor);
                counter += 1;
            }
            antalKassor = Math.max(antalKassor, nyAntalKassor);
        }
        return nyAntalKassor;
    }



    public static void main(String[] args) {
        Optimize optimize = new Optimize();
        //int m1 = optimize.Metod2RaiseOne(K.M, K.L, K.SEED, K.LOW_PAYMENT_TIME, K.HIGH_PAYMENT_TIME, K.LOW_COLLECTION_TIME, K.HIGH_COLLECTION_TIME, K.END_TIME);
        //System.out.println("Metod2RaiseOne: " + m1 + " kassor behövs för att få minsta antal missade kunder");
        //maxAntalKunder, ankomstRate, frö, minKassaTid, maxKassaTid, minPlockTid, maxPlockTid, tidenSnabbköpetStänger
        //int metod2 = optimize.binarySearchMetod(K.M, K.L, K.SEED, K.LOW_PAYMENT_TIME, K.HIGH_PAYMENT_TIME, K.LOW_COLLECTION_TIME, K.HIGH_COLLECTION_TIME, K.END_TIME);
        //System.out.println("Metod2: " + metod2 + " kassor behövs för att få minsta antal missade kunder");
        //int OptimizeMain = optimize.OptimizeMain(metod2,K.M, K.L, K.SEED, K.LOW_PAYMENT_TIME, K.HIGH_PAYMENT_TIME, K.LOW_COLLECTION_TIME, K.HIGH_COLLECTION_TIME, K.END_TIME);
        ///System.out.println(String.format("OptimizeMain with Metod2 (%d) as an argument: %d missade kunder", metod2,OptimizeMain));
        int m3 = optimize.OptimizeSim(K.M, K.L, K.SEED, K.LOW_PAYMENT_TIME, K.HIGH_PAYMENT_TIME, K.LOW_COLLECTION_TIME, K.HIGH_COLLECTION_TIME, K.END_TIME);
        System.out.println("OptimizeSim: " + m3); // Optimised max antal kassor 164: för randomiserad frö i 100 steg
    }



}