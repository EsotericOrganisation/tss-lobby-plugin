package org.esoteric_organisation.tss_lobby_plugin.cosmetic;

import org.esoteric_organisation.tss_lobby_plugin.TSSLobbyPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Cosmetic {
  ItemStack getDisplayItem(Player player, TSSLobbyPlugin plugin);

  int getRequiredRankWeight();
}
