package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
	@Override
	public void start(Stage stage) {
		try {
			MainMenuWindow window = new MainMenuWindow();
			stage = window.getMainStage();
			stage.setTitle("Zombie Game");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
