package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Explosion extends GameObject{
	private Image Explosion = new Image
			("/resources/explosionGif.gif");
	private ImageView ExplosionIcon;
	private AnchorPane gamePane;
	private Handler handler;
	private int despawnTime = 100;
	
	public Explosion(float x, float y, ID id, AnchorPane gamePane, Handler handler) {
		super(x, y, 0, 10, 0, 125, 125, id);
		this.x = x;
		this.y = y;
		this.gamePane = gamePane;
		this.handler = handler;
		createExplosion();
	}
	
	private void createExplosion(){
		ExplosionIcon = new ImageView();
		ExplosionIcon.setImage(Explosion);
		ExplosionIcon.setFitWidth(this.width);
		ExplosionIcon.setFitHeight(this.height);
		ExplosionIcon.setLayoutX(this.x);
		ExplosionIcon.setLayoutY(this.y);
		gamePane.getChildren().add(ExplosionIcon);
	}

	@Override
	public void move() {
		despawnTime--;
		if(despawnTime<=0) {
			gamePane.getChildren().remove(ExplosionIcon);
			handler.addRemoveTask(this);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
}
