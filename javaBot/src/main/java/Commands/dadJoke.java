package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class dadJoke extends Command {
    public dadJoke() {
        this.name = "dad";
        this.aliases = new String[]{"dadJoke"};
        this.help = "Random dad joke";
    }

    @Override
    protected void execute(CommandEvent e) {
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());

        try {
            String command = "curl -H \"Accept: text/plain\" https://icanhazdadjoke.com/";
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = reader.readLine();
            e.getChannel().sendMessage(line).queue();

            reader.close();
        } catch (IOException | IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }
    }
}
