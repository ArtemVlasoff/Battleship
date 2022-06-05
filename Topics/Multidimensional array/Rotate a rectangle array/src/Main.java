import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int[][] matrix = new int[x][y];
        int[][] result = new int[y][x];


        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] = scanner.nextInt();
            }
        }

        for (int i = 0; i < result.length; i++) {
            for (int k = 0; k < result[i].length; k++) {
                result[i][k] = matrix[matrix.length - 1 - k][i];
                System.out.print(result[i][k] + " ");
            }
            System.out.println("");
        }


    }
}