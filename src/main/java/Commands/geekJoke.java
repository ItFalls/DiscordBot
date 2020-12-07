package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class geekJoke extends Command {
    public geekJoke() {
        this.name = "geek";
        this.aliases = new String[]{"geekjoke"};
        this.help = "Random unfunny joke";
    }

    @Override
    protected void execute(CommandEvent e) {
        try {
            URL url = new URL("https://geek-jokes.sameerkumar.website/api");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            String line = reader.readLine();
            e.getChannel().sendMessage(line.substring(1, line.length() - 1)).queue();
            reader.close();
        } catch (IOException | IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }
        e.getMessage().delete().queue();
    }
}
