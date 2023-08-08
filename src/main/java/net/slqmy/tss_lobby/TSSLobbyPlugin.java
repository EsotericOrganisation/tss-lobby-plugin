package net.slqmy.tss_lobby;

import net.slqmy.tss_core.TSSCorePlugin;
import net.slqmy.tss_lobby.event.JoinListener;
import net.slqmy.tss_lobby.event.NPCClickListener;
import net.slqmy.tss_ranks.TSSRanksPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TSSLobbyPlugin extends JavaPlugin {

	private final PluginManager pluginManager = Bukkit.getPluginManager();

	private final TSSCorePlugin core = (TSSCorePlugin) pluginManager.getPlugin("TSS-Core");
	private final TSSRanksPlugin ranksPlugin = (TSSRanksPlugin) pluginManager.getPlugin("TSS-Ranks");

	public TSSCorePlugin getCore() {
		return core;
	}

	public TSSRanksPlugin getRanksPlugin() {
		return ranksPlugin;
	}

	@Override
	public void onEnable() {
		PluginManager pluginManager = Bukkit.getPluginManager();

		pluginManager.registerEvents(new JoinListener(this), this);
		pluginManager.registerEvents(new NPCClickListener(this), this);
	}
}
