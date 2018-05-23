package sample.classes;

public class Partida {
    public String tema = "";
    public Jugador jugador1;
    public Jugador jugador2;

    public Partida(String tema, Jugador jugador1, Jugador jugador2) {
        this.tema = tema;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public Partida(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }
}
