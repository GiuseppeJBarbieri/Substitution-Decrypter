package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ShowMainView;

public class App extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		new ShowMainView(arg0);
	}
}
