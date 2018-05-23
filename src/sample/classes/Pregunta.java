package sample.classes;

public class Pregunta {

        private String enunciado;     // El texto de la pregunta
        private Respuesta[] listadoRespuestas = new Respuesta[4];

        public Pregunta () {
                iniRespuesta();
        }

        public Pregunta (String enunciado){
                iniRespuesta();
                this.enunciado=enunciado;
        }

        public Pregunta (String enunciado,Respuesta[] listadoRespuestas){
                iniRespuesta();
                this.enunciado=enunciado;
                this.listadoRespuestas=listadoRespuestas;
        }


        //devuelve una respuesta de 0-3 
        public Respuesta[] getListadoRespuestas(){
                return listadoRespuestas;
        }

        public String getEnunciado(){
                return enunciado;
        }

        public void setEnunciado(String enunciado) {
                this.enunciado = enunciado;
        }

        private void iniRespuesta() {
                for (int i=0; i<4; i++) {
                        listadoRespuestas[i] = new Respuesta();
                }
        }

}
