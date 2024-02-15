package mainSim;

import generellSim.EventQueue;
import snabbköp.KassaKöFIFO;
import snabbköp.Run;
import snabbköp.SnabbköpState;
import snabbköp.SnabbköpView;
import snabbköp.Stop;
import snabbköp.kunder.Ankomst;
import snabbköp.kunder.Kund;
import generellSim.Sim;

public class RunSim {
	private SnabbköpState state;
	private EventQueue eQ;
	private Sim sim;
	
	public RunSim(double startTid, double stopTid, double closeTid, double lambda, double kMin,
			double kMax, double pMin, double pMax, long seed, int maxKunder, int maxKassor) {
		this.eQ = new EventQueue();
		this.state = new SnabbköpState(maxKunder, maxKassor, 1, lambda, seed, kMin, kMax, pMin, pMax);
		sim = new Sim(eQ, state, state);
		SnabbköpView view = new SnabbköpView(state, eQ);
		KassaKöFIFO kkF = new KassaKöFIFO(state, eQ);

		state.setKassaKöFIFO(kkF);
		state.setSnabbköpÖppet(true);
		state.addObserver(view);

		Run run = new Run(state, eQ, startTid);
		this.eQ.addEvent(run);

		state.setCurrentEvent(run);
		sim.körHändelser();

		Stop close = new Stop(state, eQ, closeTid);
		eQ.add(0,close);
		eQ.get(0).createEvent();
	}
	
	public SnabbköpState getState() { return this.state; }
	
	public static void main(String[] args) {
		RunSim rSim = new RunSim(0, 5.00, 999, 1.0, 2.0, 3.0, .5, 1.0, 1234, 5, 5);
	}

}
