package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistroController {

    @FXML
    private Label player1Tilte;

    @FXML
    private JFXTextField player1TextField;

    @FXML
    private JFXDatePicker player1Birthday;

    @FXML
    private JFXRadioButton player1Man;

    @FXML
    private JFXRadioButton player1Woman;

    @FXML
    private Label player2Title;

    @FXML
    private JFXTextField player2TextField;

    @FXML
    private JFXDatePicker player2Birthday;

    @FXML
    private JFXRadioButton player2Man;

    @FXML
    private JFXRadioButton player2Woman;

    @FXML
    private JFXButton playButton;

    @FXML
    private Label title;

    @FXML
    private Label subtitle;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private ImageView back;

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
