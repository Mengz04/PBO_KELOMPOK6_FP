package application;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubScene extends SubScene{
	private static final String BACKGROUND_IMAGE = "/resources/panel_brown.png";
	
	private boolean isHidden;

	public GameSubScene(int x, int y) {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));
		
		isHidden = true;
		
		setLayoutX(x);
		setLayoutY(y);
	}
	
	public void moveSubscene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.6));
		transition.setNode(this);
		
		if(isHidden) {
			transition.setToY(-840);
			isHidden = false;
		}else {
			transition.setToY(840);
			isHidden = true;
		}

		transition.play();
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
}
