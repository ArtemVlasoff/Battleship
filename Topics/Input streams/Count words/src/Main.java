import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = reader.read();
        int counter = 0;

        String line = reader.readLine();
        String[] words = line.trim().split("\\s+");

        if (line.trim().isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(words.length);
        }

        reader.close();
    }
}