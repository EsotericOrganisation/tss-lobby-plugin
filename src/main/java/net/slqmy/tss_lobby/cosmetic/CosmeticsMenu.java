package net.slqmy.tss_lobby.cosmetic;

import net.slqmy.tss_core.datatype.player.Message;
import net.slqmy.tss_lobby.TSSLobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class CosmeticsMenu {

  public CosmeticsMenu(Player player, @NotNull TSSLobbyPlugin plugin) {
	Inventory inventory = Bukkit.createInventory(null, 27, plugin.getCore().getMessageManager().getPlayerMessage(Message.COSMETICS, player));

	CosmeticType[] cosmeticTypes = CosmeticType.values();
	for (int i = 0; i < cosmeticTypes.length; i++) {
	  CosmeticType cosmeticType = cosmeticTypes[i];

	  ItemStack displayItem = cosmeticType.getDisplayItem(player, plugin);
	  ItemMeta displayItemMeta = displayItem.getItemMeta();

	  displayItemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "cosmetic_type"), PersistentDataType.STRING, cosmeticType.name());
	  displayItem.setItemMeta(displayItemMeta);

	  inventory.addItem(displayItem);
	}

	player.openInventory(inventory);
  }
}
