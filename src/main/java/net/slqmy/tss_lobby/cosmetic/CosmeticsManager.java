package net.slqmy.tss_lobby.cosmetic;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CosmeticsManager {
  private final Map<UUID, ArrayList<AbstractCosmetic>> activeCosmetics = new HashMap<>();

  public <T> ArrayList<T> getPlayerCosmetics(@NotNull Player player, Class<T> targetCosmeticClass) {
	ArrayList<T> equippedTargetCosmetics = new ArrayList<>();
	ArrayList<AbstractCosmetic> equippedCosmetics = activeCosmetics.computeIfAbsent(player.getUniqueId(), key -> new ArrayList<>());
	for (AbstractCosmetic equippedCosmetic : equippedCosmetics) {
	  Cosmetic cosmetic = equippedCosmetic.getCosmetic();

	   if (targetCosmeticClass.isInstance(equippedCosmetic)) {
		 equippedTargetCosmetics.add((T) equippedCosmetic);
	   } else if (targetCosmeticClass.isInstance(cosmetic)) {
		equippedTargetCosmetics.add((T) cosmetic);
	  }
	}

	return equippedTargetCosmetics;
  }

  public Map<UUID, ArrayList<AbstractCosmetic>> getActiveCosmetics() {
	return activeCosmetics;
  }
}
