package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DummyController implements Initializable{
	
	private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TableView<?> trans_Table1;
    
  //ALERT! ALERT!	
    private boolean ticketBookingCanceled = false;
	
	@FXML
    private TableColumn<Transaction, String> date_Trans;
	
	@FXML
    private TableColumn<Transaction, String> fare_Trans;

    @FXML
    private TableColumn<Transaction, String> from_Trans;
    
    @FXML
    private TableColumn<Transaction, String> gcashNum_Trans;

    @FXML
    private TableColumn<Transaction, String> ref_number_Trans;
    
    @FXML
    private TableColumn<Transaction, String> tax_Trans;

    @FXML
    private TableColumn<Transaction, String> time_Trans;
    
    @FXML
    private TableColumn<Transaction, String> to_Trans;
    
    @FXML
    private TableColumn<Transaction, String> total_Trans;
    
    @FXML
    private TableColumn<Transaction, String> trans_ID;
    
    @FXML
    private TableColumn<Transaction, String> trans_Status;
    
    @FXML
    private TableColumn<Transaction, String> cust_Id;
    
    @FXML
    private TableColumn<Transaction, String> busType_Trans;
    
    @FXML
    private TableView<Transaction> trans_Table;

	 public void SwitchToSearchRoute(ActionEvent event) throws IOException {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchRoute.fxml"));
	        root = loader.load();
	        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();

	        // Retrieve the controller after loading the FXML file
	        TransactController controller = loader.getController();
	        controller.setItems();
	        
		    DatePicker datePicker = controller.getDatePicker();
		    datePicker.setDayCellFactory(date_chc -> new DateCell() {
		    	@Override
		        public void updateItem(LocalDate item, boolean empty) {
		            super.updateItem(item, empty);

		            LocalDate today = LocalDate.now();
		            LocalDate tomorrow = today.plusDays(1);
		            LocalDate dayAfterTomorrow = today.plusDays(2);
		            LocalDate lastDay = today.plusDays(3);

		            // Disable dates before today and after the day after tomorrow
		            if (item.isBefore(today) || item.isAfter(lastDay)) {
		                setDisable(true);
		                setStyle("-fx-background-color: #ffc0cb;"); // Set disabled dates' background color
		            }
		        }
		    });
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {

			transactionInitializer();
			
		}
		
		public void transactionInitializer() {
		    TransactController cx_details = new TransactController();
		    // cx_details.customer_Identification(0);
		    TransactionListData myTransaction = new TransactionListData();
		    ObservableList<Transaction> trans = myTransaction.fetchConfirmData();

		    // Filter out rows with null gcashNum_Trans or ref_number_Trans
		    trans = trans.filtered(t ->
		        t.getPymntNo() != null && t.getRefferNum() != null
		    );

		    trans_ID.setCellValueFactory(new PropertyValueFactory<>("transId"));
		    // cust_Id.setCellValueFactory(new PropertyValueFactory<>("cxId"));
		    from_Trans.setCellValueFactory(new PropertyValueFactory<>("fromTo"));
		    to_Trans.setCellValueFactory(new PropertyValueFactory<>("goTo"));
		    date_Trans.setCellValueFactory(new PropertyValueFactory<>("dateToDay"));
		    time_Trans.setCellValueFactory(new PropertyValueFactory<>("time"));
		    busType_Trans.setCellValueFactory(new PropertyValueFactory<>("busType"));
		    fare_Trans.setCellValueFactory(new PropertyValueFactory<>("fareCost"));
		    tax_Trans.setCellValueFactory(new PropertyValueFactory<>("bookCost"));
		    total_Trans.setCellValueFactory(new PropertyValueFactory<>("total"));
		    gcashNum_Trans.setCellValueFactory(new PropertyValueFactory<>("pymntNo"));
		    ref_number_Trans.setCellValueFactory(new PropertyValueFactory<>("refferNum"));
		    trans_Status.setCellValueFactory(new PropertyValueFactory<>("stat"));

		    trans_Table.setItems(trans); // Set items with the filtered ObservableList<Transaction>
		    System.out.println("Display successful");
		}

		@FXML
		public void customer_cancel(ActionEvent event) {
			try {
		        DBConek link = new DBConek();
		        Connection conn = link.getConnection();

		        // Get the selected transaction from the TableView
		        Transaction selectedTransaction = trans_Table.getSelectionModel().getSelectedItem();

		        cancelAlert(event);
		        /*if (ticketBookingCanceled) {
		            return; // Exit the method
		        }*/
		        
		        // Update the "Status" column for the selected transaction
		        String updateQuery = "UPDATE transact SET payment_status = ? WHERE transact_id = ?";
		        PreparedStatement updatePs = conn.prepareStatement(updateQuery);
		        updatePs.setString(1, "Cancelled");
		        updatePs.setString(2, selectedTransaction.getTransId());

		        updatePs.executeUpdate();
		        updatePs.close();
		        
		        selectedTransaction.setStat("Cancelled");
		        trans_Table.refresh();
		        conn.close();

		        System.out.println("Data updated");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    System.out.println("Button clicked");
		}
		
		public void SwitchToSignIn(ActionEvent event) throws IOException {

			root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		
		public void cancelAlert(ActionEvent event) {
		    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		    alert.setTitle("Cancel Ticket");
		    alert.setHeaderText(null);
		    alert.setContentText("Are you sure you want to cancel?");



		    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
		    ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		    alert.getButtonTypes().add(cancelButton);


		    alert.showAndWait().ifPresent(response -> {
		        if (response == ButtonType.YES) {
		            Alert verificationAlert = new Alert(Alert.AlertType.CONFIRMATION);
		            verificationAlert.setTitle("Verification");
		            verificationAlert.setHeaderText(null);
		            verificationAlert.setContentText("Are you sure you want to cancel? Noted: There's no returns.");

		            ButtonType confirmButton = new ButtonType("Confirm");
		            ButtonType backButton = new ButtonType("Back", ButtonData.CANCEL_CLOSE);
		            verificationAlert.getButtonTypes().setAll(confirmButton, backButton);

		            verificationAlert.showAndWait().ifPresent(verificationResponse -> {
		                if (verificationResponse == confirmButton) {
		                    System.out.println("Ticket cancel confirmed.");
		                    // Additional logic for booking the ticket
		                } else if (verificationResponse == backButton) {
		                    System.out.println("Ticket booking canceled (verification dialog closed).");
		                }
		            });
		        } else if (response == ButtonType.NO) {
		            // User clicked NO
		            ticketBookingCanceled = true;
		            System.out.println("Nice one!");
		        } else {
		            // User closed the dialog
		            System.out.println("Ticket booking canceled (dialog closed).");
		        }
		    });
		}
}
