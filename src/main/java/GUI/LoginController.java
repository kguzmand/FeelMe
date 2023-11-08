package GUI;

import controller.Logic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.CheckUp;
import logic.SongList;
import logic.Notification;
import logic.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    @FXML
    private Text singUp;

    @FXML
    private Button logIn;

    public void initialize(URL location, ResourceBundle resources) {
        logIn.setOnAction(this::getInformation);
        singUp.setOnMouseClicked(e -> {
            CheckUp checkUp = new CheckUp();});
    }

    private void getInformation(ActionEvent event) {
        System.out.println("Si etsoy");
        String userText = user.getText();
        String passwordText = password.getText();
        CheckUp checkUp = new CheckUp(userText, passwordText);
        User user = checkUp.logIn();
        SongList songList = new SongList();
        Notification notification = new Notification(4, 1);
        Logic logic = new Logic(songList, notification, user);
        logic.scheduleNotification();
        try {
            // Cargar la vista Principal.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Principal.fxml"));
            Parent root = loader.load();

            // Obtener el controlador de la vista Principal.fxml
            PrincipalController principalController = loader.getController();

            // Configurar cualquier dato necesario en el controlador principalController
            principalController.setLogic(logic);

            // Obtener el Stage actual
            Stage currentStage = (Stage) logIn.getScene().getWindow();

            // Configurar la nueva escena en el Stage principal
            Scene scene = new Scene(root, 600, 400);
            currentStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}