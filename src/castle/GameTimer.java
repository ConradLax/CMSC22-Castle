package castle;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;


public class GameTimer extends AnimationTimer{
	private Button resume,backToMain;
	private GraphicsContext gc;
	private Scene theScene;
	private Scene mainScene;
	private boolean flying=true;
	private Player player;
	public final Image bg = new Image("images/idle (2).png",80,80,false,false);
//	private Platform plat1,plat2,plat3;
	private Platform plat1,plat2,plat3,plat4,plat5,plat6,plat7,plat8,plat9,plat10,plat11,plat12,plat13,plat14,plat15;
	private Stage stage;
	private Princess peach;
	private ArrayList<Platform> platforms;
	private PhantomFlower flower1;
	private HoneyJar jar1;
	private ArrayList<HoneyJar> honeyjars;
	private ArrayList<PhantomFlower> flowers;
	private ArrayList<Cat> cat;
	private ArrayList<Knight> knight;
	private ArrayList<FireSpirit> fireSpirit;
	private ArrayList<Wizard> wizard;
	private ArrayList<Collectible> collectible;
	private int maxKnightCount, maxFsCount, maxWizardCount, maxCatCount;
	private Key stageKey;
	private ArrayList<Elevator> elevator;
//	private Elevator elevator;
	private Boolean canJump,collideLeft,collideRight,escapePressed=false;
	private Door doorStageOne, doorStageTwo,doorStageThree;
	private int level,steps=1,flipped=0;
	private long startSpawn, startSpawnPF;
	private Canvas escapeCanvas = new Canvas(1275,600);
	private GraphicsContext escapeGc = escapeCanvas.getGraphicsContext2D();
	private MediaPlayer jumpPlayer,achievementPlayer,oochPlayer,collectPlayer,collectPlayer2,clickPlayer;
	
