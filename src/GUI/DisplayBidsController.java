package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import entite.Bid;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceBid;


public class DisplayBidsController implements Initializable {
    @FXML
    public TableView<Bid> list_bids;
    @FXML
    public TableColumn<?, ?> id_bid_col;
    @FXML
    public TableColumn<?, ?> id_user_col;
    @FXML
    public TableColumn<?, ?> id_listing_col;
    @FXML
    public TableColumn<?, ?> bid_amount_col;
    @FXML
    public TableColumn<?, ?> entry_date_col;
    @FXML
    public TableColumn<?, ?> entry_time_col;
    @FXML
    public TableColumn<?, ?> active_duration_col;
    @FXML
    public TableColumn<?, ?> type_col;
    @FXML
    public ChoiceBox<String> bid_type;

    Stage Stage_1;
    Stage Stage_2;
    ServiceBid sb = new ServiceBid();
    List<Bid> lt = sb.afficherBids();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Bid> datalist = FXCollections.observableArrayList(lt);

        id_bid_col.setCellValueFactory(new PropertyValueFactory<>("id_bid"));
        id_user_col.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        id_listing_col.setCellValueFactory(new PropertyValueFactory<>("id_listing"));
        bid_amount_col.setCellValueFactory(new PropertyValueFactory<>("bid_amount"));
        entry_date_col.setCellValueFactory(new PropertyValueFactory<>("entry_date"));
        entry_time_col.setCellValueFactory(new PropertyValueFactory<>("entry_time"));
        active_duration_col.setCellValueFactory(new PropertyValueFactory<>("active_duration"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        //System.out.println(lt);
        list_bids.setItems(datalist);

        ObservableList<String> availableChoices = FXCollections.observableArrayList("cancelled","active");
        bid_type.setItems(availableChoices);
    }

    public void go_back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Enter_Bid.fxml"));
            Stage_2 = (Stage)((Node)event.getSource()).getScene().getWindow();
            root.setOnMousePressed(pressEvent -> {
                root.setOnMouseDragged(dragEvent -> {
                    Stage_2.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                    Stage_2.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                });
            });
            Scene scene = new Scene(root);

            Stage_2.setScene(scene);
            Stage_2.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public int getSelectedRowIndexAndExtractIntValue(TableView<?> tableView) {
        int rowIndex = tableView.getSelectionModel().getSelectedIndex();
        TableColumn<?, ?> firstColumn = tableView.getColumns().get(0);
        int intValue = Integer.parseInt(firstColumn.getCellData(rowIndex).toString());
        return intValue;
    }
    public void DeleteBid(ActionEvent event) {
        int value = getSelectedRowIndexAndExtractIntValue(list_bids);
        ServiceBid sb = new ServiceBid();
        sb.supprimerBid(value);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Display_Bids.fxml"));
            Stage_1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            root.setOnMousePressed(pressEvent -> {
                root.setOnMouseDragged(dragEvent -> {
                    Stage_1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                    Stage_1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                });
            });
            Scene scene = new Scene(root);

            Stage_1.setScene(scene);
            Stage_1.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        list_bids.refresh();


    }
    public void ModifyBid(ActionEvent event) {
        int value = getSelectedRowIndexAndExtractIntValue(list_bids);
        ServiceBid sb = new ServiceBid();
        sb.modifierBid(value, bid_type.getSelectionModel().getSelectedItem());
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Display_Bids.fxml"));
            Stage_1 = (Stage)((Node)event.getSource()).getScene().getWindow();
            root.setOnMousePressed(pressEvent -> {
                root.setOnMouseDragged(dragEvent -> {
                    Stage_1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                    Stage_1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                });
            });
            Scene scene = new Scene(root);

            Stage_1.setScene(scene);
            Stage_1.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        list_bids.refresh();
    }

    public void app_close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }



}
