package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import logic.Logic;
import logic.Notification;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    @FXML
    private MenuButton emotionsMenu;

    @FXML
    private MenuItem option1, option2, option3, option4, option5, option6;

    @FXML
    private ImageView songImage, buttonImage;

    @FXML
    private Text songName;

    @FXML
    private Button playerButton;
    Logic logic = new Logic();
    private int actions = 2;

    private Notification notification;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configura los manejadores de eventos para los MenuItem
        option1.setOnAction(e -> handleOption(1));
        option2.setOnAction(e -> handleOption(2));
        option3.setOnAction(e -> handleOption(3));
        option4.setOnAction(e -> handleOption(4));
        option5.setOnAction(e -> handleOption(5));
        option6.setOnAction(e -> handleOption(6));
        playerButton.setOnAction(this::handlePlayerButton);
    }
    @FXML
    protected void handleOption(int num) {logic.scheduleNotification(num);}
    @FXML
    private void handlePlayerButton(ActionEvent actionEvent){
        if(actions == 2){
            actions--;
        }else{
            actions++;
        }
        System.out.println(actions);
        logic.songOptions(actions);
    }
}
