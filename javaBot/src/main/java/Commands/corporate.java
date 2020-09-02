package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;

public class corporate extends Command {
    public corporate() {
        this.name = "corporate";
        this.aliases = new String[]{"cor"};
        this.help = "If you ever need some corporate bs";
    }

    @Override
    protected void execute(CommandEvent e) {
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());

        JSONParser parser = new JSONParser();
        String phrase = "";

        try {
            URL apiUrl = new URL("https://corporatebs-generator.sameerkumar.website/");
            BufferedReader reader = new BufferedReader(new InputStreamReader(apiUrl.openConnection().getInputStream()));
            Random random = new Random();

            String lines;
            while ((lines = reader.readLine()) != null) {
                JSONArray arr = new JSONArray();
                arr.add(parser.parse(lines));

                for (Object o : arr) {
                    JSONObject obj = (JSONObject) o;

                    phrase = (String) obj.get("phrase");
                }
            }
            reader.close();
            String[] pre = new String[]{"I just love to ",
                    "Hello, my TedTalk will be about the magic behind ",
                    "Don't you ever wish you could ",
                    "Behold, my latest invention! The power to ",
                    "Alright everyone, corporate says we need to "};
            int choice = random.nextInt(pre.length);
            e.getChannel().sendMessage(pre[choice] + phrase).queue();

        } catch (IOException | ParseException | IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }
    }
}