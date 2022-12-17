package application;

import javafx.scene.shape.Rectangle;

public abstract class GameObject {
	protected float HP=0;
	protected float damage=0;
	protected int EXP=0, EXPCap=0;
	protected float width, height;
	protected float x, y;
	protected float speed;
	protected ID id;
	
	public GameObject(float x, float y, float HP, float damage, int EXP, float width, float height, ID id) {
		this.x = x;
		this.y = y;
		this.HP = HP;
		this.damage = damage;
		this.width = width;
		this.height = height;
		this.EXP = EXP;
		if(id == ID.Player) {
			this.EXPCap = 100;
			this.speed = 0;
		}
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
	public void addSpeed(int value) {
		this.speed += value;
	}
	public int getEXP() {
		return this.EXP;
	}
	public int getEXPCap() {
		return this.EXPCap;
	}
	public void addEXPCap(int value) {
		this.EXPCap += value;
	}
	
	public void subHP(float value) {
		this.HP -= value;
	}
	public void addHP(float value) {
		this.HP += value;
	}
	
	public void setEXP(int value) {
		this.EXP = value;
	}
}
