package application;

public class Camera {
	private int x, y;
	private Handler handler;
	private GameObject tempPlayer = null;
	
	public Camera(int x, int y, Handler handler) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		findPlayer();
	}
	
	public void findPlayer() { //cari player
		for(int i=0; i<handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				tempPlayer = handler.object.get(i);
				break;
			}
		}
	}
	
	public void camTick() { //besaran untuk translate anchorPane respective thd. player
		if(tempPlayer !=null) {
			x= (int) tempPlayer.x-GameWindow.WIDTH/2+GameWindow.CHAR_SIZE/2;
			y= (int) tempPlayer.y-GameWindow.HEIGHT/2+GameWindow.CHAR_SIZE/2;
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
