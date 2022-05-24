package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManager extends Thread {
    
    private boolean enMarche = false;

    private Socket client;

    private PrintWriter input;
    private BufferedReader output;

    private String pseudo;
    private String guild;
    private String mdp;

    public ClientManager(Socket client) {

        this.client = client;

        this.guild = this.pseudo = null;
        
        try {
            
            this.input = new PrintWriter(client.getOutputStream(), true);
            this.output = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } 
        catch (IOException exception) {

            exception.printStackTrace();   
        }
    }

    @Override
    public void run() {

        this.enMarche = true;

        while (this.enMarche == true) {

            try {
                
                String requete = this.output.readLine();
                System.out.println(requete);
                int code = Integer.parseInt(requete.substring(0, 3));
                requete = requete.substring(4);

                System.out.println("" + code + " " + requete);

                switch (code) {

                    case 1 -> this.send("001: " + GuildManager.recherche(requete.substring(4)));
                    case 2 -> {

                        String pseudo, serveur, mdp;
                        pseudo  = requete.split("/")[0];
                        serveur = requete.split("/")[1];
                        mdp     = requete.split("/")[2];
                        
                        if (GuildManager.connecter(pseudo, serveur, mdp)) {

                            this.pseudo = pseudo;
                            this.guild = serveur;
                            this.mdp = mdp;
                            
                            this.send("002: OK");
                        }
                        else {

                            this.send("002: NO");
                        }
                    }
                    case 3 -> {

                        if (this.guild != null) {

                            GuildManager.post(this.pseudo, this.guild, this.mdp, requete);
                        }
                        else {
                            this.send("002: NO");
                        }
                    } 
                    case 4 -> {

                        String pseudo, nom, mdp;
                        pseudo = requete.split("/")[0];
                        nom    = requete.split("/")[1];
                        mdp    = requete.split("/")[1];

                        GuildManager.creerServeur(nom, mdp);

                        if (GuildManager.connecter(pseudo, nom, mdp)) {

                            this.pseudo = pseudo;
                            this.guild  = nom;
                            this.mdp    = mdp;
                            
                            this.send("004: OK");
                        }
                        else {

                            this.send("004: NO");
                        }
                    }  
                    case 6 -> {


                    }
                    case 10 -> {

                        this.send(String.format("010: %s", this.guild));
                    }
                    case 69 -> {

                        if (this.guild != null) {

                            GuildManager.delMembre(this.pseudo, this.guild, this.mdp);
                        }
                    }
                }
            } 
            catch (IOException exception) {

                exception.printStackTrace();
                continue;
            }
        }
    }

    public void sendMessages(String guild, String mdp, String messages) {

        if (this.guild.equals(guild) && this.mdp.equals(mdp)) {

            this.send("006: " + messages);
        }
    }

    public void sendMembres(String guild, String mdp, String membres) {

        if (this.guild.equals(guild) && this.mdp.equals(mdp)) {

            this.send("007: " + membres);
        }
    }

    private void send(String data) {

        this.input.print(data);
    }
}