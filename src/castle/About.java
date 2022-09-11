package castle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class About{
	private Group group;
	private Scene scene;
	private Scene sceneee;
	private GraphicsContext gc;
	private GraphicsContext newGc;
	private Canvas canvas;
	private Canvas newCanvas;
	private MediaPlayer mediaPlayer;
	private Stage stage;
	public final Image bg = new Image("images/castle1.jpg",1366,768,false,false);
	public final Image bbg = new Image("images/bg.jpg",1150,675,false,false);
	public final Image bbgg = new Image("images/bg.jpg",1150,100,false,false);
	public final Image back = new Image("images/back.png",75,60,false,false);
	public ImageView backBtn = new ImageView(back);
	public final ImageView pressedBack = new ImageView(new Image("images/backpressed.png",75,60,false,false));
	private MediaPlayer clickPlayer;

	public About(){
		this.group = new Group();
		this.scene = new Scene(group, 1366, 768);
		this.canvas = new Canvas(1366, 768);
		this.newCanvas = new Canvas(1275, 675);
		this.gc = canvas.getGraphicsContext2D();
		this.newGc = newCanvas.getGraphicsContext2D();
	}
	
	public void setStage(Stage stage, Scene sceneee, MediaPlayer mediaPlayer) {
		this.stage = stage;
		this.gc.drawImage(bg,0,0);
		this.gc.drawImage(bbgg,100,50);
		this.newGc.drawImage(bbg,100,200);
		this.sceneee = sceneee;
		this.mediaPlayer = mediaPlayer;

		this.newGc.setLineWidth(5.0);
		this.newGc.setStroke(Color.DARKGOLDENROD);
		this.newGc.setFill(Color.WHITE);
		this.newGc.strokeRect(100,200, 1150, 470);
		this.newGc.strokeRect(100, 50, 1150, 100);
		Font theFont = Font.font("Castellar",FontWeight.BOLD,80);
		this.gc.setFont(theFont);
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("ABOUT",520,125);
		Font newFont = Font.font("Times New Roman",FontWeight.BLACK, 25);
		this.newGc.setFont(newFont);
		this.newGc.setFill(Color.WHITE);
		this.newGc.fillText("In the world of Castle your main goal is to rescue Princess Thea. \r\n", 350,380);
		this.newGc.fillText("\r\n This game is for those who loves adventure and challenge. You'll enjoy unlocking every room, ", 200,380);
		this.newGc.fillText("\r\n\n collecting keys and surpassing every enemy.", 450,380);
		this.newGc.fillText("\r\n\n\n\n This game is created as a project requirement for CMSC22.", 350,380);
		
		Button btn = new Button("");
		btn.setLayoutX(0);
		btn.setLayoutY(60);
		btn.setPrefSize(80,75);
		btn.setStyle("-fx-background-color: rgb(47,40,48); ");
//		btn.setStyle("-fx-background-color: rgb(252,90,3);  -fx-font: 28px Courier New;");
		btn.setGraphic(backBtn);
		
		putEventHandler(btn,this.stage);
		
		this.group.getChildren().addAll(this.canvas, this.newCanvas, btn);

		//add the stage
		stage.setTitle("Castle");
		stage.setScene(this.scene);
		stage.show();
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
	        		try {
						String clickPath = GameMenu.class.getResource("/musics/click_item.mp3").toString();
						Media clickSfx = new Media(clickPath);
						clickPlayer = new MediaPlayer(clickSfx);
						clickPlayer.play();
	        		}
	        		catch(Exception ea) {
	        			System.out.println("Error in getting audio.");
	        		}
//            		GameMenu ins = new GameMenu();
//            		ins.setStage(stage);
            		stage.setScene(sceneee);		//LINE OF CODES SO THAT IT WILL GO BACK TO THE PREVIOUS SCENE WITHOUT INSTANTIATING IT
            		stage.show();
            		mediaPlayer.play();
            	}

            }

			
		});
	}


}
