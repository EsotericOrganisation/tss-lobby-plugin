package net.slqmy.tss_lobby.cosmetic.particle_cosmetic;

import net.slqmy.tss_lobby.cosmetic.Cosmetic;
import org.bukkit.Particle;

public interface ParticleCosmetic extends Cosmetic {
  Particle getParticle();

  boolean shouldSpawnWhenNotMoving();
}
