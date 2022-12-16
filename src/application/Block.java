package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Block extends GameObject{
	private String BlockType;
	private Image GreenContainerHorizontal =  new Image
			("/resources/container-green-horizontal.png");
	private Image GreenContainerVertical = new Image
			("/resources/container-green-vertical.png");
	
	private ImageView BlockIcon = new ImageView(GreenContainerHorizontal);
	private AnchorPane gamePane;
	private Handler handler;
	private GameObject tempPlayer = null;
	
	public Block(float x, float y, ID id, float width, float height, AnchorPane gamePane, Handler handler, String BlockType) {
		super(x, y, 0, 0, 0, width, height, id);
		this.x = x;
		this.y = y;
		this.BlockType = BlockType;
		this.gamePane = gamePane;
		this.handler = handler;
		createBlock();
	}
	
	@Override
	public void move() {
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.width, this.height);
	}
	private void createBlock() {
		if(BlockType == "GreenContainerHorizontal") {
			BlockIcon.setImage(GreenContainerHorizontal);
		}
		else if(BlockType == "GreenContainerVertical") {
			BlockIcon.setImage(GreenContainerVertical);
		}
		BlockIcon.setFitWidth(this.width);
		BlockIcon.setFitHeight(this.height);
		BlockIcon.setLayoutX(this.x);
		BlockIcon.setLayoutY(this.y);
		gamePane.getChildren().add(BlockIcon);
	}
}
