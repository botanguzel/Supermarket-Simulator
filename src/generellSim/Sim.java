package generellSim;

import snabbköp.*;
import snabbköp.kunder.Ankomst;
import snabbköp.kunder.Betalning;
import snabbköp.kunder.Kund;
import snabbköp.kunder.KundHändelse;

public class Sim {
	EventQueue eQ;
	SimState ss;
	SnabbköpState sss;
	Object lock = new Object();

	SnabbköpView skV;
	public Sim(EventQueue eQ, SimState ss, SnabbköpState sss) {
		this.eQ = eQ;
		this.ss = ss;
		this.sss = sss;
		skV = new SnabbköpView(sss, eQ);
	}
	
	public void körHändelser() {
		//skV.update(ss, eQ);
		while (ss.getIo() && !this.eQ.isEmpty()) {
			//System.out.println(String.format("%s\t%s",sss.getAntalLedigaKassor(),  sss.getKassaKöFIFO()));
			//System.out.println(sss.getAntalLedigaKassor());
			//for (int i = 0; i < eQ.size(); i++) {System.out.println(eQ.get(i).getName());}
			eQ.get(0).createEvent();
			sss.setTime(eQ.get(0).händelseTid());
			eQ.remove(0);
			//System.out.println(eQ);
			if (sss.getTotalAntalKunder() == 6) { sss.setSnabbköpÖppet(false); }
			if (eQContainsBetalning() && !sss.isSnabbköpÖppet()) {
				//System.out.println(eQ);
				Stop stop = new Stop(sss, ss, eQ, sss.getTime());
				eQ.add(stop);
			}
			skV.update(sss, eQ);
		}
	}

	private boolean eQContainsBetalning() {
		for (Event event : eQ) {
			if (event instanceof Betalning) {
				return true;
			}
		}
		return false;
	}
}
