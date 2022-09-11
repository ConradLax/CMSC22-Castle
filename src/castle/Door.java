package castle;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Door extends Element{
	private GraphicsContext gc;
	private Scene theScene;
	private int x,y;
	public final static Image STAGE_ONE_DOOR = new Image("images/doorOne.png",70,140,false,false);
	public final static Image STAGE_TWO_DOOR = new Image("images/doorTwo.png",70,140,false,false);
	public final static Image STAGE_THREE_DOOR = new Image("images/doorThree.png",70,140,false,false);

	
	Door(GraphicsContext gc, Scene theScene,int x,int y,Image img){
		super(x,y);
		this.x=x;
		this.y=y;
		this.gc = gc;
		this.theScene = theScene;
		this.loadImage(img);
	}
}
