package castle;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Elevator extends Element{
		private GraphicsContext gc;
		private Scene theScene;
		private boolean movingUp, movingDown;
		public final static Image ELEVATOR_IMAGE = new Image("images/elevator_image.png",284,815,false,false);

		Elevator(GraphicsContext gc, Scene theScene,int x,int y,Image img){
			super(x,y);
			this.x=x;
			this.y=y;
			this.gc = gc;
			this.theScene = theScene;
			this.loadImage(img);
		}
		public Rectangle2D getBoundsTop() {
			
			return new Rectangle2D(this.x+10,this.y,this.img.getWidth()-10,10);
		}
		public Rectangle2D getBoundsBottom() {

			return new Rectangle2D(this.x+10,this.y+10,this.img.getWidth()-30,10);
		}
		public Rectangle2D getBoundsLeft() {
			return new Rectangle2D(this.x-5,this.y,10,30);
		}
		public Rectangle2D getBoundsRight() {
			return new Rectangle2D(this.x+(this.img.getWidth()),this.y,10,30);
		}
		
		public void move() {
			if((this.x+this.dx)>0 && (this.x+this.dx)<1300)
	    	this.x += this.dx;
			if((this.dy+this.y)>0 && (this.y+this.dy)<710)
	    	this.y += this.dy;

		}
		public boolean isMovingUp() {
			return movingUp;
		}
		public void setMovingUp(boolean movingUp) {
			this.movingUp = movingUp;
		}
		public boolean isMovingDown() {
			return movingDown;
		}
		public void setMovingDown(boolean movingDown) {
			this.movingDown = movingDown;
		}

}
