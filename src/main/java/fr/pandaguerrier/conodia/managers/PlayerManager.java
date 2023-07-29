package fr.pandaguerrier.conodia.managers;
import org.bukkit.entity.Player;

public class PlayerManager {
    private Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }

    public boolean isLink() {
        LinkManager linkManager = new LinkManager(player);
        return linkManager.isLink();
    }
}
