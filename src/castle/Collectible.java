package castle;

import javafx.scene.image.Image;

public class Collectible extends Element{
	private boolean collected;
	public final static Image COLLECTIBLE_IMAGE = new Image("images/coin.png",Collectible.COLLECTIBLE_WIDTH,Collectible.COLLECTIBLE_WIDTH,false,false);
	private final static int COLLECTIBLE_WIDTH = 50;

	public Collectible(int x, int y){
		super(x,y);
		this.collected = false;
		
		this.loadImage(Collectible.COLLECTIBLE_IMAGE);
	}
	
	//getters
	public boolean getCollected() {
		return this.collected;
	}
}
