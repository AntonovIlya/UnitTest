package web01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        final var server = new Server();

        server.addHandler("GET", "/index.html", (request, out) -> {
            try {
                final var filePath = Path.of(".", "public", "/index.html");
                final String mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                out.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n").getBytes());
                Files.copy(filePath, out);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        server.addHandler("POST", "/messages", (request, out) -> {
            System.out.println(request.getMessageBody()); // some data processing
            try {
                final var filePath = Path.of(".", "public", "/index.html");
                final String mimeType = Files.probeContentType(filePath);
                final var length = Files.size(filePath);
                out.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + mimeType + "\r\n" +
                                "Content-Length: " + length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n").getBytes());
                Files.copy(filePath, out);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        server.listen(PORT);
    }
}