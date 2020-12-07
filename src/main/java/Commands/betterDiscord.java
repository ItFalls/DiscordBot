package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class betterDiscord extends Command {
    public betterDiscord() {
        this.name = "betterDiscord";
        this.aliases = new String[]{"themes"};
        this.help = "command to show links for betterDiscord";
    }

    @Override
    protected void execute(CommandEvent e) {
        try {
            EmbedBuilder emb = new EmbedBuilder()
                    .setTitle("BetterDiscord")
                    .setThumbnail("https://10scopes.com/wp-content/uploads/2020/02/better-discord-logo.jpg")
                    .setColor(Color.blue)
                    .addField("Instructions & Download", "https://github.com/rauenzi/BetterDiscordApp", false)
                    .addField("Themes", "https://betterdiscordlibrary.com/themes", false)
                    .addField("Plugins", "https://betterdiscordlibrary.com/plugins", false);
            e.getChannel().sendMessage(emb.build()).queue();
            e.getMessage().delete().queue();
        } catch (IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }
    }
}
