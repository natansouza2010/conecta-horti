package ifsp.edu.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowPrincipal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent grafic = FXMLLoader.load(getClass().getResource("FXMLWindowPrincipal.fxml"));
        Scene scene = new Scene(grafic, 600,400 );
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
