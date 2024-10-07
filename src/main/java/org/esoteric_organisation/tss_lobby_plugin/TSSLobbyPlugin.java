package org.esoteric_organisation.tss_lobby_plugin;

import net.slqmy.tss_core.TSSCorePlugin;
import net.slqmy.tss_core.datatype.SimpleLocation;
import net.slqmy.tss_core.manager.FileManager;
import org.esoteric_organisation.tss_lobby_plugin.cosmetic.CosmeticsManager;
import org.esoteric_organisation.tss_lobby_plugin.event.JoinListener;
import org.esoteric_organisation.tss_lobby_plugin.event.LobbyListener;
import org.esoteric_organisation.tss_lobby_plugin.event.NPCClickListener;
import net.slqmy.tss_ranks.TSSRanksPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TSSLobbyPlugin extends JavaPlugin {

  private final PluginManager pluginManager = Bukkit.getPluginManager();

  private final TSSCorePlugin core = (TSSCorePlugin) pluginManager.getPlugin("tss-core-plugin");
  private final TSSRanksPlugin ranksPlugin = (TSSRanksPlugin) pluginManager.getPlugin("tss-ranks-plugin");

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
