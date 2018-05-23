package sample.classes;

public class Respuesta {

  private String opcionTexto = "";
  private boolean correcta = false;

  // CONSTRUCTORES

  public Respuesta() {

  }
  
  public Respuesta(String opcionTexto, boolean correcta) {
    this.opcionTexto = opcionTexto;
    this.correcta = correcta;
  }

    public String getOpcionTexto() {
        return opcionTexto;
    }

    public void setOpcionTexto(String opcionTexto) {
        this.opcionTexto = opcionTexto;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
}
