
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.validity.ExcessObstacles;;

class BuildingModePass {
	ExcessObstacles e;
	@BeforeEach
	void setUp() {
		 e = new ExcessObstacles();
		 
		
	}
	@Test
	void test() {
		assertTrue(e.BuildingModePass(20,20,20,20));
		 assertFalse(e.BuildingModePass(120,20,20,20));

		 assertFalse(e.BuildingModePass(Integer.MAX_VALUE,+3,0,0));
		 assertFalse(e.BuildingModePass(Integer.MIN_VALUE,0,-1,0));
		 assertFalse(e.BuildingModePass(121,0,0,0));
		
	}

}
