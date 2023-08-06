package net.slqmy.tss_lobby;

import net.slqmy.tss_core.TSSCorePlugin;
import net.slqmy.tss_lobby.event.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TSSLobbyPlugin extends JavaPlugin {

	private final TSSCorePlugin core = (TSSCorePlugin) Bukkit.getPluginManager().getPlugin("TSS-Core");

	public TSSCorePlugin getCore() {
		return core;
	}

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
	}
}
