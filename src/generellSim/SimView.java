package generellSim;

import java.util.Observable;
import java.util.Observer;

public abstract class SimView implements Observer{
	
	public abstract void update(Observable o, Object e);

}
