package castle;

import java.io.File;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class InstructionsStage{
	private Group group;
	private Scene scene;
	private GraphicsContext gc;
	private GraphicsContext newGc;
	private Canvas canvas;
	private Canvas newCanvas;
	private Stage stage;
	private Scene sceneee;
	private MediaPlayer mediaPlayer;
	public final Image bg = new Image("images/castle1.jpg",1366,768,false,false);
	public final Image bbg = new Image("images/bg.jpg",1150,675,false,false);
	public final Image bbgg = new Image("images/bg.jpg",1150,100,false,false);
	public final Image movement = new Image("images/movement.gif",190,70,false,false);
	public final Image back = new Image("images/back.png",75,60,false,false);
	public ImageView backBtn = new ImageView(back);
	public final ImageView pressedBack = new ImageView(new Image("images/backpressed.png",75,60,false,false));
	private MediaPlayer clickPlayer;
	
	public InstructionsStage(){
		this.group = new Group();
		this.scene = new Scene(group, 1366, 768);
		this.canvas = new Canvas(1366, 768);
		this.newCanvas = new Canvas(1275, 675);
		this.gc = canvas.getGraphicsContext2D();
		this.newGc=newCanvas.getGraphicsContext2D();
	}
	
	public void setStage(Stage stage, Scene sceneee, MediaPlayer mediaPlayer) {
		this.gc.drawImage(bg,0,0);
		this.stage = stage;
		this.gc.drawImage(bg,0,0);
		this.gc.drawImage(bbgg,100,50);
		this.newGc.drawImage(bbg,100,200);
		this.sceneee = sceneee;
		this.mediaPlayer = mediaPlayer;
		
		this.newGc.setLineWidth(5.0);
		this.newGc.setStroke(Color.DARKGOLDENROD);
		this.newGc.strokeRect(100,200, 1150, 470);
		this.newGc.strokeRect(100, 50, 1150, 100);
		Font theFont = Font.font("Castellar",FontWeight.BOLD,80);
		this.gc.setFont(theFont);
		this.gc.setFill(Color.WHITE);
		this.gc.fillText("Instructions",350,125);
		Font newFont = Font.font("Times New Roman",FontWeight.BLACK, 40);
		this.newGc.setFont(newFont);
		this.newGc.setFill(Color.WHITE);
		this.newGc.fillText("[W] - for jump\t[S] - for duck\n[A] - for left\t\t[D] - for right",650,400);
		this.newGc.drawImage(movement,200, 300, 400, 250);
		ImageView move = new ImageView();
		try {
			String path = InstructionsStage.class.getResource("/images/movement.gif").toString();
//			move.setImage(new Image(new File("C:\\Users\\Conrad Lax\\eclipse-workspace\\castle\\src\\images\\movement.gif").toURI().toString(),400,250,false,false));
			move.setImage(new Image(path,400,250,false,false));
		}
		catch(Exception e) {
			System.out.println("Error occured while uploading image.");
		}
		move.setLayoutX(200);
		move.setLayoutY(300);
		Button btn = new Button("");
		btn.setLayoutX(0);
		btn.setLayoutY(60);
		btn.setPrefSize(80,75);
		btn.setStyle("-fx-background-color: rgb(47,40,48); ");
		btn.setGraphic(backBtn);
		
		putEventHandler(btn,this.stage);
		
		
		this.group.getChildren().addAll(this.canvas, this.newCanvas,move,btn);

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
			public void handle(MouseEvent evt) {
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
//            		GameMenu menu = new GameMenu();
//            		menu.setStage(stage);
            		stage.setScene(sceneee);		//LINE OF CODES SO THAT IT WILL GO BACK TO THE PREVIOUS SCENE WITHOUT INSTANTIATING IT
            		stage.show();					//IMPORTANT IN GAME STAGES / LEVELS
            		mediaPlayer.play();
            	}

			}
		});
	}


}