	GameTimer(GraphicsContext gc, Scene theScene,Stage stage,int level,Scene mainScene){
		this.gc = gc;
		this.theScene = theScene;
		this.mainScene = mainScene;
		this.level=level;
		this.startSpawn = System.nanoTime();
		this.startSpawnPF = System.nanoTime();
		this.player = new Player("Going merry",100,100);
		this.elevator = new ArrayList<Elevator>();
		this.elevator.add(new Elevator(this.gc,this.theScene,200,700,Elevator.ELEVATOR_IMAGE));
		this.elevator.add(new Elevator(this.gc,this.theScene,900,700,Elevator.ELEVATOR_IMAGE));
		for(int i=0; i<this.elevator.size(); i++) {
			this.elevator.get(i).setVisible(false);
		}
//		this.elevator = new Elevator(this.gc,this.theScene,750,700,Elevator.ELEVATOR_IMAGE);
//		this.elevator.setVisible(false);
		
		this.collectible = new ArrayList<Collectible>();
		this.knight = new ArrayList<Knight>();
		this.fireSpirit = new ArrayList<FireSpirit>();
		this.wizard = new ArrayList<Wizard>();
		this.cat = new ArrayList<Cat>();
		this.maxKnightCount = 1;
		this.maxFsCount = 1;
		this.maxWizardCount = 1;
		this.maxCatCount = 1;

		
		
		if(this.level==1) {		//for level 1 stage
			this.plat1 = new Platform(this.gc,this.theScene,-20,GameStage.WINDOW_HEIGHT-57,Platform.BRICK);
			this.plat2 = new Platform(this.gc,this.theScene,300,400,Platform.HALF_BLOCK);//middle
			this.plat3 = new Platform(this.gc,this.theScene,0,230,Platform.SMALL_BLOCK);//upper left
			this.plat4 = new Platform(this.gc,this.theScene,280,235,Platform.SMALL_BLOCK);//upper middle left
			this.plat5 = new Platform(this.gc,this.theScene,560,235,Platform.SMALL_BLOCK);//upper middle
			this.plat6 = new Platform(this.gc,this.theScene,840,235,Platform.SMALL_BLOCK);//upper middle right
			this.plat7 = new Platform(this.gc,this.theScene,1120,235,Platform.HALF_BLOCK);//upper right
			this.plat8 = new Platform(this.gc,this.theScene,280,550,Platform.SMALL_BLOCK);
			this.plat9 = new Platform(this.gc,this.theScene,560,550,Platform.SMALL_BLOCK);
			this.plat10 = new Platform(this.gc,this.theScene,840,550,Platform.SMALL_BLOCK);
			this.plat11 = new Platform(this.gc,this.theScene,1120,550,Platform.SMALL_BLOCK);
			this.plat12 = new Platform(this.gc,this.theScene,0,550,Platform.SMALL_BLOCK);
			this.platforms = new ArrayList<Platform>();
			platforms.add(this.plat1);
			platforms.add(this.plat2);
			platforms.add(this.plat3);
			platforms.add(this.plat4);
			platforms.add(this.plat5);
			platforms.add(this.plat6);
			platforms.add(this.plat7);
			platforms.add(this.plat8);
			platforms.add(this.plat9);
			platforms.add(this.plat10);
			platforms.add(this.plat11);
			platforms.add(this.plat12);

			this.honeyjars = new ArrayList<HoneyJar>();
			this.jar1 = new HoneyJar(this.gc,this.theScene,950,350,HoneyJar.HONEYJAR);
			this.honeyjars.add(this.jar1);
			this.doorStageOne = new Door(this.gc,this.theScene,1300,100,Door.STAGE_ONE_DOOR);
			this.stageKey = new Key(this.gc,this.theScene,750,600,Key.RED_KEY);
			
			this.collectible.add(new Collectible(450,650));//bottom
			this.collectible.add(new Collectible(1200,490));//middle
			this.collectible.add(new Collectible(900,260));//top
			
			for(int i=0; i<this.maxKnightCount; i++) {
				this.knight.add(new Knight(platforms.get(0).getX()+50,platforms.get(0).getY()-80));
				this.knight.get(i).setNewBoundary(70, GameStage.WINDOW_WIDTH-100);
			}
			for(int i=0; i<this.maxFsCount; i++) {
				this.fireSpirit.add(new FireSpirit(platforms.get(1).getX()+50,platforms.get(1).getY()-80));
				this.fireSpirit.get(i).setNewBoundary(460, GameStage.WINDOW_WIDTH-300);
			}
			for(int i=0; i<this.maxWizardCount; i++) {
				this.wizard.add(new Wizard(platforms.get(6).getX()+50,platforms.get(6).getY()-80));
				this.wizard.get(i).setNewBoundary(1120, GameStage.WINDOW_WIDTH-80);
			}
			for(int i=0; i<this.maxCatCount; i++) {
				this.cat.add(new Cat(platforms.get(7).getX()+50,platforms.get(7).getY()-80));
				this.cat.get(i).setNewBoundary(280,370);
			}
		}
		else if(this.level==2) {		//for level 2 stage
			this.plat1 = new Platform(this.gc,this.theScene,-20,GameStage.WINDOW_HEIGHT-57,Platform.BRICK);//bottom
//			this.plat2 = new Platform(this.gc,this.theScene,200,670,Platform.SMALL_BLOCK);
			this.plat3 = new Platform(this.gc,this.theScene,300,600,Platform.SMALL_BLOCK);
//			this.plat4 = new Platform(this.gc,this.theScene,400,590,Platform.SMALL_BLOCK);
			this.plat5 = new Platform(this.gc,this.theScene,500,545,Platform.SMALL_BLOCK);
//			this.plat6 = new Platform(this.gc,this.theScene,600,500,Platform.SMALL_BLOCK);
			this.plat7 = new Platform(this.gc,this.theScene,700,460,Platform.SMALL_BLOCK);
//			this.plat8 = new Platform(this.gc,this.theScene,800,420,Platform.SMALL_BLOCK);
			this.plat9 = new Platform(this.gc,this.theScene,900,380,Platform.SMALL_BLOCK);
			this.plat10 = new Platform(this.gc,this.theScene,1220,380,Platform.SMALL_BLOCK);
			this.plat11 = new Platform(this.gc,this.theScene,0,260,Platform.HALF_BLOCK2);//door
			this.plat12 = new Platform(this.gc,this.theScene,960,570,Platform.HALF_BLOCK2);//bottom right
			this.plat13 = new Platform(this.gc,this.theScene,0,500,Platform.SMALL_BLOCK);
			this.plat14 = new Platform(this.gc,this.theScene,960,200,Platform.HALF_BLOCK2);//door right
			this.plat15 = new Platform(this.gc,this.theScene,460,350,Platform.SOLO_BLOCK);
			this.platforms = new ArrayList<Platform>();
			platforms.add(this.plat1);
//			platforms.add(this.plat2);
			platforms.add(this.plat3);
//			platforms.add(this.plat4);
			platforms.add(this.plat5);
//			platforms.add(this.plat6);
			platforms.add(this.plat7);
//			platforms.add(this.plat8);
			platforms.add(this.plat9);
			platforms.add(this.plat10);
			platforms.add(this.plat11);
			platforms.add(this.plat12);
			platforms.add(this.plat13);
			platforms.add(this.plat14);
			platforms.add(this.plat15);
			this.honeyjars = new ArrayList<HoneyJar>();
			
			this.flowers = new ArrayList<PhantomFlower>();
			this.flower1 = new PhantomFlower(this.gc,this.theScene,1250,GameStage.WINDOW_HEIGHT-107,PhantomFlower.OPEN);
			this.flowers.add(this.flower1);
			
			
//			this.jar1 = new HoneyJar(this.gc,this.theScene,250,400,HoneyJar.HONEYJAR);
//			this.honeyjars.add(this.jar1);
			this.doorStageTwo = new Door(this.gc,this.theScene,0,120,Door.STAGE_TWO_DOOR);
			this.stageKey = new Key(this.gc,this.theScene,1300,650,Key.YELLOW_KEY);
			this.collectible.add(new Collectible(0,300));//bottom
			this.collectible.add(new Collectible(1300,250));//middle
			this.collectible.add(new Collectible(1300,100));//top
			
			for(int i=0; i<this.maxKnightCount; i++) {
				this.knight.add(new Knight(platforms.get(7).getX()+50,platforms.get(7).getY()-80));
				this.knight.get(i).setNewBoundary(GameStage.WINDOW_WIDTH-450, GameStage.WINDOW_WIDTH-100);
			}
			for(int i=0; i<this.maxFsCount; i++) {
				this.fireSpirit.add(new FireSpirit(platforms.get(0).getX()+50,platforms.get(0).getY()-80));
				this.fireSpirit.get(i).setNewBoundary(430, GameStage.WINDOW_WIDTH-300);
			}
			for(int i=0; i<this.maxWizardCount; i++) {
				this.wizard.add(new Wizard(platforms.get(9).getX()+50,platforms.get(9).getY()-80));
				this.wizard.get(i).setNewBoundary(1120, GameStage.WINDOW_WIDTH-80);
			}
			for(int i=0; i<this.maxCatCount; i++) {
				this.cat.add(new Cat(platforms.get(3).getX()+50,platforms.get(3).getY()-80));
				this.cat.get(i).setNewBoundary(700, 792);
			}

		}
		else if(this.level==3) {		//for level 3 stage
			this.plat1 = new Platform(this.gc,this.theScene,-20,GameStage.WINDOW_HEIGHT-57,Platform.BRICK); //SPIKE
			this.plat2 = new Platform(this.gc,this.theScene,0,200,Platform.SMALL_BLOCK); //ENEMY 1
			this.plat3 = new Platform(this.gc,this.theScene,0,500,Platform.SMALL_BLOCK); //PLAYER
			this.plat4 = new Platform(this.gc,this.theScene,500,200,Platform.SMALL_BLOCK); //KEY
			this.plat5 = new Platform(this.gc,this.theScene,750,500,Platform.SMALL_BLOCK); 
			this.plat6 = new Platform(this.gc,this.theScene,500,500,Platform.SMALL_BLOCK); //ENEMY 2
			this.plat7 = new Platform(this.gc,this.theScene,750,200,Platform.SMALL_BLOCK); 
			this.plat8 = new Platform(this.gc,this.theScene,1300,260,Platform.SMALL_BLOCK); //door
			this.plat9 = new Platform(this.gc,this.theScene,-20,-80,Platform.BRICK);
			this.platforms = new ArrayList<Platform>();
			platforms.add(this.plat1);
			platforms.add(this.plat2);
			platforms.add(this.plat3);
			platforms.add(this.plat4);
			platforms.add(this.plat5);
			platforms.add(this.plat6);
			platforms.add(this.plat7);
			platforms.add(this.plat8);
			platforms.add(this.plat9);

//			this.elevator = new Elevator(this.gc,this.theScene,200,700,Elevator.ELEVATOR_IMAGE);
//			this.elevator.setVisible(false);
//			this.elevator2 = new Elevator(this.gc,this.theScene,750,700,Elevator.ELEVATOR_IMAGE);
			this.honeyjars = new ArrayList<HoneyJar>();
			this.jar1 = new HoneyJar(this.gc,this.theScene,500,100,HoneyJar.HONEYJAR);
			this.honeyjars.add(this.jar1);
			this.collectible.add(new Collectible(550,100));//bottom
			this.collectible.add(new Collectible(750,80));//middle
			this.collectible.add(new Collectible(750,350));//top
			this.doorStageThree = new Door(this.gc,this.theScene,1300,120,Door.STAGE_THREE_DOOR);
			this.stageKey = new Key(this.gc,this.theScene,550,300,Key.BLUE_KEY);
			for(int i=0; i<this.elevator.size(); i++) {
				this.elevator.get(i).setVisible(true);
			}
			for(int i=0; i<this.maxKnightCount; i++) {
				this.knight.add(new Knight(10000,10000));
				this.knight.get(i).setNewBoundary(0, 0);
			}
			for(int i=0; i<this.maxFsCount; i++) {
				this.fireSpirit.add(new FireSpirit(platforms.get(6).getX()+50,platforms.get(6).getY()-80));
				this.fireSpirit.get(i).setNewBoundary(450, 800);
			}
			for(int i=0; i<this.maxWizardCount; i++) {
				this.wizard.add(new Wizard(platforms.get(5).getX()+50,platforms.get(5).getY()-80));
				this.wizard.get(i).setNewBoundary(450, 550);
			}
			for(int i=0; i<this.maxCatCount; i++) {
				this.cat.add(new Cat(platforms.get(0).getX()+50,platforms.get(0).getY()-80));
				this.cat.get(i).setNewBoundary(450, 800);
			}
		}
		
		else if(this.level==4) {
			this.plat1 = new Platform(this.gc,this.theScene,-20,GameStage.WINDOW_HEIGHT-57,Platform.BRICK);
			this.platforms = new ArrayList<Platform>();
			platforms.add(this.plat1);
			this.peach = new Princess(this.gc,this.theScene,1000,GameStage.WINDOW_HEIGHT-138,Princess.PRINCESS);
			
		}

		this.stage=stage;
		this.canJump=true;
		//call method to handle mouse click event
		this.handleKeyPressEvent();
	}
	
