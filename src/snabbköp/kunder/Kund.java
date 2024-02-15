package snabbköp.kunder;

import java.util.ArrayList;

import generellSim.CreateObject;
import generellSim.EventQueue;
import snabbköp.Snabbköp;
import snabbköp.SnabbköpState;
import snabbköp.kunder.*;
public class Kund {
	static int lastID = 0;
	private int ID;
	public Kund() {
		this.ID = lastID;
		lastID++;
	}
	public int getKundID() {
		return this.ID;
	}
}
