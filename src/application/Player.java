package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends GameObject {
	private ImageView PlayerIcon = new ImageView 
			("/resources/CSM-idle-right-GIF.gif");
	private Image leftPlayerIcon= new Image
			("/resources/CSM-walk-left-GIF-1.gif");
	private Image rightPlayerIcon = new Image
			("/resources/CSM-walk-right-1.gif");
	private Image idlePlayerIcon= new Image
			("/resources/CSM-idle-right-GIF.gif");
	
	private float MaxHP= 1000;
	private Rectangle HPBar, HPBG, EXPBar, EXPBG;
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Handler handler;
	private GameObject tempObj;
	
	private Boolean isLeftKeyPressed;
	private Boolean isRightKeyPressed;
	private Boolean isUpKeyPressed;
	private Boolean isDownKeyPressed;
	
	private Boolean restrictRight = false;
	private Boolean restrictLeft = false;
	private Boolean restrictUp = false;
	private Boolean restrictDown = false;
	
	private int velx=0, vely=0;
	
	private ID id;
	
	public Player(float x, float y, ID id, Handler handler, AnchorPane mainPane, Scene mainScene) {
		super(x, y, 1000, 0, 0, 72, 72, id);
		this.id = id;
		this.handler = handler;
		this.gamePane = mainPane;
		this.gameScene = mainScene;
		isLeftKeyPressed = isRightKeyPressed = isUpKeyPressed = isDownKeyPressed = false;
		createKeyListeners();
		createPlayer();
	}
	
	private void createKeyListeners() {
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.A) {
					isLeftKeyPressed = true;
				} else if (event.getCode() == KeyCode.D) {
					isRightKeyPressed = true;
				}
				if (event.getCode() == KeyCode.W) {
					isUpKeyPressed = true;
				} else if (event.getCode() == KeyCode.S) {
					isDownKeyPressed = true;
				}

			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.A) {
					isLeftKeyPressed = false;
				} else if (event.getCode() == KeyCode.D) {
					isRightKeyPressed = false;
				}
				if (event.getCode() == KeyCode.W) {
					isUpKeyPressed = false;
				} else if (event.getCode() == KeyCode.S) {
					isDownKeyPressed = false;
				}

			}
		});
	}
	
	private void collide() {
			for(int i=0; i<handler.object.size(); i++) {
				if(handler.object.get(i).getId() == ID.Zombie) {
					tempObj = handler.object.get(i);
					if(getBounds().getBoundsInParent().intersects(tempObj.getBounds().getBoundsInParent())){
						this.HP -= 1;
					}
				}
				else if(handler.object.get(i).getId()== ID.Block) {
					tempObj = handler.object.get(i);
					if(GB1().getBoundsInParent().intersects(tempObj.getBounds().getBoundsInParent())){
						if(velx>0) {
							velx=0;
							restrictRight = true;
						}else if(velx<0) {
							velx=0;
							restrictLeft = true;
						}
					}else {restrictRight = false; restrictLeft = false;}
					
					if(GB2().getBoundsInParent().intersects(tempObj.getBounds().getBoundsInParent())){
						if(vely>0) {
							vely=0;
							restrictUp = true;
						}else if(vely<0) {
							vely=0;
							restrictDown = true;
						}
					}else {restrictUp = false; restrictDown = false;}
					
				}
			}
	}
	
	@Override
	public void move() {
			HPBar.setWidth(HP*width/MaxHP);
			EXPBar.setWidth(EXP*1540/EXPCap);
			EXPBG.toFront();
			EXPBar.toFront();
			collide();
			if (isRightKeyPressed && !isLeftKeyPressed && !restrictRight) { //kanan
				PlayerIcon.setImage(rightPlayerIcon);
				if(this.x < (GameWindow.BGWIDTH- width)/2) {
					PlayerIcon.setLayoutX(PlayerIcon.getLayoutX() + 1.5);
					this.x += 1.5;
					HPBar.setX(this.x);
					HPBG.setX(this.x);
					EXPBG.setX(this.x-732);
					EXPBar.setX(this.x-732);
					this.velx= 4;
				}
			}
			if (isUpKeyPressed && !isDownKeyPressed && !restrictUp) { //atas
				PlayerIcon.setImage(rightPlayerIcon);
				if(this.y > -((GameWindow.BGHEIGHT-GameWindow.HEIGHT)/2 + height)+GameWindow.HEIGHT/2) {
					PlayerIcon.setLayoutY(PlayerIcon.getLayoutY() -1.5);
					this.y -= 1.5;
					HPBar.setY(this.y-15);
					HPBG.setY(this.y-15);
					EXPBG.setY(this.y+380);
					EXPBar.setY(this.y+380+5);
					this.vely= 4;
				}
			}
			if (isDownKeyPressed && !isUpKeyPressed && !restrictDown) { //bawah
				PlayerIcon.setImage(rightPlayerIcon);
				if(this.y+height < GameWindow.HEIGHT+((GameWindow.BGHEIGHT-GameWindow.HEIGHT)/2)-GameWindow.HEIGHT/2) {
					PlayerIcon.setLayoutY(PlayerIcon.getLayoutY() +1.5);
					this.y += 1.5;
					HPBar.setY(this.y-15);
					HPBG.setY(this.y-15);
					EXPBG.setY(this.y+380);
					EXPBar.setY(this.y+380+5);
					this.vely= -4;
				}
			}
			if (isLeftKeyPressed && !isRightKeyPressed && !restrictLeft) { //kiri
				PlayerIcon.setImage(leftPlayerIcon);
				if(this.x > -((GameWindow.BGWIDTH)/2 -GameWindow.WIDTH)-width/2) {
					PlayerIcon.setLayoutX(PlayerIcon.getLayoutX() -1.5);
					this.x -= 1.5;
					HPBar.setX(this.x);
					HPBG.setX(this.x);
					EXPBG.setX(this.x-732);
					EXPBar.setX(this.x-732);
					this.velx=-4;
				}
			}
			if (!isRightKeyPressed && !isLeftKeyPressed && !isDownKeyPressed && !isUpKeyPressed) {
				PlayerIcon.setImage(idlePlayerIcon);
			}
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
	
	public Rectangle GB1() {
		return new Rectangle((int)x+velx/2, (int)y+2, this.width, this.height-4);
	}
	public Rectangle GB2() {
		return new Rectangle((int)x+2, (int)y-vely/2, this.width-4, this.height);
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
		
		gamePane.getChildren().add(PlayerIcon);
		gamePane.getChildren().add(HPBG);
		gamePane.getChildren().add(HPBar);
		gamePane.getChildren().add(EXPBG);
		gamePane.getChildren().add(EXPBar);
	}
}
