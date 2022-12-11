package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Game extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GameWindow window = new GameWindow();
			primaryStage = window.getMainStage();
			primaryStage.setTitle("Zombie Game");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
