package org.esoteric_organisation.tss_lobby_plugin.cosmetic.particle_cosmetic;

import org.esoteric_organisation.tss_lobby_plugin.cosmetic.Cosmetic;
import org.bukkit.Particle;

public interface ParticleCosmetic extends Cosmetic {
  Particle getParticle();

  boolean shouldSpawnWhenNotMoving();
}
