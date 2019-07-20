package us.fiercepvp.api.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import us.fiercepvp.api.command.interfaces.CommandInfo;

import java.util.Arrays;

public abstract class Command extends BukkitCommand {

    public Command(String name) {
        super(name);

        CommandInfo commandInfo = this.getClass().getAnnotation(CommandInfo.class);

        if (commandInfo == null) {
            throw new RuntimeException("Command class must have @CommandInfo annotation.");
        }

        if (!commandInfo.aliases()[0].equals("")) {
            setAliases(Arrays.asList(commandInfo.aliases()));
        }

        if (!commandInfo.desc().equals("")) {
            setDescription(commandInfo.desc());
        }

        if (!commandInfo.permission().equals("")) {
            setPermission(commandInfo.permission());
        }
    }

    protected abstract void onCommand(Player player, String currentAlias, String[] args);

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Player only!");
            return true;
        }

        if (!testPermission(commandSender)) {
            return true;
        }

        onCommand((Player) commandSender, s, strings);
        return true;
    }
}