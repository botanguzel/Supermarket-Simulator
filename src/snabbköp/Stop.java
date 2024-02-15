package snabbköp;

import generellSim.Event;
import generellSim.EventQueue;

public class Stop extends Event {

	public Stop(SnabbköpState state, EventQueue eQ, double tid) {
		super(state, eQ, tid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createEvent() { state.setSnabbköpÖppet(false); }

	@Override
	public String getName() { return "Stänger"; }
	

}