	@Override
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		
		//text for update of health and score
		this.gc.setFill(Color.TRANSPARENT);
		this.gc.fillRect(320, 0, 400, 80);

		//renders platforms
		for(int i=0;i<platforms.size();i++) {
			platforms.get(i).render(this.gc);
		}
		
		//text for the life of player and accumulated score
		Font theFont = Font.font("Calibri",40);
		this.gc.setFont(theFont);
		this.gc.setFill(Color.WHITE);	//or CHOCOLATE
		this.gc.fillText("Score: "+String.valueOf(GameStage.score),330,50);
		for(int i=0;i<GameStage.strength;i++) {
			this.gc.drawImage(bg, i*100, 0);
		}
		
		//renders elevator
		for(int i=0; i<this.elevator.size(); i++) {
			if(this.elevator.get(i).isVisible())
				this.elevator.get(i).render(this.gc);
		}
		
		if(level==4) {
			peach.render(gc);
			if(this.player.collidesWith(this.peach)) {
				this.stop();
				ResultStage rs2 = new ResultStage();
				rs2.setStage(this.stage, this.mainScene, 1);
			}
		}
		
		if(this.level!=4) {
			//renders honeyjars
			for(int i=0;i<this.honeyjars.size();i++) {
				this.honeyjars.get(i).render(gc);
			}
			
			//renders collectibles
			for(int i=0;i<this.collectible.size();i++) {
				this.collectible.get(i).render(gc);
			}
		}


		//renders door and keys
		if(this.level==1) {
			this.doorStageOne.render(this.gc);
			if(this.stageKey.isVisible())
			this.stageKey.render(this.gc);
			

		}
		else if(this.level==2) {
			this.doorStageTwo.render(this.gc);
			if(this.stageKey.isVisible())
			this.stageKey.render(this.gc);
			//renders phantomflower
			for(int i=0;i<this.flowers.size();i++) {
				this.flowers.get(i).render(gc);
			}

		}
		else if(this.level==3) {
			this.doorStageThree.render(this.gc);
			if(this.stageKey.isVisible())
			this.stageKey.render(this.gc);

		}
		


		
		//collision with elevator
		for(int i=0; i<this.elevator.size(); i++) {
			if(this.elevator.get(i).isVisible()) {
				if(this.player.collidesWith(this.elevator.get(i).getBoundsTop())) {
				//if jumping

					if(this.player.getDY() < -10
						
							) {
						this.player.setJumping(true);				
					}
					else {
						player.setJumping(false);
						this.canJump=true;
						player.setFalling(false);
						player.setDY(0);
					}
				}
				else if(this.player.collidesWith(this.elevator.get(i).getBoundsLeft())) {

//				player.setFalling(true);
					player.setDX(0);
				}
				else if(this.player.collidesWith(this.elevator.get(i).getBoundsRight())) {
//				player.setFalling(true);
					player.setDX(0);
				}
			
			//if player is between elevator and platform or top of stage, player is crushed
				for(int j=0;j<platforms.size();j++) {
					if(this.player.collidesWith(this.elevator.get(i).getBoundsBottom()) && this.player.collidesWith(platforms.get(j))|| this.player.collidesWith(this.elevator.get(i)) && this.player.getY()<=10) {
						//audio for taking damage

						this.player.setX(100);
						this.player.setY(100);
						GameStage.strength--;
					}
				}
			}
		}
		
