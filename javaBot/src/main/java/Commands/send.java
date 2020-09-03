package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class send extends Command {
    public send() {
        this.name = "send";
        this.aliases = new String[]{"message"};
        this.help = "Sends a message you want";
    }

    @Override
    protected void execute(CommandEvent e) {
        e.reply("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());
    }
}
