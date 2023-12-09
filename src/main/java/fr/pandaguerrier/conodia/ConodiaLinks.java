package fr.pandaguerrier.conodia;

import fr.pandaguerrier.conodia.commands.LinkCommand;
import fr.pandaguerrier.conodia.listeners.PlayerListener;
import fr.pandaguerrier.conodia.websocket_modules.StaffAcceptConnexion;
import fr.pandaguerrier.conodia.websocket_modules.StaffRefuseConnexion;
import fr.pandaguerrier.conodiagameapi.ConodiaGameAPI;
import fr.pandaguerrier.conodiagameapi.websocket.enums.EventEnum;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ConodiaLinks extends JavaPlugin {
    private static ConodiaLinks instance;
    private final Map<UUID, Player> connexions = new HashMap<>();
    @Override
    public void onEnable() {
        instance = this;
        registerCommands();
        registerListeners();

        this.getServer().getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                ConodiaGameAPI.getInstance().getWebsocketManager().getWebsocketManager().getDispatcher().registerAll(new HashMap<>() {{
                    putIfAbsent(EventEnum.STAFF_JOIN_ACCEPT, new StaffAcceptConnexion());
                    putIfAbsent(EventEnum.STAFF_JOIN_DENY, new StaffRefuseConnexion());
                }});
            }
        }, 50L);

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
    public Map<UUID, Player> getConnexions() {
        return connexions;
    }
}
