package org.esoteric.tss.minecraft.plugins.lobby.cosmetics.hat;

import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.AbstractCosmetic;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Hat extends AbstractCosmetic {

  public Hat(HatType hatType, @NotNull Player player, @NotNull TSSLobbyPlugin plugin) {
	super(hatType, player, plugin);
  }

  @Override
  public HatType getCosmetic() {
	return (HatType) cosmetic;
  }

  @Override
  public void activate() {
	player.getInventory().setHelmet(cosmetic.getDisplayItem(player, plugin));
  }

  @Override
  public void deactivate() {
	player.getInventory().setHelmet(null);
  }
}
