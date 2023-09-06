/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import entite.Bid;
import entite.User;
import entite.Listing;
import service.ServiceBid;
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
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import service.ServiceBid;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class Enter_BidController implements Initializable {

    @FXML
    private TextField id_listing;
    @FXML
    private TextField id_user;
    @FXML
    private Button Display_list;
    @FXML
    private TextField bid_amount;
    @FXML
    private Button button_bid;
    @FXML
    private Button button_buyout;
    @FXML
    public ChoiceBox<String> userChoice;
    @FXML
    public ChoiceBox<String> listingChoice;

    Stage Stage_1;
    ServiceBid sb;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    
     try{ 
    List<String> users = sb.afficherUsers();
    List<String> listings = sb.afficherListings();
    
    ObservableList<String> usersData= FXCollections.observableArrayList(users);
    ObservableList<String> listingsData= FXCollections.observableArrayList(listings);
    
    userChoice.getItems().addAll(usersData);
    listingChoice.setItems(listingsData);
         
          } catch (Exception ex){System.out.println(ex.getMessage());};
    }    

    @FXML
    private void Enter_Bid(ActionEvent event) {
        ServiceBid sb = new ServiceBid();
        int amount = Integer.parseInt(bid_amount.getText());
        //int bid_duration = Integer.parseInt(nb_of_days.getSelectionModel().getSelectedItem());

        try {
            ServiceBid SB= new ServiceBid();
            //Bid U = new Bid(amount,SB.CalculateActiveDuration(CurrentListing(),CurrentUser()),"active");
            Bid U = new Bid(Integer.parseInt(id_user.getText()),Integer.parseInt(id_listing.getText()),amount, "active");
            System.out.println(U.getBid_amount());
            //controle de saisie
            if (!sb.Bought_out(U.getId_listing())){
             if( (U.getBid_amount() > sb.HighestBid(U.getId_listing()))){
              if(!sb.chercherBid(U)){
                  SB.ajouterBid(U);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Notif");
                  alert.setHeaderText(null);
                  alert.setContentText("Bid entered successfully!");
                  alert.show();
              }
              else { Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Notif");
                  alert.setHeaderText(null);
                  alert.setContentText("You already have an active bid on this!");
                  alert.show();}             }

             else{Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Notif");
                 alert.setHeaderText(null);
                 alert.setContentText("You need to outbid the highest bidder!");
                 alert.show();}
            }
            else {Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Notif");
                alert.setHeaderText(null);
                alert.setContentText("Bid been bought out!");
                alert.show();}


        
    } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    public void list_display(ActionEvent event) {
        ServiceBid sb = new ServiceBid();
        try {  ObservableList<String> usersData= FXCollections.observableArrayList(sb.afficherUsers());
            System.out.println(usersData);
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
    }

    public void app_close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void Enter_Buyout(ActionEvent event) {
        try {
            ServiceBid SB= new ServiceBid();
            //Bid U = new Bid(amount,SB.CalculateActiveDuration(CurrentListing(),CurrentUser()),"active");
            Bid U = new Bid(Integer.parseInt(id_user.getText()),Integer.parseInt(id_listing.getText()),SB.GetListingBuyoutPrice(Integer.parseInt(id_listing.getText())), "buyout");

            //controle de saisie
            //if (){
            // if(){
            //  if(){               }
            // }}

            SB.ajouterBid(U);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notif");
            alert.setHeaderText(null);
            alert.setContentText("Buyout entered successfully!");
            alert.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void GoBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
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
    }
}
