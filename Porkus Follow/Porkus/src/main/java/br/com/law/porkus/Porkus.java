package br.com.law.porkus;

import br.com.law.porkus.commands.InventuryCommand;
import br.com.law.porkus.commands.SosCommand;
import br.com.law.porkus.listeners.ChatListener;
import br.com.law.porkus.listeners.GoldenBrickListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Piglin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Porkus extends JavaPlugin {

    private final HashMap<UUID, Piglin> playerPiglins = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("Porkus está ativado!");

        // Registrar eventos
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new GoldenBrickListener(this, playerPiglins), this);

        // Registrar comandos
        getCommand("bal").setExecutor(new InventuryCommand());
        getCommand("sos").setExecutor(new SosCommand(this, playerPiglins));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("Porkus está desativado!");
    }
}
