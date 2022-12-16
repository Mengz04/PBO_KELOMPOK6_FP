package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grenade extends GameObject{
	private Image Grenade = new Image
			("/resources/rezeGrenade.gif");
	private ImageView GrenadeIcon;
	private AnchorPane gamePane;
	private Handler handler;
	private GameObject tempPlayer =null;
	private int despawnTime = 2000;
	
	public Grenade(float x, float y, ID id, AnchorPane gamePane, Handler handler) {
		super(x, y, 10, 0, 0, 30, 30, id);
		this.x = x;
		this.y = y;
		this.gamePane = gamePane;
		this.handler = handler;
		createBullet();
	}


	@Override
	public void move() {
		if(HP <= 0 || despawnTime <= 0) {
			handler.addObjTask.add(new Explosion(x-(125-width)/2, y-125+height,ID.Weapon, gamePane, handler));
			gamePane.getChildren().remove(GrenadeIcon);
			handler.removeTask.add(this);
		}
		despawnTime--;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
	
	public void createBullet() {
		GrenadeIcon = new ImageView();
		GrenadeIcon.setImage(Grenade);
		GrenadeIcon.setFitWidth(this.width);
		GrenadeIcon.setFitHeight(this.height);
		GrenadeIcon.setLayoutX(this.x);
		GrenadeIcon.setLayoutY(this.y);
		gamePane.getChildren().add(GrenadeIcon);
	}
}
