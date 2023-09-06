/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entite.Exchange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceExchange;

/**
 * FXML Controller class
 *
 * @author SBS
 */
public class EnterExchangeController implements Initializable {
    @FXML
    public TextField id_listing_1;
    @FXML
    public TextField id_user_1;
    @FXML
    public TextField id_listing_2;
    @FXML
    public TextField id_user_2;

    /**
     * Initializes the controller class.
     */
    Stage Stage_1,Stage_2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void DisplayExchange(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/DisplayExchanges.fxml"));
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

    public void GoBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Menu.fxml"));
            Stage_2 = (Stage)((Node)event.getSource()).getScene().getWindow();
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

    public void EnterExchange(ActionEvent event) {
        int id_list_1= Integer.parseInt(id_listing_1.getText()),id_us_1=Integer.parseInt(id_user_1.getText()),id_list_2=Integer.parseInt(id_listing_2.getText()),id_us_2= Integer.parseInt(id_listing_1.getText());
        ServiceExchange SE = new ServiceExchange();
        Exchange E =new Exchange(id_list_1,id_list_2,id_us_1,id_us_2,"pending");
        SE.ajouterExchange(E);
    }
}
