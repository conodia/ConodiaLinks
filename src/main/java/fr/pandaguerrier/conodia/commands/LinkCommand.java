package fr.pandaguerrier.conodia.commands;

import fr.pandaguerrier.conodia.entities.DiscordLinkPlayer;
import fr.pandaguerrier.conodia.managers.LinkManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cVous devez être un joueur pour executer cette commande.");
            return false;
        }

        if(args.length != 1) {
            sender.sendMessage("§c/link <CODE>");
            return false;
        }

        final Player player = (Player) sender;

        LinkManager linkManager = new LinkManager(args[0], player);

        if(linkManager.isLink()) {
            player.sendMessage("§cVotre compte minecraft est déjà lié à un compte discord.");
            player.sendTitle("§cCompte déjà lié", "§cVotre compte minecraft est déjà lié à un compte discord !");
            player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
            return false;
        }

        DiscordLinkPlayer link = linkManager.link();

        if(link == null) {
            player.sendMessage("§cLe code que vous avez entré est invalide.");
            player.sendTitle("§cCode invalide", "§cLe code que vous avez entré est invalide.");
            player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 1, 1);
            return false;
        }

        player.sendTitle("§aCompte lié", "§aVotre compte discord est désormais lié à votre compte minecraft.");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);

        player.sendMessage("§aVous avez lié votre compte discord (" + link.getDiscordUsername() + ") avec votre compte minecraft (" + player.getName() + ")");
        return true;
    }
}