package snabbköp.kunder;

import java.util.ArrayList;

import generellSim.CreateObject;
import generellSim.EventQueue;
import snabbköp.Snabbköp;
import snabbköp.SnabbköpState;
import snabbköp.kunder.*;
public class KundID extends CreateObject{
	
	
	private int kundID;
	public KundID(int kundID, SnabbköpState state, Snabbköp snabbköp, EventQueue eQ) {
		super(kundID, state, snabbköp, eQ);
		this.setKundID(kundID);
	}
	public int getKundID() {
		return this.kundID;
	}
	public void setKundID(int kundID) {
		this.kundID = kundID;
	}
	@Override
	public void create() {
		new Ankomst(state, eQ, objectID, null).createEvent(4, 1234, this.kundID);
	}
}
