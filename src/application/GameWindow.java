package application;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/*
note:
 GreenContainerHorizontal: width = 135 ;Height = 97
 GreenContainerVertical: width = 97; Height = 135
 
 
*/
public class GameWindow{
	
	public static final int HEIGHT = 1080;
	public static final int WIDTH = 1920;
	
	public static final int BGHEIGHT = 3240;
	public static final int BGWIDTH = 5760;
	
	public static final int CHAR_SIZE = 72;
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private GameObject tempPlayer = null;
	private GameObject tempMob = null;
	private KeyInput inputKey;
	
	private Rectangle EXPBar;
	
	private Boolean isRunning = false;
	
	private AnimationTimer gameTimer;
	private Handler handler;
	private Camera cam;
	private int NAcooldownBUF = 100;
	private int NAcooldown = NAcooldownBUF;
	private int level = 0;
	private int spawnInterval = 600;
	private int spawner = spawnInterval;
	private int gacha;
	
	public GameWindow() { //new game base instruction
		isRunning = true;
		initializeCanvas();
		createObjects();
		createGameLoop();
	}
	
	private void createObjects() { //spawn objects
		handler = new Handler();
		inputKey = new KeyInput(gameScene);
		handler.addObject(new Block(0, 0, ID.Block, 135, 97,gamePane,handler, "GreenContainerHorizontal"));
		tempPlayer = new Player((WIDTH/2)-(CHAR_SIZE/2), (HEIGHT/2)-(CHAR_SIZE/2), ID.Player, handler, gamePane, gameScene, inputKey);
		handler.addObject(tempPlayer);
		cam = new Camera(0,0, handler);
	}
	
