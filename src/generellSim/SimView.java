package generellSim;

import java.util.Observable;
import java.util.Observer;

/**
 * The abstract class for observing the state of the simulation (SimState). 
 * Specific views should inherit from this class.
 * This class implements the Observer interface, allowing it to receive updates
 * about changes in the simulation state.
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public abstract class SimView implements Observer {

    /**
     * Constructs a new SimView. 
     */
    public SimView() {
         
    }
    
    /**
     * The update method is called whenever the observed object is changed. 

     * 
     * @param o the observable object.
     * @param arg an argument passed to the notifyObservers method.
     */
    @Override
    public abstract void update(Observable o, Object arg);
}
