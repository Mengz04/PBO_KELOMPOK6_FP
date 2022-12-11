package application;

import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Mob extends GameObject {
	//GIF assets
	private ImageView mobIcon= new ImageView 
			("/resources/zombie-walk-right-GIF.gif");
	private Image leftMobIcon= new Image
			("/resources/zombie-walk-left-GIF.gif");
	private Image rightMobIcon = new Image
			("/resources/zombie-walk-right-GIF.gif");
	
	private AnchorPane gamePane;
	private Handler handler;
	private GameObject tempPlayer = null;
	private GameObject tempCollide = null;
	private ID id;
	
	public Mob(float x, float y, ID id, AnchorPane mainPane, Handler mainHandler) {
		super(x, y, 500, 0, 50, 64, id);
		this.id = id;
		this.gamePane = mainPane;
		this.handler = mainHandler;
		createMob();
		findPlayer();
	}
	
	public void findPlayer() { //cari player
		for(int i=0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	
	private void collide() { //deplete HP jika collide dengan weapon atau projectile
		for(int i=0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Weapon) {
				tempCollide = handler.object.get(i);
				if(getBounds().getBoundsInParent().intersects(tempCollide.getBounds().getBoundsInParent())){
					this.HP -= tempCollide.getDamage();
				}
			}
		}
	}
	
	@Override
	public void move() { //collide & tracking player movement
		collide();
		//System.out.println(this.HP);
		if(this.HP<=0) {
			gamePane.getChildren().remove(mobIcon);
			handler.removeObject(this);
		}
		else {
			if(tempPlayer.x-this.x >0) { //kanan
				mobIcon.setImage(rightMobIcon);
				mobIcon.setLayoutX(mobIcon.getLayoutX() + 0.6);
				this.x += 0.6;
			}
			if(tempPlayer.y-this.y >0) { //bawah
				mobIcon.setImage(rightMobIcon);
				mobIcon.setLayoutY(mobIcon.getLayoutY() + 0.6);
				this.y += 0.6;
			}
			if(tempPlayer.y-this.y <0) { //atas
				mobIcon.setImage(rightMobIcon);
				mobIcon.setLayoutY(mobIcon.getLayoutY() - 0.6);
				this.y -= 0.6;
			}
			if(tempPlayer.x-this.x <0) { //kiri
				mobIcon.setImage(leftMobIcon);
				mobIcon.setLayoutX(mobIcon.getLayoutX() - 0.6);
				this.x -= 0.6;
			}
		}
	}
	
	public Rectangle getBounds() { //return hitbox mob
		return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
	}
	
	private void createMob() { //inisialisasi imageIcon mob
		mobIcon.setFitWidth(this.width);
		mobIcon.setFitHeight(this.height);
		mobIcon.setLayoutX(this.x);
		mobIcon.setLayoutY(this.y);
		gamePane.getChildren().add(mobIcon);
	}
}
