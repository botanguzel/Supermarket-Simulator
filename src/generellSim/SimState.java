package generellSim;

import java.util.Observable;

public class SimState extends Observable{
	private double time;
	private boolean io; //Öppet eller stängd
	
	
	public SimState() { this.setIo(true); }
	public void runSimulation() {
		
	}
	
	public boolean getIo() { return io;	}
	public double getTime() { return time; }
	
	public void setIo(boolean io) { this.io = io; }
	public void setTime(double time) { this.time += time; }
	
	public void avslutaSim() { this.io = false; }
	
	public void update(Event e) {
		this.setTime(e.tid);
		setChanged();
		notifyObservers();
	}
	
}
