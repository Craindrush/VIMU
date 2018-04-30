package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label title;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private ImageView matematicas;

    @FXML
    private ImageView spanish;

    @FXML
    private ImageView geografia;

    @FXML
    private Label labelMath;

    @FXML
    private Label labelGeo;

    @FXML
    private Label labelSpanish;

    @FXML
    private Text howToMath;

    @FXML
    private Text howToSpanish;

    @FXML
    private Text howToGeo;

    @FXML
    private Label subtitle;

    public void closeProgram (MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void playMath(MouseEvent mouseEvent) {
    }

    public void playSpanish(MouseEvent mouseEvent) {
    }

    public void playGeo(MouseEvent mouseEvent) {
    }
}
