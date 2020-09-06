package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class insult extends Command {
    public insult() {
        this.name = "insult";
        this.aliases = new String[]{"roast"};
        this.help = "insult [name] || Random Insult";
    }

    @Override
    protected void execute(CommandEvent e) {
        try {
            URL meme = new URL("https://insult.mattbas.org/api/insult");
            String prefix = "";
            if (e.getMessage().getContentRaw().split(" ").length > 1) {
                prefix = e.getMessage().getContentRaw().split(" ")[1];
                prefix = (prefix.charAt(0)+"").toUpperCase() + prefix.substring(1);
                BufferedReader reader = new BufferedReader(new InputStreamReader(meme.openConnection().getInputStream()));
                String insult = reader.readLine();
                reader.close();
                insult = (insult.charAt(0)+"").toLowerCase() + insult.substring(1);
                e.getChannel().sendMessage(prefix + ", " + insult).queue();
            }
            else{
                BufferedReader reader = new BufferedReader(new InputStreamReader(meme.openConnection().getInputStream()));
                e.getChannel().sendMessage(reader.readLine()).queue();
                reader.close();
            }
        } catch (IOException | IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }
    }
}
