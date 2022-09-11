package castle;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

public class GameMenu{
	private Group group;
	private Scene scene;
	private GraphicsContext gc;
	private GraphicsContext newGc;
	private Canvas canvas;
	private Canvas newCanvas;
	public Stage stage;
	public static MediaPlayer mediaPlayer,clickPlayer;
	public final Image bg = new Image("images/castle1.jpg",1366,768,false,false);
	public final Image start = new Image("images/start.png",190,70,false,false);
	public final ImageView startBtn = new ImageView(start);		
	public final ImageView pressedStart = new ImageView(new Image("images/startpressed.png",190,70,false,false));
	public final ImageView insBtn = new ImageView(new Image("images/instruction.png",190,70,false,false));
	public final ImageView pressedIns = new ImageView(new Image("images/instructionpressed.png",190,70,false,false));
	public final ImageView abtBtn = new ImageView(new Image("images/about.png",190,70,false,false));
	public final ImageView pressedAbt = new ImageView(new Image("images/aboutpressed.png",190,70,false,false));
	public final Image logo = new Image("images/castle.png",700,300,false,false);

	public GameMenu(){
		this.group = new Group();
		this.scene = new Scene(group, 1366, 768);
		this.canvas = new Canvas(1366, 768);
		this.newCanvas = new Canvas(1366,768);
		this.gc = canvas.getGraphicsContext2D();
		this.newGc = newCanvas.getGraphicsContext2D();	
		}
	
	public void setStage(Stage stage) {
		
		ImageView background = new ImageView(this.bg);
		background.setOpacity(0.8);
//		this.gc.drawImage(bg,0,0);
		this.gc.setFill(Color.rgb(47,40,48,0.1));
		try {
			String path = GameMenu.class.getResource("/musics/Auntie Whispers.mp3").toString();
//			Media bgMusic = new Media(new File("C:\\Users\\Conrad Lax\\eclipse-workspace\\castle\\src\\musics\\Auntie Whispers.mp3").toURI().toString());
			Media bgMusic = new Media(path);
			GameMenu.mediaPlayer = new MediaPlayer(bgMusic);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			
//			String clickPath = GameMenu.class.getResource("/musics/click_item.mp3").toString();
//			Media clickSfx = new Media(clickPath);
//			GameMenu.clickPlayer = new MediaPlayer(clickSfx);
			
		}
		catch(Exception e) {
			System.out.println("Bg music not found in given file path. Ask developer for assistance.");
		}

		this.stage = stage;
		startBtn.setSmooth(true);
		
//		this.gc.setFill(Color.DARKGRAY);										//set font color of text
//		this.gc.fillRect(0,0,1366,768);
		
//		Font theFont = Font.font("Colonna MT",150);
		
//		ImageView bgpaper = new ImageView(new Image("images/bgPaper2.png",800,250,false,false));
//		bgpaper.setLayoutX(275);
//		bgpaper.setLayoutY(117);
//		FadeTransition transRect = new FadeTransition(Duration.seconds(2), bgpaper);
//		transRect.setFromValue(0.1);
//		transRect.setToValue(1.0);
//		transRect.setCycleCount(1);
//		// Reverse direction on alternating cycles
//		transRect.setAutoReverse(true);
        
        this.newGc.drawImage(logo, 450, 80);
//		this.newGc.setFont(theFont);
//		this.newGc.setFill(Color.MAROON);	//or CHOCOLATE
//		this.newGc.fillText("CASTLE",380,300);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(10.0f);
		ds.setColor(Color.BLACK);
		 
		
		this.newGc.getCanvas().setEffect(ds);
		this.newGc.getCanvas().setCache(true);
	
        TranslateTransition transTitle = new TranslateTransition(Duration.seconds(2), this.newGc.getCanvas());
        transTitle.setFromX(scene.getWidth()-scene.getWidth()+50);
        transTitle.setToX(-1.0 * 35);
        
//        TranslateTransition transBg = new TranslateTransition(Duration.seconds(2), bgpaper);
//        transBg.setFromX(scene.getWidth()-scene.getWidth()+50);
//        transBg.setToX(-1.0 * 35);
        
		FadeTransition transText = new FadeTransition(Duration.seconds(2), this.newGc.getCanvas());
		transText.setFromValue(0.1);
		transText.setToValue(1.0);
		transText.setCycleCount(1);
		// Reverse direction on alternating cycles
		transText.setAutoReverse(true);
        
        //play both title and image transition
        
        ParallelTransition parTransition = new ParallelTransition();
        parTransition.setNode(this.gc.getCanvas());
        // Add the Children to the ParallelTransition
        parTransition.getChildren().addAll(transText, transTitle);
        // Play the Animation
        parTransition.play();     

		Button btn = new Button("");
		btn.setLayoutX(550);
		btn.setLayoutY(400);
		btn.setPrefSize(200,80);
//		btn.setGraphic(startBtn);
		btn.setId("start");
		btn.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/start.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat; -fx-background-position: center; ");
		putEventHandler(btn,this.stage,this.scene);
		FadeTransition transStart = new FadeTransition(Duration.seconds(2), btn);
        transStart.setFromValue(0);
        transStart.setToValue(1.0);
//        transStart.play();
		
		Button btn1 = new Button("");
		btn1.setLayoutX(550);
		btn1.setLayoutY(500);
		btn1.setPrefSize(200,80);
//		btn1.setGraphic(insBtn);
		btn1.setId("instruction");
		btn1.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/instruction.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat;\r\n -fx-background-position: center; ");
		putEventHandler(btn1,this.stage,this.scene);
		FadeTransition transIns = new FadeTransition(Duration.seconds(2), btn1);
		transIns.setFromValue(0);
		transIns.setToValue(1.0);
//		transIns.play();
        
		Button btn2 = new Button("");
		btn2.setLayoutX(550);
		btn2.setLayoutY(600);
		btn2.setPrefSize(200,80);
//		btn2.setGraphic(abtBtn);
		btn2.setId("about");
		btn2.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/about.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat;\r\n -fx-background-position: center; ");
		putEventHandler(btn2,this.stage,this.scene);
		FadeTransition transAbt = new FadeTransition(Duration.seconds(2), btn2);
		transAbt.setFromValue(0);
		transAbt.setToValue(1.0);
//		transAbt.play();
		
        SequentialTransition sequTransition = new SequentialTransition();
        // Rectangle is the node for all animations
        sequTransition.setNode(this.gc.getCanvas());
        // Add animations to the list
        sequTransition.getChildren().addAll(transStart, transIns,transAbt);
        // Let the animation run forever
        sequTransition.setCycleCount(1);
        sequTransition.setRate(2.5);
        // Play the Animation
        sequTransition.play();
		
		this.group.getChildren().addAll(background,this.canvas,this.newCanvas,btn,btn1,btn2);
		

		//add the stage
		stage.setTitle("Castle");
		stage.setScene(this.scene);
		stage.show();
	}
	
