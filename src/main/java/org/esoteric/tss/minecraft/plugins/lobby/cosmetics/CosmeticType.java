package org.esoteric.tss.minecraft.plugins.lobby.cosmetics;

import org.esoteric_organisation.tss_core_plugin.datatype.player.Message;
import org.esoteric_organisation.tss_core_plugin.datatype.player.TranslatableItemStack;
import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.hat.HatMenu;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle.trail.TrailMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public enum CosmeticType {
  HAT(new TranslatableItemStack(Material.DIAMOND_HELMET, Message.HATS_ITEM_DISPLAY_NAME, Message.HATS_ITEM_DESCRIPTION), HatMenu::new),
  TRAIL(new TranslatableItemStack(Material.DIAMOND_BOOTS, Message.TRAILS_ITEM_DISPLAY_NAME, Message.TRAILS_ITEM_DESCRIPTION), TrailMenu::new);

  private final TranslatableItemStack displayItem;
  private final BiConsumer<Player, TSSLobbyPlugin> menuConstructor;

  CosmeticType(TranslatableItemStack displayItem, BiConsumer<Player, TSSLobbyPlugin> menuConstructor) {
	this.menuConstructor = menuConstructor;
	this.displayItem = displayItem;
  }

  public ItemStack getDisplayItem(Player player, @NotNull TSSLobbyPlugin plugin) {
	return displayItem.asBukkitItemStack(player, plugin.getCore());
  }

  public BiConsumer<Player, TSSLobbyPlugin> getMenuConstructor() {
	return menuConstructor;
  }
}
