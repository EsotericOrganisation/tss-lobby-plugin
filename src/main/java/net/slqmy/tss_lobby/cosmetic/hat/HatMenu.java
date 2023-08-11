package net.slqmy.tss_lobby.cosmetic.hat;

import net.slqmy.tss_core.datatype.Rank;
import net.slqmy.tss_core.datatype.player.Message;
import net.slqmy.tss_core.manager.MessageManager;
import net.slqmy.tss_lobby.TSSLobbyPlugin;
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

public class HatMenu {
  public HatMenu(Player player, @NotNull TSSLobbyPlugin plugin) {
	MessageManager messageManager = plugin.getCore().getMessageManager();
	Inventory inventory = Bukkit.createInventory(null, 27, messageManager.getPlayerMessage(Message.HATS, player));

	ArrayList<HatType> equippedCosmetics = plugin.getCosmeticsManager().getPlayerCosmetics(player, HatType.class);
	HatType activeHat = equippedCosmetics.size() == 1 ? equippedCosmetics.get(0) : null;

	Rank rank = plugin.getRanksPlugin().getRankManager().getPlayerRank(player);

	HatType[] hatTypes = HatType.values();
	for (int i = 0; i < hatTypes.length; i++) {
	  HatType hatType = hatTypes[i];
	  ItemStack displayItem = hatType.getDisplayItem(player, plugin);
	  ItemMeta displayItemMeta = displayItem.getItemMeta();

	  displayItemMeta.displayName(
			  displayItemMeta.displayName().append(
					  messageManager.getPlayerMessage(
							  hatType.getRequiredRankWeight() <= rank.getWeight() ?
									  activeHat == hatType ? Message.CLICK_TO_UNEQUIP
											  : Message.CLICK_TO_EQUIP
									  : Message.LOCKED,
							  player
					  )
			  )
	  );

	  PersistentDataContainer container = displayItemMeta.getPersistentDataContainer();
	  container.set(new NamespacedKey(plugin, "hat_type"), PersistentDataType.STRING, hatType.name());
	  container.set(new NamespacedKey(plugin, "cosmetic"), PersistentDataType.STRING, "hat");
	  displayItem.setItemMeta(displayItemMeta);

	  inventory.addItem(displayItem);
	}

	player.openInventory(inventory);
  }
}
