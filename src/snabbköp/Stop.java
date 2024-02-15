package snabbköp;

import generellSim.*;
import snabbköp.kunder.*;

import java.util.concurrent.CountDownLatch;

public class Stop extends Event {
	boolean sistaBetalning = false;
	public Stop(SnabbköpState state, EventQueue eQ, double tid) {
		super(state, eQ, tid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createEvent() {
		state.setCurrentEvent(this);
		state.setTime(tid);
	}

	@Override
	public String getName() { return "Stänger"; }
	

}
