package domain.obstacle;

public class SimpleObstacle extends Obstacle {

	public SimpleObstacle() {

		this.setLife(1);
		this.setLength(30);
		this.setWidth(20);
		this.setIs_rectangle(true);
		hasParticle = false;
		is_rectangle = true;
		hasGiftBox = false;
		hasText = false;
		color = "yellow";

	}

}
