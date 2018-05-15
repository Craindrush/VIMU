package sample.classes;

public class Fraccion {
    protected int numerador;
    protected int denominador;
    private int resNumerador;
    private int resDenominador;


    public Fraccion(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public String sumaFraccion(int numerador, int denominador) {
        if (denominador == 0 || this.denominador == 0) {
            System.out.println("No se puede dividir entre 0");
            return "";
        }
        resNumerador = ((this.numerador * denominador) + (this.denominador * numerador));
        resDenominador = this.denominador * denominador;

        return condicionFraccion(resNumerador, resDenominador);
    }

    public String restaFraccion(int numerador, int denominador) {
        if (denominador == 0 || this.denominador == 0) {
            System.out.println("No se puede dividir entre 0");
            return "";
        }
        resNumerador = ((this.numerador * denominador) - (this.denominador * numerador));
        resDenominador = this.denominador * denominador;

        return condicionFraccion(resNumerador, resDenominador);
    }

    public String multiplicacionFraccion(int numerador, int denominador) {
        if (denominador == 0 || this.denominador == 0) {
            System.out.println("No se puede dividir entre 0");
            return "";
        }
        resNumerador = this.numerador * numerador;
        resDenominador = this.denominador * denominador;

        return condicionFraccion(resNumerador, resDenominador);
    }

    public String divisionFraccion(int numerador, int denominador) {
        if (denominador == 0 || this.denominador == 0) {
            System.out.println("No se puede dividir entre 0");
            return "";
        }
        resNumerador = this.numerador * denominador;
        resDenominador = this.denominador * numerador;

        if (resDenominador == 0) {
            System.out.println("No se puede dividir entre 0");
            return "";
        }

        return condicionFraccion(resNumerador, resDenominador);
    }

    public static String simplificarFraccion(int numerador, int denominador) {
        int i = 10;
        if (numerador == 0) {
            System.out.println("No se puede simplificar más la fracción con 0 en el numerador");
            return "";
        } else if (denominador == 0) {
            System.out.println("No se puede simplificar una fraccion con 0 en el denominador");
            return "";
        } else {
            do {
                if (numerador % i == 0 && denominador % i == 0) {
                    numerador /= i;
                    denominador /= i;
                } else i--;

            } while (i != 1);

            if (numerador % denominador == 0) return Integer.toString(numerador / denominador);
            else if (denominador == 1) return Integer.toString(denominador);
            else return Integer.toString(numerador) + "/" + Integer.toString(denominador);
        }
    }

    public static String MixtaNormal(int entero, int numerador, int denominador) {
        if (numerador == 0) {
            System.out.println("No se puede simplificar más la fracción con 0 en el numerador");
            return "";
        } else if (denominador == 0) {
            System.out.println("No se puede simplificar una fraccion con 0 en el denominador");
            return "";
        } else if (entero == 0) {
            System.out.println("No se puede convertir una fraccion mixta a propia/impropia si el entero es 0");
        }
        numerador = (entero * denominador) + numerador;

        if (numerador % denominador == 0) return Integer.toString(numerador / denominador);
        else if (denominador == 1) return Integer.toString(denominador);
        else return Integer.toString(numerador) + "/" + Integer.toString(denominador);
    }

    private String condicionFraccion(int numerador, int denominador) {
        if (numerador % denominador == 0) return Integer.toString(numerador / denominador);
        else if (denominador == 1) return Integer.toString(denominador);
        else return Integer.toString(numerador) + "/" + Integer.toString(denominador);
    }
}


