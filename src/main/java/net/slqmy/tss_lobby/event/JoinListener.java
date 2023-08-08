package net.slqmy.tss_lobby.event;

import net.kyori.adventure.title.TitlePart;
import net.slqmy.tss_core.data.Message;
import net.slqmy.tss_core.data.type.FireworkType;
import net.slqmy.tss_core.data.type.Rank;
import net.slqmy.tss_core.manager.MessageManager;
import net.slqmy.tss_lobby.TSSLobbyPlugin;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.FireworkMeta;
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

		for (int i = 0; i < 5; i++) {
			final Firework firework = player.getWorld().spawn(
							player.getLocation().clone().add(
											random.nextDouble(-1D, 1D),
											1,
											random.nextDouble(-1D, 1D)),
							Firework.class
			);

			final FireworkMeta fireworkMeta = firework.getFireworkMeta();
			FireworkType fireworkType = rank.getFireworkType();

			for (FireworkEffect effect : fireworkType.getEffects()) {
				fireworkMeta.addEffects(effect);
			}

			fireworkMeta.setPower(fireworkType.getPower());

			firework.setFireworkMeta(fireworkMeta);
		}
	}
}
