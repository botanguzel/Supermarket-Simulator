package snabbköp;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.kunder.Ankomst;
import snabbköp.kunder.Kund;
public class Run extends Event {
	private Kund kund;
	

	public Run(SnabbköpState state, EventQueue eQ, double tid) {
		super(state, eQ, tid);
		this.kund = new Kund();
	}

	@Override
	public void createEvent() {
		state.setCurrentEvent(this);
		eQ.addEvent(new Ankomst(state, eQ, state.getAnkomstTid(), this.kund));
		this.setEventState(true);
	}

	@Override
	public String getName() { state.setCurrentEvent(this);  return "Start"; }
}