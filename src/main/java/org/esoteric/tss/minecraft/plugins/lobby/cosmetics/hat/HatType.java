package org.esoteric.tss.minecraft.plugins.lobby.cosmetics.hat;

import org.esoteric_organisation.tss_core_plugin.datatype.player.Message;
import org.esoteric_organisation.tss_core_plugin.datatype.player.TranslatableItemStack;
import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.Cosmetic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public enum HatType implements Cosmetic {
  TOP_HAT(new TranslatableItemStack(Material.PLAYER_HEAD, Message.TOP_HAT_DISPLAY_NAME, Message.TOP_HAT_DESCRIPTION)),
  TIGER(new TranslatableItemStack(Material.PLAYER_HEAD, Message.TIGER_HAT_DISPLAY_NAME, Message.TIGER_HAT_DESCRIPTION)),
  DOG(new TranslatableItemStack(Material.PLAYER_HEAD, Message.DOG_HAT_DISPLAY_NAME, Message.DOG_HAT_DESCRIPTION));

  private final TranslatableItemStack item;

  HatType(TranslatableItemStack item) {
	this.item = item;
  }

  @Override
  public ItemStack getDisplayItem(Player player, @NotNull TSSLobbyPlugin plugin) {
	return item.asBukkitItemStack(player, plugin.getCore());
  }

  @Override
  public int getRequiredRankWeight() {
	return 0;
  }
}
