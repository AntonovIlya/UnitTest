package osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8091);
        Socket clientSocket = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

        System.out.printf("New connection accepted. Port %d%n", clientSocket.getPort());

        final String name = bufferedReader.readLine();

        System.out.printf("Hi %s!", name);

        printWriter.println(String.format("Your port is %d", clientSocket.getPort()));


    }
}
