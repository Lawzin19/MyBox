package br.com.law.porkus.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class ItemPickupListener implements Listener {

    private final HashMap<UUID, Piglin> playerPiglins;

    public ItemPickupListener(HashMap<UUID, Piglin> playerPiglins) {
        this.playerPiglins = playerPiglins;
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (playerPiglins.containsKey(player.getUniqueId())) {
            Piglin porku = playerPiglins.get(player.getUniqueId());
            ItemStack item = event.getItemDrop().getItemStack();

            // Porku pega qualquer item
            event.getItemDrop().remove();
            porku.getWorld().dropItemNaturally(porku.getLocation(), item);
            player.sendMessage(ChatColor.GREEN + "Porku pegou seu item!");
        }
    }
}
