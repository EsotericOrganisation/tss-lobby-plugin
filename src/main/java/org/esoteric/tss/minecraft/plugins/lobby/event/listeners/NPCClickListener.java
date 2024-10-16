package org.esoteric.tss.minecraft.plugins.lobby.event.listeners;

import org.esoteric_organisation.tss_core_plugin.datatype.npc.NPC;
import org.esoteric_organisation.tss_core_plugin.event.custom_event.NPCClickEvent;
import org.esoteric.tss.minecraft.plugins.lobby.TSSLobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class NPCClickListener implements Listener {

  private final TSSLobbyPlugin plugin;

  public NPCClickListener(TSSLobbyPlugin plugin) {
	this.plugin = plugin;
  }

  @EventHandler
  public void onNPCClick(@NotNull NPCClickEvent event) {
	Player player = event.getPlayer();
	NPC npc = event.getNpc();

	String worldName = npc.getDestinationWorldName();

	World world = Bukkit.getWorld(worldName);

	if (world != null) {
	  Bukkit.getScheduler().runTask(plugin, () ->
			  player.teleport(world.getSpawnLocation())
	  );
	}
  }
}
