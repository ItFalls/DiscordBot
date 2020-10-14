package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class joke extends Command {
    public joke() {
        this.name = "joke";
        this.aliases = new String[]{"haha"};
        this.help = "Random unfunny joke";
    }

    @Override
    protected void execute(CommandEvent e) {
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());

        try {
            URL url = new URL("https://sv443.net/jokeapi/v2/joke/Miscellaneous,Dark,Pun?blacklistFlags=nsfw,religious,political,racist,sexist&format=txt&type=single");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                e.getChannel().sendMessage(line).queue();
            }
            reader.close();
        } catch (IOException | IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }
        e.getMessage().delete().queue();
    }

}
