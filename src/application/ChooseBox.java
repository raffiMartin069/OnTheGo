package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ChooseBox implements Initializable {

	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private ChoiceBox<String> loc_choice;

    private String[] destination = {"Location 1", "Location 2", "Location 3"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loc_choice.getItems().addAll(destination);
        loc_choice.setOnAction(this::getDestiny);
    }

    public void getDestiny(ActionEvent event) {
        String myDestination = loc_choice.getValue();
        //myLabel.setText(myDestination);
    }
    
    public void SwitchToTickets (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("TiksHistory.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
    
    public void SwitchToOperator (ActionEvent event) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("TicketWindow.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
