package br.com.law.porkus.listeners;

import br.com.law.porkus.commands.SosCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class GoldenBrickListener implements Listener {
    private final Plugin plugin;
    private final HashMap<UUID, Piglin> playerPiglins;

    public GoldenBrickListener(Plugin plugin, HashMap<UUID, Piglin> playerPiglins) {
        this.plugin = plugin;
        this.playerPiglins = playerPiglins;
    }

    @EventHandler
    public void onPlayerUseGoldenBrick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Verifica se o item é o GoldenBrick
        if (isGoldenBrick(item)) {
            // Invoca ou remove o Porku
            handlePorkuInvocation(player);
        }
    }

    private void handlePorkuInvocation(Player player) {
        SosCommand.HashMetohod(player, playerPiglins, plugin);
    }

    // Verifica se o item é o GoldenBrick
    private boolean isGoldenBrick(ItemStack item) {
        if (item == null || item.getType() != Material.GOLD_INGOT) {
            return false;
        }

        if (!item.hasItemMeta()) {
            return false;
        }

        var meta = item.getItemMeta();
        return meta != null && meta.getDisplayName().equals(ChatColor.GOLD + "GoldenBrick");
    }
}
