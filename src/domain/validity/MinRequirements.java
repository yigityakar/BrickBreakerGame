package domain.validity;

import java.util.List;
import java.util.ArrayList;

import domain.Strategy;
import domain.listeners.ValidityListener;
import domain.obstacle.Obstacle;

public class MinRequirements implements Strategy {
	private int simple_req = 5;
	private int firm_req = 10;
	private int exp_req = 0;
	private int gift_req = 10;
	private int sum_max = 120;
	private List<ValidityListener> validityListeners = new ArrayList<ValidityListener>();

	@Override
	public boolean BuildingModePass(int s, int y, int e, int g) {
		//if the minimum requirements are not satisfied, publish validity event
		if( s < simple_req || y < firm_req || e < exp_req || g < gift_req) {
			publishValidityEvent("Minimum requirements not satisfied.");
			return false;
		}
			
		else return true;
	}

	@Override
	public void publishValidityEvent(String message) {
		for (ValidityListener listener: validityListeners) {
			System.out.println("published minreq");
			listener.onValidityEvent(message);
			
		}
		
	}
	@Override
	public void addValidityListener(ValidityListener l) {
		getValidityListeners().add(l);
		System.out.println("added listener to min req");
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