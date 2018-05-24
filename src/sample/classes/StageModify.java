package sample.classes;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.controller.ResultadosQuizController;

import java.io.IOException;

public class StageModify {
    // Recibe una ImageView porque minimo todas las view que hice contienen ese
    // elemento/nodo en su diseño
    public void changeStage (String ruta, ImageView imagen) {
        Stage stage = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource(ruta));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            stage.setResizable(false);

            imagen.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeResultsStage(ImageView imagen, Partida partida) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/resultadosQuiz.fxml"));
            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);

            ResultadosQuizController resultadosQuizController = loader.getController();
            if (partida.jugador1.puntaje.puntos > partida.jugador2.puntaje.puntos) {
                resultadosQuizController.setGanador(partida.jugador1);
                resultadosQuizController.setPerdedor(partida.jugador2);
            }
            else {
                resultadosQuizController.setGanador(partida.jugador2);
                resultadosQuizController.setPerdedor(partida.jugador1);
            }
            resultadosQuizController.setNombreMateria(partida.tema);
            stage.show();
            stage.setResizable(false);
            imagen.getScene().getWindow().hide();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
