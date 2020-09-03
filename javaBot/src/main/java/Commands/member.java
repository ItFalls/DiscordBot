package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class member extends Command {
    public member() {
        this.name = "member";
        this.aliases = new String[]{"mem", "me"};
        this.help = "mem/member [User] - Tells you about the member";
    }

    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentDisplay();
        message = message.replaceAll("  ", " ");
        String[] arr = message.split(" ");

        EmbedBuilder emb = new EmbedBuilder();
        if (arr.length == 1) {
            emb.setTitle("Member: " + e.getAuthor().getName());
            emb.addField("Date Joined Discord", e.getAuthor().getTimeCreated().getMonthValue() + "/" + e.getAuthor().getTimeCreated().getDayOfMonth() + "/" + e.getAuthor().getTimeCreated().getYear(), true);
            emb.setThumbnail(e.getAuthor().getAvatarUrl());
            if (e.getMember().getRoles().size() > 0)
                emb.setColor(e.getMember().getRoles().get(0).getColor());
            else
                emb.setColor(Color.gray);
        } else {
            try {
                User user = e.getMessage().getMentionedUsers().get(0);
                emb.setTitle("Member: " + user.getName());
                emb.addField("Date Joined Discord", user.getTimeCreated().getMonthValue() + "/" + user.getTimeCreated().getDayOfMonth() + "/" + user.getTimeCreated().getYear(), true);
                emb.setThumbnail(user.getAvatarUrl());
                if (e.getGuild().getMember(user).getRoles().size() > 0)
                    emb.setColor(e.getMember().getRoles().get(0).getColor());
                else
                    emb.setColor(Color.gray);
            }catch (IndexOutOfBoundsException exception){
                e.getChannel().sendMessage(":no_entry: Something went wrong, f in the chat.").queue();
                exception.printStackTrace();
            }
        }

        e.getChannel().sendMessage(emb.build()).queue();
    }

}
