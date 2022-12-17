package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class GunDevil extends GameObject{
	private Handler handler;
	private GameObject tempPlayer = null;
	private AnchorPane gamePane;
	private int cooldown=500;
	private int cooldownCount=cooldown;
	
	public GunDevil(float x, float y, ID id, Handler handler, AnchorPane gamePane) {
		super(x, y, 0, 0, 0, 0, 0, id);
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.gamePane = gamePane;
		findPlayer();
	}
	
	public void findPlayer() {
		for(int i=0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}

	@Override
	public void move() {
		this.x = tempPlayer.x+36;
		this.y = tempPlayer.y+36;
		
		if(cooldownCount<=0) {
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "right"));
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "left"));
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "up"));
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "down"));
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "right-up"));
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "right-down"));
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "left-up"));
			handler.addObjTask.add(new Bullet(x, y, ID.Bullet, gamePane, handler, "left-down"));
			cooldownCount = cooldown;
		}
		cooldownCount--;
	}
	
	public void cdDecrease(int val) {
		if(cooldown>=250) {
			cooldown -= val;
		}
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}
