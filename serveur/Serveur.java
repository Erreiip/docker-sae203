package serveur;

import java.io.IOException;
import java.net.ServerSocket;

public class Serveur {
    
    // Port du serveur
    private static final int PORT = 6969;

    // Thread qui gère les connexions
    private static ConnectionManager thread;

    // Lance le serveur sur le port renseigné
    private static void lancerServeur() {

        try {

            Serveur.thread = new ConnectionManager(new ServerSocket(Serveur.PORT));
            Serveur.thread.start();
        } 
        catch (IOException exception) {

            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        Serveur.lancerServeur();
    }
}