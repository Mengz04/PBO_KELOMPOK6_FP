package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class BloodEXP extends GameObject{
	private ImageView EXPIcon = new ImageView 
			("D:\\Kuliah\\sems 3\\PBO\\gem\\attempt2fx\\src\\resources\\blood.gif");
	private AnchorPane gamePane;
	private Handler handler;
	private GameObject tempPlayer = null;
	
	public BloodEXP(float x, float y, ID id, AnchorPane gamePane, Handler handler) {
		super(x, y, 0, 0, 0, 15, 25, id);
		this.x = x;
		this.y = y;
		this.gamePane = gamePane;
		this.handler = handler;
		createBloodEXP();
		findPlayer();
		
	}

	public void findPlayer() {
		for(int i=0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	
	@Override
	public void move() {
		if(getBounds().getBoundsInParent().intersects(tempPlayer.getBounds().getBoundsInParent())) {
			tempPlayer.addEXP(5);
			gamePane.getChildren().remove(EXPIcon);
			handler.removeTask.add(this);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
	private void createBloodEXP() {
		EXPIcon.setFitWidth(this.width);
		EXPIcon.setFitHeight(this.height);
		EXPIcon.setLayoutX(this.x);
		EXPIcon.setLayoutY(this.y);
		gamePane.getChildren().add(EXPIcon);
	}
}