	private void initializeCanvas() { //setup window, canvas, dll
		gamePane = new AnchorPane();
		ImageView backgroundImage = new ImageView
				("/resources/warehouse.png");
		backgroundImage.setFitWidth(BGWIDTH);
		backgroundImage.setFitHeight(BGHEIGHT);
		backgroundImage.setLayoutX(-((backgroundImage.getFitWidth()/2)-WIDTH/2));
		backgroundImage.setLayoutY(-((backgroundImage.getFitHeight()/2)-HEIGHT/2));
		gamePane.getChildren().add(backgroundImage);
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameScene.setOnMouseClicked(mouseHandler);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		Rectangle EXPBG = new Rectangle(0, 1055, 1920, 25);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) { //Game tick method
				if(tempPlayer.HP <= 0) { // player mati
					isRunning = false;
				}
				if(isRunning) {
					if(tempPlayer.getEXPCap() == tempPlayer.getEXP()) { //level up condition
						tempPlayer.addEXPCap(50);
						level++;
						tempPlayer.setEXP(0);
						if(spawnInterval>190) { //cap minimum spawnInterval = 200
							spawnInterval -=10;
						}
					}
					if(spawner<=0) { //spawn mob generator
						int position = getRandomNumber(1,4);
						//kuadran adjustment
						if(position == 1) { //kuadran 1 thd player
							if(tempPlayer.x<=2094 && tempPlayer.y>=-105) {position = 1;}
							else if(tempPlayer.x<=2094) {position = 4;}
							else if(tempPlayer.y>=-105) {position = 2;}
							else {position = 3;}
						}
						if(position == 2) { //kuadran 2 thd player
							if(tempPlayer.x>=-250 && tempPlayer.y>=-105) {position = 2;}
							else if(tempPlayer.x>=-250) {position = 3;}
							else if(tempPlayer.y>=-105) {position = 1;}
							else {position = 4;}
						}
						if(position == 3) { //kuadran 2 thd player
							if(tempPlayer.x>=-250 && tempPlayer.y<=1215) {position = 3;}
							else if(tempPlayer.x>=-250) {position = 2;}
							else if(tempPlayer.y<=1215) {position = 4;}
							else {position = 1;}
						}
						if(position == 4) { //kuadran 1 thd player
							if(tempPlayer.x<=2094 && tempPlayer.y<=1215) {position = 4;}
							else if(tempPlayer.x<=2094) {position = 1;}
							else if(tempPlayer.y<=1215) {position = 3;}
							else {position = 2;}
						}
						//spawn correct kuadran
						if(position == 1) { //kuadran 1 thd player
							handler.addObject(new Mob(tempPlayer.getX()+(WIDTH/2+100),
								tempPlayer.getY()-(HEIGHT/2+100), ID.Zombie, gamePane, handler));
						}
						if(position == 2) { //kuadran 2 thd player
								handler.addObject(new Mob(tempPlayer.getX()-(WIDTH/2+100),
									tempPlayer.getY()-(HEIGHT/2+100), ID.Zombie, gamePane, handler));
						}
						if(position == 3) { //kuadran 3 thd player
							handler.addObject(new Mob(tempPlayer.getX()-(WIDTH/2+100),
									tempPlayer.getY()+(HEIGHT/2+100), ID.Zombie, gamePane, handler));
						}
						if(position == 4){ //kuadran 4 thd player
							handler.addObject(new Mob(tempPlayer.getX()+(WIDTH/2+100),
									tempPlayer.getY()+(HEIGHT/2+100), ID.Zombie, gamePane, handler));
						}
							
						spawner = spawnInterval;
					}
					
					spawner--;
					if(NAcooldown>0)NAcooldown--;
					gamePane.setLayoutX(gamePane.getLayoutX()+cam.getX());
					gamePane.setLayoutY(gamePane.getLayoutY()+cam.getY());
					while(!handler.removeTask.isEmpty()) {
						if(handler.removeTask.peek().getId()== ID.Zombie) {
							tempMob = handler.removeTask.peek();
							gacha = getRandomNumber(1,10);
							if(gacha == 1) {
								gacha = getRandomNumber(1,5);
								switch (gacha) {
									case 1: handler.addObjTask.add(new DropItem(tempMob.x+tempMob.width/2 -25, tempMob.y+tempMob.height/2, ID.Drop, gamePane, handler, "health")); break;
									case 2: handler.addObjTask.add(new DropItem(tempMob.x+tempMob.width/2 -25, tempMob.y+tempMob.height/2, ID.Drop, gamePane, handler, "speed"));break;
									case 3: handler.addObjTask.add(new DropItem(tempMob.x+tempMob.width/2 -25, tempMob.y+tempMob.height/2, ID.Drop, gamePane, handler, "invincibility"));break;
									case 4: handler.addObjTask.add(new DropItem(tempMob.x+tempMob.width/2 -25, tempMob.y+tempMob.height/2, ID.Drop, gamePane, handler, "GDevil"));break;
									default: handler.addObjTask.add(new DropItem(tempMob.x+tempMob.width/2 -25, tempMob.y+tempMob.height/2, ID.Drop, gamePane, handler, "BDevil"));
								}
							}
							handler.addObject(new BloodEXP(tempMob.x+tempMob.width/2, tempMob.y+tempMob.height, ID.BloodEXP, gamePane, handler));
						}
						handler.removeObject(handler.removeTask.poll());
					}
					while(!handler.addObjTask.isEmpty()) {
						handler.addObject(handler.addObjTask.poll());
					}
					handler.move();
					cam.camTick();
					gamePane.setLayoutX(gamePane.getLayoutX()-cam.getX());
					gamePane.setLayoutY(gamePane.getLayoutY()-cam.getY());
				}
			}
		};

		gameTimer.start();
	}
	
	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {
		
        @Override
        public void handle(MouseEvent e) {
        	if(NAcooldown == 0) {
	        	double xOrientation = e.getX()-tempPlayer.getX()+cam.getX();
	        	double yOrientation = e.getY()-tempPlayer.getY()+cam.getY()-50;
	
	        	if(xOrientation>0 && yOrientation<0) {
	        		handler.addObject(new Pochita(tempPlayer.getX(), tempPlayer.getY(), ID.Weapon, handler, gamePane, "top-right"));
	        	}
	        	else if(xOrientation>0 && yOrientation>0) {
	        		handler.addObject(new Pochita(tempPlayer.getX(), tempPlayer.getY(), ID.Weapon, handler, gamePane, "bot-right"));
	        	}
	        	if(xOrientation<0 && yOrientation<0) {
	        		handler.addObject(new Pochita(tempPlayer.getX(), tempPlayer.getY(), ID.Weapon, handler, gamePane, "top-left"));
	        	}
	        	else if(xOrientation<0 && yOrientation>0) {
	        		handler.addObject(new Pochita(tempPlayer.getX(), tempPlayer.getY(), ID.Weapon, handler, gamePane, "bot-left"));
	        	}
	        	NAcooldown = NAcooldownBUF;
        	}
        }
     
    };

	public Stage getMainStage() {
		return gameStage;
	}
		
}
