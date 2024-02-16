package snabbköp.kunder;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.SnabbköpState;

public class Plock extends KundHändelse{
	
	public Plock (SnabbköpState state, EventQueue eQ, double tid, Kund kund) { super(state, eQ, tid, kund); }

	@Override
	public void createEvent() {
		state.setCurrentEvent(this);
		state.setKundID(this.getKund().getKundID());
		if (state.getAntalLedigaKassor() > 0) {
			eQ.addEvent(new Betalning(state, eQ, state.getBetalningsTid(), kund));
			state.minskaAntalLedigaKassor();
		}
		else {
			eQ.addEvent(new Betalning(state, eQ, state.getBetalningsTid(), kund));

			state.getKassaKöFIFO().add(this.kund.getKundID());
		}
	}	
	
	public String getName() { return "Plock"; }
	public Kund getKund() { return this.kund; }
	
}
