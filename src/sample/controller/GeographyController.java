package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class GeographyController {

    @FXML
    private Label tituloMateria;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private ImageView home;

    @FXML
    private Text pregunta;

    @FXML
    private ImageView imagenPregunta;

    @FXML
    private JFXButton opcionB;

    @FXML
    private JFXButton opcionD;

    @FXML
    private JFXButton opcionC;

    @FXML
    private JFXButton opcionA;

    @FXML
    private Label enunciado;

    @FXML
    private ImageView timerIcon;

    @FXML
    private Label timeCount;

    @FXML
    private Label secondsLabel;

    @FXML
    void initialize() {
        //Esta en el mismo thread principal que nuestra aplicacion de javafx
        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            int i = 29;
            @Override
            public void handle(ActionEvent event) {
                timeCount.setText(Integer.toString(i--));
            }
        }));
        timer.setCycleCount(29);
        timer.play();
    }

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void returnHome(MouseEvent mouseEvent) {
        Stage menuStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/view/menu.fxml"));
            Scene scene = new Scene(root);
            menuStage.setScene(scene);
            menuStage.initStyle(StageStyle.UNDECORATED);

            menuStage.show();
            menuStage.setResizable(false);

            home.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
