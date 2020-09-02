package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class meme extends Command {
    public meme() {
        this.name = "meme";
        this.aliases = new String[]{"memes", "reddit"};
        this.help = "meme : Random meme || meme [subreddit] : Meme from subreddit";
    }

    @Override
    protected void execute(CommandEvent e) {
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());

            JSONParser parser = new JSONParser();
            String post = "";
            String sub = "";
            String title = "";
            String url = "";
            boolean nsfw = false;

            try {
                URL meme;
                if (e.getMessage().getContentRaw().split(" ").length > 1)
                    meme = new URL("https://meme-api.herokuapp.com/gimme" + "/" + e.getMessage().getContentRaw().split(" ")[1]);
                else
                    meme = new URL("https://meme-api.herokuapp.com/gimme");
                BufferedReader reader = new BufferedReader(new InputStreamReader(meme.openConnection().getInputStream()));

                String lines;
                while ((lines = reader.readLine()) != null) {
                    JSONArray arr = new JSONArray();
                    arr.add(parser.parse(lines));

                    for (Object o : arr) {
                        JSONObject obj = (JSONObject) o;

                        post = (String) obj.get("postLink");
                        sub = (String) obj.get("subreddit");
                        title = (String) obj.get("title");
                        url = (String) obj.get("url");
                        nsfw = (boolean) obj.get("nsfw");
                    }
                }
                reader.close();

                if(!nsfw) {
                    EmbedBuilder emb = new EmbedBuilder()
                            .setTitle(title, post)
                            .setImage(url)
                            .setColor(Color.yellow)
                            .setFooter("Subreddit: " + sub);
                    e.getChannel().sendMessage(emb.build()).queue();
                }
                else{
                    e.getChannel().sendMessage(":woman_gesturing_no:  No peeking at my OnlyFans :woman_gesturing_no: ").queue();
                }
            } catch (IOException | ParseException | IndexOutOfBoundsException | ClassCastException exception) {
                e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
                exception.printStackTrace();
            }

    }
}
