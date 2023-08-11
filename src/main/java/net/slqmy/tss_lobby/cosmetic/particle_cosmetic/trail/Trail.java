package net.slqmy.tss_lobby.cosmetic.particle_cosmetic.trail;

import net.slqmy.tss_lobby.TSSLobbyPlugin;
import net.slqmy.tss_lobby.cosmetic.particle_cosmetic.AbstractParticleCosmetic;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class Trail extends AbstractParticleCosmetic {

  public Trail(TrailType trailType, Player player, TSSLobbyPlugin plugin) {
	super(trailType, player, plugin);
  }

  @Override
  public BukkitTask spawnParticles(Player player) {

	if (((TrailType) cosmetic).shouldSpawnWhenNotMoving()) {
	  return new BukkitRunnable() {

		@Override
		public void run() {
		  spawnParticle(player.getLocation());
		}
	  }.runTaskTimer(plugin, 0L, 1L);
	} else {
	  return new BukkitRunnable() {

		Location previousLocation = player.getLocation();

		@Override
		public void run() {
		  double minDistance = 0.1D;
		  if (previousLocation.distance(player.getLocation()) >= minDistance) {
			spawnParticle(player.getLocation());
		  }

		  this.previousLocation = player.getLocation();
		}
	  }.runTaskTimer(plugin, 0L, 1L);
	}
  }

  @Override
  protected void spawnParticle(@NotNull Location location) {
	location.getWorld().spawnParticle(((TrailType) cosmetic).getParticle(), location, 1);
  }

  @Override
  public TrailType getCosmetic() {
	return (TrailType) cosmetic;
  }
}
