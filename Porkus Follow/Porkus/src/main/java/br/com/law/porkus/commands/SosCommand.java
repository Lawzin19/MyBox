package br.com.law.porkus.commands;

import br.com.law.porkus.tasks.PorkuFollowTask;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class SosCommand implements CommandExecutor {
    private final Plugin plugin;
    private final HashMap<UUID, Piglin> playerPiglins;

    public SosCommand(Plugin plugin, HashMap<UUID, Piglin> playerPiglins) {
        this.plugin = plugin;
        this.playerPiglins = playerPiglins;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Apenas jogadores podem usar este comando.");
            return true;
        }

        // Invoca ou remove o Porku usando a mesma lógica
        HashMetohod(player, playerPiglins, plugin);

        return true;
    }

    public static void HashMetohod(Player player, HashMap<UUID, Piglin> playerPiglins, Plugin plugin) {
        if (playerPiglins.containsKey(player.getUniqueId())) {
            Piglin porku = playerPiglins.get(player.getUniqueId());
            porku.remove();
            playerPiglins.remove(player.getUniqueId());
            player.sendMessage(ChatColor.RED + "Porku sumiu!");
        } else {
            // Invoca um novo Porku
            Piglin porku = (Piglin) player.getWorld().spawn(player.getLocation(), Piglin.class);
            porku.setCustomName(ChatColor.GOLD + "Porku's " + player.getName());
            porku.setCustomNameVisible(true);
            porku.setPersistent(true);
            // Desativa a IA e previne transformação em Zombie Pigman
            porku.setAI(false);
            porku.setRemoveWhenFarAway(false); // Garante que ele não seja removido
            porku.setConversionTime(-1); // Previne a transformação em Zombified Piglin

            playerPiglins.put(player.getUniqueId(), porku);
            player.sendMessage(ChatColor.GREEN + "Você invocou Porku!");

            new PorkuFollowTask(porku, player).runTaskTimer(plugin, 0L, 20L);
        }
    }
}
