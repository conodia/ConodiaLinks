package fr.pandaguerrier.conodia.websocket_modules;

import fr.pandaguerrier.conodia.ConodiaLinks;
import fr.pandaguerrier.conodiagameapi.websocket.contracts.WebsocketEvent;
import fr.pandaguerrier.conodiagameapi.websocket.entities.WebsocketResponse;
import fr.pandaguerrier.conodiagameapi.websocket.enums.EventEnum;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.json.simple.JSONObject;

public class StaffRefuseConnexion extends WebsocketEvent {
  @Override
  public void handle(WebsocketResponse websocketResponse) {
    JSONObject playerPayload = (JSONObject) websocketResponse.data.get("player");
    Player player = Bukkit.getPlayer(playerPayload.get("minecraft_playername").toString());

    if(player == null || !ConodiaLinks.getInstance().getConnexions().containsKey(player.getUniqueId())) {
       System.out.println("§cPlayer not found or not in connexion list");
       return;
    }

    ConodiaLinks.getInstance().getConnexions().remove(player.getUniqueId());

    player.removePotionEffect(PotionEffectType.SLOW);
    player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
    player.removePotionEffect(PotionEffectType.WEAKNESS);
    player.removePotionEffect(PotionEffectType.BLINDNESS);

    Bukkit.getScheduler().runTask(ConodiaLinks.getInstance(), () -> player.kickPlayer("§cVotre connexion a été refusée."));
  }

  @Override
  public EventEnum getEvent() {
    return EventEnum.STAFF_JOIN_DENY;
  }
}
