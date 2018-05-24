package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.classes.Partida;
import sample.classes.StageModify;

import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroController {
    // SOLO HE DECLARADO LOS NODOS QUE MANIPULO EN EL JUEGO
    @FXML private JFXTextField player1Name;
    @FXML private JFXDatePicker player1Birthday;
    @FXML private JFXRadioButton player1Man;
    @FXML private JFXRadioButton player1Woman;
    @FXML private JFXTextField player2Name;
    @FXML private JFXDatePicker player2Birthday;
    @FXML private JFXRadioButton player2Man;
    @FXML private JFXRadioButton player2Woman;
    @FXML private JFXButton jugarBtn;
    @FXML private ImageView minimize;
    @FXML private ImageView home;
    @FXML private ImageView close;
    private ToggleGroup group1 = new ToggleGroup();
    private ToggleGroup group2 = new ToggleGroup();

    // ATRIBUTOS PARA LA LOGICA DEL JUEGO
    private String chooseQuiz;
    private Partida partida;
    private StageModify stageModify = new StageModify();

    @FXML
    void initialize() {

        //Agrupando lo RadioButtons
        player1Man.setToggleGroup(group1);
        player1Woman.setToggleGroup(group1);

        player2Man.setToggleGroup(group2);
        player2Woman.setToggleGroup(group2);
        // Usando lambdas
        jugarBtn.setOnAction(actionEvent -> {
            if(checkUserField()) {
                savePlayerInfo(chooseQuiz);
                stageModify.changeStage("/sample/view/menu.fxml",close);
                jugarBtn.getScene().getWindow().hide();
            } else showAlert("Campos vacíos","¡No deje espacios vacíos!");
        });
    }

    // Metodos para actuar como handlers de las imagenes al ser clickeadas
    public void minimizeProgram() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram() {
        System.exit(0);
    }

    public void returnHome() {
        stageModify.changeStage("/sample/view/bienvenida.fxml",close);
    }

    private boolean checkUserField() {
        if (!player1Name.getText().trim().equals("") && !player2Name.getText().trim().equals("")) {
            try {
                if (!player1Birthday.getValue().toString().trim().equals("") && !player2Birthday.getValue().toString().trim().equals("")) {
                    if ( (player1Man.isSelected() || player1Woman.isSelected()) && (player2Man.isSelected() || player2Woman.isSelected()) ) {
                        return true;
                    }
                }
            } catch (NullPointerException e) {}
        }
        return false;
    }

    private void showAlert(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(null);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
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
        file = new File("src/sample/QuestionsAnswers/players.txt");

        try (FileWriter fileWriter = new FileWriter(file)){
            // Guardando info jugador 2
            if(player1Man.isSelected()) fileWriter.write("Hombre;");
                else fileWriter.write("Mujer;");
            fileWriter.write(player1Name.getText()+";"+getAge(player1Birthday.getValue().toString())+"\n");
            // Guardando info jugador 2
            if(player2Man.isSelected()) fileWriter.write("Hombre;");
                else fileWriter.write("Mujer;");
            fileWriter.write(player2Name.getText()+";"+getAge(player2Birthday.getValue().toString())+"\n");
            fileWriter.close();
        } catch (Exception e) {}
    }
}
