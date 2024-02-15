package snabbköp.kunder;

import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.SnabbköpState;

public abstract class KundHändelse extends Event {
	protected Kund kund;

	public KundHändelse(SnabbköpState state, EventQueue eQ, double tid, Kund kund) {
		super(state, eQ, tid);
		this.kund = kund;
	}

	@Override
	public abstract void createEvent();

	@Override
	public abstract String getName();
	public abstract Kund getKund();

}
