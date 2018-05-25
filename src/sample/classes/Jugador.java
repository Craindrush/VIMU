package sample.classes;

public class Jugador {
    public String nombre;
    public int edad;
    public Puntaje puntaje;
    public String genero = "";

    public Jugador (String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        puntaje = new Puntaje(0);
    }

    public Jugador(String nombre, int edad, String genero) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        puntaje = new Puntaje(0);
    }
}
