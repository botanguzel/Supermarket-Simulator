package generellSim;

import snabbköp.SnabbköpState;
import snabbköp.kunder.Kund;

public abstract class Event {
	
	protected SnabbköpState state;
	protected double tid;
	protected EventQueue eQ;
	protected boolean eventState = false;
	
	public Event(SnabbköpState state, EventQueue eQ, double tid) {
		this.state = state;
		this.eQ = eQ;
		this.tid = tid;
	}
	public abstract void createEvent();	
	public abstract String getName();
	
	public double händelseTid() { return this.tid; }
	public boolean getEventState() { return this.eventState; }
	
	public void setEventState(boolean b) { this.eventState = b; }
	
}
