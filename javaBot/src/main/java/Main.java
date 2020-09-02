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
        JDA builder = new JDABuilder(AccountType.BOT).setToken("NzQ5Njc4NzE2MjUwMjI2NzA4.X0vetg.Fn2urwHAGHdY5Erzlc865S40Nps").build();
        builder.addEventListener(new Filter());

        CommandClientBuilder cmd = new CommandClientBuilder();
        cmd.setOwnerId("749678716250226708");
        cmd.setPrefix("~");
        cmd.setHelpWord("cmd");
        cmd.setActivity(new Activity() {
            @Override
            public boolean isRich() {
                return true;
            }

            @Nullable
            @Override
            public RichPresence asRichPresence() {
                return null;
            }

            @NotNull
            @Override
            public String getName() {
                return "Your Every Step";
            }

            @Nullable
            @Override
            public String getUrl() {
                return null;
            }

            @NotNull
            @Override
            public ActivityType getType() {
                return ActivityType.fromKey(3);
            }

            @Nullable
            @Override
            public Timestamps getTimestamps() {
                return new Timestamps(0,1);
            }

            @Nullable
            @Override
            public Emoji getEmoji() {
                return null;
            }
        });

        cmd.addCommand(new anti());
        cmd.addCommand(new ping());
        cmd.addCommand(new member());
        cmd.addCommand(new avatar());
        cmd.addCommand(new meme());
        cmd.addCommand(new fanclub());
        cmd.addCommand(new joke());
        cmd.addCommand(new dadJoke());
        cmd.addCommand(new geekJoke());
        cmd.addCommand(new corporate());

        CommandClient client = cmd.build();
        builder.addEventListener(client);
    }
}
