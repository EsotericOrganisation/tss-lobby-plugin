package org.esoteric_organisation.tss_lobby_plugin.cosmetic.hat;

import org.esoteric_organisation.tss_core_plugin.datatype.player.Message;
import org.esoteric_organisation.tss_core_plugin.datatype.player.TranslatableItemStack;
import org.esoteric_organisation.tss_lobby_plugin.TSSLobbyPlugin;
import org.esoteric_organisation.tss_lobby_plugin.cosmetic.Cosmetic;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public enum HatType implements Cosmetic {
  TOPHAT(new TranslatableItemStack(Material.PLAYER_HEAD, Message.TOP_HAT_DISPLAY_NAME, Message.TOP_HAT_DESCRIPTION)),
  TIGER(new TranslatableItemStack(Material.PLAYER_HEAD, Message.TIGER_HAT_DISPLAY_NAME, Message.TIGER_HAT_DESCRIPTION)),
  DOG(new TranslatableItemStack(Material.PLAYER_HEAD, Message.DOG_HAT_DISPLAY_NAME, Message.DOG_HAT_DESCRIPTION));

  private final TranslatableItemStack item;

  HatType(TranslatableItemStack item) {
	this.item = item;
  }

  @Override
  public ItemStack getDisplayItem(Player player, TSSLobbyPlugin plugin) {
	return item.asBukkitItemStack(player, plugin.getCore());
  }

  @Override
  public int getRequiredRankWeight() {
	return 0;
  }
}
