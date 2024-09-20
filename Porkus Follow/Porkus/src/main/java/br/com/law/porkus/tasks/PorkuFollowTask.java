package br.com.law.porkus.tasks;

import net.minecraft.world.entity.ai.navigation.NavigationAbstract;
import net.minecraft.world.entity.monster.EntityPiglin;
import org.bukkit.craftbukkit.entity.CraftPiglin;
import org.bukkit.Location;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PorkuFollowTask extends BukkitRunnable {

    private final Piglin porku;
    private final Player player;

    public PorkuFollowTask(Piglin porku, Player player) {
        this.porku = porku;
        this.player = player;
    }

    @Override
    public void run() {
        // Verifica se o Porku está morto ou se o jogador não está mais online
        if (porku.isDead() || !player.isOnline()) {
            this.cancel();
            return;
        }

        Location playerLocation = player.getLocation();
        Location porkuLocation = porku.getLocation();
        double distance = porkuLocation.distance(playerLocation);

        // Usar navegação da entidade nativa do Minecraft para mover o Porku
        if (distance > 3) {
            // Converte o Piglin para o tipo CraftPiglin (necessário para acessar a navegação)
            EntityPiglin entityPorku = ((CraftPiglin) porku).getHandle();
            NavigationAbstract navigation = entityPorku.G();

            // Navega até o local do jogador
            navigation.a(playerLocation.getX(), playerLocation.getY(), playerLocation.getZ(), 1.0);  // 1.0 é a velocidade de movimento
        }
    }
}
