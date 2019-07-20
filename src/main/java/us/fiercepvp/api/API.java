package us.fiercepvp.api;

import lombok.Getter;

import org.bukkit.plugin.java.JavaPlugin;
import us.fiercepvp.api.command.example.CommandExample;
import us.fiercepvp.api.command.registrar.CommandRegistrar;

public class API extends JavaPlugin {

    @Getter private static API instance;

    @Override
    public void onEnable() {
        instance = this;

        CommandRegistrar commandRegistrar = new CommandRegistrar(getServer());
        commandRegistrar.registerCommand("test", new CommandExample());
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
