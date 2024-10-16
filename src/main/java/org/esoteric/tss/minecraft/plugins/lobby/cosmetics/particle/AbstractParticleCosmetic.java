package org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle;

import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.AbstractCosmetic;
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
