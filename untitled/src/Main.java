import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 8088;
    private static Set<PrintWriter> clients = new HashSet<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер работает");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                username = in.readLine();
                System.out.println(username + " зашел в чат.");
                clients.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(username + ": " + message);
                    for (PrintWriter client : clients) {
                        client.println(username + ": " + message);
                    }
                }
            } catch (IOException e) {
                System.err.println("Клиентская ошибка: " + e.getMessage());
            } finally {
                try {
                    socket.close();
                    clients.remove(out);
                    System.out.println("Клиент потерял соединение.");
                } catch (IOException e) {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            }
        }
    }
}
