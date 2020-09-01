package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class anti extends Command {
    public anti(){
        this.name = "anti";
        this.help = "ANTISTEVE";
    }

    @Override
    protected void execute(CommandEvent e){
        System.out.println("[" + e.getAuthor().getName() + "]: " + e.getMessage().getContentRaw());

        e.reply("steve!");
    }
}
