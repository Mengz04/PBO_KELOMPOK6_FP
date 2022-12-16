package application;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

import javafx.scene.canvas.GraphicsContext;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public ConcurrentLinkedQueue<GameObject> removeTask = new ConcurrentLinkedQueue<GameObject>();//
	public ConcurrentLinkedQueue<GameObject> addObjTask = new ConcurrentLinkedQueue<GameObject>();
	
	public void move() {
		for(GameObject tempObject : object) {
			if(tempObject != null) {
				tempObject.move();
			}
		}
	}
	
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}
	
	public void addRemoveTask(GameObject tempObject) {//
		removeTask.add(tempObject);
	}
}
