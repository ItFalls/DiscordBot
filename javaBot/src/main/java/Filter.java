import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Filter extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        System.out.println("[" + e.getChannel().getName() + "] [" + e.getMember().getEffectiveName() + "]: " + e.getMessage().getContentRaw());
        if (e.getMessage().getAttachments().size() > 0) {
            for (int x = 0; x < e.getMessage().getAttachments().size(); x++) {
                System.out.println(e.getMessage().getAttachments().get(0).getProxyUrl());
            }
        }

        String message = e.getMessage().getContentRaw();
        if (!e.getAuthor().isBot() && message.toLowerCase().contains("steve"))
            e.getChannel().sendMessage("Steve? You mean the inferior bot?").queue();

        if (!e.getAuthor().isBot() && message.toLowerCase().contains("for xp"))
            e.getChannel().sendMessage("<:mike:750142207167692810> smh this dood grinding for virtual levels <:sulley:756739582187274290>").queue();
    }
}
