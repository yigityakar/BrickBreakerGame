package domain.obstacle;

public class ExplosiveObstacle extends Obstacle {

	public ExplosiveObstacle() {
		this.setLife(2);
		this.setIs_rectangle(false);
		this.setLength(30);
		this.setWidth(25);
		this.setLife(1);
		hasParticle = true;
		is_rectangle = false;
		hasGiftBox = false;
		hasText = false;
		color = "blue";

	}

}
