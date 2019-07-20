package us.fiercepvp.api.command.registrar;

import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import us.fiercepvp.api.command.Command;
import us.fiercepvp.api.command.example.CommandExample;

import java.lang.reflect.Field;

public class CommandRegistrar {

    private CommandMap map;

    public CommandRegistrar(Server server) {
        try {
            Field field = server.getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            this.map = (CommandMap) field.get(server);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public <T extends Command> void registerCommand(String fallback, CommandExample command) {
        map.register(fallback, command);
    }
}