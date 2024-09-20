package br.com.law.porkus.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class InventuryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Apenas jogadores podem usar este comando.");
            return false;
        }

        if (command.getName().equalsIgnoreCase("bal")) {
            Inventory inventory = Bukkit.createInventory(null, 9, "Inventário do Porkus");

            // Criando o GoldenBrick (Barra de Ouro Encantada)
            ItemStack goldenBrick = new ItemStack(Material.GOLD_INGOT);
            ItemMeta meta = goldenBrick.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(ChatColor.GOLD + "GoldenBrick");
                goldenBrick.setItemMeta(meta);
            }

            // Adicionando o GoldenBrick no inventário
            inventory.setItem(4, goldenBrick);

            // Abrindo o inventário para o jogador
            player.openInventory(inventory);
            player.sendMessage(ChatColor.GREEN + "Abra seu inventário e pegue o GoldenBrick!");

            return true;
        }

        return false;
    }
}
