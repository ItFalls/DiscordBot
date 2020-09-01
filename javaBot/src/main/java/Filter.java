import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Filter extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());

        String message = e.getMessage().getContentRaw();
        if(!e.getAuthor().isBot() && message.toLowerCase().contains("steve"))
            e.getChannel().sendMessage("Steve? You mean the inferior bot?").queue();
    }
}
