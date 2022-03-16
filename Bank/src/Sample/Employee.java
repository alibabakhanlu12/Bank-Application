package Sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Employee extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Employee.class.getResource("Employee.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Employee");
        stage.setHeight(620);
        stage.setWidth(1180);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
