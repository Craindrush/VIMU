package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SpanishController {

    @FXML
    private Label tituloMateria;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private ImageView back;

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


    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
