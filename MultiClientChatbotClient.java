import java.io.*;
import java.net.*;

public class MultiClientChatbotClient {
    public static void main(String[] args) {
        try {
            Socket clientSocket = new Socket("localhost", 12345);
            System.out.println("Connected to chatbot server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            Thread receiveThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = serverReader.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

            String message;
            while (true) {
                message = reader.readLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                writer.println(message);
            }

            System.out.println("Chat ended. Closing connection.");
            receiveThread.interrupt();
            clientSocket.close();
            reader.close();
            writer.close();
            serverReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
