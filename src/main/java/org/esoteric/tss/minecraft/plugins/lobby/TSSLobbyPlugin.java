package org.esoteric.tss.minecraft.plugins.lobby;

import org.esoteric.tss.minecraft.plugins.core.TSSCorePlugin;
import org.esoteric.tss.minecraft.plugins.core.data.SimpleLocation;
import org.esoteric.tss.minecraft.plugins.core.managers.FileManager;
import org.esoteric.tss.minecraft.plugins.lobby.cosmetics.CosmeticsManager;
import org.esoteric.tss.minecraft.plugins.lobby.event.listeners.JoinListener;
import org.esoteric.tss.minecraft.plugins.lobby.event.listeners.LobbyListener;
import org.esoteric.tss.minecraft.plugins.lobby.event.listeners.NPCClickListener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.esoteric.tss.minecraft.plugins.ranks.TSSRanksPlugin;

public final class TSSLobbyPlugin extends JavaPlugin {

  private final PluginManager pluginManager = Bukkit.getPluginManager();

  private final TSSCorePlugin core = (TSSCorePlugin) pluginManager.getPlugin("TSSCore");
  private final TSSRanksPlugin ranksPlugin = (TSSRanksPlugin) pluginManager.getPlugin("TSSRanks");

  private FileManager fileManager;

  private CosmeticsManager cosmeticsManager;

  private Location lobbyLocation;

  public TSSCorePlugin getCore() {
	return core;
  }

  public TSSRanksPlugin getRanksPlugin() {
	return ranksPlugin;
  }

  public FileManager getFileManager() {
	return fileManager;
  }

  public CosmeticsManager getCosmeticsManager() {
	return cosmeticsManager;
  }

  public Location getLobbyLocation() {
	return lobbyLocation;
  }

  @Override
  public void onEnable() {
	fileManager = new FileManager(this);
	fileManager.initiateJsonFile("lobby-location");

	cosmeticsManager = new CosmeticsManager();

	SimpleLocation storedLocation = fileManager.readJsonFile("lobby-location", SimpleLocation.class);
	assert storedLocation != null;

	lobbyLocation = storedLocation.asBukkitLocation();

	PluginManager pluginManager = Bukkit.getPluginManager();

	pluginManager.registerEvents(new JoinListener(this), this);
	pluginManager.registerEvents(new LobbyListener(this), this);
	pluginManager.registerEvents(new NPCClickListener(this), this);
  }
}
