package castle;


import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Princess extends Element{
	private GraphicsContext gc;
	private Scene theScene;
	public final static Image PRINCESS = new Image("images/peach.png",150,90,false,false);


	Princess(GraphicsContext gc, Scene theScene,int x,int y,Image img){
		super(x,y);
		this.x=x;
		this.y=y;
		this.gc = gc;
		this.theScene = theScene;
		this.loadImage(img);
	}
}

