package application;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class BombDevil extends GameObject{
	private Handler handler;
	private GameObject tempPlayer = null;
	private AnchorPane gamePane;
	private int cooldown=800;
	
	public BombDevil(float x, float y, ID id, Handler handler, AnchorPane gamePane) {
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
		
		if(cooldown<=0) {
			handler.addObjTask.add(new Grenade(x, y, ID.Grenade, gamePane, handler));
			cooldown = 800;
		}
		cooldown--;
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}
