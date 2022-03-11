package Primitiva;

//*IMPORTS LIBRERIAS
//import util.Lib;

//*IMPORTS PARA OPERACIONES
import Primitiva.config.Bombo;
import Primitiva.config.Config;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;
import java.util.LinkedHashSet;

public class JuegoPrimitiva {
    // Variables Globales
    // Scanner & Random
    Scanner myInput = new Scanner(System.in);
    Random r = new Random();

    //Bombo de 49 Numeros
    Bombo bombo49 = new Bombo(49,1);
    Bombo bomboComputer = new Bombo(49,1);

    //Para los numeros del usuario y del sistema
    Primitiva numerosUser = new Primitiva();
    Primitiva numerosComputer = new Primitiva();

    // Para uso de Operaciones
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
        int reintegro = r.nextInt(Config.MAX_RND-Config.MIN_RND+1)+Config.MIN_RND;
        numerosUser.setNumReintegro(reintegro);
        int comple = bombo49.extraerBola();
        numerosUser.setNumeroComp(comple);
        switch (userInt) {
            case 1:
                System.out.println("Seleccion de numeros");
                numerosUser.setNumerosElegidos(validarNumeros());
                System.out.println(numerosUser);
                break;
            case 2:
                System.out.println("El sistema generara 6 numero automaticamente para ti");
                numerosUser.setNumerosElegidos(generatorRandom());
                System.out.println(numerosUser);
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
            correct = userInt >= 0 && userInt <= 5;

        } while (!correct);

        numerosComputer.setNumerosElegidos(generarRandomComputer());
        int comple = bomboComputer.extraerBola();
        numerosComputer.setNumeroComp(comple);
        int reintegro = r.nextInt(Config.MAX_RND-Config.MIN_RND+1)+Config.MIN_RND;
        numerosComputer.setNumReintegro(reintegro);

        switch (userInt) {
            case 1:
                juegoUnico();
                break;
            case 2:
                jugarHastaPremio();
                break;
            case 3:
                jugarHastaPremioSR();
                numerosUser.setNumReintegro(0);
                break;
            case 4:
                sorteo1000();
                break;
            case 5:
                break;
            case 0:
                System.out.println("Adios");
        }
    }

    /**
     * 
     * @return a set object which stores group of elements, it grows dynamically and
     *         it does not allow duplicate elements.
     */

    public int[] generatorRandom() {
        int[] arr = new int[Config.MAX_NUMERO_SUERTE];
        for(int i = 0; i<arr.length;i++ ){
            arr[i] = bombo49.extraerBola();
        }
        return arr;
    }

    private int[] generarRandomComputer(){
        int[] arr = new int[Config.MAX_NUMERO_SUERTE];
        for(int i = 0; i<arr.length;i++ ){
            arr[i] = bomboComputer.extraerBola();
        }
        return arr;
    }

    /**
     * 
     * @return numeros que el jugador a optado
     */

    public int[] validarNumeros() {
        int[] numeros = new int[Config.MAX_NUMERO_SUERTE];
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
        int cont = 0;
        for(int i = 0; i<Config.MAX_NUMERO_SUERTE; i++){
            for(int j = 0; j <Config.MAX_NUMERO_SUERTE; j++){
                if(numerosUser.getNumerosElegidos()[i]==numerosComputer.getNumerosElegidos()[j]){
                    cont++;
                }
            }
        }

        switch (cont){
            case 3:
                cateCinco++;
                System.out.println("Tienes : " + cont + " letras igueles, has ganado un premio de categoria 5");
                return true;
            case 4:
                cateQuat++;
                System.out.println("Tienes : " + cont + " letras igueles, has ganado un premio de categoria 4");
                return true;
            case 5:
                if(numerosUser.getNumeroComp() == numerosComputer.getNumeroComp()){
                    System.out.println("Tienes : " + cont + " letras igueles, has ganado un premio de categoria 2");
                    cateDos++;
                    return true;
                }
                cateTres++;
                System.out.println("Tienes : " + cont + " letras igueles, has ganado un premio de categoria 3");
                return true;
            case 6:
                if(numerosUser.getNumReintegro() == numerosComputer.getNumReintegro()){
                    System.out.println("Tienes : " + cont + " letras igueles, has ganado un premio de categoria ESPECIAL");
                    cateEspe++;
                    return true;
                }
                cateUno++;
                System.out.println("Tienes : " + cont + " letras igueles, has ganado un premio de categoria 1");
                return true;
            case 0:
                System.out.println("no has ganado en ninguna categoria, suente en la siguiente partida!");
        }
        return false;
    }

    public JuegoPrimitiva() {
        menu();
        subMenu();
    }

    private void juegoUnico() {
        comprobarCategoria();
        System.out.println(numerosComputer.toString());
    }

    private void jugarHastaPremio(){
        boolean ganado = false;
        do{
            numerosComputer.setNumerosElegidos(generarRandomComputer());
            ganado = comprobarCategoria();
            bomboComputer.reset();
        }while (!ganado);
    }

    private void jugarHastaPremioSR(){
        boolean ganado = false;
        do{
            numerosComputer.setNumerosElegidos(generarRandomComputer());
            ganado = comprobarCategoria();
            bomboComputer.reset();
        }while (!ganado);
    }

    private void sorteo1000() {
        cateEspe = 0;cateUno = 0; cateDos = 0; cateTres = 0; cateQuat = 0; cateCinco = 0;
        for(int i = 0; i < Config.SORTEO1000;i++) {
            numerosComputer.setNumerosElegidos(generarRandomComputer());
            comprobarCategoria();
            bomboComputer.reset();
        }
        System.out.println("Has ganado la categoria 5:"+cateCinco+" veces");
        System.out.println("Has ganado la categoria 4:"+cateQuat+" veces");
        System.out.println("Has ganado la categoria 3:"+cateTres+" veces");
        System.out.println("Has ganado la categoria 2:"+cateDos+" veces");
        System.out.println("Has ganado la categoria 1:"+cateUno+" veces");
        System.out.println("Has ganado la categoria ESPECIAL:"+cateEspe+" veces");
    }

    private void jugarHastaCategoriaESP() {
        cateEspe = 0;
        boolean ganado = false;
        do{
            numerosComputer.setNumerosElegidos(generarRandomComputer());
            comprobarCategoria();
            if(cateEspe > 0) {
                ganado = true;
            }
            bomboComputer.reset();
        } while(!ganado);
    }

}
