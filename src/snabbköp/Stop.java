package snabbköp;

import generellSim.*;
import snabbköp.kunder.*;

import java.util.concurrent.CountDownLatch;

public class Stop extends Event {
	SimState ss;
	public Stop(SnabbköpState state, SimState ss, EventQueue eQ, double tid) {
		super(state, eQ, tid);
		this.ss = ss;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createEvent() {
		EventQueue nQ = new EventQueue();
		eQ.forEach(event -> { if (event.getClass() != Ankomst.class && event.getClass() != this.getClass()) { nQ.add(event); } });
		ss.setIo(false);
		for (int i = 0; i <= nQ.size(); i++) {
			nQ.get(i).createEvent();
			nQ.remove(i);
		}
		if ( nQ.isEmpty()) { state.setCurrentEvent(this); state.setTime(tid); }
	}

	@Override
	public String getName() { return "Stänger"; }
	void setEventStop(SnabbköpState state) {state.setCurrentEvent(this);}
	

}
