package castle;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Platform extends Element{
	private GraphicsContext gc;
	private Scene theScene;
	public final static Image BRICK = new Image("images/Brick_Block1.png",1750,102,false,false);
	private int x,y;
	public final static Image LONG_BLOCK = new Image("images/long-block.png",1350,135,false,false);
	public final static Image HALF_BLOCK = new Image("images/half-block.png",820,50,false,false);
	public final static Image SMALL_BLOCK = new Image("images/small-block.png",142,46,false,false);
	public final static Image TINY_BLOCK = new Image("images/tiny-block.png",69,42,false,false);
	public final static Image HALF_BLOCK2 = new Image("images/half-block.png",400,46,false,false);
	public final static Image SOLO_BLOCK = new Image("images/Brick_Block.png",50,50,false,false);

	Platform(GraphicsContext gc, Scene theScene,int x,int y,Image img){
		super(x,y);
		this.x=x;
		this.y=y;
		this.gc = gc;
		this.theScene = theScene;
		this.loadImage(img);
	}

	public Rectangle2D getBoundsTop() {
		
		return new Rectangle2D(this.x,this.y,this.img.getWidth()-5,this.img.getHeight()/2);
	}
	public Rectangle2D getBoundsBottom() {

		return new Rectangle2D(this.x,this.y+(this.img.getHeight()/2)-10,this.img.getWidth(),this.img.getHeight()/2);
	}
	public Rectangle2D getBoundsLeft() {
		return new Rectangle2D(this.x,this.y+10,10,this.img.getHeight()-3);
	}
	public Rectangle2D getBoundsRight() {
		return new Rectangle2D(this.x+(this.img.getWidth()-10),this.y+10,10,this.img.getHeight()-3);
	}

}
