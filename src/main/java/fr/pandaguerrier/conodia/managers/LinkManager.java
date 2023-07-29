package fr.pandaguerrier.conodia.managers;

import fr.pandaguerrier.conodia.entities.DiscordLinkPlayer;
import fr.pandaguerrier.conodiagameapi.ConodiaGameAPI;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public class LinkManager {
    private String code;
    private Player player;

    public LinkManager(String code, Player player) {
        this.code = code;
        this.player = player;
    }

    public LinkManager(Player player) {
        this.player = player;
    }

    public boolean isLink() {
        JSONObject payload = ConodiaGameAPI.getInstance().getApiManager().get("/link/islink/" + player.getUniqueId(), new JSONObject());
        return (boolean) payload.get("linked");
    }

    public DiscordLinkPlayer link() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uuid", player.getUniqueId().toString());
        jsonObject.put("playername", player.getName());

        JSONObject payload = ConodiaGameAPI.getInstance().getApiManager().post("/link/verify/" + code, jsonObject);

        if(!payload.containsKey("player")) return null;

        JSONObject playerObj = (JSONObject) payload.get("player");

        return new DiscordLinkPlayer(code, playerObj.get("discord_user_id").toString(), playerObj.get("discord_username").toString(), player.getUniqueId().toString());
    }
}
