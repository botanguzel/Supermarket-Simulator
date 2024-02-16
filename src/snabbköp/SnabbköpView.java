package snabbköp;

import java.util.Observable;

import generellSim.Event;
import generellSim.EventQueue;
import generellSim.SimView;
import snabbköp.kunder.Ankomst;

public class SnabbköpView extends SimView{
	private SnabbköpState state;
	private EventQueue eQ;

	public SnabbköpView(SnabbköpState state, EventQueue eQ) {
		super();
		this.state= state;
		this.eQ = eQ;
	}
	
	public void printUtRun(Observable o, Object e) {
		System.out.println(String.format(
				"""
    			PARAMETRAR
				==========
				Antal kassor, N..........: %s
				Max som ryms, M..........: %s
				Ankomshastighet, lambda..: %s
				Plocktider, [P_min..Pmax]: [%s..%s]
				Betaltider, [K_min..Kmax]: [%s..%s]
				Frö, f...................: %s
				FÖRLOPP
				=======
				%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s""",
				state.getAntalLedigaKassor(),
				state.getMaxKunder(),
				state.getLambda(),
				state.getPMin(),
				state.getPMax(),
				state.getKMin(),
				state.getKMax(),
				state.getSeed(),
				"Tid",
				"Händelse",
				"Kund",
				"?",
				"led",
				"ledT",
				"I",
				"$",
				":-(",
				"köat",
				"köT",
				"köar",
				"[Kassakö..]"
		));
				//state.getTime(),
				//state.getCurrentEvent().getName()));
	}

	
	@Override
	public void update(Observable o, Object e) {
		if (state.getCurrentEvent() instanceof Run) {
			printUtRun(o ,e);
			System.out.println(String.format(
					"%-10.2f\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s",
					state.getTime(),
					state.getCurrentEvent().getName(),
					state.getKundID(),
					state.isSnabbköpÖppet() ? "Ö" : "S",
					state.getAntalLedigaKassor(),
					state.getSummaTidLedigaKassor(),
					state.getAntalKunderIButik(),
					state.getAntalKunderSomHandlat(),
					state.getAntalMissadeKunder(),
					state.getAntalKunderSomKöat(),
					state.getSummaKöTid(),
					state.getKassaKöLängd(),
					state.getKassaKöFIFO()
			));
		}
		else if (state.getCurrentEvent() instanceof Stop) { Resultat(); }
		else {
			System.out.println(String.format(
					"%-10.2f\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s",
					state.getTime(),
					state.getCurrentEvent().getName(),
					state.getKundID(),
					state.isSnabbköpÖppet() ? "Ö" : "S",
					state.getAntalLedigaKassor(),
					state.getSummaTidLedigaKassor(),
					state.getAntalKunderIButik(),
					state.getAntalKunderSomHandlat(),
					state.getAntalMissadeKunder(),
					state.getAntalKunderSomKöat(),
					state.getSummaKöTid(),
					state.getKassaKöLängd(),
					state.getKassaKöFIFO()
			));
		}
	}

	public void Resultat() {
		eQ.clear(); //Work-around
		state.ökaAntalKunderSomKöat();
		double x = state.getAntalKunderSomKöat();
		System.out.println(String.format(
				"""
				%-10.2f\t%-10s
				
				Resultat
				========
				
				1) Av %s kunder handlade %s medan %s missades.
				
				2) Total tid %s kassor varit ledig: %s te.
				   Genomsnittlig ledig kassatid: %s te (dvs %.2f av tiden från öppning tills sista kunden betalat).
				   
				3) Total tid %s kunder tvingats köa: %s te.
				   Genomsnittlig kötid: %s te.
				""",
				state.getTime(),
				"Stop",
				state.getTotalAntalKunder(),
				state.getAntalKunderSomHandlat(),
				state.getAntalMissadeKunder(),
				state.getMaxKassor(),
				state.getSummaTidLedigaKassor(),
				state.getSummaTidLedigaKassor() / state.getMaxKassor(),
				state.getSummaTidLedigaKassor() / state.getMaxKassor() / state.getTime() * 100,
				state.getAntalKunderSomKöat(),
				state.getSummaKöTid(),
				x
		));
	}
}
