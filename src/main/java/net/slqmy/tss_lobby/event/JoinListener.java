package net.slqmy.tss_lobby.event;

import net.kyori.adventure.title.TitlePart;
import net.slqmy.tss_core.data.Message;
import net.slqmy.tss_core.manager.MessageManager;
import net.slqmy.tss_lobby.TSSLobbyPlugin;
import org.bukkit.Color;
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

	private final MessageManager messageManager;

	public JoinListener(@NotNull TSSLobbyPlugin plugin) {
		this.messageManager = plugin.getCore().getMessageManager();
	}

	@EventHandler
	public void onJoin(@NotNull PlayerJoinEvent event) {
		Player player = event.getPlayer();

		player.sendMessage(messageManager.getPlayerMessage(Message.WELCOME_CHAT_MESSAGE, player));
		player.sendTitlePart(TitlePart.TITLE, messageManager.getPlayerMessage(Message.THE_SLIMY_SWAMP, player));
		player.sendTitlePart(TitlePart.SUBTITLE, messageManager.getPlayerMessage(Message.WELCOME_SUBTITLE, player));

		for (int i = 0; i < 5; i++) {
			final Firework firework = player.getWorld().spawn(
							player.getLocation().add(
											random.nextDouble(-1D, 1D),
											random.nextDouble(-1D, 1D),
											random.nextDouble(-1D, 1D)),
							Firework.class);

			final FireworkMeta fireworkMeta = firework.getFireworkMeta();

			fireworkMeta.addEffect(
							FireworkEffect.builder()
											.withColor(Color.RED)
											.withColor(Color.BLACK)
											.with(FireworkEffect.Type.values()[i])
											.withTrail()
											.withFlicker()
											.withFade(Color.BLACK)
											.build()
			);

			fireworkMeta.setPower(3);

			firework.setFireworkMeta(fireworkMeta);
		}
	}
}
