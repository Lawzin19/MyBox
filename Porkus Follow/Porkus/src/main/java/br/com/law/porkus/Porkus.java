package br.com.law.porkus;


import br.com.law.porkus.commands.SosCommand;
import br.com.law.porkus.listeners.GoldenBrickListener;
import br.com.law.porkus.listeners.ItemPickupListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Porkus extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("PorkuProjectEnable");
        events();
        commands();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("PorkuProjectDisable");
    }

    public void events() {
        getServer().getPluginManager().registerEvents(new GoldenBrickListener(this), this);
        getServer().getPluginManager().registerEvents(new ItemPickupListener(), this);
    }

    public void commands() {
        CommandExecutor sosCommandExecutor = new SosCommand(this);

        if (this.getCommand("sos") != null) {
            this.getCommand("sos").setExecutor(sosCommandExecutor);
        } else {
            getLogger().severe("Comando 'sos' não encontrado no plugin.yml. Verifique se está registrado corretamente.");
        }
    }
}
