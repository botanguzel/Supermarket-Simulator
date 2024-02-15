package generellSim;

import java.lang.reflect.Array;
import java.util.ArrayList;

import mainSim.RunSim;
import snabbköp.SnabbköpState;

public class Optimise {
	Integer[] arrayKassor = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20 };
	public SnabbköpState metod1(int maxKassor) { //TODO
		RunSim runSim = new RunSim(0, 10.00, 999, 1.0, 2.0, 3.0, .5, 1.0, 1234, 5, maxKassor);
		return runSim.getState();
	}
	
	public Integer metod2(Integer[] a, int x, int low, int high) {
		SnabbköpState sss = metod1(x);
		while ((0 < sss.getAntalLedigaKassor()) && (sss.getAntalLedigaKassor() <= sss.getMaxKunder())) {
			if (low > high) { return null; }
			else {
				int mid = (low + high) / 2;
				if (x == a[mid]) {
					metod1(x);
					return mid;
					}
				else {
					if (x > a[mid]) { metod1(x); return metod2(a, x, mid+1, high); }
					else { metod1(x); return metod2(a, x, low, mid-1); }					
				}	
			}
		}
		return x;
	}
	
	public void metod3() { //TODO
		//Köra metod2()= fler agånger med givna parametrar men med olika frön. Tar
		//frö f som argument och har en variabel av typen random som refererar till
		//slumptalskällan new Random(f)
		
		return; //se om minsta antal kassor är högre än tidigare. Då sparas som högsta minsta
				//tal. Kör tills detta inte hänt på 100 körningar - en loop.
				//skapa en räknare som nollställs när ett nytt högsta tal hittats.
	}

}
