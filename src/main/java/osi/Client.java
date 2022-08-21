package osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 8091);
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        printWriter.println("ClientName");
        String receiveMsg = bufferedReader.readLine();
        System.out.println(receiveMsg);
    }
}
