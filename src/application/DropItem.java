package application;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DropItem extends GameObject{
	private ImageView DropIcon = new ImageView();
	private Image Drop = new Image
			("/resources/crateDrop.png");
	private Label dropLabel = new Label();
	private AnchorPane gamePane;
	private Handler handler;
	private int despawnTime = 2000;
	private String type;
	
	public DropItem(float x, float y, ID id, AnchorPane gamePane, Handler handler, String type) {
		super(x, y, 10, 0, 0, 50, 30, id);
		this.x = x;
		this.y = y;
		this.gamePane = gamePane;
		this.handler = handler;
		this.type = type;
		createDropItem();		
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public void move() {
		if(despawnTime <=0) {
			despawnProtocol();
		}
		despawnTime--;
	}
	public void despawnProtocol() {
		gamePane.getChildren().remove(DropIcon);
		gamePane.getChildren().remove(dropLabel);
		handler.removeTask.add(this);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
	
	private void createDropItem(){
		DropIcon.setImage(Drop);
		DropIcon.setFitWidth(this.width);
		DropIcon.setFitHeight(this.height);
		DropIcon.setLayoutX(this.x);
		DropIcon.setLayoutY(this.y);
		
		switch(type) {
		case "health": dropLabel.setText("Health +100"); break;
		case "speed": dropLabel.setText("Speed +1"); break;
		case "invincibility": dropLabel.setText("Invincibility"); break;
		case "GDevil": dropLabel.setText("Gun Devil Upgrade"); break;
		default: dropLabel.setText("Bomb Devil Upgrade");
		}
		
		dropLabel.setAlignment(Pos.CENTER);
		dropLabel.setTextFill(Color.WHITE);
		dropLabel.setPrefWidth(150);
		dropLabel.setPrefHeight(30);
		dropLabel.setLayoutX(this.x-50);
		dropLabel.setLayoutY(this.y-35);
		
		gamePane.getChildren().add(DropIcon);
		gamePane.getChildren().add(dropLabel);
	}
}
