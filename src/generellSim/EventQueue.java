package generellSim;

/**
 * The class that represents the general list of events in a Simulator.
 * The class will put the events in order depending on which time the
 * event will occur. 
 * 
 * @author Ludvig Lidén, Botan Guzel, Sergij Wennström
 */


import java.util.ArrayList;

public class EventQueue extends ArrayList<Event> {
	/**
	 * The constructor calls the parent class ArrayList, so that
	 * the child class EventQueue get's all the properties from
	 * the ArrayList class.
	 */
	public EventQueue() { super();}
	
	/**
	 * This function checks if an event should be added to
	 * the event queue or not.
	 * 
	 * @param e The parameter e represents an event.
	 * @return 
	 */
	public boolean addEvent(Event e) {
		
		if (!e.getEventState()) {
			
			if (this.isEmpty()) { 
				this.add(e); //Adds event E to the list. 
				return true; //Shows that the event got added tox
			}
			else {
				this.add(e); //Adds event E to the list. RETURN TRUE?
				/*
				for (int i = 0; i < this.size(); i++) {
					if (e.tid < this.get(i).tid) {
						System.out.println(i);
						this.add(i, e);
						return true;
					}
				}*/
			}
		}
		return false;
	} 
}