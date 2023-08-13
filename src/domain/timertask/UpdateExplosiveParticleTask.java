package domain.timertask;



import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import domain.ExplosiveParticle;
import domain.ExplosiveParticleList;
public class UpdateExplosiveParticleTask extends TimerTask {
	//ExplosiveParticles  exp;
  
    public UpdateExplosiveParticleTask() {
    	
    	 //this.exp= exp;
    }
    @Override
    public void run() {
        // update ball position
    	
//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		//System.out.println(exp.getX());
		
		//System.out.println(exp.getY());
 
		//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
  
		for (ExplosiveParticle particle: ExplosiveParticleList.getInstance().getList()) {
			particle.move();
		}
    	
    	  }

}