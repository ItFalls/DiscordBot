package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class clear extends Command {
    public clear() {
        this.name = "clear";
        this.aliases = new String[]{"purge"};
        this.help = "Clears messages sent by bots";
    }

    @Override
    protected void execute(CommandEvent e) {
        {
            TextChannel channel = e.getTextChannel();

            OffsetDateTime time = OffsetDateTime.now().minus(1, ChronoUnit.HOURS);

            new Thread(() ->
            {
                List<Message> messages = channel.getHistory().retrievePast(50).complete();
                ArrayList<Message> delete = new ArrayList<>();

                for (Message m : messages)
                    if (m.getTimeCreated().isAfter(time))
                        if (m.getAuthor().isBot()) {
                            delete.add(m);
                            System.out.println("Deleting: " + m.toString());
                        }

                if (delete.isEmpty())
                    return;
                else
                    channel.deleteMessages(delete).complete();
            }).start();

            e.getMessage().delete().queue();
        }
    }
}