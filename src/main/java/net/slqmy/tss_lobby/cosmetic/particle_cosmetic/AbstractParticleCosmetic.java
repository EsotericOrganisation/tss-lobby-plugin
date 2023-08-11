package net.slqmy.tss_lobby.cosmetic.particle_cosmetic;

import net.slqmy.tss_lobby.TSSLobbyPlugin;
import net.slqmy.tss_lobby.cosmetic.AbstractCosmetic;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public abstract class AbstractParticleCosmetic extends AbstractCosmetic {

  private BukkitTask task;

  protected AbstractParticleCosmetic(ParticleCosmetic cosmetic, Player player, TSSLobbyPlugin plugin) {
	super(cosmetic, player, plugin);
  }

  @Override
  public void activate() {
	task = spawnParticles(player);
  }

  @Override
  public void deactivate() {
	task.cancel();
  }

  public abstract BukkitTask spawnParticles(Player player);

  protected abstract void spawnParticle(Location location);
}