	public void putEventHandler(Button btn, Stage stage,Scene scene) {
		btn.setOnMousePressed(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent evt) {
//            	if(btn.getGraphic()==startBtn) 
//            	btn.setGraphic(pressedStart);
            	if(btn.getId()=="start")
            	{
					btn.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/startpressed.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat; -fx-background-position: center; ");
					btn.setId("startpressed");
            	}

            	
//            	if(btn.getGraphic()==insBtn) {
//                    btn.setGraphic(pressedIns);
//            	}
            	else if(btn.getId()=="instruction") {
					btn.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/instructionpressed.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat; -fx-background-position: center; ");
					btn.setId("instructionpressed");System.out.println("play");
					
            	}

            	
//            	if(btn.getGraphic()==abtBtn) {
//                    btn.setGraphic(pressedAbt);
//
//            	}
            	else if(btn.getId()=="about") {
					btn.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/aboutpressed.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat; -fx-background-position: center; ");
					btn.setId("aboutpressed");
            	}

            }

        });
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent evt) {
//            	if(btn.getGraphic()==startBtn) 
            	if(btn.getId()=="startpressed")
            	{
            		try {
        					String clickPath = GameMenu.class.getResource("/musics/click_item.mp3").toString();
        					Media clickSfx = new Media(clickPath);
        					GameMenu.clickPlayer = new MediaPlayer(clickSfx);
        					GameMenu.clickPlayer.play();
                		}
                		catch(Exception e) {
                			System.out.println("Error in getting audio.");
                		}
              		GameStage gameStage = new GameStage();
              		
            		gameStage.setStage(stage,scene);
            		gameStage.setStrength(3);
            		gameStage.setScore(0);
            		mediaPlayer.stop();
   
            	}

            	
//            	if(btn.getGraphic()==insBtn) 
            	else if(btn.getId()=="instructionpressed")
            	{
            		try {
        					String clickPath = GameMenu.class.getResource("/musics/click_item.mp3").toString();
        					Media clickSfx = new Media(clickPath);
        					GameMenu.clickPlayer = new MediaPlayer(clickSfx);
        					GameMenu.clickPlayer.play();
                		}
                		catch(Exception e) {
                			System.out.println("Error in getting audio.");
                		}
            		InstructionsStage insStage = new InstructionsStage();
            		insStage.setStage(stage, scene,mediaPlayer);
            		mediaPlayer.stop();
   
            	}
            	
//            	if(btn.getGraphic()==abtBtn) 
            	else if(btn.getId()=="aboutpressed")
            	{
            		try {
    					String clickPath = GameMenu.class.getResource("/musics/click_item.mp3").toString();
    					Media clickSfx = new Media(clickPath);
    					GameMenu.clickPlayer = new MediaPlayer(clickSfx);
    					GameMenu.clickPlayer.play();
            		}
            		catch(Exception e) {
            			System.out.println("Error in getting audio.");
            		}
            		About abtStage = new About();
            		abtStage.setStage(stage, scene,mediaPlayer);
            		mediaPlayer.stop();

            	}

            }

        });
		btn.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent evt) {
//				if(btn.getGraphic()==pressedStart)
//				btn.setGraphic(startBtn);
				if(btn.getId()=="start") {
					btn.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/start.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat; -fx-background-position: center; ");
					btn.setId("startpressed");
				}
				
//				else if(btn.getGraphic()==pressedIns)
//					btn.setGraphic(insBtn);
				else if(btn.getId()=="instruction") {
					btn.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/instruction.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat; -fx-background-position: center; ");
					btn.setId("instructionpressed");
				}
//			    else if(btn.getGraphic()==pressedAbt)
//            		btn.setGraphic(abtBtn);
				else if(btn.getId()=="about") {
					btn.setStyle("-fx-background-color: rgb(252,90,3);-fx-background-image: url(images/about.png); -fx-background-size: 200px 80px; -fx-background-repeat: no-repeat; -fx-background-position: center; ");
					btn.setId("aboutpressed");
				}
				
			}
			
		});
	}


}
