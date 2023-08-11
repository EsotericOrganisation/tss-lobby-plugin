package net.slqmy.tss_lobby.cosmetic;

import net.slqmy.tss_lobby.TSSLobbyPlugin;
import net.slqmy.tss_lobby.cosmetic.hat.HatMenu;
import net.slqmy.tss_lobby.cosmetic.particle_cosmetic.trail.TrailMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public enum CosmeticType {
  HAT(new ItemStack(Material.DIAMOND_HELMET), HatMenu::new),
  TRAIL(new ItemStack(Material.DIAMOND_BOOTS), TrailMenu::new);

  private final ItemStack displayItem;
  private final BiConsumer<Player, TSSLobbyPlugin> menuConstructor;

  CosmeticType(ItemStack displayItem, BiConsumer<Player, TSSLobbyPlugin> menuConstructor) {
	this.menuConstructor = menuConstructor;
	this.displayItem = displayItem;
  }

  public ItemStack getDisplayItem() {
	return displayItem;
  }

  public BiConsumer<Player, TSSLobbyPlugin> getMenuConstructor() {
	return menuConstructor;
  }
}
