package castle;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoadingStage {
	private Group group;
	private Scene scene;
	private Scene sceneee;
	private GraphicsContext gc;
	private GraphicsContext newGc;
	private Canvas canvas;
	private Canvas newCanvas;
	private Stage stage;
	//public final Image defenders = new Image("images/defenders3.png",1366,768,false,false);	//new logo
	public final Image defenders = new Image("images/defenders2.png",600,350,false,false);
	public final ImageView bg = new ImageView(new Image("images/castle1.jpg",1366,768,false,false));
	private MediaPlayer loadingPlayer;
	
	public LoadingStage(){
		this.group = new Group();
		this.scene = new Scene(group, 1366, 768);
		this.canvas = new Canvas(1366, 768);
		this.newCanvas = new Canvas(1366, 768);
		this.gc = canvas.getGraphicsContext2D();
		this.newGc = newCanvas.getGraphicsContext2D();

	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		
		try {
			String path = LoadingStage.class.getResource("/musics/enter.mp3").toString();
//			Media bgMusic = new Media(new File("C:\\Users\\Conrad Lax\\eclipse-workspace\\castle\\src\\musics\\Auntie Whispers.mp3").toURI().toString());
			Media enterMusic = new Media(path);
			this.loadingPlayer = new MediaPlayer(enterMusic);
//			Media enterMusic = new Media(new File("C:\\Users\\Conrad Lax\\eclipse-workspace\\castle\\src\\musics\\enter.mp3").toURI().toString());
//			this.loadingPlayer = new MediaPlayer(enterMusic);
		}
		catch(Exception e) {
			System.out.println("Bg music not found in given file path. Ask developer for assistance.");
		}
//		this.gc.drawImage(defenders, 0, 0);		//new logo
		this.gc.drawImage(defenders, 400,200);
//		Font gcFont = Font.font("Colonna MT",40);
//		this.gc.setFont(gcFont);
//		this.gc.setFill(Color.MAROON);	//or CHOCOLATE
//		this.gc.fillText("Developed by",360,210);
//		
		Font theFont = Font.font("Candara",FontWeight.EXTRA_LIGHT,50);
		this.newGc.setFont(theFont);
		this.newGc.setFill(Color.MAROON);	//or CHOCOLATE
		this.newGc.fillText("A CMSC 22 Project",480,350);
		//this.scene.setFill(Color.BLACK);								//new logo
		//this.newGc.drawImage(new Image("images/intro.png",1366,768,false,false), 0, 0);	//new logo
		
//		Text text = new Text("Press Enter");
//		text.setLayoutX(440);
//		text.setLayoutY(600);
//		text.setFont(Font.font ("Stencil", 70));
//		text.setFill(Color.RED);
//		
//		DropShadow ds = new DropShadow();
//		ds.setOffsetY(10.0f);
//		ds.setColor(Color.BLACK);
//		 
//		
//		text.setEffect(ds);
//		text.setCache(true);
		ImageView pressEnter = new ImageView(new Image("images/enter.png",1366,768,false,false));
		pressEnter.setLayoutY(200);
		
		FadeTransition first = new FadeTransition(Duration.seconds(2), this.newGc.getCanvas());
		first.setFromValue(0);
		first.setToValue(1.0);
		first.setCycleCount(2);
		// Reverse direction on alternating cycles
		first.setAutoReverse(true);
		
		FadeTransition second = new FadeTransition(Duration.seconds(2), this.gc.getCanvas());
		second.setFromValue(0);
		second.setToValue(1.0);
		second.setCycleCount(2);
		// Reverse direction on alternating cycles
		second.setAutoReverse(true);
		
		FadeTransition third = new FadeTransition(Duration.seconds(2), bg);
		third.setFromValue(0);
		third.setToValue(1.0);
		third.setCycleCount(2);
		third.setRate(3);
//		third.setAutoReverse(true);
		
		FadeTransition fourth = new FadeTransition(Duration.seconds(2), pressEnter);
		fourth.setFromValue(0);
		fourth.setToValue(1.0);
		
        SequentialTransition sequTransition = new SequentialTransition();
        // Rectangle is the node for all animations
        sequTransition.setNode(this.newGc.getCanvas());
        // Add animations to the list
        sequTransition.getChildren().addAll(first, second,third,fourth);
        // Play the Animation
        sequTransition.play();
        
		this.group.getChildren().addAll(this.newCanvas,this.canvas,bg,pressEnter);
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent evt) {
            	if(evt.getCode()==KeyCode.ENTER) {
            		loadingPlayer.setRate(2);
            		loadingPlayer.play();
            		GameMenu menu = new GameMenu();
            		menu.setStage(stage);
            	}
            	
            }

        });

		//add the stage
		stage.setTitle("Castle");
		stage.setScene(this.scene);
		stage.show();
	}
}
