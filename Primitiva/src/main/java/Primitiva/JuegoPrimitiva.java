package Primitiva;

//*IMPORTS LIBRERIAS
//import util.Lib;

//*IMPORTS PARA OPERACIONES
import Primitiva.config.Config;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;
import java.util.LinkedHashSet;

public class JuegoPrimitiva {
    // Variables Globales
    // Variable de la clase primitiva
    Primitiva numerosElegidos;
    // Scanner & Random
    Scanner myInput = new Scanner(System.in);
    Random r = new Random();
    //Para los numeros del usuario y del sistema
    Primitiva usePrimitiva = new Primitiva();
    Primitiva computerPrimitiva = new Primitiva();
    // Para uso de Operaciones
    static int[] numeros = new int[Config.MAX_NUMERO_SUERTE];
    int userInt;
    boolean correct;
    //cuantas vez ha ganado en cada categoria
    int cateEspe, cateUno, cateDos, cateTres, cateQuat, cateCinco;

    int primerNumero, segundoNumero, tercerNumero, cuartoNumero, quintoNumero, sextoNumero;
    // +Todo variable para contar los numeros de intentos

    public void menu() {
        do {
            System.out.println("*******JUEGO DE LA PRIMITIVA ******");
            System.out.println("Para empezar necesitamos que escojas 6 numeros");
            System.out.println("Tienes dos opciones");
            System.out.println("1.Introducir los numeros manualmente");
            System.out.println("2.Introducir los numeros automaticamente");
            userInt = Integer.parseInt(myInput.nextLine());
            correct = userInt > 0 && userInt <= 2;

        } while (!correct);

        switch (userInt) {
            case 1:
                System.out.println("Seleccion de numeros");
                numerosElegidos = new Primitiva(validarNumeros());
                System.out.println(numerosElegidos);
                break;
            case 2:
                System.out.println("El sistema generara 6 numero automaticamente para ti");
                /*numerosElegidos = new Primitiva(generatorRandom());*/
                System.out.println(numerosElegidos);
                break;
        }

    }

    public void subMenu() {

        do {
            StringBuilder subMenu = new StringBuilder();
            subMenu.append("****ELIGE UNA MODALIDAD DE JUEGO *****\n");
            subMenu.append("1. Juego Unico\n");
            subMenu.append("2.Jugar hasta obtener un premio(con reintegro)\n");
            subMenu.append("3.Jugar hasta obtener un premio (Sin reintegro)\n");
            subMenu.append("4.Ciclo de 10000 sorteos\n");
            subMenu.append("5.Jugar hasta obtener premio categoria especial\n");
            subMenu.append("************************************\n");
            subMenu.append("0.Para Salir\n");
            System.out.println(subMenu.toString());
            userInt = Integer.parseInt(myInput.nextLine());
            correct = userInt >= 0 && userInt >= 5;

        } while (!correct);

        switch (userInt) {
            case 1:

            case 2:

            case 3:
                jugarHastaPremioSR();
            case 4:

            case 5:
        }
    }

    /**
     * 
     * @return a set object which stores group of elements, it grows dynamically and
     *         it does not allow duplicate elements.
     */

    public Set<Integer> generatorRandom() {
        Set<Integer> set = new LinkedHashSet<Integer>();
        while (set.size() < Config.MAX_NUMERO_SUERTE) {
            set.add(r.nextInt(49) + 1);
        }
        // System.out.println("Random numbers with no duplicates = " + set);
        return set;
    }

    /**
     * 
     * @return numeros que el jugador a optado
     */

    public int[] validarNumeros() {
        for (int i = 0; i < Config.MAX_NUMERO_SUERTE; i++) {
            do {
                correct = true;
                System.out.print("Introduce un numero (del 1 al 49): ");
                try {
                    userInt = Integer.parseInt(myInput.nextLine());
                    if (userInt < 1 || userInt > 49) {
                        System.out.println("Error: Incorrect integer.");
                        correct = false;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: El input no es un integer");
                    correct = false;
                }
                for (int j = 0; j < i; j++) {
                    if (numeros[j] == userInt) {
                        System.out.println("Introduce un numero que no hayas escogido");
                        correct = false;
                        break;
                    }
                }
                numeros[i] = userInt;
            } while (!correct);
        }
        return numeros;
    }

    private boolean comprobarCategoria(){
        int[]contArrayNum = new int[48];
        int numComprop = 0;
        for(int i = 0; i < numerosElegidos.getNumerosElegidos().length; i++){
            numComprop=numerosElegidos.getNumerosElegidos()[i];
            numComprop--;
            contArrayNum[numComprop]++;
        }
        int complementario = numerosElegidos.getNumeroComp();
        if(complementario!=0){
            int posNum = complementario-1;
            contArrayNum[posNum]++;
        }

        int numCat =0;
        for(int j = 0; j< contArrayNum.length;j++){
            switch (contArrayNum[j]){
                case 3:
                    cateCinco++;
                    numCat = contArrayNum[j];
                    System.out.println("has optenido: " + numCat + " numeros igual, has ganado un premio de categoria "+ numCat);
                    return true;
                case 4:
                    cateQuat++;
                    numCat = contArrayNum[j];
                    System.out.println("has optenido: " + numCat + " numeros igual, has ganado un premio de categoria "+ numCat);
                    return true;
                case 5:
                    cateTres++;
                    numCat = contArrayNum[j];
                    System.out.println("has optenido: " + numCat + " numeros igual, has ganado un premio de categoria "+ numCat);
                    return true;
                case 6:
                    cateUno++;
                    numCat = contArrayNum[j];
                    System.out.println("has optenido: " + numCat + " numeros igual, has ganado un premio de categoria "+ numCat);
                    return true;
            }
        }
        System.out.println("no has ganado en ninguna categoria, suente en la siguiente partida!");
        return false;
    }

    private void jugarHastaPremioSR(){
        boolean ganado = false;
        do{
            /*numerosElegidos = new Primitiva(generatorRandom());*/
            ganado = comprobarCategoria();
        }while (!ganado);
    }

    public JuegoPrimitiva() {
        menu();
        subMenu();
    }

}