		//function for enemies, collectible
		if(this.level!=4) {
			this.touchEnemy();
			this.checkCollectible();
		}
		
		
		//for player landing on platform
		for(int i=0;i<platforms.size();i++) {
			if(this.player.collidesWith(platforms.get(i).getBoundsTop())) {
				//if jumping
//				System.out.println("top"+"no. "+i);
				if(this.player.getDY() < 0) {

					
					this.player.setJumping(true);
//					this.player.move();
					
				}
				else {
					player.setJumping(false);
					this.canJump=true;
					player.setFalling(false);
					player.setDY(0);
				}
			}

			if(this.player.collidesWith(platforms.get(i).getBoundsLeft())) {
//				System.out.println("left"+"no. "+i);
//				boolean topPlatformCollisionCount=false;
//				for(int j=0;j<platforms.size();j++) {
//					if(this.player.collidesWith(platforms.get(j).getBoundsTop()) && j!=i && flipped==0) {
//						topPlatformCollisionCount=true;
//						player.setFalling(false);
//						player.setDX(0);
//						break;
//					}
//				}
//				if(!topPlatformCollisionCount) {
					player.setFalling(true);
					player.setDX(0);
//				}
				
			}
			if(this.player.collidesWith(platforms.get(i).getBoundsRight())) {
//				System.out.println("right"+"no. "+i);
//				boolean topPlatformCollisionCount=false;
//				for(int j=0;j<platforms.size();j++) {
//					if(this.player.collidesWith(platforms.get(j).getBoundsTop()) && j!=i) {
//						topPlatformCollisionCount=true;
//						player.setFalling(false);
//						player.setDX(0);
//						break;
//					}
//				}
//				if(!topPlatformCollisionCount) {
					player.setFalling(true);
					player.setDX(0);
//				}
			}
			if(this.player.collidesWith(platforms.get(i).getBoundsBottom())) {
				player.setFalling(true);
//				System.out.println("bottom"+"no. "+i);

				player.setDY(0);
			}


		}
		//for falling objects
		if(this.level!=4) {
			for(int i=0;i<platforms.size();i++) {
				for(int j=0;j<honeyjars.size();j++) {

					if(this.honeyjars.get(j).collidesWith(platforms.get(i).getBoundsTop())) {
							this.honeyjars.get(j).setFalling(false);
							this.honeyjars.get(j).setDY(0);
							this.honeyjars.get(j).setDX(0);

					}

					
				}
			}
		}
		if(this.level==2) {
			//phantom flower
			for(int i=0;i<flowers.size();i++) {
				if (this.flowers.get(i).getCheck() == 0) {
					if(this.player.isBlocked() && this.flipped==1 && this.collideLeft==true) {
						this.player.setBlocked(false);
					}
					if(this.player.isBlocked() && this.flipped==0 && this.collideRight==true) {
						this.player.setBlocked(false);
					}
					if(this.player.collidesWith(this.flowers.get(i).getBoundsLeft()) && flipped==0 || this.player.collidesWith(this.flowers.get(i).getBoundsRight()) && flipped==1) {
						if(this.flipped==0) {
							this.collideLeft=true;
							this.collideRight=false;
							this.player.setBlocked(true);
						}
						if(this.flipped==1) {
							this.collideLeft=false;
							this.collideRight=true;
							this.player.setBlocked(true);
						}
						if(this.flowers.get(i).getX()>=1250) {
							this.player.setBlocked(true);

						}
						if(this.flowers.get(i).getX()<=30) {
							this.player.setBlocked(true);

						}
					}
		
					if(this.player.collidesWith(this.flowers.get(i).getBoundsTop())) {
						if(this.player.getDY() < 0) {
							this.player.setJumping(true);					
						}
						else {
							player.setJumping(false);
							this.canJump=true;
							player.setFalling(false);
							player.setDY(0);
						}
					}
//				
//					}
				} else {
					this.player.setBlocked(false);
//					this.player.move();
				}
			}
			
			//image method for phantom flower
			
			for (int i = 0; i<this.flowers.size(); i++){
				long currentSecPF = TimeUnit.NANOSECONDS.toSeconds(currentNanoTime);
				long startSecPF = TimeUnit.NANOSECONDS.toSeconds(this.startSpawnPF);
				if((currentSecPF - startSecPF) % 2 ==0 || (currentSecPF - startSecPF) % 3 ==0){
					if(flowers.get(i).getCheck() == 0) {
						flowers.get(i).setCheck(1);
						flowers.get(i).changeImage(1);
					} else if (flowers.get(i).getCheck() == 1) {
						flowers.get(i).setCheck(0);
						flowers.get(i).changeImage(0);
					}
					this.startSpawnPF = currentNanoTime;
				}		
			}
		}

		
		if(this.level!=4) {
			//for moving objects
			for(int i=0;i<honeyjars.size();i++) {
				if(this.player.isBlocked() && this.flipped==1 && this.collideLeft==true) {
					this.player.setBlocked(false);
				}
				if(this.player.isBlocked() && this.flipped==0 && this.collideRight==true) {
					this.player.setBlocked(false);
				}
				if(this.player.collidesWith(this.honeyjars.get(i).getBoundsLeft()) && flipped==0 || this.player.collidesWith(this.honeyjars.get(i).getBoundsRight()) && flipped==1) {
					if(this.flipped==0) {
						this.collideLeft=true;
						this.collideRight=false;
					}
					if(this.flipped==1) {
						this.collideLeft=false;
						this.collideRight=true;
					}
					this.honeyjars.get(i).setDX(this.player.getDX());
					if(this.honeyjars.get(i).getX()>=1250) {
						this.player.setBlocked(true);

					}
					if(this.honeyjars.get(i).getX()<=30) {
						this.player.setBlocked(true);

					}
					int jarcounter=0;
					for(int j=0;j<platforms.size();j++) {
						if(!this.honeyjars.get(i).collidesWith(platforms.get(j))) {
							jarcounter++;
						}
					}
					if(jarcounter==platforms.size()) {
						this.honeyjars.get(i).setFalling(true);
//						if(this.honeyjars.get(i).getDX()>0)
//							this.honeyjars.get(i).setDX(30);
//						else if(this.honeyjars.get(i).getDX()<0)
//							this.honeyjars.get(i).setDX(-30);
					}
				}
				if(this.player.collidesWith(this.honeyjars.get(i).getBoundsTop())) {
					if(this.player.getDY() < 0) {
						this.player.setJumping(true);					
					}
					else {
						player.setJumping(false);
						this.canJump=true;
						player.setFalling(false);
						player.setDY(0);
					}
				}
				int countPlat=0;
				for(int j=0;j<platforms.size();j++) {
					if(this.player.collidesWith(this.platforms.get(j))) {
						countPlat++;
						break;
					}
				}
				if(this.player.collidesWith(this.honeyjars.get(i).getBoundsBottom()) && !this.player.isBlocked() && countPlat==0) {
					this.player.setFalling(true);
					this.player.setDY(0);
				}
				if(!this.player.collidesWith(this.honeyjars.get(i))) this.honeyjars.get(i).setDX(0);

			}
		}

		

		
		//falling off platform
		int counter=0;
		for(int i=0;i<platforms.size();i++) {
			if(!this.player.collidesWith(platforms.get(i))) {
				counter++;
			
			}

			
		}
		int counter2=0;
		if(level!=4) {
			
			for(int i=0;i<honeyjars.size();i++) {
				if(!this.player.collidesWith(honeyjars.get(i))) {
					counter2++;
				
				}

				
			}
		}

