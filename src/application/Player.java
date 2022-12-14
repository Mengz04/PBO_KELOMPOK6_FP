package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Player extends GameObject {
	private ImageView PlayerIcon = new ImageView 
			("/resources/CSM-idle-right-GIF.gif");
	private Image leftPlayerIcon= new Image
			("/resources/CSM-walk-left-GIF-1.gif");
	private Image rightPlayerIcon = new Image
			("/resources/CSM-walk-right-1.gif");
	private Image idlePlayerIcon= new Image
			("/resources/CSM-idle-right-GIF.gif");
	private Label levelLabel = new Label("");
	private ImageView bloodSplat = new ImageView
			("/resources/bloodSplat.png");
	
	private float MaxHP= 1000;
	public int level =1;
	private Rectangle HPBar, HPBG, EXPBar, EXPBG;
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Handler handler;
	private GameObject tempObj;
	private DropItem tempDrop;
	private GunDevil GD = null;
	private BombDevil BD = null;
	private KeyInput inputKey;
	
	private Boolean invincible = false;
	
	private int velx=0, vely=0;
	private int speedDuration = 500;
	private int invincibleDuration = 500;
	
	private ID id;
	
	public Player(float x, float y, ID id, Handler handler, AnchorPane mainPane, Scene mainScene, KeyInput inputKey) {
		super(x, y, 1000, 0, 0, 72, 72, id);
		this.id = id;
		this.handler = handler;
		this.gamePane = mainPane;
		this.gameScene = mainScene;
		this.inputKey = inputKey;
		createPlayer();
	}
	
	private void collide() {
			for(int i=0; i<handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.Zombie) {
					tempObj = handler.object.get(i);
					if(getBounds().getBoundsInParent().intersects(tempObj.getBounds().getBoundsInParent()) && !invincible){
						this.HP -= 1;
					}
				}
				else if(handler.object.get(i).getId() == ID.Drop) {
					tempDrop = (DropItem) handler.object.get(i);
					if(getBounds().getBoundsInParent().intersects(tempDrop.getBounds().getBoundsInParent())){
						if(tempDrop.getType() == "health") {addHP(100);}
						else if(tempDrop.getType() == "speed") {addSpeed(1);}
						else if(tempDrop.getType() == "invincibility") {invincible = true;}
						else if (tempDrop.getType() == "GDevil") {
							if(GD == null) {
								GD = new GunDevil(x, y, ID.Talent, handler, gamePane);
								handler.addObjTask.add(GD);
							}else {
								GD.cdDecrease(50);
							}
						}
						else {
							if(BD == null) {
								BD = new BombDevil(x, y, ID.Talent, handler, gamePane);
								handler.addObjTask.add(BD);
							}else {
								BD.cdDecrease(100);
							}
						}
						tempDrop.despawnProtocol();
					}
				}
				else if(handler.object.get(i).getId()== ID.Block) {
					tempObj = handler.object.get(i);
					if(GB1().getBoundsInParent().intersects(tempObj.getBounds().getBoundsInParent())){
						if(velx>0) {
							this.x = tempObj.getX() - this.width;
							this.velx = 0;
						}else if(velx<0) {
							this.x = tempObj.getX() + tempObj.getWidth();
							velx=0;
						}
					}
					
					if(GB2().getBoundsInParent().intersects(tempObj.getBounds().getBoundsInParent())){
						if(vely>0) {
							this.y = tempObj.getY() + tempObj.getHeight();
							vely=0;
						}else if(vely<0) {
							this.y = tempObj.getY() - this.height;
							vely=0;
						}
					}
				}
			}
	}
	
	@Override
	public void move() {
			//EXP bar adjustment
			HPBar.setWidth(HP*width/MaxHP);
			EXPBar.setWidth(EXP*1540/EXPCap);
			EXPBG.toFront();
			EXPBar.toFront();
			bloodSplat.toFront();
			levelLabel.toFront();
			levelLabel.setText(String.valueOf(level));
			
			//speed buf control
			if(speed>0 && speedDuration>0) {speedDuration--;}
			else if(speed>0 && speedDuration<=0) {
				speed = 0;
				speedDuration = 500;
			}
			//invincible buf control
			if(invincible && invincibleDuration>0) {invincibleDuration--;}
			else if(invincible && invincibleDuration<=0) {
				invincible = false;
				invincibleDuration = 500;
			}
			
			//movement
			if (inputKey.isRightKeyPressed && !inputKey.isLeftKeyPressed) { //kanan
				PlayerIcon.setImage(rightPlayerIcon);
				if(this.x < (GameWindow.BGWIDTH- width)/2) {
					this.x += (1.5 + speed);
					this.velx= 4;
				}
			}
			if (inputKey.isUpKeyPressed && !inputKey.isDownKeyPressed) { //atas
				PlayerIcon.setImage(rightPlayerIcon);
				if(this.y > -((GameWindow.BGHEIGHT-GameWindow.HEIGHT)/2 + height)+GameWindow.HEIGHT/2) {
					this.y -= (1.5 + speed);
					this.vely= 4;
				}
			}
			if (inputKey.isDownKeyPressed && !inputKey.isUpKeyPressed) { //bawah
				PlayerIcon.setImage(rightPlayerIcon);
				if(this.y+height < GameWindow.HEIGHT+((GameWindow.BGHEIGHT-GameWindow.HEIGHT)/2)-GameWindow.HEIGHT/2) {
					this.y += (1.5 + speed);
					this.vely= -4;
				}
			}
			if (inputKey.isLeftKeyPressed && !inputKey.isRightKeyPressed) { //kiri
				PlayerIcon.setImage(leftPlayerIcon);
				if(this.x > -((GameWindow.BGWIDTH)/2 -GameWindow.WIDTH)-width/2) {
					this.x -= (1.5 + speed);
					this.velx=-4;
				}
			}
			collide();
			PlayerIcon.setLayoutX(this.x);
			PlayerIcon.setLayoutY(this.y);
			HPBar.setX(this.x);
			HPBG.setX(this.x);
			EXPBG.setX(this.x-732);
			EXPBar.setX(this.x-732);
			HPBar.setY(this.y-15);
			HPBG.setY(this.y-15);
			EXPBG.setY(this.y+380);
			EXPBar.setY(this.y+380+5);
			levelLabel.setLayoutX(this.x-750+60);
			levelLabel.setLayoutY(this.y+270+60);
			bloodSplat.setLayoutX(this.x-750);
			bloodSplat.setLayoutY(this.y+270);
			if (!inputKey.isRightKeyPressed && !inputKey.isLeftKeyPressed && !inputKey.isDownKeyPressed && !inputKey.isUpKeyPressed) {
				PlayerIcon.setImage(idlePlayerIcon);
			}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
	
	public Rectangle GB1() {
		return new Rectangle((int)x+velx, (int)y+5, this.width, this.height-10);
	}
	public Rectangle GB2() {
		return new Rectangle((int)x+5, (int)y-vely, this.width-10, this.height);
	}
	
	private void createPlayer() {
		PlayerIcon.setImage(idlePlayerIcon);
		PlayerIcon.setFitWidth(this.width);
		PlayerIcon.setFitHeight(this.height);
		PlayerIcon.setLayoutX(this.x);
		PlayerIcon.setLayoutY(this.y);
		
		HPBG = new Rectangle(this.x, this.y-15, this.width, 5);
		HPBG.setFill(Color.RED);
		
		HPBar = new Rectangle(this.x, this.y-15, this.width, 5);
		HPBar.setFill(Color.GREEN);
		
		EXPBG = new Rectangle(this.x-732, this.y+380, 1540, 35);
		EXPBG.setFill(Color.BLACK);
		
		EXPBar = new Rectangle(this.x-732, this.y+380+5, 1540, 25);
		EXPBar.setFill(Color.RED);
		
		levelLabel.setText(String.valueOf(level));
		levelLabel.setAlignment(Pos.BASELINE_LEFT);
		levelLabel.setTextFill(Color.WHITE);
		levelLabel.setFont(new Font("Arial", 50));
		levelLabel.setPrefWidth(200);
		levelLabel.setPrefHeight(80);
		levelLabel.setLayoutX(this.x-750+60);
		levelLabel.setLayoutY(this.y+270+60);
		
		
		bloodSplat.setFitWidth(200);
		bloodSplat.setFitHeight(200);
		bloodSplat.setLayoutX(this.x-750);
		bloodSplat.setLayoutY(this.y+270);
		
		gamePane.getChildren().add(PlayerIcon);
		gamePane.getChildren().add(HPBG);
		gamePane.getChildren().add(HPBar);
		gamePane.getChildren().add(EXPBG);
		gamePane.getChildren().add(EXPBar);
		gamePane.getChildren().add(bloodSplat);
		gamePane.getChildren().add(levelLabel);
	}
}
