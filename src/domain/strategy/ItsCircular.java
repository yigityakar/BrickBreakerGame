package domain.strategy;

import domain.obstacle.Obstacle;

public class ItsCircular implements Moving {
	
	private int x ,y;
	int counter;
	int radius;
	public ItsCircular() {
		counter=1;
	}
	
	public void move(Obstacle ob){
		
		if(counter>0) {
			if(counter<12) {
		
		ob.setX_position(ob.getX_position()+1);
		
		
		
		
		
		counter++;
			}else {
				counter =-1;
			}
	}else if(counter>-12) {
			
			ob.setX_position(ob.getX_position()-1);
			counter--;
				}else {
					counter =1;
		
	}
		
	
	}
	}