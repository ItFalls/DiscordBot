package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
            AtomicBoolean isWorking = new AtomicBoolean(true);

            OffsetDateTime time = OffsetDateTime.now().minus(1, ChronoUnit.MINUTES);

            new Thread(() ->
            {
                List<Message> messages = channel.getHistory().retrievePast(50).complete();
                System.out.println("Deleting " + messages);

                for (Message m : messages)
                    if (m.getTimeCreated().isAfter(time))
                        if (m.getAuthor().getName().equalsIgnoreCase("Mike Bruhsowski"))
                            messages.remove(m);

                if (messages.isEmpty())
                    return;

                channel.deleteMessages(messages).complete();
            }).start();
        }
    }
}
