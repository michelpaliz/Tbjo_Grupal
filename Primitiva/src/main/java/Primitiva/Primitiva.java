package Primitiva;

import java.util.Arrays;
import java.util.Set;

public class Primitiva {
    // Creacion de atributos
    private int[] numerosElegidos;
    // +Todo generar los tipos de datos para estos dos arrays
    private int numReintegro;
    private int numeroComp;

    // Generacion de constructores
    public Primitiva(int[] numerosElegidos, String modalidadJuego) {
        this.numerosElegidos = numerosElegidos;

    }

    public Primitiva() {
        numerosElegidos=null;
        numeroComp = 0;
        numReintegro = 0;
    }

    public Primitiva(int[] validarNumeros) {
        this.numerosElegidos = validarNumeros;
    }



    public int[] getNumerosElegidos() {
        return numerosElegidos;
    }

    public int getNumReintegro() {
        return numReintegro;
    }

    public int getNumeroComp() {
        return numeroComp;
    }

    @Override
    public String toString() {
        return "Primitiva{" +
                "numerosElegidos=" + Arrays.toString(numerosElegidos) +
                ", modalidadJuego='" + '\'' +
                '}';
    }

}
