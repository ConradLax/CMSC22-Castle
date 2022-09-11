package castle;

import javafx.scene.image.Image;

public class FireSpirit extends Element{
	private int speed;
	public boolean alive;
	public int move;
	private int direction;
	public int xRightBoundary;
	public int xLeftBoundary;
	
	public final static Image FS_LEFT = new Image("images/fsleft.png",FireSpirit.FS_WIDTH,FireSpirit.FS_WIDTH,false,false);
	public final static Image FS_RIGHT = new Image("images/fsright.png",FireSpirit.FS_WIDTH,FireSpirit.FS_WIDTH,false,false);
	private final static int FS_WIDTH = 80;

	public FireSpirit(int x, int y){
		super(x,y);
		this.speed = 1;
		this.alive = true;
		this.direction=1;
		this.xLeftBoundary=0;
		this.xRightBoundary=GameStage.WINDOW_WIDTH-80;
		
		this.loadImage(FireSpirit.FS_RIGHT);
	}
	public void move() {
//		if((this.x+this.dx)>0 && (this.x+this.dx)<1300)
    	this.x += this.dx;
//		if((this.dy+this.y)>0 && (this.y+this.dy)<760)
    	this.y += this.dy;
}
	//getters
	public boolean isAlive(){
		if(this.alive) return true;
		return false;
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
			this.loadImage(FireSpirit.FS_RIGHT);
		} else {
			this.loadImage(FireSpirit.FS_LEFT);
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
