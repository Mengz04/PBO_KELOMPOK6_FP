package application;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet extends GameObject{
	private Rectangle BulletIcon;
	private AnchorPane gamePane;
	private Handler handler;
	private String Orientation;
	private int despawnTime = 500;
	
	public Bullet(float x, float y, ID id, AnchorPane gamePane, Handler handler, String Orientation) {
		super(x, y, 0, 4, 0, 5, 5, id);
		this.x = x;
		this.y = y;
		this.gamePane = gamePane;
		this.handler = handler;
		this.Orientation = Orientation;
		createBullet();
	}

	@Override
	public void move() {
		if(Orientation == "right")this.x += 3;
		else if(Orientation == "left") this.x -= 3;
		else if(Orientation == "up") this.y -=3;
		else if(Orientation == "down") this.y += 3;
		else if(Orientation == "right-up") {this.x +=3; this.y-=3;}
		else if(Orientation == "right-down") {this.x +=3; this.y+=3;}
		else if(Orientation == "left-up") {this.x -=3; this.y-=3;}
		else {this.x -=3; this.y+=3;}
		BulletIcon.setX(this.x);
		BulletIcon.setY(this.y);
		
		despawnTime--;
		if(despawnTime<=0) {
			gamePane.getChildren().remove(BulletIcon);
			handler.addRemoveTask(this);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
	
	public void createBullet() {
		BulletIcon = new Rectangle(this.x, this.y, width, height);
		BulletIcon.setFill(Color.WHITE);
		gamePane.getChildren().add(BulletIcon);
	}
}
