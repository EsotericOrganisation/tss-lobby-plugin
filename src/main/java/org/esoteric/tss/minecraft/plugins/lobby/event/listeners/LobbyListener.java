package org.esoteric.tss.minecraft.plugins.lobby.event.listeners;

import org.esoteric_organisation.tss_core_plugin.util.DebugUtil;
import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.AbstractCosmetic;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.CosmeticType;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.CosmeticsManager;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.CosmeticsMenu;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.hat.Hat;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.hat.HatMenu;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.hat.HatType;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle.trail.Trail;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle.trail.TrailMenu;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle.trail.TrailType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LobbyListener implements Listener {

  private final TSSLobbyPlugin plugin;

  public LobbyListener(TSSLobbyPlugin plugin) {
	this.plugin = plugin;
  }

  @EventHandler
  public void onInteract(@NotNull PlayerInteractEvent event) {
	Player player = event.getPlayer();

	if (player.getLocation().getWorld().getName().equals(plugin.getLobbyLocation().getWorld().getName())) {
	  event.setCancelled(true);

	  if (player.getInventory().getItemInMainHand().getType().equals(Material.CHEST)) {
		new CosmeticsMenu(player, plugin);
	  }
	}
  }

  @EventHandler
  public void onInventoryClick(@NotNull InventoryClickEvent event) {
	if (event.getWhoClicked().getWorld().getName().equals(plugin.getLobbyLocation().getWorld().getName())) {
	  event.setCancelled(true);
	}
  }

  @EventHandler
  public void onItemDrop(@NotNull PlayerDropItemEvent event) {
	if (event.getPlayer().getWorld().getName().equals(plugin.getLobbyLocation().getWorld().getName())) {
	  event.setCancelled(true);
	}
  }

  @EventHandler
  public void onCosmeticMenuNavigate(@NotNull InventoryClickEvent event) {
	ItemStack clickedItem = event.getCurrentItem();
	if (clickedItem == null) {
	  return;
	}

	ItemMeta itemInfo = clickedItem.getItemMeta();

	CosmeticType cosmeticType;
	try {
	  cosmeticType = CosmeticType.valueOf(itemInfo.getPersistentDataContainer().get(new NamespacedKey(plugin, "cosmetic_type"), PersistentDataType.STRING));
	} catch (NullPointerException | IllegalArgumentException exception) {
	  return;
	}

	cosmeticType.getMenuConstructor().accept((Player) event.getWhoClicked(), plugin);
  }

  @EventHandler
  public void onCosmeticMenuSelect(@NotNull InventoryClickEvent event) {
	ItemStack clickedItem = event.getCurrentItem();
	if (clickedItem == null) {
	  return;
	}

	ItemMeta itemInfo = clickedItem.getItemMeta();

	if (itemInfo == null) {
	  return;
	}

	PersistentDataContainer container = itemInfo.getPersistentDataContainer();
	String cosmetic = container.get(new NamespacedKey(plugin, "cosmetic"), PersistentDataType.STRING);

	if (cosmetic == null) {
	  return;
	}

	Player player = (Player) event.getWhoClicked();
	CosmeticsManager cosmeticsManager = plugin.getCosmeticsManager();
	ArrayList<AbstractCosmetic> activePlayerCosmetics = cosmeticsManager.getActiveCosmetics().get(player.getUniqueId());

	switch (cosmetic) {
	  case "hat" -> {
		HatType hatType = HatType.valueOf(container.get(new NamespacedKey(plugin, "hat_type"), PersistentDataType.STRING));
		ArrayList<Hat> activeHat = cosmeticsManager.getPlayerCosmetics(player, Hat.class);

		if (!activeHat.isEmpty()) {
		  Hat hat = activeHat.get(0);

		  hat.unEquip();
		  activePlayerCosmetics.remove(hat);
		}

		if (activeHat.isEmpty() || activeHat.get(0).getCosmetic() != hatType) {
		  new Hat(hatType, player, plugin);
		}

		new HatMenu(player, plugin);
	  }
	  case "trail" -> {
		TrailType trailType = TrailType.valueOf(container.get(new NamespacedKey(plugin, "trail_type"), PersistentDataType.STRING));
		ArrayList<Trail> activeTrails = cosmeticsManager.getPlayerCosmetics(player, Trail.class);

		for (Trail trail : activeTrails) {
		  if (trail.getCosmetic().equals(trailType)) {
			trail.unEquip();
			activePlayerCosmetics.remove(trail);

			new TrailMenu(player, plugin);
			return;
		  }
		}

		DebugUtil.log(trailType);

		new Trail(trailType, (Player) event.getWhoClicked(), plugin);
		new TrailMenu(player, plugin);
	  }
	}
  }
}
