package castle;

import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameStageTwo extends GameStage{
	static Group group;
	private Scene scene;
	private Scene mainScene;
	private GraphicsContext gc;
	private GraphicsContext newGc;
	private Canvas canvas;
	private Canvas newCanvas;
	private Stage stage;
//	private TheGame game;
	public final Image bg = new Image("images/castle-game-background-5.jpg",1366,768,false,false);
	public final Image back = new Image("images/back.png",75,60,false,false);
	public ImageView backBtn = new ImageView(back);
	public final ImageView pressedBack = new ImageView(new Image("images/backpressed.png",75,60,false,false));
	public final ImageView stageTwoImage = new ImageView(new Image("images/stage2start.png",1366,768,false,false));
	public final static int WINDOW_WIDTH = 1366;
	public final static int WINDOW_HEIGHT = 768;
	private GameTimer gametimer;
	

	public GameStageTwo(){
		GameStageTwo.group = new Group();
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
		this.gametimer = new GameTimer(this.gc,this.scene, this.stage,2,this.mainScene);
//		Rectangle rect1 = new Rectangle(0,GameStage.WINDOW_WIDTH-50,Color.BROWN);
		Button btn1 = new Button("");
		btn1.setLayoutX(100);
		btn1.setLayoutY(60);
		btn1.setPrefSize(80,75);
		btn1.setStyle("-fx-background-color: rgb(47,40,48); ");
//		btn.setStyle("-fx-background-color: rgb(252,90,3);  -fx-font: 28px Courier New;");
		btn1.setGraphic(backBtn);
		
		putEventHandler(btn1,this.stage);
		
		FadeTransition secondStage = new FadeTransition(Duration.seconds(2), stageTwoImage);
		secondStage.setFromValue(0);
		secondStage.setToValue(1);
		secondStage.setCycleCount(2);
		// Reverse direction on alternating cycles
		secondStage.setAutoReverse(true);
		secondStage.play();
		GameStageTwo.group.getChildren().addAll(bg1,this.canvas, this.newCanvas,stageTwoImage);

		//add the stage
		stage.setTitle("Castle");
		stage.setScene(this.scene);
		this.gametimer.start();
		stage.show();
	}
	


}
