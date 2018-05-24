package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.classes.ReadWriteInfo;
import sample.classes.StageModify;
import sample.classes.rankingPlayer;

import java.io.IOException;
import java.util.List;

public class RankingGeoController {

    @FXML   private Label player1Points;
    @FXML   private Label player1Name;
    @FXML   private Label player7Points;
    @FXML   private Label player7Name;
    @FXML   private Label player3Points;
    @FXML   private Label player3Name;
    @FXML   private Label player9Points;
    @FXML   private Label player9Name;
    @FXML   private Label player5Points;
    @FXML   private Label player5Name;
    @FXML   private Label player6Points;
    @FXML   private Label player6Name;
    @FXML   private Label player8Points;
    @FXML   private Label player8Name;
    @FXML   private Label player10Points;
    @FXML   private Label player10Name;
    @FXML   private Label player2Points;
    @FXML   private Label player2Name;
    @FXML   private Label player4Points;
    @FXML   private Label player4Name;
    @FXML   private ImageView minimize;
    @FXML   private ImageView close;
    @FXML   private Label tituloMateria;
    @FXML   private ImageView back;

    private StageModify stageModify = new StageModify();
    private ReadWriteInfo readWriteInfo = new ReadWriteInfo();

    @FXML
    void initialize() {
        int count = 0;
        tituloMateria.setText("RANKING TOP 10 | GEOGRAFÍA");

        try {
            List<rankingPlayer> listaJugadores = readWriteInfo.readRankingList("Geo");
            while (count != listaJugadores.size()) {
                setPlayerScore(count, listaJugadores);
                count++;
                if (count == 10) break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void minimizeProgram(MouseEvent mouseEvent) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public void closeProgram(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void backMenu(MouseEvent mouseEvent) {
        stageModify.changeStage("/sample/view/menu.fxml",close);
    }

    private void setPlayerScore(int index, List<rankingPlayer> listaJugadores) {
        switch (index) {
            case 0: {
                player1Name.setText(listaJugadores.get(index).nombre);
                player1Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 1: {
                player2Name.setText(listaJugadores.get(index).nombre);
                player2Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 2: {
                player3Name.setText(listaJugadores.get(index).nombre);
                player3Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 3: {
                player4Name.setText(listaJugadores.get(index).nombre);
                player4Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 4: {
                player5Name.setText(listaJugadores.get(index).nombre);
                player5Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 5: {
                player6Name.setText(listaJugadores.get(index).nombre);
                player6Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 6: {
                player7Name.setText(listaJugadores.get(index).nombre);
                player7Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 7: {
                player8Name.setText(listaJugadores.get(index).nombre);
                player8Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 8: {
                player9Name.setText(listaJugadores.get(index).nombre);
                player9Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
            case 9: {
                player10Name.setText(listaJugadores.get(index).nombre);
                player10Points.setText(String.valueOf(listaJugadores.get(index).puntos)+"PTS");
            }
            break;
        }
    }
}
