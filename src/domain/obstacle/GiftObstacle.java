package domain.obstacle;

import java.util.Random;

import domain.ExplosiveParticle;
import domain.GiftBox;

public class GiftObstacle extends Obstacle {

	public GiftObstacle() {

		this.setLife(1);
		this.setIs_rectangle(true);
		this.setLength(30);
		this.setWidth(20);
		hasGiftBox = true;
		hasParticle = false;
		is_rectangle = true;
		hasText = false;
		color = "green";
		this.setLife(1);

	}

}
