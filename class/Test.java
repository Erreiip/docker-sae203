import serveur.GuildManager;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {

        File file = new File("./guilds/test.json");
        System.out.println(file.toString());
        System.out.println(file.exists());

        GuildManager.creerServeur("test2", "test2");
    }
}