package Sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    public Label captcha;
    @FXML
    public Button newCaptchaButton;

    @FXML
    private TextField accountnumber,username ,accounttype, availibility ,deposit,email,lastName ,name ,newcaptha,withdraw;
      @FXML
   private DatePicker date;
    @FXML
    private PasswordField password, confpassword;

@FXML
private Button signup;


    public String GenerateCaptcha() {

        char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's','t', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
                 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] index = new char[6];

        Random r = new Random();
        int i;
        String captcha = "";

        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
            captcha = String.valueOf(index);
        }
        return captcha;
    }

    public void onNewCaptchaButtonClick(ActionEvent e) {
        String Captcha = GenerateCaptcha();
        captcha.setText(Captcha);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String Captcha = GenerateCaptcha();
        captcha.setText(Captcha);

        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!(name.getText().trim().isEmpty())
                        &&!(accountnumber.getText().trim().isEmpty()) &&!(accountnumber.getText().trim().isEmpty()) && !(availibility.getText().trim().isEmpty())
                        &&!(accounttype.getText().trim().isEmpty()) && !(deposit.getText().trim().isEmpty()) &&
                       !(lastName.getText().trim().isEmpty()) &&(password.getText().equals(confpassword.getText())) &&
                        !(lastName.getText().trim().isEmpty()) && !(confpassword.getText().trim().isEmpty())
                        && !(password.getText().trim().isEmpty()) && !(lastName.getPromptText().trim().isEmpty())) {


//
                    DButilsLS.singUp(event, name.getText(), lastName.getText(), username.getText(),
                            email.getText(), password.getText(), confpassword.getText(), String.valueOf(date.getValue()),availibility.getText()
                            , withdraw.getText(), deposit.getText(), accounttype.getText(),
                            newcaptha.getText(), accountnumber.getText());

                } else {
                    if (!(password.getText().equals(confpassword.getText()))) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setContentText("your entered passwords are not same!!");
                        alert2.show();


                    }
                    if (confpassword.getText().trim().isEmpty() && password.getText().trim().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("please fill passwords to sign up");
                        alert.show();
                    }
                    if ((username.getText().trim().isEmpty())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("please fill username to sign up");
                        alert.show();
                    }
                    if ((email.getText().trim().isEmpty())) {

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("please fill email to sign up");
                        alert.show();
                    }

                }
            }
        });

    }
}
