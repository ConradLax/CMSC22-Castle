package castle;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player extends Element{
	private String name;
	private int strength;
	private boolean alive;
	private boolean falling=true, jumping=false, blocked=false,right,left;
	private float gravity=2;
	private final int MAX_SPEED=3;
	private ArrayList<Key> keys;

	private Random r;
	private int score;
	
	public final static Image PLAYER_IMAGE = new Image("images/idle (2).png",Player.PLAYER_WIDTH,Player.PLAYER_WIDTH,false,false);
	public final static Image PLAYER_IMAGE_FLIPPED = new Image("images/idleflipped.png",Player.PLAYER_WIDTH,Player.PLAYER_WIDTH,false,false);
	public final static Image PLAYER_WALKING = new Image("images/walking (2).png",Player.PLAYER_WIDTH,Player.PLAYER_WIDTH,false,false);
	public final static Image PLAYER_WALKING_FLIPPED = new Image("images/walkingflipped.png",Player.PLAYER_WIDTH,Player.PLAYER_WIDTH,false,false);
	private final static int PLAYER_WIDTH = 60;

	public Player(String name, int xPos, int yPos) {
		super(xPos, yPos);
		this.name = name;
		this.r = new Random();
		this.strength = 3;
		this.alive = true;
		this.score=0;
		this.loadImage(Player.PLAYER_IMAGE);
		this.keys = new ArrayList<Key>();
	}
	void render(GraphicsContext gc,int step,int flipped){
		if(step==0) {
			if(flipped==0)
				gc.drawImage(Player.PLAYER_IMAGE, this.x, this.y);
			else if(flipped==1) {
				gc.drawImage(Player.PLAYER_IMAGE_FLIPPED, this.x, this.y);
			}
		}
			
				
		else if(step==1) {
			if(flipped==0) {
				gc.drawImage(Player.PLAYER_WALKING, this.x, this.y);
			}
			else if(flipped==1) {
				gc.drawImage(Player.PLAYER_WALKING_FLIPPED, this.x, this.y);
			}
		}
			
        
    }
	public ArrayList<Key> getKeys(){
		return this.keys;
	}
	public void addKey(Key key) {
		this.keys.add(key);
	}
	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	} 
	public String getName(){
		return this.name;
	}
	
	public void addScore() {
		this.score++;
	}
	
	public int getScore() {
		return this.score;
	}

	public void die(){
    	this.alive = false;
    }
	public void	deductStrength(){
    	this.strength -= 1;
    }
	
	public int getStrength(){
    	return this.strength;
    }

	public void move() {
		if(this.blocked) {
			this.dx=0;
		}
		else if((this.x+this.dx)>0 && (this.x+this.dx)<1300)
    	this.x += this.dx;
		if((this.dy+this.y)>0 && (this.y+this.dy)<760)
    	this.y += this.dy;
		if(this.falling || this.jumping) {

			this.dy+=(int)this.gravity;
			this.y += this.dy;
			if(this.dy>this.MAX_SPEED)
				this.dy=this.MAX_SPEED;
		}

	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public boolean isFalling() {
		return falling;
	}
	public void setFalling(boolean falling) {
		this.falling = falling;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	public float getGravity() {
		return gravity;
	}
	public void setGravity(int gravity) {
		this.gravity = gravity;
	}
	

}
