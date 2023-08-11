package net.slqmy.tss_lobby;

import net.slqmy.tss_core.TSSCorePlugin;
import net.slqmy.tss_core.datatype.SimpleLocation;
import net.slqmy.tss_core.manager.FileManager;
import net.slqmy.tss_lobby.cosmetic.CosmeticsManager;
import net.slqmy.tss_lobby.event.JoinListener;
import net.slqmy.tss_lobby.event.LobbyListener;
import net.slqmy.tss_lobby.event.NPCClickListener;
import net.slqmy.tss_ranks.TSSRanksPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TSSLobbyPlugin extends JavaPlugin {

  private final PluginManager pluginManager = Bukkit.getPluginManager();

  private final TSSCorePlugin core = (TSSCorePlugin) pluginManager.getPlugin("TSS-Core");
  private final TSSRanksPlugin ranksPlugin = (TSSRanksPlugin) pluginManager.getPlugin("TSS-Ranks");

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
