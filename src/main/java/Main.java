import Commands.*;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.RichPresence;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Main extends ListenerAdapter {
    public static void main (String[] args) throws Exception {
        JDA builder = JDABuilder.createDefault("NzQ5Njc4NzE2MjUwMjI2NzA4.X0vetg.Fn2urwHAGHdY5Erzlc865S40Nps").build();
        builder.addEventListener(new Filter());
        CommandClientBuilder cmd = new CommandClientBuilder();
        cmd.setOwnerId("749678716250226708");
        cmd.setPrefix("~");
        cmd.setHelpWord("help");
        cmd.setActivity(new Activity() {
            @Override
            public boolean isRich() {
                return true;}
            @Nullable
            @Override
            public RichPresence asRichPresence() {
                return null;
            }

            @NotNull
            @Override
            public String getName() {
                return "AKUDAMA DRIVE";
            }

            @Nullable
            @Override
            public String getUrl() {
                return "https://media.discordapp.net/attachments/748734717825908778/765062481344331786/ao53s5xnfis51.png?width=596&height=475";
            }

            @NotNull
            @Override
            public ActivityType getType() {
                return ActivityType.fromKey(3);
            }

            @Nullable
            @Override
            public Timestamps getTimestamps() {
                return new Timestamps(0,999);
            }

            @Nullable
            @Override
            public Emoji getEmoji() {
                return new Emoji("U+1F440");
            }
        });

        cmd.addCommand(new anti());
        cmd.addCommand(new send());
        cmd.addCommand(new ping());
        cmd.addCommand(new member());
        cmd.addCommand(new avatar());
        cmd.addCommand(new meme());
        cmd.addCommand(new fanclub());
        cmd.addCommand(new joke());
        cmd.addCommand(new dadJoke());
        cmd.addCommand(new geekJoke());
        cmd.addCommand(new corporate());
        cmd.addCommand(new insult());
        cmd.addCommand(new clear());
        cmd.addCommand(new betterDiscord());
        cmd.addCommand(new kill());

        CommandClient client = cmd.build();
        builder.addEventListener(client);
    }
}
