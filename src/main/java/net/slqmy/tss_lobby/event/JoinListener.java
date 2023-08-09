package net.slqmy.tss_lobby.event;

import net.kyori.adventure.title.TitlePart;
import net.slqmy.tss_core.data.Message;
import net.slqmy.tss_core.data.type.FireworkType;
import net.slqmy.tss_core.data.type.Rank;
import net.slqmy.tss_core.data.type.player.PlayerProfile;
import net.slqmy.tss_core.manager.MessageManager;
import net.slqmy.tss_lobby.TSSLobbyPlugin;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class JoinListener implements Listener {

	private final TSSLobbyPlugin plugin;

	public JoinListener(@NotNull TSSLobbyPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onJoin(@NotNull PlayerJoinEvent event) {
		Player player = event.getPlayer();

		MessageManager messageManager = plugin.getCore().getMessageManager();

		player.sendMessage(messageManager.getPlayerMessage(Message.WELCOME_CHAT_MESSAGE, player));
		player.sendTitlePart(TitlePart.TITLE, messageManager.getPlayerMessage(Message.THE_SLIMY_SWAMP, player));
		player.sendTitlePart(TitlePart.SUBTITLE, messageManager.getPlayerMessage(Message.WELCOME_SUBTITLE, player));

		PlayerProfile profile = plugin.getCore().getPlayerManager().getProfile(player);

		if (!profile.getPlayerPreferences().isLobbyFireworkEnabled()) {
			return;
		}

		Rank rank = plugin.getRanksPlugin().getRankManager().getPlayerRank(profile);

		if (rank.getWeight() == 0 && profile.getPlayerStats().getJoinCount() > 5) {
			return;
		}

		World playerWorld = player.getWorld();

		Firework firework = playerWorld.spawn(
						player.getLocation().clone().add(
										0,
										2,
										0
						),
						Firework.class
		);

		FireworkMeta fireworkMeta = firework.getFireworkMeta();
		FireworkType fireworkType = rank.getFireworkType();

		for (FireworkEffect effect : fireworkType.getEffects()) {
			fireworkMeta.addEffects(effect);
		}

		fireworkMeta.setPower(fireworkType.getPower());
		firework.setFireworkMeta(fireworkMeta);

		Particle spiralParticle = fireworkType.getSpiralParticle();

		if (spiralParticle == null) {
			return;
		}

		new BukkitRunnable() {

			final double radius = 0.65D;
			double angleDegrees = 0;

			@Override
			public void run() {
				if (firework.isDetonated() || firework.isDead()) {
					cancel();
					return;
				}

				double angleRadians = Math.toRadians(angleDegrees);
				playerWorld.spawnParticle(spiralParticle, firework.getLocation().add(Math.cos(angleRadians) * radius, 0, Math.sin(angleRadians) * radius), 1, 0, 0, 0, 0);

				angleDegrees += 45D;
			}
		}.runTaskTimer(plugin, 0L, 1L);
	}
}
