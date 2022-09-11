package castle;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ResultStage {
	private Scene scene,mainScene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private MediaPlayer endingPlayer,gameOverPlayer;
	
	static int score=0;
	
	public ResultStage(){
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.BLACK);	
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);	
		this.gc = canvas.getGraphicsContext2D();
	}
	
	public void setStage(Stage stage,Scene mainScene, int res) {
		this.stage = stage;
		this.mainScene = mainScene;
		if(res==1) {
    		try {
					String endingPath = ResultStage.class.getResource("/musics/Euterpe_cut.mp3").toString();
					Media endingBGM = new Media(endingPath);
					this.endingPlayer = new MediaPlayer(endingBGM);
					this.endingPlayer.play();
					this.endingPlayer.setAutoPlay(true);
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
//			this.gc.drawImage(new Image("images/youwon.gif",WINDOW_WIDTH,WINDOW_HEIGHT,false,false),0,0);
			ImageView encounter = new ImageView(new Image("images/room with princess.png",1366,552,false,false));
			ImageView thanks = new ImageView(new Image("images/thankyou.png",1366,768,false,false));
			ImageView reward = new ImageView(new Image("images/room_blurred.jpg",1366,552,false,false));
			ImageView theEnd = new ImageView(new Image("images/the_end.png",1366,768,false,false));
			ImageView enter_small = new ImageView(new Image("images/enter_small.png",1366,768,false,false));
			ImageView sunset = new ImageView(new Image("images/sunset.png",1366,768,false,false));
			
			encounter.setLayoutY(100);
			reward.setLayoutY(100);
			
			FadeTransition first = new FadeTransition(Duration.seconds(4), encounter);
			first.setFromValue(0);
			first.setToValue(1.0);
			first.setCycleCount(2);
			// Reverse direction on alternating cycles
			first.setAutoReverse(true);
			
			FadeTransition second = new FadeTransition(Duration.seconds(2), thanks);
			second.setFromValue(0);
			second.setToValue(1.0);
			second.setCycleCount(2);
			// Reverse direction on alternating cycles
			second.setAutoReverse(true);			// Reverse direction on alternating cycles
			
			FadeTransition third = new FadeTransition(Duration.seconds(1.5), reward);
			third.setFromValue(0);
			third.setToValue(1.0);
			third.setCycleCount(2);
			// Reverse direction on alternating cycles
			third.setAutoReverse(true);
			
			FadeTransition fourth = new FadeTransition(Duration.seconds(4), theEnd);
			fourth.setFromValue(0);
			fourth.setToValue(1.0);
			fourth.setCycleCount(2);
			// Reverse direction on alternating cycles
			fourth.setAutoReverse(true);
			
			FadeTransition fifth = new FadeTransition(Duration.seconds(4), sunset);
			fifth.setFromValue(0);
			fifth.setToValue(1.0);
			fifth.setCycleCount(2);
			// Reverse direction on alternating cycles
			fifth.setAutoReverse(true);
			
			FadeTransition enter = new FadeTransition(Duration.seconds(1.5), enter_small);
			enter.setFromValue(0);
			enter.setToValue(1.0);
//			enter.setCycleCount(2);
			// Reverse direction on alternating cycles
//			enter.setAutoReverse(true);
			
			//transition for previous nodes
	        SequentialTransition sequTransition = new SequentialTransition();
	        // Rectangle is the node for all animations
	        sequTransition.setNode(this.root);
	        // Add animations to the list
	        sequTransition.getChildren().addAll(first,second,third,fourth,fifth,enter);
	        // Play the Animation
	        sequTransition.play();
	        //add nodes to Group root
	        this.root.getChildren().addAll(canvas,encounter,thanks,reward,theEnd,sunset,enter_small);
			
	        //event handler when enter is pressed (which is to exit)
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

	            public void handle(KeyEvent evt) {
	            	if(evt.getCode()==KeyCode.ENTER) {
//	            		stage.setScene(mainScene);
//						stage.show();
	            		endingPlayer.stop();
	            		GameMenu gm = new GameMenu();
	            		gm.setStage(stage);
						}
	            	
	            }

	        });
		}
		else if(res==0) {
    		try {
					String gameoverPath = ResultStage.class.getResource("/musics/you_died.mp3").toString();
					Media youDiedSfx = new Media(gameoverPath);
					this.gameOverPlayer = new MediaPlayer(youDiedSfx);
					this.gameOverPlayer.play();
        		}
        		catch(Exception e) {
        			System.out.println("Error in getting audio.");
        		}
			ImageView gameover = new ImageView(new Image("images/gameover.jpg",1366,768,false,false));
			ImageView enter = new ImageView(new Image("images/enter.png",1366,768,false,false));
			ImageView princess_crying = new ImageView();
			try {
				String path = InstructionsStage.class.getResource("/images/princess_crying.gif").toString();
//				move.setImage(new Image(new File("C:\\Users\\Conrad Lax\\eclipse-workspace\\castle\\src\\images\\movement.gif").toURI().toString(),400,250,false,false));
				princess_crying.setImage(new Image(path,117,110,false,false));
			}
			catch(Exception e) {
				System.out.println("Error occured while uploading image.");
			}
			ImageView tower = new ImageView(new Image("images/tower.png",231,500,false,false));
			gameover.setLayoutY(-70);
			princess_crying.setLayoutX(1190);
			princess_crying.setLayoutY(465);
			tower.setLayoutX(1150);
			tower.setLayoutY(280);
//			this.gc.drawImage(new Image("images/lose.png",WINDOW_WIDTH,WINDOW_HEIGHT,false,false),0,0);
		
			//gameover screen
			FadeTransition first = new FadeTransition(Duration.seconds(1.5), gameover);
			first.setFromValue(0);
			first.setToValue(1.0);
			first.setCycleCount(2);
			// Reverse direction on alternating cycles
			first.setAutoReverse(true);
			
			//image for the game result (either a win or lose image)
//			FadeTransition second = new FadeTransition(Duration.seconds(1.5), this.gc.getCanvas());
//			second.setFromValue(0);
//			second.setToValue(1.0);
//			second.setCycleCount(2);
//			// Reverse direction on alternating cycles
//			second.setAutoReverse(true);
			
			FadeTransition third = new FadeTransition(Duration.seconds(2), enter);
			third.setFromValue(1);
			third.setToValue(1.0);
			// Reverse direction on alternating cycles
			
			FadeTransition princessGIF = new FadeTransition(Duration.seconds(2), princess_crying);
			third.setFromValue(0);
			third.setToValue(1.0);
			
			FadeTransition princessTower = new FadeTransition(Duration.seconds(2), tower);
			third.setFromValue(0);
			third.setToValue(1.0);
			

	        
	        ParallelTransition parTransition = new ParallelTransition();
	        parTransition.setNode(this.gc.getCanvas());
	        // Add the Children to the ParallelTransition
	        parTransition.getChildren().addAll(third,princessTower, princessGIF);
	        // Play the Animation
	        
			//transition for previous nodes
	        SequentialTransition sequTransition = new SequentialTransition();
	        // Rectangle is the node for all animations
	        sequTransition.setNode(this.root);
	        // Add animations to the list
	        sequTransition.getChildren().addAll(first,third,parTransition);
	        // Play the Animation
	        sequTransition.play();
	        //add nodes to Group root
	        this.root.getChildren().addAll(gameover,canvas,tower,princess_crying,enter);
			
	        //event handler when enter is pressed (which is to exit)
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

	            public void handle(KeyEvent evt) {
	            	if(evt.getCode()==KeyCode.ENTER) {
	            		gameOverPlayer.stop();
	            		stage.setScene(mainScene);
						stage.show();
//	            		GameMenu gm = new GameMenu();
//	            		gm.setStage(stage);
						}
	            	
	            }

	        });
		}

		
		//set stage elements here	 
		
		//text to state what the user should do to exit
//		Text text = new Text("Press Enter");
//		text.setLayoutX(150);
//		text.setLayoutY(240);
//		text.setFont(Font.font ("Castellar", 40));
//		text.setFill(Color.BLACK);
//		
//		DropShadow ds = new DropShadow();
//		ds.setOffsetY(10.0f);
//		ds.setColor(Color.BLACK);
//		 
//		
//		text.setEffect(ds);
//		text.setCache(true);
		

		
		stage.setTitle("Castle");
		stage.setScene(this.scene);
		
		
		stage.show();
	}
}
