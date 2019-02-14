package view;

/*
 * =====================================================================
 * =																   =
 * =	Created by Giuseppe Barbieri								   =
 * =	Com 290														   =
 * =	Simple Decoder Program										   =
 * =	#3															   =
 * =																   =
 * =====================================================================
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Letter_Object;

public class MainViewController implements Initializable {
	@FXML
	private TableView<Letter_Object> letterTbl;
	@FXML
	private TableColumn<Letter_Object, String> letterCol;
	@FXML
	private TableColumn<Letter_Object, Double> frequencyCol;
	@FXML
	private TableColumn<Letter_Object, Integer> countCol;
	@FXML
	private TextArea textArea, decyptedTxt;
	@FXML
	private TextField keyTxt;
	@FXML
	private Button guessBtn;

	private ArrayList<Letter_Object> letterList;
	@SuppressWarnings("unused")
	private Stage stage;
	private String letters[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setTblCols();
		setArrayList();
		textAreaListener();
		buttonListener();
		guessBtn.setOnAction(e -> guessKey());
	}

	private void guessKey() {
		int key = Integer.parseInt(keyTxt.getText());
		String message = textArea.getText();
		StringBuilder newMessage = new StringBuilder();
		/*
		 * Get character Find what index in list it is move over n spots and add to new
		 * message
		 */
		for (int i = 0; i < message.length(); i++) {
			for (int j = 0; j < letters.length; j++) {
				if (Character.toString(message.charAt(i)).equalsIgnoreCase(letters[j])) {
					newMessage.append(letters[j + key]);
				}
			}
		}
		decyptedTxt.setText(newMessage.toString());
	}

	// Makes sure the use cannot enter anything besides an integer.
	private void buttonListener() {
		keyTxt.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					keyTxt.setText(newValue.replaceAll("[^\\d\\.]", ""));
				}
			}
		});
	}

	private void textAreaListener() {
		/*
		 * This TextArea Listener checks the string in the text area and updates the
		 * letter frequency table
		 */
		textArea.setOnKeyReleased(e -> {
			decyptedTxt.clear();
			setArrayList();
			String s = textArea.getText();
			// Loop through text area string
			for (int i = 0; i < s.length(); i++) {
				// Loop through table list and update frequency
				for (int j = 0; j < letterList.size(); j++) {
					// Compare the text area string at i index to letterList letter at j index and
					// update
					// if they are the same.
					if (Character.toString(s.charAt(i)).equalsIgnoreCase(letterList.get(j).getLetter())) {
						letterList.get(j).setFrequency(letterList.get(j).getFrequency() + 1);
						letterList.get(j).setCount(letterList.get(j).getCount() + 1);
					}
				}
			}

			for (int i = 0; i < letterList.size(); i++) {
				letterList.get(i).setFrequency(((letterList.get(i).getFrequency() / s.length()) * 100));
			}

			updateTable();
		});
	}

	// Updates table with new frequency
	private void updateTable() {
		letterTbl.getItems().clear();
		ObservableList<Letter_Object> tableList3 = FXCollections.observableArrayList(letterList);
		letterTbl.setItems(tableList3);
	}

	// Set columns to listen for Letter_Objects
	private void setTblCols() {
		letterCol.setCellValueFactory(new PropertyValueFactory<Letter_Object, String>("letter"));
		frequencyCol.setCellValueFactory(new PropertyValueFactory<Letter_Object, Double>("frequency"));
		countCol.setCellValueFactory(new PropertyValueFactory<Letter_Object, Integer>("count"));
	}

	// Updates letter table with a fresh list
	private void setArrayList() {
		letterList = new ArrayList<>();
		for (int i = 0; i < letters.length; i++) {
			letterList.add(new Letter_Object(letters[i], 0.0, 0));
		}
		ObservableList<Letter_Object> tableList3 = FXCollections.observableArrayList(letterList);
		letterTbl.setItems(tableList3);
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
