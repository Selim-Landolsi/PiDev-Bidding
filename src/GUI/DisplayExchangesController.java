/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import entite.Bid;
import entite.Exchange;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceBid;
import service.ServiceExchange;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class DisplayExchangesController implements Initializable {
    @FXML
    public TableView<Exchange> list_exchanges;
    @FXML
    public TableColumn<?, ?> id_exchange_col;
    @FXML
    public TableColumn<?, ?> id_listing_1_col;
    @FXML
    public TableColumn<?, ?> id_user_1_col;
    @FXML
    public TableColumn<?, ?> id_listing_2_col;
    @FXML
    public TableColumn<?, ?> id_user_2_col;
    @FXML
    public TableColumn<?, ?> offer_status_col;
    @FXML
    public TableColumn<?, ?> date_created_col;
    @FXML
    public TableColumn<?, ?> value_diff_col;
    public ChoiceBox offer_status;

    Stage Stage_1;
    Stage Stage_2;
    ServiceExchange se = new ServiceExchange();
    List<Exchange> lt = se.afficherExchange();

    public DisplayExchangesController() throws SQLException {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Exchange> datalist = FXCollections.observableArrayList(lt);

        id_exchange_col.setCellValueFactory(new PropertyValueFactory<>("id_exchange"));
        id_listing_1_col.setCellValueFactory(new PropertyValueFactory<>("id_listing_1"));
        id_user_1_col.setCellValueFactory(new PropertyValueFactory<>("id_user_1"));
        id_listing_2_col.setCellValueFactory(new PropertyValueFactory<>("id_listing_2"));
        id_user_2_col.setCellValueFactory(new PropertyValueFactory<>("id_user_2"));
        offer_status_col.setCellValueFactory(new PropertyValueFactory<>("offer_status"));
        date_created_col.setCellValueFactory(new PropertyValueFactory<>("date_created"));
        value_diff_col.setCellValueFactory(new PropertyValueFactory<>("value_diff"));
        list_exchanges.setItems(datalist);

        ObservableList<String> availableChoices = FXCollections.observableArrayList("pending","accepted");
        offer_status.setItems(availableChoices);

    }

    public void GoBack(ActionEvent event) {
    }

    public void app_close(ActionEvent event) {
    }

    public void DeleteExchange(ActionEvent event) {
    }

    public void ModifyExchange(ActionEvent event) {
    }
}
