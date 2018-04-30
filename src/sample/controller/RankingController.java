package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RankingController {

    @FXML
    private Label name1;

    @FXML
    private Label points1;

    @FXML
    private Label name7;

    @FXML
    private Label points7;

    @FXML
    private Label name3;

    @FXML
    private Label points3;

    @FXML
    private Label name9;

    @FXML
    private Label points9;

    @FXML
    private Label name5;

    @FXML
    private Label points5;

    @FXML
    private Label name6;

    @FXML
    private Label points6;

    @FXML
    private Label name8;

    @FXML
    private Label points8;

    @FXML
    private Label name10;

    @FXML
    private Label points10;

    @FXML
    private Label name2;

    @FXML
    private Label points2;

    @FXML
    private Label name4;

    @FXML
    private Label points4;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView close;

    @FXML
    private Label title;

    @FXML
    private Label subtitle;

    @FXML
    private ImageView home;

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
