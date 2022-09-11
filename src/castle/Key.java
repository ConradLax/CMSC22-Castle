package castle;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Key extends Element{
	private GraphicsContext gc;
	private Scene theScene;
	public final static Image YELLOW_KEY = new Image("images/yellow_key.png",60,62,false,false);
	public final static Image BLUE_KEY = new Image("images/blue_key.png",60,62,false,false);
	public final static Image RED_KEY = new Image("images/red_key.png",60,62,false,false);

	Key(GraphicsContext gc, Scene theScene,int x,int y,Image img){
		super(x,y);
		this.x=x;
		this.y=y;
		this.gc = gc;
		this.theScene = theScene;
		this.loadImage(img);
	}
}
