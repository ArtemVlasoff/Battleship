
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[scanner.nextInt()];

        for (int i = 0; i < input.length; i++) {
            input[i] = scanner.nextInt();
        }

        int target = scanner.nextInt();
        boolean flag = false;

        for (int current : input) {
            if (current == target) {
                flag = true;
                break;
            }
        }

        System.out.println(flag);

    }
}
