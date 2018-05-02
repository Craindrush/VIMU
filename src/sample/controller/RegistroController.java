package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RegistroController {

    @FXML private Label player1Tilte;

    @FXML private JFXTextField player1TextField;

    @FXML private JFXDatePicker player1Birthday;

    @FXML private JFXRadioButton player1Man;

    @FXML private JFXRadioButton player1Woman;

    @FXML private Label player2Title;

    @FXML private JFXTextField player2TextField;

    @FXML private JFXDatePicker player2Birthday;

    @FXML private JFXRadioButton player2Man;

    @FXML private JFXRadioButton player2Woman;

    @FXML private JFXButton playButton;

    @FXML private Label title;

    @FXML private Label subtitle;

    @FXML private ImageView minimize;

    @FXML private ImageView close;

    @FXML private ImageView back;

    private ToggleGroup group1 = new ToggleGroup();
    private ToggleGroup group2 = new ToggleGroup();

    @FXML
    void initialize() {
        //Agrupando lo RadioButtons
        player1Man.setToggleGroup(group1);
        player1Woman.setToggleGroup(group1);

        player2Man.setToggleGroup(group2);
        player2Woman.setToggleGroup(group2);

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(checkUserField() == true ) {
                    System.out.println(player1Birthday.getValue().toString());
//                    playButton.getScene().getWindow().hide();
                }
            }
        });
    }

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }

    //Return arrow
    public void returnView(MouseEvent mouseEvent) {
        Stage backStage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/sample/view/menu.fxml"));
            Scene menuScene = new Scene(root);
            backStage.setScene(menuScene);
            backStage.initStyle(StageStyle.UNDECORATED);
            backStage.show();
            backStage.setResizable(false);

            back.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkUserField() {
        if (!player1TextField.getText().trim().equals("") && !player2TextField.getText().trim().equals("")) {
            try {
                if (!player1Birthday.getValue().toString().trim().equals("") && !player2Birthday.getValue().toString().trim().equals("")) {
                    if ( (player1Man.isSelected() || player1Woman.isSelected()) && (player2Man.isSelected() || player2Woman.isSelected()) ) {
                        return true;
                    }
                }
            } catch (NullPointerException e) {
                System.out.println("No se puede convetir a cadena un dato vacio");
            }
        }
        return false;
    }
}
