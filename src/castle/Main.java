package castle;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		LoadingStage menu = new LoadingStage();
		menu.setStage(stage);
	}

}
