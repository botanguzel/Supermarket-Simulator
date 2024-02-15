package snabbköp;
import generellSim.Event;
import generellSim.EventQueue;
import snabbköp.kunder.*;
import java.util.LinkedList;
import java.util.Objects;

public class KassaKöFIFO extends LinkedList<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	EventQueue eQ;
	SnabbköpState state;
	
	public KassaKöFIFO(SnabbköpState state, EventQueue eQ) {
		this.eQ = eQ;
		this.state = state;
	}
	@Override
	public boolean add(Integer e) {
		this.add(0,e);
		return true;
	}

	public void ordnaKö() {
		if (!this.isEmpty() && state.getAntalLedigaKassor() > 0) {
			for (Event e: eQ) {
				if (e instanceof Betalning) {
					e.createEvent();
					eQ.remove(e);
					this.remove(((Betalning) e).getKund());

				}
			}
		}
	}
}
