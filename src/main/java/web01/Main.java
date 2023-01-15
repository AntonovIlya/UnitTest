package web01;

public class Main {

    private static final int PORT = 8080;

    public static void main(String[] args) {

        final var server = new Server();

        server.listen(PORT);

    }
}