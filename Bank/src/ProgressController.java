import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProgressController implements Initializable {

    @FXML
    ProgressBar ProgressBar;

    double progress = 0.0;

    Parent root;
    Stage stage;
    Scene scene;

    Timeline timeLine = new Timeline(
            new KeyFrame(Duration.millis(1),
                    e -> {
                        if (progress<1) {
                            progress += 0.0003;
                            ProgressBar.setProgress(progress);
                        }
                        else {
                            try {
                                switchScene();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            stopTimeLine();
                        }
                    })
    );

    public void stopTimeLine()  {
        timeLine.stop();
    }
    public void switchScene() throws IOException{

        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ProgressBar.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        timeLine.setCycleCount(Animation.INDEFINITE);
        timeLine.play();
    }
}
