package application;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Mob extends GameObject {
	private ImageView mobIcon= new ImageView 
			("D:\\Kuliah\\sems 3\\PBO\\gem\\attempt2fx\\src\\resources\\zombie-walk-right-GIF.gif");
	private Image leftMobIcon= new Image
			("D:\\Kuliah\\sems 3\\PBO\\gem\\attempt2fx\\src\\resources\\zombie-walk-left-GIF.gif");
	private Image rightMobIcon = new Image
			("D:\\Kuliah\\sems 3\\PBO\\gem\\attempt2fx\\src\\resources\\zombie-walk-right-GIF.gif");
	private Image deadMobIcon = new Image
			("D:\\Kuliah\\sems 3\\PBO\\gem\\attempt2fx\\src\\resources\\Zombie-deceased-GIF.gif");
	private ColorAdjust hitBrightness = new ColorAdjust();	
	private Boolean hit = false;
	
	private AnchorPane gamePane;
	private Handler handler;
	private GameObject tempPlayer = null;
	private GameObject tempCollide = null;
	private int despawnTimer = 5000;
	private ID id;
	
	private int deathDuration = 50;
	
	public Mob(float x, float y, ID id, AnchorPane mainPane, Handler mainHandler) {
		super(x, y, 500, 0, 0, 50, 64, id);
		this.id = id;
		this.gamePane = mainPane;
		this.handler = mainHandler;
		hitBrightness.setBrightness(0);
		createMob();
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
	
	private void collide() {
		hit = false;
		for(int i=0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Weapon) {
				tempCollide = handler.object.get(i);
				if(getBounds().getBoundsInParent().intersects(tempCollide.getBounds().getBoundsInParent())){
					this.HP -= tempCollide.getDamage();
					hit = true;
				}
			}
		}
	}
	
	@Override
	public void move() {
		if(HP<=0 || despawnTimer <=0) { //mati
			hitBrightness.setBrightness(0);
			mobIcon.setEffect(hitBrightness);
			mobIcon.setImage(deadMobIcon);
			if(deathDuration>0) deathDuration--;
			else{
				gamePane.getChildren().remove(mobIcon);
				handler.addRemoveTask(this);
			}
		}
		else { //hidup
			collide();
			
			if(hit) {
				hitBrightness.setBrightness(0.7);
				mobIcon.setEffect(hitBrightness);
			}
			else {
				hitBrightness.setBrightness(0);
				mobIcon.setEffect(hitBrightness);
			}
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
		despawnTimer--;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)this.x, (int)this.y, this.width, this.height);
	}
	
	private void createMob() {
		mobIcon.setFitWidth(this.width);
		mobIcon.setFitHeight(this.height);
		mobIcon.setLayoutX(this.x);
		mobIcon.setLayoutY(this.y);
		gamePane.getChildren().add(mobIcon);
	}
}
