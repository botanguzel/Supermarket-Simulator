
package randomNum;

import java.util.Random;

/**
 * 
 * En klass som randomiserar tal baserat på en expontialström. 
 * 
 * @author Håkan
 *
 */
public class ExponentialRandomStream {
	
	private Random rand;
	private double lambda;
	  
	public ExponentialRandomStream(double lambda, long seed) {
	  	rand = new Random(seed);
	  	this.lambda = lambda;
	}
	
	
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
	    this.lambda = lambda;
	}
	  
	public double next() {
	  	return -Math.log(rand.nextDouble())/lambda;
	}
}

