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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.classes.Jugador;
import sample.classes.Partida;
import sample.classes.Puntaje;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroController {

    private String chooseQuiz;

    @FXML private Label player1Tilte;

    @FXML private JFXTextField player1Name;

    @FXML private JFXDatePicker player1Birthday;

    @FXML private JFXRadioButton player1Man;

    @FXML private JFXRadioButton player1Woman;

    @FXML private Label player2Title;

    @FXML private JFXTextField player2Name;

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

    private Partida partida;

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
                    savePlayerInfo(chooseQuiz);
                    goQuiz();
                    playButton.getScene().getWindow().hide();
                } else {
                    showAlert();
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

    private void goQuiz () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/" + chooseQuiz + "Quiz.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkUserField() {
        if (!player1Name.getText().trim().equals("") && !player2Name.getText().trim().equals("")) {
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

    private void showAlert() {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(null);
        alerta.setTitle("Campos vacíos");
        alerta.setContentText("¡No deje espacios vacíos!");
        alerta.showAndWait();
    }

    public void setChooseQuiz(String chooseQuiz) {
        this.chooseQuiz = chooseQuiz;
    }

    private String getAge (String fechaNacimiento) {
        int edad;
        // Obteniendo fecha actual
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateobj = new Date();

        //Separando Año, Mes y Dia
        String[] fechaActual = df.format(dateobj).split("-");
        String[] fechaNac = fechaNacimiento.split("-");

        // Restando al año actual el año de nacimiento
        edad = Integer.parseInt(fechaActual[0]) - Integer.parseInt(fechaNac[0]);
        // Comprobando si el mes actual es mayor al mes de nacimiento
        if (Integer.parseInt(fechaActual[1]) < Integer.parseInt(fechaNac[1])) {
            edad--;
        }
        // Si estamos en el mismo mes
        else if (Integer.parseInt(fechaActual[1]) == Integer.parseInt(fechaNac[1])) {
            if ( Integer.parseInt(fechaNac[2]) > Integer.parseInt(fechaActual[2]) ) {
                edad--;
            }
        }
        return String.valueOf(edad);
    }

    private void savePlayerInfo (String materia) {
        File file = null;
        if (materia == "spanish") {
            file = new File("src/sample/QuestionsAnswers/Spanish/players.txt");
        }
        else if (materia == "math") {
            file = new File("src/sample/QuestionsAnswers/Math/players.txt");
        }
        else if (materia == "geography") {
            file = new File("src/sample/QuestionsAnswers/Geography/players.txt");
        }

        try (FileWriter fileWriter = new FileWriter(file)){
            if(player1Man.isSelected()) fileWriter.write("Hombre;");
            else fileWriter.write("Mujer;");
            fileWriter.write(player1Name.getText()+";"+getAge(player1Birthday.getValue().toString())+"\n");

            if(player2Man.isSelected()) fileWriter.write("Hombre;");
            else fileWriter.write("Mujer;");
            fileWriter.write(player2Name.getText()+";"+getAge(player2Birthday.getValue().toString())+"\n");
        } catch (Exception e) {}

    }



}
