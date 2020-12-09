package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.io.File;

public class betterDiscord extends Command {
    public betterDiscord() {
        this.name = "betterdiscord";
        this.aliases = new String[]{"themes","bd","theme"};
        this.help = "command to show links for betterDiscord";
    }

    @Override
    protected void execute(CommandEvent e) {
        try {
            File clearvision = new File("assets/ClearVision_v6.theme.css");
            File frostedglass = new File("assets/FrostedGlass.theme.css");
            File bestplugins = new File("assets/deathetho's_op_plugins.zip");

            e.getChannel().sendMessage("I gotchu fam").queue();

            EmbedBuilder emb = new EmbedBuilder()
                    .setTitle("BetterDiscord")
                    .setThumbnail("https://10scopes.com/wp-content/uploads/2020/02/better-discord-logo.jpg")
                    .setColor(Color.blue)
                    .addField("Instructions & Download", "https://github.com/rauenzi/BetterDiscordApp", false)
                    .addField("Themes", "https://betterdiscordlibrary.com/themes", false)
                    .addField("Plugins", "https://betterdiscordlibrary.com/plugins", false);
            e.getChannel().sendMessage(emb.build()).queue();

            e.getChannel().sendMessage("Here are the best themes combo").queue();
            e.getChannel().sendFile(clearvision).queue();
            e.getChannel().sendFile(frostedglass).queue();
            e.getChannel().sendMessage("Here are the best plugins").queue();
            e.getChannel().sendFile(bestplugins).queue();


            e.getMessage().delete().queue();
        } catch (IndexOutOfBoundsException | ClassCastException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
            exception.printStackTrace();
        }
    }
}
