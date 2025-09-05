package com.co.programacion.ejemplosprintboot1.ejemploanterior;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraConsola {

    public static void main(String[] args) {
        // Objeto Scanner para leer la entrada del usuario desde la consola
        Scanner scanner = new Scanner(System.in);
        int opcion; // Variable para almacenar la opción del menú seleccionada por el usuario
        double num1, num2; // Variables para almacenar los números para las operaciones

        // Bucle principal para mostrar el menú y realizar operaciones hasta que el usuario decida salir
        do {
            // Mostrar el menú de opciones al usuario
            System.out.println("\n--- Menú de Calculadora ---");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("5. Finalizar");
            System.out.print("Seleccione una opción: ");

            try {
                // Leer la opción del usuario
                opcion = scanner.nextInt();

                // Evaluar la opción seleccionada
                switch (opcion) {
                    case 1: // Sumar
                        System.out.print("Ingrese el primer número: ");
                        num1 = scanner.nextDouble();
                        System.out.print("Ingrese el segundo número: ");
                        num2 = scanner.nextDouble();
                        System.out.println("Resultado de la suma: " + (num1 + num2));
                        break;
                    case 2: // Restar
                        System.out.print("Ingrese el primer número: ");
                        num1 = scanner.nextDouble();
                        System.out.print("Ingrese el segundo número: ");
                        num2 = scanner.nextDouble();
                        System.out.println("Resultado de la resta: " + (num1 - num2));
                        break;
                    case 3: // Multiplicar
                        System.out.print("Ingrese el primer número: ");
                        num1 = scanner.nextDouble();
                        System.out.print("Ingrese el segundo número: ");
                        num2 = scanner.nextDouble();
                        System.out.println("Resultado de la multiplicación: " + (num1 * num2));
                        break;
                    case 4: // Dividir
                        System.out.print("Ingrese el dividendo: ");
                        num1 = scanner.nextDouble();
                        System.out.print("Ingrese el divisor: ");
                        num2 = scanner.nextDouble();
                        // Manejo de la división por cero
                        if (num2 != 0) {
                            System.out.println("Resultado de la división: " + (num1 / num2));
                        } else {
                            System.out.println("Error: No se puede dividir por cero.");
                        }
                        break;
                    case 5: // Finalizar
                        System.out.println("¡Gracias por usar la calculadora! Finalizando programa.");
                        break;
                    default: // Opción no válida
                        System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 5.");
                        break;
                }
            } catch (InputMismatchException e) {
                // Capturar excepción si el usuario ingresa algo que no es un número
                System.out.println("Entrada  no válida. Por favor, ingrese un número válido para la opción.");
                scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
                opcion = 0; // Establecer la opción en 0 para que el bucle continúe
            }

        } while (opcion != 5); // El bucle continúa mientras la opción no sea "Finalizar"

        // Cerrar el objeto Scanner para liberar recursos
        scanner.close();
    }
}