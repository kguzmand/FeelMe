package GUI;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import controller.Logic;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    @FXML
    private MenuButton emotionsMenu;

    @FXML
    private MenuItem option1, option2, option3, option4, option5, option6;

    @FXML
    private ImageView songImage, buttonImage;

    @FXML
    private Text songArtist, songName;

    @FXML
    private Button playerButton;
    private int actions;
    Image play = new Image("Play.png");
    Image stop = new Image("Stop.png");
    private Logic logic;

    public PrincipalController() {
        this.actions = 2;
    }
    public void initialize(URL location, ResourceBundle resources) {

        // Configura los manejadores de eventos para los MenuItem
        option1.setOnAction(e -> handleOption(1));
        option2.setOnAction(e -> handleOption(2));
        option3.setOnAction(e -> handleOption(3));
        option4.setOnAction(e -> handleOption(4));
        option5.setOnAction(e -> handleOption(5));
        option6.setOnAction(e -> handleOption(6));
        playerButton.setOnAction(this::handlePlayerButton);

        // Configura la animación de desplazamiento horizontal
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(4), songName);
        // Establece el desplazamiento horizontal a lo largo del eje X
        translateTransition.setByX(40); // Ajusta la distancia del desplazamiento
        // Configura la animación para que se repita indefinidamente
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        // Inicia la animación
        translateTransition.play();
    }
    @FXML
    protected void handleOption(int num) {
        String emotion = logic.emotionChoice(num);
        emotionsMenu.setText("Me siento... " + emotion);
        logic.getNotification().setChoice(num);
        buttonImage.setImage(stop);
        Platform.runLater(() -> {
            songName.setText(logic.songName());
            songArtist.setText(logic.artistName());
            Image song = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ImageSongs/" + logic.imageName())));
            songImage.setImage(song);
            Image stop = new Image("Stop.png");
        });
        // Restaurar el CountDownLatch
        logic.getNotification().resetChoiceLatch();
    }
    @FXML
    private void handlePlayerButton(ActionEvent actionEvent){
        if(actions == 2){
            actions--;
            buttonImage.setImage(play);
        }else{
            actions++;
            buttonImage.setImage(stop);
        }
        logic.getNotification().setOption(actions);
    }

    public void setLogic(Logic logic) {
        this.logic = logic;
    }
}
