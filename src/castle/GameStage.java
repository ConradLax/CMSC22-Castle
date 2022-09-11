package castle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class GameStage {
	static Group group;
	private Scene scene;
	private Scene mainScene;
	private GraphicsContext gc;
	private GraphicsContext newGc;
	private Canvas canvas;
	private Canvas newCanvas;
	private Stage stage;
//	private TheGame game;
	public final Image bg = new Image("images/inside-castle-dour.jpg",1366,768,false,false);
	public final Image back = new Image("images/back.png",75,60,false,false);
	public ImageView backBtn = new ImageView(back);



	public final ImageView pressedBack = new ImageView(new Image("images/backpressed.png",75,60,false,false));
	public final ImageView stageOneImage = new ImageView(new Image("images/stage1start.png",1366,768,false,false));
	public final static int WINDOW_WIDTH = 1366;
	public final static int WINDOW_HEIGHT = 768;
	private GameTimer gametimer;
	static int strength=3;
	static int score=0;
	

	public GameStage(){
		GameStage.group = new Group();
		this.scene = new Scene(group, 1366, 768);
		this.canvas = new Canvas(1366, 768);
		this.newCanvas = new Canvas(1275, 675);
		this.gc = canvas.getGraphicsContext2D();
		this.newGc = newCanvas.getGraphicsContext2D();
//		this.scene.setFill(Color.BLACK);
//		this.game = new TheGame();
//		this.game.start2();
	}
	
	
	
	public void setStage(Stage stage,Scene mainScene) {
		this.stage = stage;
		this.mainScene = mainScene;
//		this.gc.drawImage(bg,0,0);
		ImageView bg1 = new ImageView(bg);
		this.gametimer = new GameTimer(this.gc,this.scene, this.stage,1,this.mainScene);
//		Rectangle rect1 = new Rectangle(0,GameStage.WINDOW_WIDTH-50,Color.BROWN);
		Button btn = new Button("");
		btn.setLayoutX(0);
		btn.setLayoutY(60);
		btn.setPrefSize(80,75);
		btn.setStyle("-fx-background-color: rgb(47,40,48); ");
//		btn.setStyle("-fx-background-color: rgb(252,90,3);  -fx-font: 28px Courier New;");
		btn.setGraphic(backBtn);
		
		putEventHandler(btn,this.stage);
		FadeTransition firstStage = new FadeTransition(Duration.seconds(2), stageOneImage);
		firstStage.setFromValue(0);
		firstStage.setToValue(1);
		firstStage.setCycleCount(2);
		// Reverse direction on alternating cycles
		firstStage.setAutoReverse(true);
		firstStage.play();
		
		GameStage.group.getChildren().addAll(bg1,this.canvas, this.newCanvas,stageOneImage);

		//add the stage
		stage.setTitle("Castle");
		stage.setScene(this.scene);
		this.gametimer.start();
		stage.show();
	}
	
	
	
	public Group getGroup() {
		return group;
	}


	public void setGroup(Group group) {
		GameStage.group = group;
	}


	public void putEventHandler(Button btn, Stage stage) {
		btn.setOnMousePressed(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent evt) {
            	if(btn.getGraphic()==backBtn) {
                    btn.setGraphic(pressedBack);
            	}

            }

        });
		btn.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt1) {
				if(btn.getGraphic()==pressedBack)
				btn.setGraphic(backBtn);
			}


        });
		
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
            public void handle(MouseEvent evt) {
            	
            	if(btn.getGraphic()==backBtn) {
            		GameMenu ins = new GameMenu();
            		
            		ins.setStage(stage);
            	}

            }

			
		});
	}

	public static int getStrength() {
		return strength;
	}



	public static void setStrength(int strength) {
		GameStage.strength = strength;
	}



	public static int getScore() {
		return score;
	}



	public static void setScore(int score) {
		GameStage.score = score;
	}
}
