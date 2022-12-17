package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class MainMenuWindow {
	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private static final int MENU_BUTTONS_START_X = 1700;
	private static final int MENU_BUTTONS_START_Y = 150;
	
	List<MenuButton> menuButtons;
	
	public MainMenuWindow() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		menuButtons = new ArrayList<>();
		mainStage.setFullScreen(true);
		mainStage.setResizable(false);
		createButtons();
		createBackground();
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButtons(MenuButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButtons() {
		createStartButton();
		createCreditButton();
		createExitButton();
	}

	private void createStartButton() {
		MenuButton starButton = new MenuButton("START");
		addMenuButtons(starButton);
		
		starButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				GameWindow game = new GameWindow();
				game.createNewGame(mainStage);
			}
		});
	}

	private void createCreditButton() {
		MenuButton creditButton = new MenuButton("CREDITS");
		addMenuButtons(creditButton);
		
		
	}
	private void createExitButton() {
		MenuButton exitButton = new MenuButton("EXIT");
		addMenuButtons(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.close();
			}

		});
	}

	private void createBackground() {
		Image backgroundImage = new Image("/resources/chainsawman_bg.png", 1920, 1080, true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

}
