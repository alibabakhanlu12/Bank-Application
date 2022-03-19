package Sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    String[] roles = {"customer", "employee", "admin"};

    @FXML
   private ChoiceBox<String> rolesChoiceBox;
    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup1;

    @FXML
    private PasswordField pass_login;
    @FXML
    private TextField user_login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rolesChoiceBox.getItems().addAll(roles);
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButilsLS.loninUser(event, user_login.getText(), pass_login.getText());
            }
        });
        btn_signup1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButilsLS.changeScene("Signup.fxml", event, "sign Up", null, null);
            }
        });


    }
}
