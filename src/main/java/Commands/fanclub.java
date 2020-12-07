package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class fanclub extends Command {
    public fanclub() {
        this.name = "fanclub";
        this.aliases = new String[]{"onlyfans"};
        this.help = "For checking out my fanclub";
    }

    @Override
    protected void execute(CommandEvent e) {
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());

        e.reply("Check out my OnlyFans : https://www.reddit.com/r/MikeWazowski/");
        e.getMessage().delete().queue();
    }
}
