package us.fiercepvp.api.command.example;


import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import us.fiercepvp.api.command.Command;
import us.fiercepvp.api.command.interfaces.CommandInfo;

@CommandInfo(aliases = {"examplealias", "anotheralias"}, permission = "command.example", desc = "Example command.")
public class CommandExample extends Command {

    public CommandExample() {
        super("test");
    }

    @Override
    protected void onCommand(Player player, String currentAlias, String[] args) {
        player.sendMessage(ChatColor.GOLD + "Example!");
    }
}
