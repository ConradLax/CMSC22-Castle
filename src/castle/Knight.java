package castle;

import javafx.scene.image.Image;

public class Knight extends Element{
	private int speed;
	public boolean alive;
	public int move;
	public int direction;
	public int xRightBoundary;
	public int xLeftBoundary;
	
	public final static Image KNIGHT_LEFT = new Image("images/knightleft.png",Knight.KNIGHT_WIDTH,Knight.KNIGHT_WIDTH,false,false);
	public final static Image KNIGHT_RIGHT = new Image("images/knightright.png",Knight.KNIGHT_WIDTH,Knight.KNIGHT_WIDTH,false,false);
	private final static int KNIGHT_WIDTH = 80;

	public Knight(int x, int y){
		super(x,y);
		this.speed = 1;
		this.alive = true;
		this.direction=1;
		this.xLeftBoundary=30;
		this.xRightBoundary=GameStage.WINDOW_WIDTH-80;
		
		this.loadImage(Knight.KNIGHT_RIGHT);
	}
	
	//getters
	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	} 
	public void move() {
		if((this.x+this.dx)>0 && (this.x+this.dx)<1300)
    	this.x += this.dx;
		if((this.dy+this.y)>0 && (this.y+this.dy)<760)
    	this.y += this.dy;
}
	public void die(){
    	this.alive = false;
    }
	
	public void moveLeft() {
    	this.x -= this.speed;
	}
	
	public void moveRight() {
    	this.x += this.speed;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	public void setDirection(int direction) {
		this.direction=direction;
	}
	
	public void changeImage(int direction) {
		if(direction==1) {
			this.loadImage(Knight.KNIGHT_RIGHT);
		} else {
			this.loadImage(Knight.KNIGHT_LEFT);
		}
	}
	
	public void setNewBoundary(int left, int right) {
		this.xLeftBoundary=left;
		this.xRightBoundary=right;
	}

	public int getxRightBoundary() {
		return xRightBoundary;
	}

	public void setxRightBoundary(int xRightBoundary) {
		this.xRightBoundary = xRightBoundary;
	}

	public int getxLeftBoundary() {
		return xLeftBoundary;
	}

	public void setxLeftBoundary(int xLeftBoundary) {
		this.xLeftBoundary = xLeftBoundary;
	}
	
}
