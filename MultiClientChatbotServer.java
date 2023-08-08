import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiClientChatbotServer {
    private static final int PORT = 12345;
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String clientAddress = clientSocket.getInetAddress().getHostAddress();
            System.out.println("Handling messages from client: " + clientAddress);

            writer.println("Connected to chatbot. Start typing your messages...");

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received from " + clientAddress + ": " + message);

                // Process the client's message with the chatbot
                String response = processChatbotMessage(message);
                writer.println("Chatbot: " + response);
            }

            System.out.println("Client disconnected: " + clientAddress);
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processChatbotMessage(String message) {
        // Simple chatbot logic (echo the client's message with a prefix)
        return "You said: " + message;
    }
}
