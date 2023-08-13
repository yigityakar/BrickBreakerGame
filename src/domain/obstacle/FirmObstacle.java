package domain.obstacle;

import java.util.Random;

public class FirmObstacle extends Obstacle {

	public FirmObstacle() {
		Random rand2 = new Random();
		this.setLife(rand2.nextInt(5) + 1);
		this.setLength(30);
		this.setWidth(20);
		this.setIs_rectangle(true);
		color = "red";
		hasText = true;
		hasParticle = false;
		is_rectangle = true;
		hasGiftBox = false;
	}

}
