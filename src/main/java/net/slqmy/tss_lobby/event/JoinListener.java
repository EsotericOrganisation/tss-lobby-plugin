package net.slqmy.tss_lobby.event;

import net.kyori.adventure.title.TitlePart;
import net.slqmy.tss_core.data.Message;
import net.slqmy.tss_core.data.type.FireworkType;
import net.slqmy.tss_core.data.type.Rank;
import net.slqmy.tss_core.manager.MessageManager;
import net.slqmy.tss_lobby.TSSLobbyPlugin;
import org.bukkit.FireworkEffect;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class JoinListener implements Listener {

	private static final Random random = new Random();

	private final TSSLobbyPlugin plugin;

	public JoinListener(@NotNull TSSLobbyPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(@NotNull PlayerJoinEvent event) {
		Player player = event.getPlayer();

		MessageManager messageManager = plugin.getCore().getMessageManager();

		player.sendMessage(messageManager.getPlayerMessage(Message.WELCOME_CHAT_MESSAGE, player));
		player.sendTitlePart(TitlePart.TITLE, messageManager.getPlayerMessage(Message.THE_SLIMY_SWAMP, player));
		player.sendTitlePart(TitlePart.SUBTITLE, messageManager.getPlayerMessage(Message.WELCOME_SUBTITLE, player));

		Rank rank = plugin.getRanksPlugin().getRankManager().getPlayerRank(player);
		World playerWorld = player.getWorld();

		for (int i = 0; i < 5; i++) {
			Firework firework = playerWorld.spawn(
							player.getLocation().clone().add(
											random.nextDouble(-1D, 1D),
											2,
											random.nextDouble(-1D, 1D)),
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
				continue;
			}

			new BukkitRunnable() {

				double angleDegrees = 0;

				@Override
				public void run() {
					double angleRadians = Math.toRadians(angleDegrees);
					playerWorld.spawnParticle(spiralParticle, firework.getLocation().add(-Math.cos(angleRadians), 0, Math.sin(angleRadians)), 1);

					angleDegrees += 5;
				}
			}.runTaskTimer(plugin, 0L, 1L);
		}
	}
}
