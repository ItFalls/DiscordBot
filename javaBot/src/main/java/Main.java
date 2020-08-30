import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static void main (String[] args) throws LoginException {
        JDABuilder builder = new JDABuilder((AccountType.BOT));
        String token = "NzQ5Njc4NzE2MjUwMjI2NzA4.X0vetg.lbnj54QPkYZSenlFJND8WD58UFw";
        builder.setToken(token);
        builder.addEventListeners(new Main());
        builder.build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        System.out.println("[" + event.getAuthor().getName() + "]:" + event.getMessage().getContentDisplay());

        if(event.getMessage().getContentRaw().equals("!ping")){
            event.getChannel().sendMessage("Pong!").queue();
        }

    }
}
