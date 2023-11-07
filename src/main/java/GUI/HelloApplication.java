package GUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.ReproductorDeMusica;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/Principal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("FeelMe");
        stage.setScene(scene);
        // Configura un controlador para el evento de cierre de la ventana
        stage.setOnCloseRequest(event -> {
            stage.close();
            System.exit(0); // Termina la ejecución de Java
        });
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}