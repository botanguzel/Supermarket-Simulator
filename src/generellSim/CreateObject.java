package generellSim;

import snabbköp.Snabbköp;
import snabbköp.SnabbköpState;

/**
 * An abstract class for creating the objects for the Simulator.
 * 
 * @author Ludvig Lidén, Botan Guzel, Sergij Wennström
 */


public abstract class CreateObject {

	/**
	 * Represents the ID of the object
	 */
	public int objectID;
	
	/**
	 * Represents a variable that refers to the state of the variables inside Snabbköp
	 */
	public SnabbköpState state;
	
	/**
	 * Represents a variable that refers to Snabbköp. 
	 */
	public Snabbköp snabbköp;
	
	/**
	 * Represents a variable that refers to the EventQueue. 
	 */
	public EventQueue eQ;
	
	/**
	 * @param objectID
	 * @param state
	 * @param snabbköp
	 * @param eQ
	 */
	
	/**
	 * The constructor for this class.
	 */
	public CreateObject(int objectID, SnabbköpState state, Snabbköp snabbköp, EventQueue eQ) {
		
		this.objectID = objectID;
		this.state = state;
		this.snabbköp = snabbköp;
		this.eQ = eQ;
		
	}

	public abstract void create(); 


}