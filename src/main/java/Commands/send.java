package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class send extends Command {
    public send() {
        this.name = "send";
        this.aliases = new String[]{"message", "say"};
        this.help = "{\"message\", \"say\"} Sends a message you want";
    }

    @Override
    protected void execute(CommandEvent e) {
        e.reply("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw().substring(e.getMessage().getContentRaw().indexOf(" ")));
        e.getMessage().delete().queue();
    }
}
