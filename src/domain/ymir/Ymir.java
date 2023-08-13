package domain.ymir;

import java.util.Random;

public class Ymir {
	YmirAbility ability;


	public Ymir() {}
	
	public void chooseAbility() {
		
		//randomly chooses an ability
		Random random = new Random();
		int abilityNo = random.nextInt(3);
		System.out.println("Ability no: " + abilityNo);
		switch(abilityNo) {
		case 0:	
			setAbility(new InfiniteVoid());
			break;
		case 1:	
			setAbility(new DoubleAccel());
			break;
		case 2:	
			setAbility(new HollowPurple());
			break;
		}
		
		//activates ability
		ability.activate();
	}

	
	public YmirAbility getAbility() {
		return ability;
	}

	public void setAbility(YmirAbility ability) {
		this.ability = ability;
	}
}
