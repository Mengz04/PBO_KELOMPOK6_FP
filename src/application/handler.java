package application;

import java.util.LinkedList;

import javafx.scene.canvas.GraphicsContext;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void move() {
		for(GameObject tempObject : object) {
			tempObject.move();
		}
	}
	
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}
}
