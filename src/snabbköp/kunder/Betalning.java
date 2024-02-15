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
			state.minskaAntalLedigaKassor();
			state.setCurrentEvent(this);
			state.minskaAntalKunderIButik();
			state.ökaAntalKunderSomHandlat();
			if (state.getKassaKöFIFO().isEmpty()) {
				state.getKassaKöFIFO().ordnaKö();
				state.ökaAntalLedigaKassor();
			} else {
				System.out.println(eQ);
			}
			//state.setCurrentEvent(this);
		}
	}


	public String getName() { return "Betalning"; }
}
