package application;

import javafx.scene.shape.Rectangle;

public abstract class GameObject {
	protected float HP;
	protected float damage;
	protected int EXP;
	protected float width, height;
	protected float x, y;
	protected ID id;
	
	public GameObject(float x, float y, float HP, float damage, int EXP, float width, float height, ID id) {
		this.x = x;
		this.y = y;
		this.HP = HP;
		this.damage = damage;
		this.width = width;
		this.height = height;
		this.EXP = EXP;
		this.id = id;
	}
	
	public abstract void move();
	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	public float getDamage() {
		return damage;
	}
	public void addEXP(int value) {
		this.EXP += value;
	}
	
	public int getEXP() {
		return this.EXP;
	}
	
	public void setEXP(int value) {
		this.EXP = value;
	}
}
