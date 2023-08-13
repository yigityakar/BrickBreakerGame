package domain.listeners;

//interface for the listener/ subscribers of a publisher
public interface ValidityListener {
	public void onValidityEvent(String message);
}
