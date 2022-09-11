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

public class GameStageThree extends GameStage{
	static Group group;
	private Scene scene;
	private Scene mainScene;
	private GraphicsContext gc;
	private GraphicsContext newGc;
	private Canvas canvas;
	private Canvas newCanvas;
	private Stage stage;
//	private TheGame game;
	public final Image bg = new Image("images/inside-castle-background-2.jpg",1366,768,false,false);
	public final Image back = new Image("images/back.png",75,60,false,false);
	public ImageView backBtn = new ImageView(back);
	public final ImageView pressedBack = new ImageView(new Image("images/backpressed.png",75,60,false,false));
	public final ImageView stageThreeImage = new ImageView(new Image("images/stage3start.png",1366,768,false,false));
	public final static int WINDOW_WIDTH = 1366;
	public final static int WINDOW_HEIGHT = 768;
	private GameTimer gametimer;
	

	public GameStageThree(){
		GameStageThree.group = new Group();
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
		this.gametimer = new GameTimer(this.gc,this.scene, this.stage,3,this.mainScene);
//		Rectangle rect1 = new Rectangle(0,GameStage.WINDOW_WIDTH-50,Color.BROWN);
		Button btn = new Button("");
		btn.setLayoutX(0);
		btn.setLayoutY(60);
		btn.setPrefSize(80,75);
		btn.setStyle("-fx-background-color: rgb(47,40,48); ");
//		btn.setStyle("-fx-background-color: rgb(252,90,3);  -fx-font: 28px Courier New;");
		btn.setGraphic(backBtn);
		
		putEventHandler(btn,this.stage);
		
		FadeTransition thirdStage = new FadeTransition(Duration.seconds(2), stageThreeImage);
		thirdStage.setFromValue(0);
		thirdStage.setToValue(1);
		thirdStage.setCycleCount(2);
		// Reverse direction on alternating cycles
		thirdStage.setAutoReverse(true);
		thirdStage.play();
		
		GameStageThree.group.getChildren().addAll(bg1,this.canvas, this.newCanvas,stageThreeImage);

		//add the stage
		stage.setTitle("Castle");
		stage.setScene(this.scene);
		this.gametimer.start();
		stage.show();
	}
	

	



}
