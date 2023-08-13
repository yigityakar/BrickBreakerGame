package domain.validity;

import java.util.ArrayList;

import domain.Strategy;

public class BuildingModeRequirement {
	private Strategy strategy;
	
	public BuildingModeRequirement (Strategy strategy) {
		this.strategy = strategy;
	}
	
	
	public boolean executeStrategy(int s ,int f, int e, int g) {
		return strategy.BuildingModePass(s, f, e, g);
	}


	public Strategy getStrategy() {
		return strategy;
	}


	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	

}
