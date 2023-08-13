package domain.obstacle;

public class HollowObstacle extends Obstacle {

	public HollowObstacle() {

		this.setLife(1);
		this.setLength(30);
		this.setWidth(20);
		this.setIs_rectangle(true);
		hasParticle = false;
		is_rectangle = true;
		hasGiftBox = false;
		hasText = false;
		color = "purple";
		

	}

}