		for(int i=0; i<this.elevator.size();i++) {
			if(this.elevator.get(i).isVisible() && this.level!=4) {
				if(counter==platforms.size() && counter2==honeyjars.size() && !this.player.collidesWith(this.elevator.get(i))) player.setFalling(true);
			}
			else if(this.level==4) {
				if(counter==platforms.size()) player.setFalling(true);

			}
			else {
				if(counter==platforms.size() && counter2==honeyjars.size()) player.setFalling(true);
			}
		}

		if(level!=4) {
			
		
		//movement of enemies
		this.checkJar(this.honeyjars);
		for (int i = 0; i<this.knight.size(); i++){
			this.moveTheKnight(this.knight.get(i));
			this.knight.get(i).move();
			this.knight.get(i).render(this.gc);			
		}
		
		
		for (int i = 0; i<this.fireSpirit.size(); i++){
			this.fireSpirit.get(i).move();
			this.moveTheFS(this.fireSpirit.get(i),platforms);
			
			this.fireSpirit.get(i).render(this.gc);			
		}
		
		for (int i = 0; i<this.wizard.size(); i++){
			this.wizard.get(i).move();
			this.moveTheWizard(this.wizard.get(i));
			
			this.wizard.get(i).render(this.gc);			
		}
		
		for (int i = 0; i<this.cat.size(); i++){
			this.cat.get(i).move();
			this.moveTheCat(this.cat.get(i));
			
			this.cat.get(i).render(this.gc);			
		}


		//collision of enemies and honeyjar
		
		//wizard
		for(int i=0;i<this.wizard.size();i++) {
			for(int j=0;j<this.honeyjars.size();j++) {
				if(this.wizard.get(i).collidesWith(this.honeyjars.get(j).getBoundsBottom())) {
					this.wizard.remove(i);
					break;
				}
			}
		}
		
		//fire spirit
		for(int i=0;i<this.fireSpirit.size();i++) {
			for(int j=0;j<this.honeyjars.size();j++) {
				if(this.fireSpirit.get(i).collidesWith(this.honeyjars.get(j).getBoundsBottom())) {
					this.fireSpirit.remove(i);
					break;
				}
			}
		}
		
		//knight
		for(int i=0;i<this.knight.size();i++) {
			for(int j=0;j<this.honeyjars.size();j++) {
				if(this.knight.get(i).collidesWith(this.honeyjars.get(j).getBoundsBottom())) {
					this.knight.remove(i);
					break;
				}
			}
		}
		
		for(int i=0;i<this.cat.size();i++) {
			for(int j=0;j<this.honeyjars.size();j++) {
				if(this.cat.get(i).collidesWith(this.honeyjars.get(j).getBoundsBottom())) {
					this.cat.remove(i);
					break;
				}
			}
		}
		
		//adds key to player
		if(this.player.collidesWith(this.stageKey)) {
			this.player.addKey(this.stageKey);
			if(this.stageKey.isVisible()) {
        		try {
    					String collectPath2 = GameMenu.class.getResource("/musics/click_item.mp3").toString();
    					Media collectSfx2 = new Media(collectPath2);
    					this.collectPlayer2 = new MediaPlayer(collectSfx2);
    					this.collectPlayer2.play();
            		}
            		catch(Exception e) {
            			System.out.println("Error in getting audio.");
            		}
				System.out.println("Key acquired.");
				GameStage.score+=100;
				System.out.println("+100 score.");
			}
			this.stageKey.setVisible(false);
		}
		
		}
		//elevator movement and interactions
		for(int j=0;j<this.elevator.size();j++) {
			if(this.elevator.get(j).isVisible()) {
				//elevator movement
				if(this.elevator.get(j).getY()==700) {
					this.elevator.get(j).setMovingUp(true);
					this.elevator.get(j).setMovingDown(false);
				}
				else if(this.elevator.get(j).getY()==170) {
					this.elevator.get(j).setMovingUp(false);
					this.elevator.get(j).setMovingDown(true);
				}
				if(this.elevator.get(j).isMovingUp()) this.elevator.get(j).setDY(-5);
				else if(this.elevator.get(j).isMovingDown()) this.elevator.get(j).setDY(5);
				
				this.elevator.get(j).move();
				
				if(this.player.collidesWith(this.elevator.get(j).getBoundsTop())) {
					this.player.setDY(this.elevator.get(j).getDY());
				}
				for(int k=0;k<this.honeyjars.size();k++) {
					if(this.honeyjars.get(k).collidesWith(this.elevator.get(j).getBoundsTop())) {
						this.honeyjars.get(k).setDY(this.elevator.get(j).getDY());
						this.honeyjars.get(k).setDX(0);
					}
					else if(this.honeyjars.get(k).collidesWith(this.elevator.get(j).getBoundsLeft()) || this.honeyjars.get(k).collidesWith(this.elevator.get(j).getBoundsRight())) {
						this.honeyjars.get(k).setDX(0);
						this.player.setDX(0);
					}
					for(int l=0;l<this.platforms.size();l++) {
						if(this.honeyjars.get(k).collidesWith(this.platforms.get(l)) && this.honeyjars.get(k).collidesWith(this.elevator.get(j).getBoundsBottom())) {
							this.honeyjars.remove(k);
							break;
						}
					}
				}
		
			}
		}

