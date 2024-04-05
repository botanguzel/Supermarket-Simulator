package generellSim;

/**
 * Abstract base class for events within the simulation.
 *
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public abstract class Event {
    protected SimState state; // General state of the simulation
    protected EventQueue eQ; // Queue for managing events within the simulation
    protected double timeOfEvent; // Simulation time when the event occurs

    /**
     * Constructor for creating a general event within the simulation.
     *
     * @param state The general state of the simulation.
     * @param eQ The event queue for the simulation.
     * @param timeOfEvent The time at which the event occurs within the simulation.
     */
    public Event(SimState state, EventQueue eQ, double timeOfEvent) {
        this.state = state;
        this.eQ = eQ;
        this.timeOfEvent = timeOfEvent;
    }

    /**
     * Executes the general event.
     */
    public abstract void executeEvent();

    /**
     * Returns the name of the event.
     * @return The name of the event.
     */
    public abstract String getName();

    /**
     * Returns the simulation time at which the event occurs.
     * @return The current time in the simulation when the event occurs.
     */
    public double getTimeOfEvent() {
        return this.timeOfEvent;
    }

   
}