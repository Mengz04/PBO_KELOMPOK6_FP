package application;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenuWindow {
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 900;

	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;

	private GameSubScene creditSubScene;

	private static final int MENU_BUTTONS_START_X = 1200;
	private static final int MENU_BUTTONS_START_Y = 150;
	
	private Boolean creditSubSceneHidden = true;

	private Text titleText;
	private Text contributorText;
	private Text specialThanksText;
	String newLine = System.getProperty("line.separator");
	private String title = "FINAL PROJECT\nPEMROGRAMAN BERORIENTASI OBJEK";
	private String contributor = "Anggota:\n1. Muhammad Rafi Insan Fillah / 5025211169\n2. Ahda Filza Ghaffaru / 5025211144\n3. Aurelio Killian Lexi Verrill / 5025211126";
	private String specialThanks = "\"Special thanks to youtube.com/@RealTutsGML for providing tutorial video\"";
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
		createCreditSubScene();
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

	private void createCreditSubScene() {
		creditSubScene = new GameSubScene(WIDTH / 2 - 345, HEIGHT + 100);
		titleText = new Text(title);
		contributorText = new Text(contributor);
		specialThanksText = new Text(specialThanks);
		
		creditSubScene.getPane().getChildren().addAll(titleText, contributorText, specialThanksText);
		titleText.setWrappingWidth(550);
		contributorText.setWrappingWidth(550);
		specialThanksText.setWrappingWidth(550);
		
		titleText.setTextAlignment(TextAlignment.CENTER);
		titleText.setX(25);
		titleText.setY(55);
		
		contributorText.setX(45);
		contributorText.setY(150);
		
		specialThanksText.setTextAlignment(TextAlignment.CENTER);
		specialThanksText.setX(25);
		specialThanksText.setY(300);
		
		titleText.setFont(Font.font("Helvetica", 23));
		contributorText.setFont(Font.font("Helvetica", 23));
		specialThanksText.setFont(Font.font("Helvetica", 23));

		mainPane.getChildren().add(creditSubScene);
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
				if(!creditSubSceneHidden)creditSubScene.moveSubscene();
				GameWindow game = new GameWindow();
				game.createNewGame(mainStage);
			}
		});
	}

	private void createCreditButton() {
		MenuButton creditButton = new MenuButton("CREDITS");
		addMenuButtons(creditButton);

		creditButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				creditSubSceneHidden = !creditSubSceneHidden;
				creditSubScene.moveSubscene();
			}
		});
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
		Image backgroundImage = new Image("/resources/chainsawman_bg.png", 1600, 900, true, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

}
