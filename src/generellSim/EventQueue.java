package generellSim;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the queue of events in the simulation, ensuring events are processed
 * in chronological order.
 *
 * @author Botzan Güzel, Sergij Wennströmm, Ludvig Lidén
 */
public class EventQueue {
    private ArrayList<Event> eventQueue = new ArrayList<>();

    /**
     * Constructs an empty event queue. No arguments are required as the constructor
     * only initializes the list to hold the events.
     */
    public EventQueue() {
    }

    /**
     * Adds a new event to the queue if it has not already occurred. The queue is
     * then sorted to ensure chronological order of events.
     *
     * @param e The event to be added.
     * @return True if the event was added, false if it has already occurred.
     */
    public void addEvent(Event e) {
        this.eventQueue.add(e);
        this.eventQueue.sort(Comparator.comparingDouble(Event::getTimeOfEvent));
    }

    /**
     * Retrieves and removes the next event in the queue, if any.
     *
     * @return The next event, or null if the queue is empty.
     */
    public Event getNextEvent() {
        return !this.eventQueue.isEmpty() ? this.eventQueue.remove(0) : null;
    }

    /**
     * Checks if the event queue is empty.
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.eventQueue.isEmpty();
    }
}
