package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class ping extends Command {
    public ping(){
        this.name = "ping";
        this.aliases = new String[]{"pingpong"};
        this.help = "Pong!";
    }

    @Override
    protected void execute(CommandEvent e){
        e.reply("Pong!");
    }
}