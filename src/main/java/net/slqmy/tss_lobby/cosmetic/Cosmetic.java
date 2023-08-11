package net.slqmy.tss_lobby.cosmetic;

import net.slqmy.tss_lobby.TSSLobbyPlugin;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Cosmetic {
  ItemStack getDisplayItem(Player player, TSSLobbyPlugin plugin);

  int getRequiredRankWeight();
}
