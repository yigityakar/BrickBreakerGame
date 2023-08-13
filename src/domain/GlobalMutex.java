package domain;

import java.util.concurrent.Semaphore;

public class GlobalMutex {
	static GlobalMutex  instance ;
	//SynchronizedObject mutex;
	Semaphore semaphore = new Semaphore(1, true);
	
	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	private GlobalMutex() {
		
	}
	
	public static GlobalMutex getInstance() {
		if(instance == null) {
			instance = new GlobalMutex();
		}
		return instance;
	}

}
