public class Lab1 {

    public static void main(String[] args) {
        try {
            int rows = 4;
            int cols = 4;
            int a = 5;

            int[][] B = new int[rows][cols];

            int value = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    B[i][j] = value++;
                }
            }

            System.out.println("Матриця B:");
            printMatrix(B);

            // C = a * B
            int[][] C = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    C[i][j] = a * B[i][j];
                }
            }

            System.out.println("\nМатриця C = a * B:");
            printMatrix(C);

            // середнє значення елементів матриці C
            double average = calculateAverage(C);
            System.out.println("\nСереднє значення елементів матриці C: " + average);

        }
        catch (ArithmeticException e) {
            System.out.println("Помилка арифметичної операції: " + e.getMessage());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Вихід за межі масиву: " + e.getMessage());
        }
        catch (NullPointerException e) {
            System.out.println("Спроба доступу до неініціалізованої матриці: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        }
    }

    // Виведення матриці на екран
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.printf("%5d", element);
            }
            System.out.println();
        }
    }

    // Обчислення середнього значення елементів матриці
    private static double calculateAverage(int[][] matrix) {
        int sum = 0;
        int count = 0;

        for (int[] row : matrix) {
            for (int element : row) {
                sum += element;
                count++;
            }
        }
        return (double) sum / count;
    }
}
