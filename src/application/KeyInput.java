package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyInput {
	public Boolean isLeftKeyPressed = false;
	public Boolean isRightKeyPressed = false;
	public Boolean isUpKeyPressed = false;
	public Boolean isDownKeyPressed = false;
	public Boolean isESCKeyPressed = false;
	
	private Scene gameScene;
	
	public KeyInput(Scene gameScene) {
		this.gameScene = gameScene;
		createKeyListeners();
	}
	
	public void createKeyListeners(){
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
				if (event.getCode() == KeyCode.ESCAPE) {
					isESCKeyPressed = true;
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
				if (event.getCode() == KeyCode.ESCAPE) {
					isESCKeyPressed = false;
				}
	
			}
		});
	}
}
