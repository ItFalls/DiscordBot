package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.utils.AttachmentOption;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class meme extends Command {
    public meme() {
        this.name = "meme";
        this.aliases = new String[]{"memes", "reddit"};
        this.help = "meme : Random meme | meme [subreddit] : Meme from subreddit";
    }

    @Override
    protected void execute(CommandEvent e) {
        JSONParser parser = new JSONParser();
        String post = "";
        String sub = "";
        String title = "";
        String url = "";
        boolean nsfw = false;
        boolean spoiler = false;
        long upvotes = 0;

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
                    spoiler = (boolean) obj.get("spoiler");
                    upvotes = (long) obj.get("ups");
                }
            }
            reader.close();

            if (!nsfw) {
                e.reply(e.getMember().getAsMention());
                URL tempurl = new URL(url);
                String tDir = System.getProperty("java.io.tmpdir");
                String path = tDir + "tmp" + ".pdf";
                File file = new File(path);
                file.deleteOnExit();
                FileUtils.copyURLToFile(tempurl, file);
                EmbedBuilder emb = new EmbedBuilder()
                        .setTitle(title, post)
                        .setColor(e.getMember().getRoles().get(0).getColor())
                        .setFooter("Subreddit: " + sub + "   |   Upvotes: " + upvotes);
                if(spoiler)
                    e.getChannel().sendFile(file, "cat.png", AttachmentOption.SPOILER).embed(emb.build()).queue();
                else {
                    emb.setImage(url);
                    e.getChannel().sendMessage(emb.build()).queue();
                }
            }
            else
                e.getChannel().sendMessage(":woman_gesturing_no:  No peeking at my OnlyFans :woman_gesturing_no: ").queue();

        } catch (IOException | ParseException | IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }

        e.getMessage().delete().queue();
    }
}
