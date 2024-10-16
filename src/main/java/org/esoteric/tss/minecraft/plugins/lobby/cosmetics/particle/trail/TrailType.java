package org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle.trail;

import org.esoteric.tss.minecraft.plugins.core.data.player.Message;
import org.esoteric.tss.minecraft.plugins.core.data.player.TranslatableItemStack;
import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle.ParticleCosmetic;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum TrailType implements ParticleCosmetic {
  HEART(
		  Particle.HEART,
		  new TranslatableItemStack(Material.RED_DYE, Message.HEART_TRAIL_DISPLAY_NAME, Message.HEART_TRAIL_DESCRIPTION)
  ),
  FIRE(
		  Particle.FLAME,
		  new TranslatableItemStack(Material.FIRE_CHARGE, Message.FIRE_TRAIL_DISPLAY_NAME, Message.FIRE_TRAIL_DESCRIPTION)
  ),
  WATER(
		  Particle.FALLING_WATER,
		  new TranslatableItemStack(Material.WATER_BUCKET, Message.WATER_TRAIL_DISPLAY_NAME, Message.WATER_TRAIL_DESCRIPTION)
  ),
  BUBBLE(
		  Particle.BUBBLE,
		  new TranslatableItemStack(Material.LIGHT_BLUE_STAINED_GLASS, Message.BUBBLE_TRAIL_DISPLAY_NAME, Message.BUBBLE_TRAIL_DESCRIPTION)
  ),
  EXPLOSIVE(
		  Particle.EXPLOSION,
		  new TranslatableItemStack(Material.TNT, Message.EXPLOSIVE_TRAIL_DISPLAY_NAME, Message.EXPLOSIVE_TRAIL_DESCRIPTION)
  ),
  SLIME(
		  Particle.ITEM_SLIME,
		  new TranslatableItemStack(Material.SLIME_BALL, Message.SLIME_TRAIL_DISPLAY_NAME, Message.SLIME_TRAIL_DESCRIPTION)
  );

  private final Particle particle;
  private final TranslatableItemStack displayItem;
  private final int requiredRankWeight;
  private final boolean spawnWhenNotMoving;

  TrailType(Particle particle, TranslatableItemStack displayItem, int requiredRankWeight, boolean spawnWhenNotMoving) {
	this.particle = particle;
	this.displayItem = displayItem;
	this.requiredRankWeight = requiredRankWeight;
	this.spawnWhenNotMoving = spawnWhenNotMoving;
  }

  TrailType(Particle particle, TranslatableItemStack displayItem, int requiredRankWeight) {
	this(particle, displayItem, requiredRankWeight, false);
  }

  TrailType(Particle particle, TranslatableItemStack displayItem, boolean spawnWhenNotMoving) {
	this(particle, displayItem, 0, spawnWhenNotMoving);
  }

  TrailType(Particle particle, TranslatableItemStack displayItem) {
	this(particle, displayItem, 0, false);
  }

  @Override
  public ItemStack getDisplayItem(Player player, @NotNull TSSLobbyPlugin plugin) {
	return displayItem.asBukkitItemStack(player, plugin.getCore());
  }

  @Override
  public int getRequiredRankWeight() {
	return requiredRankWeight;
  }

  @Override
  public Particle getParticle() {
	return particle;
  }

  @Override
  public boolean shouldSpawnWhenNotMoving() {
	return spawnWhenNotMoving;
  }
}
