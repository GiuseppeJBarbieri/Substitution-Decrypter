package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShowMainView {

	public ShowMainView(Stage stage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainViewSkin.fxml"));
		AnchorPane root;
		try {
			root = loader.load();
			MainViewController controller = loader.getController();
			controller.setStage(stage);
			Scene scene = new Scene(root, 678, 381);
			scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
