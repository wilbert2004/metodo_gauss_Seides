/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gaussseidel;

/**
 *
 * @author jesus
 */
import java.util.Scanner;

public class GaussSeidel {

    public static final int MAX_ITERATIONS = 1000;
    public static final double TOLERANCE = 1e-6;//MAX_ITERATIONS y TOLERANCE aseguran que el algoritmo de Gauss-Seidel se ejecute de manera controlada y eficiente, 
   // evitando casos de bucles infinitos y garantizando que se detenga una vez que se haya alcanzado una solución aceptable dentro de los límites especificados.

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el número de ecuaciones: ");
        int n = scanner.nextInt();
        
        double[][] coefficients = new double[n][n];
        double[] constants = new double[n];

        System.out.println("Ingrese los coeficientes de las ecuaciones:");
        for (int i = 0; i < n; i++) {
            System.out.printf("Ecuación %d:\n", i + 1);
            for (int j = 0; j < n; j++) {
                System.out.printf("Coeficiente x%d: ", j + 1);
                coefficients[i][j] = scanner.nextDouble();
            }
            System.out.print("Constante: ");
            constants[i] = scanner.nextDouble();
        }

        double[] initialGuess = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Suposición inicial para x%d: ", i + 1);
            initialGuess[i] = scanner.nextDouble();
        }

        double[] solution = gaussSeidel(coefficients, constants, initialGuess);

        System.out.println("Solución:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.6f\n", i + 1, solution[i]);
        }
    }

    public static double[] gaussSeidel(double[][] coefficients, double[] constants, double[] initialGuess) {
        int n = constants.length;
        double[] solution = new double[n];
        double[] nextSolution = new double[n];

        System.arraycopy(initialGuess, 0, solution, 0, n);

        int iteration = 0;
        while (iteration < MAX_ITERATIONS) {
            for (int i = 0; i < n; i++) {
                double sum = constants[i];
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum -= coefficients[i][j] * nextSolution[j];
                    }
                }
                nextSolution[i] = sum / coefficients[i][i];
            }

            double maxDiff = 0;
            for (int i = 0; i < n; i++) {
                double diff = Math.abs(nextSolution[i] - solution[i]);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }
            if (maxDiff < TOLERANCE) {
                break;
            }

            System.arraycopy(nextSolution, 0, solution, 0, n);

            iteration++;
        }

        if (iteration == MAX_ITERATIONS) {
            System.err.println("El método no converge después de " + MAX_ITERATIONS + " iteraciones.");
        }

        return solution;
    }
}

