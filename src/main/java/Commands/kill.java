package Commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class kill extends Command {
    public kill(){
        this.name = "kill";
        this.aliases = new String[]{"bai"};
        this.help = "Bye Bye!";
    }

    @Override
    protected void execute(CommandEvent e){
        if(e.getAuthor().getId().equalsIgnoreCase("666880405303066627")) {
            e.reply("https://media1.tenor.com/images/ba6274e75aa5db794344160f52f93cab/tenor.gif?itemid=13907870");
            System.exit(0);
        }
        else
            e.reply("Lol no");
    }
}
