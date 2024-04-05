package generellSim;

import java.util.Observable;

/**
 * Represents the general state of the simulation. It manages the simulation's running state and time.
 * Observers are notified of changes, allowing for reactive updates in the simulation's view and other components.
 * 
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public abstract class SimState extends Observable {
    
    /**
     * Indicates whether the simulation is currently running. Initialized to false.
     */
    private boolean simulationRunning = false;

    /**
     * The current time of the simulation.
     */
    private double time;

    /**
     * Constructs a new simulation state, initially not running and with the simulation time set to zero.
     */
    public SimState() {
    }

    /**
     * Starts the simulation and notifying observers.
     */
    @SuppressWarnings("deprecation")
	public void startSimulation() {
        this.simulationRunning = true;
        this.notifyObservers();
    }

    /**
     * Stops the simulation  and notifying observers.
     */
    @SuppressWarnings("deprecation")
	public void stopSimulation() {
        this.simulationRunning = false;
        this.notifyObservers();
    }

    /**
     * Returns the current running state of the simulation.
     * 
     * @return {@code true} if the simulation is running, {@code false} otherwise.
     */
    public boolean simulationRunning() {
        return this.simulationRunning;
    }

    /**
     * Retrieves the current simulation time.
     * 
     * @return The current time of the simulation.
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Sets the simulation time to the specified value.
     * 
     * @param time The new time to set for the simulation.
     */
    public void setTime(double time) {
        this.time = time;
    }

}
