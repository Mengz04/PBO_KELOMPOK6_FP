package application;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameWindow{
	
	public static final int HEIGHT = 600;
	public static final int WIDTH = 800;
	public static final int CHAR_SIZE = 72;
	
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private GameObject tempPlayer = null;
	
	private Boolean isRunning = false;
	
	private AnimationTimer gameTimer;
	private Handler handler;
	private Camera cam;
	private int NAcooldown = 100;
	
	public GameWindow() { //new game base instruction
		isRunning = true;
		initializeCanvas();
		createObjects();
		createGameLoop();
	}
	
	private void createObjects() { //spawn objects
		handler = new Handler();
		tempPlayer = new Player((WIDTH/2)-(CHAR_SIZE/2), (HEIGHT/2)-(CHAR_SIZE/2), ID.Player, handler, gamePane, gameScene);
		handler.addObject(tempPlayer);
		cam = new Camera(0,0, handler);
		handler.addObject(new Mob(100, 100, ID.Zombie, gamePane, handler));
	}
	
	private void initializeCanvas() { //setup window, canvas, dll
		gamePane = new AnchorPane();
		gamePane.setStyle("-fx-background-color: lightblue;");
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameScene.setOnMouseClicked(mouseHandler);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
	}
	
	private void createGameLoop() {
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long now) { //Game tick method
				if(tempPlayer.HP <= 0) { // player mati
					isRunning = false;
				}
				if(isRunning) {
					gamePane.setLayoutX(gamePane.getLayoutX()+cam.getX());
					gamePane.setLayoutY(gamePane.getLayoutY()+cam.getY());
					handler.move();	
					cam.camTick();
					if(NAcooldown>0)NAcooldown--;
					gamePane.setLayoutX(gamePane.getLayoutX()-cam.getX());
					gamePane.setLayoutY(gamePane.getLayoutY()-cam.getY());
				}
			}
		};

		gameTimer.start();
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
	        	NAcooldown = 100;
        	}
        }
    };

	public Stage getMainStage() {
		return gameStage;
	}
}
