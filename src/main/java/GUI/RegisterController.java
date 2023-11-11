package GUI;

import controller.Logic;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.CheckUp;
import logic.Notification;
import logic.SongList;
import logic.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private TextField nameUser, ageUser, emailUser;

    @FXML
    private PasswordField passwordUser;

    @FXML
    private Button singUp;

    @FXML
    private Text logIn;

    private String name, email, passwordText;
    private int age;


    public void initialize(URL location, ResourceBundle resources) {
        singUp.setOnAction(this::saveInformation);
        logIn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    registeredUser(event);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void saveInformation(ActionEvent event){
        setName(nameUser.getText());
        setAge(Integer.parseInt(ageUser.getText()));
        setEmail(emailUser.getText());
        setPassword(passwordUser.getText());
        CheckUp checkUp = new CheckUp(getName(), getEmail(), getAge(), getPasswordText());
        getAccess(checkUp);
    }

    private void getAccess(CheckUp checkUp){
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
            Stage currentStage = (Stage) singUp.getScene().getWindow();

            // Configurar la nueva escena en el Stage principal
            Scene scene = new Scene(root, 600, 400);
            currentStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void registeredUser(MouseEvent event) throws InterruptedException {
        try {
            // Cargar la vista Principal.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();

            // Obtener el Stage actual
            Stage currentStage = (Stage) singUp.getScene().getWindow();

            // Configurar la nueva escena en el Stage principal
            Scene scene = new Scene(root, 600, 400);
            currentStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordText() {
        return passwordText;
    }

    public void setPassword(String passwordText) {
        this.passwordText = passwordText;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
