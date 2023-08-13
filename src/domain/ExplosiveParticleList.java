package domain;

import java.util.ArrayList;
import java.util.List;
import domain.obstacle.Obstacle;

public class ExplosiveParticleList {
	private List<ExplosiveParticle> paritclesList;
	private static ExplosiveParticleList instance;

	private ExplosiveParticleList() {
	}

	public List<ExplosiveParticle> getList() {
		return paritclesList;
	}

	public static ExplosiveParticleList getInstance() {
		if (instance == null) {
			instance = new ExplosiveParticleList();
		}
		return instance;
	}

	public void initilizeList() {
		paritclesList = new ArrayList<>();

	}

	public void addParticles(Obstacle obstacle) {
		ExplosiveParticle ExplosiveParticle = new ExplosiveParticle();
		ExplosiveParticle.setX(obstacle.getX_position());
		ExplosiveParticle.setY(obstacle.getY_position());
		ExplosiveParticle.setDownwardPathX(obstacle.getX_position());
		ExplosiveParticle.setDownwardPathY(obstacle.getY_position());
		paritclesList.add(ExplosiveParticle);

	}

}
