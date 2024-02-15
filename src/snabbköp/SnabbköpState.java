package snabbköp;

import generellSim.Event;
import generellSim.SimState;
import snabbköp.Time.TimeCalculations;
import snabbköp.kunder.KundHändelse;

public class SnabbköpState extends SimState{
	
	final TimeCalculations tC;
	private int antalKunderSomHandlat, antalKunderSomKöat,
		kassaKöLängd, summaKöTid, summaTidLedigaKassor,
		antalMissadeKunder, maxKassor, antalKunderIButik, 
		antalLedigaKassor, maxKunder, totalAntalKunder, kundID;
	
	private KassaKöFIFO kassaKöFIFO;
	
	private boolean snabbköpÖppet;
	private double lambda, ankomstTid, plockTid, betalningsTid, kMin, kMax, pMin, pMax;
	private long seed;
	private Event currentEvent;
	
	public SnabbköpState(int maxKunder, int maxKassor, int antalLedigaKassor, double lambda,
			long seed, double kMin, double kMax, double pMin, double pMax) {
		this.maxKunder = maxKunder;
		this.maxKassor = maxKassor;
		this.antalLedigaKassor = antalLedigaKassor;
		this.lambda = lambda;
		this.seed = seed;
		this.kMin = kMin;
		this.kMax = kMax;
		this.pMin = pMin;
		this.pMax = pMax;
		this.tC = new TimeCalculations(lambda, seed, kMin, kMax, pMin, pMax);
		this.ankomstTid = tC.calculateAnkomst(this.getTime());
		this.plockTid = tC.calculatePlock(this.getTime());
		this.betalningsTid = tC.calculateBetalnings(this.getTime());
	}
	
	public int getAntalKunderSomHandlat() { return antalKunderSomHandlat; }
	public int getAntalKunderSomKöat() { return this.antalKunderSomKöat; }
	public int getKassaKöLängd() { return this.kassaKöLängd; }
	public int getMaxKassor() { return this.maxKassor; }
	public int getAntalKunderIButik() { return this.antalKunderIButik; }
	public int getSummaKöTid() { return this.summaKöTid; }
	public int getSummaTidLedigaKassor() { return this.summaTidLedigaKassor; }
	public int getAntalMissadeKunder() { return this.antalMissadeKunder; }
	public int getAntalLedigaKassor() { return this.antalLedigaKassor; }
	public int getMaxKunder() { return this.maxKunder; }
	public int getTotalAntalKunder() { return this.totalAntalKunder; }
	public int getKundID() { return this.kundID; }
	public double getLambda() { return this.lambda; }
	public double getAnkomstTid() { return this.ankomstTid; }
	public double getPlockTid() { return this.plockTid; }
	public double getBetalningsTid() { return this.betalningsTid; }
	public double getKMin() { return this.kMin; }
	public double getKMax() { return this.kMax; }
	public double getPMin() { return this.pMin; }
	public double getPMax() { return this.pMax; }
	public long getSeed() { return seed; }
	public boolean isSnabbköpÖppet() { return snabbköpÖppet; }
	public KassaKöFIFO getKassaKöFIFO() { return kassaKöFIFO; }
	public Event getCurrentEvent() { return this.currentEvent; }
	public void setKundID(int i) { this.kundID = i; }

	public void setSnabbköpÖppet(boolean a) { this.snabbköpÖppet = a; }
	public void setCurrentEvent(Event e) {
		this.currentEvent = e;
		if (e instanceof KundHändelse) { this.setKundID(((KundHändelse) e).getKund().getKundID()); };
	}
	public void setKassaKöFIFO(KassaKöFIFO k) { this.kassaKöFIFO = k; }
	
	public void läggMissadKund() {this.antalMissadeKunder += 1; }
	public void minskaAntalKunderIButik() { this.antalKunderIButik -= 1; }
	public void ökaAntalLedigaKassor() { this.antalLedigaKassor += 1; }
	public void ökaAntalKunderSomHandlat() { this.antalKunderSomHandlat += 1; }
	public void minskaAntalLedigaKassor() { this.antalLedigaKassor -= 1;	}
	public void ökaSummaKöTid() { this.summaKöTid += 1; }
	public void ökaAntalKunderIButik() { this.antalKunderIButik += 1; }
	public void ökaTotalAntalKunder() { this.totalAntalKunder += 1; }		
	}
	

