package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainMenuWindow {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private static final int MENU_BUTTONS_START_X = 100;
	private static final int MENU_BUTTONS_START_Y = 150;
	
	List<MenuButton> menuButtons;
	
	public MainMenuWindow() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		menuButtons = new ArrayList<>();
		createButtons();
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButtons(MenuButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_X + menuButtons.size() * 100);
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
	}

	private void createCreditButton() {
		MenuButton creditButton = new MenuButton("CREDITS");
		addMenuButtons(creditButton);
	}
	private void createExitButton() {
		MenuButton exitButton = new MenuButton("EXIT");
		addMenuButtons(exitButton);
	}


}
