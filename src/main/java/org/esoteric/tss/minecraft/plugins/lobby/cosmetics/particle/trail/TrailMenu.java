package org.esoteric.tss.minecraft.plugins.lobby.cosmetics.particle.trail;

import org.esoteric.tss.minecraft.plugins.core.data.Rank;
import org.esoteric.tss.minecraft.plugins.core.data.player.Message;
import org.esoteric.tss.minecraft.plugins.core.managers.MessageManager;
import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TrailMenu {
  public TrailMenu(Player player, @NotNull TSSLobbyPlugin plugin) {
	MessageManager messageManager = plugin.getCore().getMessageManager();
	Inventory inventory = Bukkit.createInventory(null, 27, messageManager.getPlayerMessage(Message.TRAILS, player));

	ArrayList<TrailType> activeTrails = plugin.getCosmeticsManager().getPlayerCosmetics(player, TrailType.class);
	Rank rank = plugin.getRanksPlugin().getRankManager().getPlayerRank(player);

	TrailType[] trailTypes = TrailType.values();
	for (int i = 0; i < trailTypes.length; i++) {
	  TrailType trailType = trailTypes[i];
	  ItemStack displayItem = trailType.getDisplayItem(player, plugin);
	  ItemMeta displayItemMeta = displayItem.getItemMeta();

	  displayItemMeta.displayName(
			  displayItemMeta.displayName().append(
					  messageManager.getPlayerMessage(
							  trailType.getRequiredRankWeight() <= rank.getWeight() ?
									  activeTrails.contains(trailType) ? Message.CLICK_TO_UNEQUIP
											  : Message.CLICK_TO_EQUIP
									  : Message.LOCKED,
							  player
					  )
			  )
	  );

	  PersistentDataContainer container = displayItemMeta.getPersistentDataContainer();
	  container.set(new NamespacedKey(plugin, "trail_type"), PersistentDataType.STRING, trailType.name());
	  container.set(new NamespacedKey(plugin, "cosmetic"), PersistentDataType.STRING, "trail");
	  displayItem.setItemMeta(displayItemMeta);

	  inventory.addItem(displayItem);
	}

	player.openInventory(inventory);
  }
}
