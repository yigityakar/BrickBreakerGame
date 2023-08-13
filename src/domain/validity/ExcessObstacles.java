package domain.validity;
import java.util.ArrayList;
import java.util.List;

import constants.UI;
import domain.Strategy;
import domain.listeners.ValidityListener;

public class ExcessObstacles implements Strategy {
	private int sum_max = 120;
	public static long sum ;
	private List<ValidityListener> validityListeners = new ArrayList<ValidityListener>();
    
	@Override
	public boolean BuildingModePass(int s, int y, int e, int g) {
		
		long ss=s;
		long yy=y;
		long ee=e;
		long gg =g;

	
		
		 sum =ss + yy + ee + gg;
		//System.out.println(sum);
		if(sum > Integer.MAX_VALUE) {
			return false;
		}
		if(sum < Integer.MIN_VALUE) {

			return false;
		}
		

		if(sum >= 121) {
			//publishValidityEvent("Maximum number of obstacles is 120.");
			return false;
		}else 
			return true;
	
		
		
	}
	@Override
	public void publishValidityEvent(String message) {
		// TODO Auto-generated method stub
		for (ValidityListener listener: validityListeners) {
			System.out.println("published excess");
			listener.onValidityEvent(message);
		}
		
	}
	@Override
	public void addValidityListener(ValidityListener l) {
		getValidityListeners().add(l);
		System.out.println("added listener to excess");
	}

	@Override
	public List<ValidityListener> getValidityListeners() {
		return validityListeners;
	}

	@Override
	public void setValidityListeners(List<ValidityListener> validityListeners) {
		this.validityListeners = validityListeners;
	}

}
