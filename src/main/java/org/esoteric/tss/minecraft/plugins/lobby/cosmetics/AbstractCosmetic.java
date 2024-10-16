package org.esoteric.tss.minecraft.plugins.lobby.cosmetics;

import org.esoteric.tss.minecraft.plugins.core.data.Rank;
import org.esoteric.tss.minecraft.plugins.core.data.player.Message;
import org.esoteric.tss.minecraft.plugins.core.managers.MessageManager;
import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractCosmetic {

  protected TSSLobbyPlugin plugin;

  protected Cosmetic cosmetic;
  protected Player player;

  protected AbstractCosmetic(Cosmetic cosmetic, @NotNull Player player, @NotNull TSSLobbyPlugin plugin) {
	this.plugin = plugin;

	this.cosmetic = cosmetic;
	this.player = player;
	equip();
  }

  public void equip() {
	UUID playerUuid = player.getUniqueId();
	Map<UUID, ArrayList<AbstractCosmetic>> playerCosmetics = plugin.getCosmeticsManager().getActiveCosmetics();
	ArrayList<AbstractCosmetic> equippedCosmetics = playerCosmetics.computeIfAbsent(playerUuid, key -> new ArrayList<>());

	Rank playerRank = plugin.getRanksPlugin().getRankManager().getPlayerRank(player);

	MessageManager messageManager = plugin.getCore().getMessageManager();
	if (cosmetic.getRequiredRankWeight() > playerRank.getWeight()) {
	  messageManager.sendMessage(player, Message.COSMETIC_IS_LOCKED);
	} else {
	  activate();
	  equippedCosmetics.add(this);

	  messageManager.sendMessage(player, Message.EQUIPPED_COSMETIC, cosmetic.getDisplayItem(player, plugin).getItemMeta().displayName());
	}
  }

  public void unEquip() {
	plugin.getCore().getMessageManager().sendMessage(player, Message.UNEQUIPPED_COSMETIC, cosmetic.getDisplayItem(player, plugin).getItemMeta().displayName());
	deactivate();
  }

  public abstract Cosmetic getCosmetic();

  public abstract void activate();

  public abstract void deactivate();
}
