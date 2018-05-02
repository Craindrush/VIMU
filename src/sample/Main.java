package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("view/menu.fxml"));
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setScene(new Scene(root, 1280, 768));
        primaryStage.show();

        AudioClip music = new AudioClip(this.getClass().getResource("/sample/assets/menu/Chill.mp3").toString());
        music.setCycleCount(AudioClip.INDEFINITE);
        music.play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
