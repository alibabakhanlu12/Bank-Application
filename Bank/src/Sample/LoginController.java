package Sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    String[] roles = {"customer", "employee", "admin"};

    @FXML
    ChoiceBox<String> rolesChoiceBox;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rolesChoiceBox.getItems().addAll(roles);
    }
}
