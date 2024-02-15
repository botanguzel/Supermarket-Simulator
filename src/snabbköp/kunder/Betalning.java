package snabbköp.kunder;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.Snabbköp;
import snabbköp.SnabbköpState;

public class Betalning extends KundHändelse{

	public Betalning(SnabbköpState state, EventQueue eQ, double tid, Kund kund) { super(state, eQ, tid, kund); }
	
	@Override
	public void createEvent() {
		System.out.println(eQ);
		if (state.getAntalLedigaKassor() >=0) {
			System.out.println("Empty REgister Found");
			if (!state.getKassaKöFIFO().isEmpty()) {
				System.out.println("People in LINE");
				//state.getKassaKöFIFO().ordnaKö();
			} else {
				System.out.println("NOONE IS IN LINE");
				state.minskaAntalLedigaKassor();
				state.setCurrentEvent(this);
				state.minskaAntalKunderIButik();
				state.ökaAntalKunderSomHandlat();
			}
			//state.setCurrentEvent(this);
			state.ökaAntalLedigaKassor();
		} else {
			System.out.println("Empty REgister NOT Found");
			eQ.set(1, this);

		}
	}


	public String getName() { return "Betalning"; }

	public Kund getKund() { return this.kund; }
}
