package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager extends Thread {
    
    public static boolean enMarche = false;

    private ServerSocket socket;    
    private static List<ClientManager> clients;

    public ConnectionManager(ServerSocket socket) {

        this.socket = socket;
        ConnectionManager.enMarche = true;
        ConnectionManager.clients = new ArrayList<ClientManager>();
    }

    @Override
    public void run() {

        while(ConnectionManager.enMarche == true) {

            try {
                
                ClientManager client = new ClientManager(socket.accept());
                ConnectionManager.clients.add(client);
                client.start();
            } 
            catch (IOException exception) {
                
                exception.printStackTrace();
            }
        }
    }

    public static void sendMessages(String guild, String mdp, String messages) {

        for (ClientManager client : ConnectionManager.clients) {

            client.sendMessages(guild, mdp, messages);
        }
    }

    public static void sendMembres(String guild, String mdp, String membres) {

        for (ClientManager client : ConnectionManager.clients) {

            client.sendMembres(guild, mdp, membres);
        }
    }
}