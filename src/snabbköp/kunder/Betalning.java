package snabbköp.kunder;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.Snabbköp;
import snabbköp.SnabbköpState;

public class Betalning extends KundHändelse{

	public Betalning(SnabbköpState state, EventQueue eQ, double tid, Kund kund) { super(state, eQ, tid, kund); }
	
	@Override
	public void createEvent() {
		if (state.getAntalLedigaKassor() >=0) {
			//System.out.println("Empty REgister Found");
			if (!state.getKassaKöFIFO().isEmpty()) {
				//System.out.println("People in LINE");
				state.getKassaKöFIFO().ordnaKö();
			} else {
				//System.out.println("NOONE IS IN LINE");
				state.minskaAntalLedigaKassor();
				state.setCurrentEvent(this);
				state.minskaAntalKunderIButik();
				state.ökaAntalKunderSomHandlat();
			}
		} else {
			//System.out.println("Empty NOT Found");
			if (!eQ.isEmpty()) { eQ.addEvent(this); }
			else { eQ.set(0, this); }

		}
		state.ökaAntalLedigaKassor();
	}


	public String getName() { return "Betalning"; }

	public Kund getKund() { return this.kund; }
}
