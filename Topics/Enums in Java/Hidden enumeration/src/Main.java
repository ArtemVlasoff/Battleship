    public class Main {

    public static void main(String[] args) {
        int counter = 0;

        for (Secret secret : Secret.values()) {
            if (secret.name().substring(0, 4).equals("STAR")) {
                counter++;
            }
        }


        System.out.println(counter);
    }
}

/* sample enum for inspiration
enum Secret {
    STAR, CRASH, START, // ...
}
*/