package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.User;

import java.util.Objects;

public class avatar extends Command {
    public avatar() {
        this.name = "avatar";
        this.aliases = new String[]{"av"};
        this.help = "Shows the specified user's avatar";
    }

    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentDisplay();
        message = message.replaceAll("  ", " ");
        String[] arr = message.split(" ");

        if (arr.length == 1)
            e.getChannel().sendMessage(Objects.requireNonNull(e.getAuthor().getAvatarUrl())).queue();

        else {
            User user = e.getMessage().getMentionedUsers().get(0);
            e.getChannel().sendMessage(Objects.requireNonNull(user.getAvatarUrl())).queue();
        }
    }
}
