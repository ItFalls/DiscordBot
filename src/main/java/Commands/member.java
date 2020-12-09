package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.ArrayList;

public class member extends Command {
    public member() {
        this.name = "member";
        this.aliases = new String[]{"mem", "me"};
        this.help = "{\"mem\", \"me\"} member [User] - Tells you about the member";
    }

    @Override
    protected void execute(CommandEvent e) {
        String message = e.getMessage().getContentDisplay();
        message = message.replaceAll("  ", " ");
        String[] arr = message.split(" ");

        EmbedBuilder emb = new EmbedBuilder();

        try {
            User user = null;
            Member member = null;
            if (arr.length == 1) {
                user = e.getMessage().getAuthor();
                member = e.getSelfMember();
            } else {
                user = e.getMessage().getMentionedUsers().get(0);
                member = e.getMessage().getMentionedMembers().get(0);
            }

            emb.setTitle("Member: " + user.getAsTag());
            emb.addField("Date Joined Discord", user.getTimeCreated().getMonthValue() + "/" + user.getTimeCreated().getDayOfMonth() + "/" + user.getTimeCreated().getYear(), true);
            emb.setThumbnail(user.getAvatarUrl());

            if(member.hasTimeJoined())
                emb.addField("Date Joined Server", member.getTimeJoined().getMonthValue() + "/" + member.getTimeJoined().getDayOfMonth() + "/" + member.getTimeJoined().getYear(), true);

            if (member.getRoles().size() > 0)
                emb.setColor(e.getMember().getRoles().get(0).getColor());
            else
                emb.setColor(Color.gray);

            ArrayList<String> perms = new ArrayList<>();

            if (user.getId().equalsIgnoreCase("666880405303066627")) {
                perms = new ArrayList<>();
                perms.add(Permission.valueOf("MESSAGE_ADD_REACTION").getName());
                perms.add(Permission.valueOf("VIEW_CHANNEL").getName());
                perms.add(Permission.valueOf("NICKNAME_MANAGE").getName());
                perms.add(Permission.valueOf("MANAGE_ROLES").getName());
                perms.add(Permission.valueOf("MANAGE_EMOTES").getName());

            } else {
                if (member.hasPermission(Permission.valueOf("CREATE_INSTANT_INVITE")))
                    perms.add(Permission.valueOf("CREATE_INSTANT_INVITE").getName());
                if (member.hasPermission(Permission.valueOf("KICK_MEMBERS")))
                    perms.add(Permission.valueOf("KICK_MEMBERS").getName());
                if (member.hasPermission(Permission.valueOf("BAN_MEMBERS")))
                    perms.add(Permission.valueOf("BAN_MEMBERS").getName());
                if (member.hasPermission(Permission.valueOf("ADMINISTRATOR")))
                    perms.add(Permission.valueOf("ADMINISTRATOR").getName());
                if (member.hasPermission(Permission.valueOf("MANAGE_CHANNEL")))
                    perms.add(Permission.valueOf("MANAGE_CHANNEL").getName());
                if (member.hasPermission(Permission.valueOf("MANAGE_SERVER")))
                    perms.add(Permission.valueOf("MANAGE_SERVER").getName());
                if (member.hasPermission(Permission.valueOf("MESSAGE_ADD_REACTION")))
                    perms.add(Permission.valueOf("MESSAGE_ADD_REACTION").getName());
                if (member.hasPermission(Permission.valueOf("VIEW_AUDIT_LOGS")))
                    perms.add(Permission.valueOf("VIEW_AUDIT_LOGS").getName());
                if (member.hasPermission(Permission.valueOf("PRIORITY_SPEAKER")))
                    perms.add(Permission.valueOf("PRIORITY_SPEAKER").getName());
                if (member.hasPermission(Permission.valueOf("VIEW_GUILD_INSIGHTS")))
                    perms.add(Permission.valueOf("VIEW_GUILD_INSIGHTS").getName());
                if (member.hasPermission(Permission.valueOf("VIEW_CHANNEL")))
                    perms.add(Permission.valueOf("VIEW_CHANNEL").getName());
                if (member.hasPermission(Permission.valueOf("NICKNAME_MANAGE")))
                    perms.add(Permission.valueOf("NICKNAME_MANAGE").getName());
                if (member.hasPermission(Permission.valueOf("NICKNAME_CHANGE")))
                    perms.add(Permission.valueOf("NICKNAME_CHANGE").getName());
                if (member.hasPermission(Permission.valueOf("MANAGE_ROLES")))
                    perms.add(Permission.valueOf("MANAGE_ROLES").getName());
                if (member.hasPermission(Permission.valueOf("MANAGE_PERMISSIONS")))
                    perms.add(Permission.valueOf("MANAGE_PERMISSIONS").getName());
                if (member.hasPermission(Permission.valueOf("MANAGE_WEBHOOKS")))
                    perms.add(Permission.valueOf("MANAGE_WEBHOOKS").getName());
                if (member.hasPermission(Permission.valueOf("MANAGE_EMOTES")))
                    perms.add(Permission.valueOf("MANAGE_EMOTES").getName());
            }
            StringBuilder build = new StringBuilder();
            for (String str : perms)
                build.append(str).append("\n");

            emb.addField("Permissions", build.toString(), false);

            emb.setFooter("Requested By: @" + e.getMember().getEffectiveName());

        } catch (IndexOutOfBoundsException exception) {
            e.getChannel().sendMessage(":no_entry: Something went wrong, F in the chat.").queue();
            exception.printStackTrace();
        } catch (NullPointerException exception) {
            e.getChannel().sendMessage(":no_entry: No Permissions :cry:").queue();
            exception.printStackTrace();
        }
        e.getChannel().sendMessage(emb.build()).queue();
    }
}