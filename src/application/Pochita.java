package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Pochita extends GameObject{
	private ImageView pochitaIcon = new ImageView 
			("/resources/pochita-right.gif");
	private Image rightTopPochitaIcon = new Image 
			("/resources/pochita-right.gif");
	private Image rightBotPochitaIcon = new Image 
			("/resources/pochita-rightDown.gif");
	private Image leftTopPochitaIcon = new Image 
			("/resources/pochita-left.gif");
	private Image leftBotPochitaIcon = new Image 
			("/resources/pochita-leftDown.gif");
	private Handler handler;
	private AnchorPane gamePane;
	private GameObject tempPlayer=null;
	
	private String orientation;
	private int time = 50;
	
	public Pochita(float x, float y, ID id, Handler handler, AnchorPane gamePane, String orientation) {
		super(x, y, 0, 2, 0, 90, 90, id);
		this.handler = handler;
		this.id = id;
		this.gamePane = gamePane;
		this.orientation = orientation;
		createPochita();
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
			if(orientation == "top-right") {
				pochitaIcon.setImage(rightTopPochitaIcon);
				pochitaIcon.setLayoutX(tempPlayer.getX()+50);
				x = tempPlayer.getX()+50;
				
				pochitaIcon.setLayoutY(tempPlayer.getY()-50);
				y = tempPlayer.getY()-50;
			}
			else if(orientation == "bot-right") {
				pochitaIcon.setImage(rightBotPochitaIcon);
				pochitaIcon.setLayoutX(tempPlayer.getX()+50);
				x = tempPlayer.getX()+50;
				
				pochitaIcon.setLayoutY(tempPlayer.getY()+50);
				y = tempPlayer.getY()+50;
			}
			else if(orientation == "top-left") {
				pochitaIcon.setImage(leftTopPochitaIcon);
				pochitaIcon.setLayoutX(tempPlayer.getX()-50);
				x = tempPlayer.getX()-50;
				
				pochitaIcon.setLayoutY(tempPlayer.getY()-50);
				y = tempPlayer.getY()-50;
			}
			else if(orientation == "bot-left") {
				pochitaIcon.setImage(leftBotPochitaIcon);
				pochitaIcon.setLayoutX(tempPlayer.getX()-50);
				x = tempPlayer.getX()-50;
				
				pochitaIcon.setLayoutY(tempPlayer.getY()+50);
				y = tempPlayer.getY()+50;
			}
			time -=1;
			if(time <= 0) {
				gamePane.getChildren().remove(pochitaIcon);
				handler.addRemoveTask(this);
			}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);
	}
	
	private void createPochita() {
		pochitaIcon.setFitWidth(this.width);
		pochitaIcon.setFitHeight(this.height);
		pochitaIcon.setLayoutX(this.x);
		pochitaIcon.setLayoutY(this.y);
		gamePane.getChildren().add(pochitaIcon);
	}

}
