import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[scanner.nextInt()][scanner.nextInt()];

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                matrix[i][k] = scanner.nextInt();
            }
        }

        int max = matrix[0][0];
        int[] xy = {0, 0};

        for (int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix[i].length; k++) {
                if (matrix[i][k] > max) {
                    max = matrix[i][k];
                    xy[0] = i;
                    xy[1] = k;
                }
            }
        }

        System.out.println(xy[0] + " " + xy[1]);
    }
}