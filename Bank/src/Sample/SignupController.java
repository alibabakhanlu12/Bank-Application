package Sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    public Label captcha;
    @FXML
    public Button newCaptchaButton;

    public String GenerateCaptcha() {

        char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
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
    }
}
