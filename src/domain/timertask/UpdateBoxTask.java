package domain.timertask;

import java.util.TimerTask;

import domain.GiftBox;
import domain.GiftBoxList;

public class UpdateBoxTask extends TimerTask {


	public UpdateBoxTask() {

	}
	@Override
	public void run() {
		// update ball position
		for (GiftBox box: GiftBoxList.getInstance().getList()) {
			box.move();
		}

	}

}