		if(level!=4) {
			
			for(int i=0;i<cat.size();i++) {
				if(this.player.collidesWith(this.cat.get(i))) {
			//audio for taking damage
	        		try {
						String oochPath = GameMenu.class.getResource("/musics/ooch.mp3").toString();
						Media oochSfx = new Media(oochPath);
						this.oochPlayer = new MediaPlayer(oochSfx);
						this.oochPlayer.play();
	        		}
	        		catch(Exception e) {
	        			System.out.println("Error in getting audio.");
	        		}
					GameStage.strength--;
					this.player.setX(100);
					this.player.setY(100);
				}
			
			}
		for(int i=0;i<fireSpirit.size();i++) {
			if(this.player.collidesWith(this.fireSpirit.get(i))) {
		//audio for taking damage
        		try {
					String oochPath = GameMenu.class.getResource("/musics/ooch.mp3").toString();
					Media oochSfx = new Media(oochPath);
					this.oochPlayer = new MediaPlayer(oochSfx);
					this.oochPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
				GameStage.strength--;
				this.player.setX(100);
				this.player.setY(100);
			}
			
		}
		for(int i=0;i<wizard.size();i++) {
			if(this.player.collidesWith(this.wizard.get(i))) {
		//audio for taking damage
        		try {
					String oochPath = GameMenu.class.getResource("/musics/ooch.mp3").toString();
					Media oochSfx = new Media(oochPath);
					this.oochPlayer = new MediaPlayer(oochSfx);
					this.oochPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
				GameStage.strength--;
				this.player.setX(100);
				this.player.setY(100);
			}
		
		}
		for(int i=0;i<knight.size();i++) {
			if(this.player.collidesWith(this.knight.get(i))) {
		//audio for taking damage
        		try {
					String oochPath = GameMenu.class.getResource("/musics/ooch.mp3").toString();
					Media oochSfx = new Media(oochPath);
					this.oochPlayer = new MediaPlayer(oochSfx);
					this.oochPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
				GameStage.strength--;
				this.player.setX(100);
				this.player.setY(100);
			}
		
		}
		}
		//for moving the player
		this.player.move();
		//proceeds to next stage
		//for moving the object(s)
		if(level!=4) {
			
		
		for(int i=0;i<honeyjars.size();i++) {
			honeyjars.get(i).move();
		}
		
		//works but makes the game sluggish when going to next stage
		/**
//		if(this.level==1) {
//			for(int i=0;i<this.player.getKeys().size();i++) {
//				if(this.player.collidesWith(this.doorStageOne) && this.player.getKeys().get(i).getImage()==Key.RED_KEY) {
//					
//					GameStageTwo levelTwo = new GameStageTwo();
//					levelTwo.setStage(stage,this.mainScene);
//					this.stop();
//				}
//			}
//
//		}
//		else if(this.level==2) {
//			for(int i=0;i<this.player.getKeys().size();i++) {
//				if(this.player.collidesWith(this.doorStageTwo) && this.player.getKeys().get(i).getImage()==Key.YELLOW_KEY) {
//					GameStageThree levelThree = new GameStageThree();
//					levelThree.setStage(stage,this.mainScene);
//					this.stop();
//
//				}
//			}
//		}
 * 
 */
		//proceeds to next stage (faster)
		if(player.getKeys().contains(this.stageKey)) {
			if(this.level==1 && this.player.collidesWith(this.doorStageOne)) {
		//music for entering next stage
        		try {
					String achievedPath = GameMenu.class.getResource("/musics/achievement.mp3").toString();
					Media achievedSfx = new Media(achievedPath);
					this.achievementPlayer = new MediaPlayer(achievedSfx);
					this.achievementPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
				GameStageTwo levelTwo = new GameStageTwo();
				levelTwo.setStage(stage,this.mainScene);
				this.stop();
			}
			else if(this.level==2 && this.player.collidesWith(this.doorStageTwo)) {
				
		//music for entering next stage
        		try {
					String achievedPath = GameMenu.class.getResource("/musics/achievement.mp3").toString();
					Media achievedSfx = new Media(achievedPath);
					this.achievementPlayer = new MediaPlayer(achievedSfx);
					this.achievementPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
				GameStageThree levelThree = new GameStageThree();
				levelThree.setStage(stage,this.mainScene);
				this.stop();
			}
			else if(this.level==3 && this.player.collidesWith(this.doorStageThree)) {
				//music for entering next stage
        		try {
					String achievedPath = GameMenu.class.getResource("/musics/achievement.mp3").toString();
					Media achievedSfx = new Media(achievedPath);
					this.achievementPlayer = new MediaPlayer(achievedSfx);
					this.achievementPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
        		EndingStage es = new EndingStage();
        		es.setStage(stage, this.mainScene);
        		this.stop();
			}
		}
		
		
		}//level!=4
		
		//render the player animation
		long currentSec = TimeUnit.NANOSECONDS.toSeconds(currentNanoTime);
		long startSec = TimeUnit.NANOSECONDS.toSeconds(this.startSpawn);	
		if((currentSec - startSec)%2 == 0){
			this.player.render(this.gc,this.steps,this.flipped);
			this.startSpawn = currentNanoTime;
		}
		else {
			if(flipped==0)
				this.player.render(this.gc);
			else if(flipped==1) {
				this.player.render(this.gc,this.steps,1);
			}
		}

		
		if(GameStage.strength==0) {
			this.player.die();
			this.stop();
			ResultStage rs = new ResultStage();
			rs.setStage(this.stage, this.mainScene, 0);
			GameStage.strength=3;
			GameStage.score=0;
//			this.stage.setScene(this.mainScene);
//			stage.show();
//			GameMenu.mediaPlayer.play();
		}

		
	}
	
	private void buttonPressEvent(Button btn) {
		btn.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if(btn.getId()=="resume") {
					GameTimer.super.start();
					if(level==1)
						GameStage.group.getChildren().removeAll(escapeCanvas, resume,backToMain);
					else if(level==2)
						GameStageTwo.group.getChildren().removeAll(escapeCanvas, resume,backToMain);
					else if(level==3)
						GameStageThree.group.getChildren().removeAll(escapeCanvas, resume,backToMain);
					escapePressed=false;
	        		try {
						String clickPath = GameMenu.class.getResource("/musics/click_item.mp3").toString();
						Media clickSfx = new Media(clickPath);
						clickPlayer = new MediaPlayer(clickSfx);
						clickPlayer.play();
	        		}
	        		catch(Exception ea) {
	        			System.out.println("Error in getting audio.");
	        		}
	        		
				}
				if(btn.getId()=="backtomain") {
	        		try {
						String clickPath = GameMenu.class.getResource("/musics/click_item.mp3").toString();
						Media clickSfx = new Media(clickPath);
						clickPlayer = new MediaPlayer(clickSfx);
						clickPlayer.play();
	        		}
	        		catch(Exception ea) {
	        			System.out.println("Error in getting audio.");
	        		}
					stage.setScene(mainScene);
					stage.show();
					GameMenu.mediaPlayer.play();
				}
			}
		});
		
	}
	
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyShip(code);
			}
			
		});
		
		theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopMyShip(code);
		            }
		        });
    }
	
	//method that will move the ship depending on the key pressed
	private void moveMyShip(KeyCode ke) {
		if(ke==KeyCode.UP || ke==KeyCode.W) {
			if(this.canJump) {
				this.player.setDY(-20);
				
	//sound for jumping
        		try {
					String jumpPath = GameMenu.class.getResource("/musics/jump.mp3").toString();
					Media jumpSfx = new Media(jumpPath);
					jumpPlayer = new MediaPlayer(jumpSfx);
					jumpPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
			}
				 
			this.canJump=false;
		}

		if(ke==KeyCode.LEFT|| ke==KeyCode.A) {
			this.flipped=1;
			this.steps=1;
			this.player.setDX(-7);
		}

		if(ke==KeyCode.DOWN || ke==KeyCode.S) {
			for(int i=0;i<this.platforms.size();i++) {
				if(!this.player.collidesWith(platforms.get(i)))
					this.player.setDY(3);
			}
			
		}
		
		if(ke==KeyCode.RIGHT || ke==KeyCode.D) {
			this.flipped=0;
			this.steps=1;
			this.player.setDX(7);
		}
		
		if(ke==KeyCode.ESCAPE) {
//			this.gc.getCanvas().setOpacity(30);
//			this.escapeGc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
			if(this.escapePressed==false) {
				this.stop();
				this.escapeGc.setFill(Color.DARKSLATEGRAY);		//previously darkslategray
				this.escapeGc.fillRect(480, 200, 400, 340);
				this.escapeGc.setStroke(Color.BROWN);			//previously brown/darkgoldenrod
				this.escapeGc.setLineWidth(5);
				this.escapeGc.strokeRect(480, 202, 400, 340);
				
				this.resume = new Button();
				resume.setLayoutX(580);
				resume.setLayoutY(280);
				resume.setPrefSize(200,80);
				resume.setId("resume");
				resume.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/resumebtn.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat;\r\n -fx-background-position: center; ");
				this.buttonPressEvent(resume);
				
				this.backToMain = new Button();
				backToMain.setLayoutX(580);
				backToMain.setLayoutY(400);
				backToMain.setPrefSize(200,80);
				backToMain.setId("backtomain");
				backToMain.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/menubtn.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat;\r\n -fx-background-position: center; ");
				this.buttonPressEvent(backToMain);


				if(this.level==1)
					GameStage.group.getChildren().addAll(this.escapeCanvas,resume,backToMain);
				else if(this.level==2)
					GameStageTwo.group.getChildren().addAll(this.escapeCanvas,resume,backToMain);
				else if(this.level==3)
					GameStageThree.group.getChildren().addAll(this.escapeCanvas,resume,backToMain);

//				this.stage.setScene(this.mainScene);
//				this.stage.show();
//				GameMenu.mediaPlayer.play();
			}
			if(this.level==1) {
				if(GameStage.group.getChildren().contains(resume))
					this.escapePressed=true;
			}

			else if(this.level==2) {
				if(GameStageTwo.group.getChildren().contains(resume))
					this.escapePressed=true;
			}
			else if(this.level==3) {
				if(GameStageThree.group.getChildren().contains(resume))
					this.escapePressed=true;
			}
		}
		
		System.out.println(ke+" key pressed.");
   	}
	
	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopMyShip(KeyCode ke){
		this.steps=0;
		this.player.setDX(0);
		this.player.setDY(0);
	}
	//method for moving the knight
	private void moveTheKnight(Knight knight){   		
   		knight.setDX(5);
   		knight.setDX(knight.getDX()*knight.getDirection());
   		if (knight.getX() <= knight.xLeftBoundary) {
   			knight.setDirection(1);
   			knight.changeImage(1);
   		}
   		else if(knight.getX() >= knight.xRightBoundary) {
   			knight.setDirection(-1);
   			knight.changeImage(-1);
   		}
  
   	}
	
//	method for moving the fire spirit
	private void moveTheFS(FireSpirit fireSpirit,ArrayList<Platform> platforms){
   		
		fireSpirit.setDX(5);
   		fireSpirit.setDX(fireSpirit.getDX()*fireSpirit.getDirection());
   		for(int i=0;i<platforms.size();i++) {
   			if(!fireSpirit.collidesWith(platforms.get(i))) {
   				if(flying) fireSpirit.setDY(-1);
   				else if(!flying) fireSpirit.setDY(1);
   			}
   			if(fireSpirit.collidesWith(platforms.get(i).getBoundsBottom()) || fireSpirit.getY()==0) {
   				flying=false;
   				fireSpirit.setDY(1);

   			}
   			else if(fireSpirit.collidesWith(platforms.get(i).getBoundsTop())) {
   				flying=true;
   				fireSpirit.setDY(-1);

   			}
   		}
   		
   		if (fireSpirit.getX() <= fireSpirit.xLeftBoundary) {
   			fireSpirit.setDirection(1);
   			fireSpirit.changeImage(1);
   		}
   		else if(fireSpirit.getX() >= fireSpirit.xRightBoundary) {
   			fireSpirit.setDirection(-1);
   			fireSpirit.changeImage(-1);
   		}
   	}
	
//	method for moving the wizard
	private void moveTheWizard(Wizard wizard){
   		wizard.setDX(2);
   		wizard.setDX(wizard.getDX()*wizard.getDirection());
   		if (wizard.getX() <= wizard.xLeftBoundary) {
   			wizard.setDirection(1);
   			wizard.changeImage(1);   			
   		}
   		else if(wizard.getX() >= wizard.xRightBoundary) {
   			wizard.setDirection(-1);
   			wizard.changeImage(-1);
   		}
   	}
	
//	method for moving the cat
	private void moveTheCat(Cat cat){
   		cat.setDX(2);
   		cat.setDX(cat.getDX()*cat.getDirection());
   		if (cat.getX() <= cat.xLeftBoundary) {
   			cat.setDirection(1);
   			cat.changeImage(1);   			
   		}
   		else if(cat.getX() >= cat.xRightBoundary) {
   			cat.setDirection(-1);
   			cat.changeImage(-1);
   		}
   	}
	
	//method for checking collectible
	public void checkCollectible() {
		for(int i=0; i<this.collectible.size();i++) {
			if(this.player.collidesWith(this.collectible.get(i))) {
        		try {
    					String collectPath = GameMenu.class.getResource("/musics/collect_item.mp3").toString();
    					Media collectSfx = new Media(collectPath);
    					this.collectPlayer = new MediaPlayer(collectSfx);
    					this.collectPlayer.play();
            		}
            		catch(Exception e) {
            			System.out.println("Error in getting audio.");
            		}
				GameStage.score+=10;
				System.out.println("+10 score.");
				this.collectible.get(i).loadImage(null);
				this.collectible.get(i).setY(1500);
			}
		}
	}
	
	//method for checking jar
	public void checkJar(ArrayList<HoneyJar> honeyJar) {
		for(int i=0; i<this.knight.size(); i++) {
			for(int j=0;j<honeyjars.size();j++) {
				if(this.knight.get(i).collidesWith(this.honeyjars.get(j).getBoundsLeft())) {
					this.knight.get(i).setNewBoundary(this.knight.get(i).getxLeftBoundary(), honeyJar.get(j).getX()-100);
				}
				else if(this.knight.get(i).collidesWith(this.honeyjars.get(j).getBoundsRight())){
					this.knight.get(i).setNewBoundary(this.honeyjars.get(j).getX()+100, this.knight.get(i).getxRightBoundary());
				}
			}
		}
		for(int i=0; i<this.fireSpirit.size(); i++) {
			for(int j=0;j<honeyjars.size();j++) {
				if(this.fireSpirit.get(i).collidesWith(this.honeyjars.get(j).getBoundsLeft())) {
					this.fireSpirit.get(i).setNewBoundary(this.fireSpirit.get(i).getxLeftBoundary(), honeyJar.get(j).getX()-100);
				}
				else if(this.fireSpirit.get(i).collidesWith(this.honeyjars.get(j).getBoundsRight())){
					this.fireSpirit.get(i).setNewBoundary(this.honeyjars.get(j).getX()+100, this.fireSpirit.get(i).getxRightBoundary());
				}
			}
		}
		for(int i=0; i<this.wizard.size(); i++) {
			for(int j=0;j<honeyjars.size();j++) {
				if(this.wizard.get(i).collidesWith(this.honeyjars.get(j).getBoundsLeft())) {
					this.wizard.get(i).setNewBoundary(this.wizard.get(i).getxLeftBoundary(), honeyJar.get(j).getX()-100);
				}
				else if(this.wizard.get(i).collidesWith(this.honeyjars.get(j).getBoundsRight())){
					this.wizard.get(i).setNewBoundary(this.honeyjars.get(j).getX()+100, this.wizard.get(i).getxRightBoundary());
				}
			}
		}
		
		for(int i=0; i<this.cat.size(); i++) {
			for(int j=0;j<honeyjars.size();j++) {
				if(this.cat.get(i).collidesWith(this.honeyjars.get(j).getBoundsLeft())) {
					this.cat.get(i).setNewBoundary(this.cat.get(i).getxLeftBoundary(), honeyJar.get(j).getX()-100);
				}
				else if(this.cat.get(i).collidesWith(this.honeyjars.get(j).getBoundsRight())){
					this.cat.get(i).setNewBoundary(this.honeyjars.get(j).getX()+100, this.cat.get(i).getxRightBoundary());
				}
			}
		}
	}
	
	//method for checking if enemy touched
	public void touchEnemy() {
		for(int i=0;i<platforms.size();i++) {
			for(int j=0; j<this.knight.size(); j++) {
				if(this.player.collidesWith(this.knight.get(j))) {
					this.player.setX(100);
					this.player.setY(100);
					GameStage.strength--;
				}
			}
			for(int j=0; j<this.fireSpirit.size(); j++) {
				if(this.player.collidesWith(this.fireSpirit.get(j))) {
					this.player.setX(100);
					this.player.setY(100);
					GameStage.strength--;
				}
			}
			for(int j=0; j<this.wizard.size(); j++) {
				if(this.player.collidesWith(this.wizard.get(j))) {
					this.player.setX(100);
					this.player.setY(100);
					GameStage.strength--;
				}
			}
			
			for(int j=0; j<this.cat.size(); j++) {
				if(this.player.collidesWith(this.cat.get(j))) {
					this.player.setX(100);
					this.player.setY(100);
					GameStage.strength--;
				}
			}
			
		}
	}
	

}
