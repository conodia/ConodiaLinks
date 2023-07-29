package fr.pandaguerrier.conodia.entities;

public class DiscordLinkPlayer {
    private String code;
    private String discordId;
    private String discordUsername;
    private String minecraftUuid;
    private boolean linked;

    public DiscordLinkPlayer(String code, String discordId, String discordUsername, String minecraftUuid) {
        this.code = code;
        this.discordId = discordId;
        this.discordUsername = discordUsername;
        this.minecraftUuid = minecraftUuid;
    }

    public DiscordLinkPlayer(String code, String discordId, String discordUsername, String minecraftUuid, boolean linked) {
        this.code = code;
        this.discordId = discordId;
        this.discordUsername = discordUsername;
        this.minecraftUuid = minecraftUuid;
        this.linked = linked;
    }

    public String getCode() {
        return code;
    }
    public String getDiscordId() {
        return discordId;
    }
    public String getDiscordUsername() {
        return discordUsername;
    }
    public String getMinecraftUuid() {
        return minecraftUuid;
    }

    public boolean isLinked() {
        return linked;
    }
}
