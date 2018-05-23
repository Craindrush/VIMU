package sample.classes;

public class GeneradorAritmetico  {

    public String suma(Fraccion fraccion1, Fraccion fraccion2) {
        return fraccion1.sumaFraccion(fraccion2.getNumerador(),fraccion2.getDenominador());
    }

    public String suma (int entero1, int entero2) {
        return String.valueOf(entero1+entero2);
    }

    public String suma (float decimal1, float decimal2) {
        return String.valueOf(decimal1+decimal2);
    }

    public String resta(Fraccion fraccion1, Fraccion fraccion2) {
        return fraccion1.restaFraccion(fraccion2.getNumerador(),fraccion2.getDenominador());
    }

    public String resta (int entero1, int entero2) {
        return String.valueOf(entero1-entero2);
    }

    public String resta (float decimal1, float decimal2) {
        return String.valueOf(decimal1-decimal2);
    }

    public String multiplicacion(Fraccion fraccion1, Fraccion fraccion2) {
        return fraccion1.multiplicacionFraccion(fraccion2.getNumerador(),fraccion2.getDenominador());
    }

    public String multiplicacion (int entero1, int entero2) {
        return String.valueOf(entero1*entero2);
    }

    public String division(Fraccion fraccion1, Fraccion fraccion2) {
        return fraccion1.divisionFraccion(fraccion2.getNumerador(),fraccion2.getDenominador());
    }

    public String division (int entero1, int entero2) {
        return String.valueOf(entero1/entero2);
    }

    public String fraccionDecimal (Fraccion fraccion) {
        return String.valueOf( (float)fraccion.getNumerador()/ (float)fraccion.getDenominador());
    }

    public String simplificarFraccion(Fraccion fraccion) {
        return Fraccion.simplificarFraccion(fraccion.getNumerador(),fraccion.getDenominador());
    }

    public String simplificarMixta(int entero, int numerador, int denominador) {
        return Fraccion.MixtaNormal(entero, numerador, denominador);
    }
}
