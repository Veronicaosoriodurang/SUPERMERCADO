package com.co.programacion.ejemplosprintboot1.ejemploanterior;

import java.util.InputMismatchException;
import java.util.Scanner;

// Clase que contiene la lógica de la calculadora
public class Calculadora {

    // Objeto Scanner para leer la entrada del usuario, declarado como un campo para que sea accesible en todos los métodos
    private Scanner scanner;

    // Constructor de la clase Calculadora
    public Calculadora() {
        this.scanner = new Scanner(System.in);
    }

    // Método principal para ejecutar la calculadora
    public void ejecutarCalculadora() {
        int opcion; // Variable para almacenar la opción del menú seleccionada por el usuario

        // Bucle principal para mostrar el menú y realizar operaciones hasta que el usuario decida salir
        do {
            opcion = mostrarMenuYObtenerOpcion(); // Llama al método para mostrar el menú y obtener la opción

            try {
                // Evaluar la opción seleccionada
                switch (opcion) {
                    case 1:
                        sumarNumeros();
                        break;
                    case 2:
                        restarNumeros();
                        break;
                    case 3:
                        multiplicarNumeros();
                        break;
                    case 4:
                        dividirNumeros();
                        break;
                    case 5:
                        System.out.println("¡Gracias por usar la calculadora! Finalizando programa.");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
                        break;
                }
            } catch (InputMismatchException e) {
                // Capturar excepción si el usuario ingresa algo que no es un número
                System.out.println("Entrada inválida. Por favor, ingrese un número válido para la opción o para la operación.");
                scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
                opcion = 0; // Establecer la opción en 0 para que el bucle continúe
            } catch (Exception e) {
                // Capturar cualquier otra excepción inesperada
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                opcion = 0;
            }

        } while (opcion != 5); // El bucle continúa mientras la opción no sea "Finalizar"

        // Cerrar el objeto Scanner para liberar recursos al finalizar la calculadora
        scanner.close();
    }

    // Método para mostrar el menú y obtener la opción del usuario
    private int mostrarMenuYObtenerOpcion() {
        System.out.println("\n--- Menú de Calculadora ---");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.println("5. Finalizar");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt(); // Lee y devuelve la opción
    }

    // Método para obtener dos números del usuario
    private double obtenerNumeros(String operacion) {
        System.out.print("Ingrese el " + operacion + " número: ");
        return scanner.nextDouble();
    }

    // Métodos para cada operación
    private void sumarNumeros() {
        double num1 = obtenerNumeros("primer");
        double num2 = obtenerNumeros("segundo");
        System.out.println("Resultado de la suma: " + (num1 + num2));
    }

    private void restarNumeros() {
        double num1 = obtenerNumeros("primer");
        double num2 = obtenerNumeros("segundo");
        System.out.println("Resultado de la resta: " + (num1 - num2));
    }

    private void multiplicarNumeros() {
        double num1 = obtenerNumeros("primer");
        double num2 = obtenerNumeros("segundo");
        System.out.println("Resultado de la multiplicación: " + (num1 * num2));
    }

    private void dividirNumeros() {
        double num1 = obtenerNumeros("dividendo");
        double num2 = obtenerNumeros("divisor");
        if (num2 != 0) {
            System.out.println("Resultado de la división: " + (num1 / num2));
        } else {
            System.out.println("Error: No se puede dividir por cero.");
        }
    }
}
