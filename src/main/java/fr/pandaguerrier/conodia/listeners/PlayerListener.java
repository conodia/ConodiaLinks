package fr.pandaguerrier.conodia.listeners;

import fr.pandaguerrier.conodia.managers.LinkManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

  @EventHandler
  public void playerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    LinkManager linkManager = new LinkManager(player);

    if (!linkManager.isLink()) {
      player.sendTitle("§cCompte non lié", "§cVotre compte n'est pas lié a votre compte discord !");
      player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
      player.sendMessage("§cVotre compte Minecraft n'est pas lié à votre compte §9Discord §callez sur le discord et vérifiez vous !");
    }
  }
}
