package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.Objects;

public class avatar extends Command {
    public avatar() {
        this.name = "avatar";
        this.aliases = new String[]{"av"};
        this.help = "Shows the specified user's avatar";
    }

    @Override
    protected void execute(CommandEvent e) {
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());
        String message = e.getMessage().getContentDisplay();

        e.getChannel().sendMessage(Objects.requireNonNull(e.getAuthor().getAvatarUrl())).queue();
    }
}
