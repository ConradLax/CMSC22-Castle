package castle;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class HoneyJar extends Element{
	private GraphicsContext gc;
	private Scene theScene;
	private boolean falling=true;
	private int gravity=2;
	private final int MAX_SPEED=7;
	public final static Image HONEYJAR = new Image("images/honeyjar.png",50,50,false,false);
	
	HoneyJar(GraphicsContext gc, Scene theScene,int x,int y,Image img){
		super(x,y);
		this.x=x;
		this.y=y;
		this.gc = gc;
		this.theScene = theScene;
		this.loadImage(img);
	}
	public Rectangle2D getBoundsTop() {
		
		return new Rectangle2D(this.x,this.y,this.img.getWidth()-10,this.img.getHeight()/2);
	}
	public Rectangle2D getBoundsBottom() {

		return new Rectangle2D(this.x+15,this.y+(this.img.getHeight()/2),this.img.getWidth()-10,this.img.getHeight()/2);
	}
	public Rectangle2D getBoundsLeft() {
		return new Rectangle2D(this.x,this.y+7,10,this.img.getHeight());
	}
	public Rectangle2D getBoundsRight() {
		return new Rectangle2D(this.x+(this.img.getWidth()-10),this.y+7,20,this.img.getHeight());
	}
	
	public void move() {
		if((this.x+this.dx)>0 && (this.x+this.dx)<1300)
    	this.x += this.dx;
		if((this.dy+this.y)>0 && (this.y+this.dy)<710)
    	this.y += this.dy;
		if(this.falling) {
			this.dy+=this.gravity;
			this.y += this.dy;
			this.dx+=this.gravity;
			this.x+=this.dx;
			if(this.dy>this.MAX_SPEED)
				this.dy=this.MAX_SPEED;
		}
	}
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public float getGravity() {
		return gravity;
	}
	public void setGravity(int gravity) {
		this.gravity = gravity;
	}
	

}
