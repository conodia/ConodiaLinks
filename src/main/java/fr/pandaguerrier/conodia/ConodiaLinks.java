package fr.pandaguerrier.conodia;

import fr.pandaguerrier.conodia.commands.LinkCommand;
import fr.pandaguerrier.conodia.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConodiaLinks extends JavaPlugin {
    private static ConodiaLinks instance;

    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {}

    private void registerCommands() {
        this.getCommand("link").setExecutor(new LinkCommand());
    }

    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    public static ConodiaLinks getInstance() {
        return instance;
    }
}
