package net.slqmy.tss_lobby.cosmetic.hat;

import net.slqmy.tss_core.datatype.player.Message;
import net.slqmy.tss_core.datatype.player.TranslatableItemStack;
import net.slqmy.tss_lobby.TSSLobbyPlugin;
import net.slqmy.tss_lobby.cosmetic.Cosmetic;
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
