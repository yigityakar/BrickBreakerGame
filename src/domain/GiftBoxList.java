
package domain;

import java.util.ArrayList;
import java.util.List;
import domain.obstacle.Obstacle;

public class GiftBoxList {
	private List<GiftBox> boxList;
	private static GiftBoxList instance;
	
	private GiftBoxList() {
	}

	public List<GiftBox> getList() {
		return boxList;
	}

	public static GiftBoxList getInstance() {
		if (instance == null) {
			instance = new GiftBoxList();
		}
		return instance;
	}

	public void initilizeList() {
		boxList = new ArrayList<>();
	}

	public void addGiftBox(Obstacle obstacle) {
		GiftBox GiftBox = new GiftBox();
		GiftBox.setX(obstacle.getX_position());
		GiftBox.setY(obstacle.getY_position());
		GiftBox.setUpwardPathX(obstacle.getX_position());
		GiftBox.setUpwardPathY(obstacle.getY_position());
		boxList.add(GiftBox);
	}

}