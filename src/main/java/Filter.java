import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Filter extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getMember() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy',' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            if (e.getMessage().getContentRaw().charAt(0) == '~')
                System.out.println("~  {" + formatter.format(date) + "} [" + e.getChannel().getName() + "] [" + e.getMember().getUser().getAsTag() + "]: " + e.getMessage().getContentRaw());
            else
                System.out.println("{" + formatter.format(date) + "} [" + e.getChannel().getName() + "] [" + e.getMember().getUser().getAsTag() + "]: " + e.getMessage().getContentRaw());
            if (e.getMessage().getAttachments().size() > 0) {
                for (int x = 0; x < e.getMessage().getAttachments().size(); x++) {
                    System.out.println(e.getMessage().getAttachments().get(0).getProxyUrl());
                }
            }
        }

        if (e.getMessage().getContentRaw().contains("~help"))
            e.getMessage().addReaction("\uD83D\uDC4C").queue();

    }

    public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy',' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.print("DM {" + formatter.format(date) + "} [" + e.getChannel().getName() + "] [" + e.getAuthor().getAsTag() + "]: " + e.getMessage().getContentRaw());
        if (e.getMessage().getAttachments().size() > 0) {
            for (int x = 0; x < e.getMessage().getAttachments().size(); x++) {
                System.out.println("[ATTACHMENT] " + e.getMessage().getAttachments().get(0).getFileName());
            }
        }
        else System.out.println();

        if (e.getAuthor().getId().equalsIgnoreCase("666880405303066627")) {
            if (e.getMessage().getAttachments() != null) {
                for (Message.Attachment a : e.getMessage().getAttachments()) {
                    a.downloadToFile("C:\\Users\\ethan\\Desktop\\" + a.getFileName())
                            .exceptionally(t ->
                            {
                                t.printStackTrace();
                                return null;
                            });
                }
            }
        }
    }

}
