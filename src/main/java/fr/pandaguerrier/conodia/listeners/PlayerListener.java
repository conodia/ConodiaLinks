package fr.pandaguerrier.conodia.listeners;

import fr.pandaguerrier.conodia.ConodiaLinks;
import fr.pandaguerrier.conodia.managers.LinkManager;
import fr.pandaguerrier.conodiagameapi.ConodiaGameAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.json.simple.JSONObject;

public class PlayerListener implements Listener {
  
  @EventHandler
  public void playerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    LinkManager linkManager = new LinkManager(player);

    if (!linkManager.isLink()) {
      player.sendTitle("§cCompte non lié", "§cVotre compte n'est pas lié a votre compte discord !");
      player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
      player.sendMessage("§cVotre compte Minecraft n'est pas lié à votre compte §9Discord §callez sur le discord et vérifiez vous !");
      return;
    }

    if (player.hasPermission("link.staff")) {
      JSONObject payload = new JSONObject();
      payload.put("playerId", player.getUniqueId().toString());
      payload.put("ip", player.getAddress().getAddress().getHostAddress());

      ConodiaGameAPI.getInstance().getApiManager().post("/staffconnexion", payload);
      ConodiaLinks.getInstance().getConnexions().put(player.getUniqueId(), player);

      player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999, 1000));
      player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 999999, 1000));
      player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 999999, 1000));
      player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 1000));

      player.sendTitle("§cAcceptez la connexion", "§cAcceptez la connexion sur discord.");
      player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
    }
  }

  @EventHandler
  public void playerQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      ConodiaLinks.getInstance().getConnexions().remove(player.getUniqueId());
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onMove(PlayerMoveEvent event) {
    Player player = event.getPlayer();

    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      double distance = event.getFrom().distance(event.getTo());
      if (distance < 0.1) {
        return;
      }

      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onCommand(PlayerCommandPreprocessEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onDrop(PlayerDropItemEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onBreak(BlockBreakEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onPickup(PlayerPickupItemEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }

  @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
  public void onBlockPlace(BlockPlaceEvent event) {
    Player player = event.getPlayer();
    if (ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
      event.setCancelled(true);
      player.sendMessage("§cVeuillez accepter la connexion.");
    }
  }
}
