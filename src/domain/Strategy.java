package domain;

import java.util.List;

import domain.listeners.ValidityListener;

public interface Strategy {
	// method for strategy GoF pattern
	public boolean BuildingModePass(int s,int f,int e,int g);
	
	// methods for publish-subscribe GoF pattern
	public void publishValidityEvent(String message);
	
	public void addValidityListener(ValidityListener l) ;

	public List<ValidityListener> getValidityListeners();

	public void setValidityListeners(List<ValidityListener> validityListeners);

}
