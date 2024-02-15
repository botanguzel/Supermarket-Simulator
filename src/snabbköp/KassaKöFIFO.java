package snabbköp;
import generellSim.EventQueue;
import snabbköp.kunder.*;
import java.util.LinkedList;

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
		if (state.getAntalLedigaKassor() < 0) {
			System.out.println("Finaly empty");
			this.remove(0);
			state.minskaAntalLedigaKassor();
		}
	}
}
