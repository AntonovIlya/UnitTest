package web01;

import java.io.*;
import java.net.ServerSocket;
import java.util.Map;
import java.util.concurrent.*;

public class Server {

    private static final int NUMBER_OF_THREADS = 64;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private final Map<Integer, Handler> handlers = new ConcurrentHashMap<>();

    public Server() {
        addDefaultHandler();
    }

    public void listen(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (final var socket = serverSocket.accept();
                     final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     final var out = new BufferedOutputStream(socket.getOutputStream())) {
                    Request request = new Request(in);
                    Runnable muRunnable = () -> processing(request, out);
                    final Future<?> task = threadPool.submit(muRunnable);
                    task.get();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processing(Request request, BufferedOutputStream out) {
        Handler handler = handlers.get(getHashCode(request.getRequestMethod(), request.getRequestedURL()));
        if (handler != null) {
            handler.handle(request, out);
        } else {
            handlers.get("default".hashCode()).handle(request, out);
        }
    }

    public void addHandler(String method, String path, Handler handler) {
        handlers.put(getHashCode(method, path), handler);
    }

    private int getHashCode(String a, String b) {
        return (a + b).hashCode();
    }

    private void addDefaultHandler() {
        handlers.put("default".hashCode(),(request, out) -> {
            try {
                out.write((
                        "HTTP/1.1 404 Not Found\r\n" +
                                "Content-Length: 0\r\n" +
                                "Connection: close\r\n" +
                                "\r\n").getBytes());
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
