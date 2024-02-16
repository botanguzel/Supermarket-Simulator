package snabbköp.kunder;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.SnabbköpState;

public class Ankomst extends KundHändelse {

	public Ankomst ( SnabbköpState state, EventQueue eQ, double tid, Kund kund) {
		super(state, eQ, tid, kund);
	}
	
	@Override
	public void createEvent() {
		state.setKundID(this.getKund().getKundID());
		state.setCurrentEvent(this);
		state.setTime(tid);
		if (state.isSnabbköpÖppet() && state.getAntalKunderIButik() < state.getMaxKunder()) {
			eQ.addEvent(new Plock(state, eQ, state.getPlockTid(), kund));
			state.ökaAntalKunderIButik();
			state.ökaTotalAntalKunder();
		} else {
			state.läggMissadKund();
			state.ökaTotalAntalKunder();
		}
		Kund nKund = new Kund();
		eQ.addEvent(new Ankomst(state, eQ, state.getAnkomstTid(), nKund));
		this.setEventState(true);
	}
	@Override
	public String getName() { return "Ankomst"; }

	public Kund getKund() { return this.kund;}
}
