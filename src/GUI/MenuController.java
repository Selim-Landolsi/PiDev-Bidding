package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * FXML Controller class
 *
 * @author SBS
 */
public class MenuController {
    Stage Stage_1;
    Stage Stage_2;
    @FXML
    private void OpenBid(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Enter_Bid.fxml"));
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

    @FXML
    private void OpenExchange(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/EnterExchange.fxml"));
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
}
