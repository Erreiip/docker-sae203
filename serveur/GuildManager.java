package serveur;

import java.util.List;
import serveur.objet.Message;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import libs.UtilsJSON;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class GuildManager {

    private static Map<Integer, List<String>> membres = new HashMap<Integer, List<String>>();

    public static String recherche(String regex) {

        File[] files = new File("serveur/guilds").listFiles();

        String sRet = "";
        for (File file : files) {
            if (file.isFile() && file.getName().replace(".json", "").contains(regex)) {
                sRet += file.getName().replace(".json", "") + "|";
            }
        }

        return sRet;
    }

    public synchronized static boolean creerServeur(String nom, String mdp) {

        File file = new File("serveur/guilds/" + GuildManager.hashCode(nom, mdp) + ".json");
        if (file.exists())
            return false;

        Map<String, Object> serveur = new HashMap<String, Object>();

        serveur.put("nom", nom);
        serveur.put("clé", mdp);
        serveur.put("messages", new ArrayList<Message>());

        try {

            PrintWriter fichier = new PrintWriter(new FileOutputStream(file));
            fichier.print(UtilsJSON.toJSON(serveur));
            fichier.close();

            return true;
        } catch (IOException exception) {

            exception.printStackTrace();
            return false;
        }
    }

    public synchronized static boolean connecter(String pseudo, String serveur, String mdp) {

        File[] files = new File("serveur/guilds").listFiles();

        for (File file : files) {
            if (file.isFile() &&
                    file.getName().replace(".json", "").equals(
                            Integer.toString(GuildManager.hashCode(serveur, mdp)))) {

                GuildManager.addMembre(pseudo, serveur, mdp);

                return true;
            }
        }

        return false;
    }

    public synchronized static void post(String pseudo, String serveur, String mdp, String message) {

        try {

            Map<String, Object> data = UtilsJSON.getFromJSON(
                    Files.readString(
                            Paths.get("serveur/guilds/" + GuildManager.hashCode(serveur, mdp) + ".json")),
                    Map.class);

            List<Message> messages = (List<Message>) data.get("messages");
            messages.add(new Message(
                pseudo, 
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()).toString(), 
                message
            ));

            PrintWriter fichier = new PrintWriter(new FileOutputStream(
                    new File("serveur/guilds/" + GuildManager.hashCode(serveur, mdp) + ".json")));
            fichier.print(UtilsJSON.toJSON(data));
            fichier.close();
        } catch (IOException exception) {

            exception.printStackTrace();
        }

        try {

            Map<String, Object> data = UtilsJSON.getFromJSON(
                    Files.readString(
                            Paths.get("serveur/guilds/" + GuildManager.hashCode(serveur, mdp) + ".json")),
                    Map.class);

            List<Map<String, String>> messages = (List<Map<String, String>>) data.get("messages");

            String sMsg = "";
            for (Map<String, String> msg : messages) {

                sMsg += String.format(
                        "%s/%s/%s",
                        msg.get("auteur"),
                        msg.get("contenu"),
                        msg.get("date")) + "&r3u";
            }
            sMsg = sMsg.substring(0, sMsg.length() - 4);

            ConnectionManager.sendMessages(serveur, mdp, sMsg);
        } catch (IOException exception) {

            exception.printStackTrace();
        }
    }

    public synchronized static void addMembre(String pseudo, String serveur, String mdp) {

        if (GuildManager.membres.containsKey(GuildManager.hashCode(serveur, mdp)) == false) {

            GuildManager.membres.put(GuildManager.hashCode(serveur, mdp), new ArrayList<String>());
        }

        GuildManager.membres.get(GuildManager.hashCode(serveur, mdp)).add(pseudo);

        String sMembre = "";
        for (String membre : GuildManager.membres.get(GuildManager.hashCode(serveur, mdp))) {

            sMembre += membre + "&r3u";
        }
        sMembre = sMembre.substring(0, sMembre.length() - 4);

        ConnectionManager.sendMembres(serveur, mdp, sMembre);
    }

    public synchronized static void delMembre(String pseudo, String serveur, String mdp) {

        if (GuildManager.membres.containsKey(GuildManager.hashCode(serveur, mdp))) {

            for (int cpt = 0; cpt < GuildManager.membres.get(GuildManager.hashCode(serveur, mdp)).size(); cpt++) {

                if (pseudo.equals(GuildManager.membres.get(GuildManager.hashCode(serveur, mdp)).get(cpt))) {

                    GuildManager.membres.get(GuildManager.hashCode(serveur, mdp)).remove(cpt);
                    break;
                }
            }
        }

        List<String> membres = GuildManager.membres.get(GuildManager.hashCode(serveur, mdp));

        String sMembre = "";
        for (String membre : membres) {

            sMembre += membre + "&r3u";
        }
        if (membres.size() > 0)
            sMembre = sMembre.substring(0, sMembre.length() - 4);

        ConnectionManager.sendMembres(serveur, mdp, sMembre);
    }

    private static int hashCode(String nom, String mdp) {

        return (nom + mdp).hashCode();
    }
}
