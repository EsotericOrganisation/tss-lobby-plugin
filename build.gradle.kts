import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    `java-library`

    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1"
}

group = "org.esoteric_organisation"
version = "0.1"
description = "The plugin that manages The Slimy Swamp's lobby world!"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("com.github.EsotericOrganisation:tss-core-plugin:0.1.6:dev-all")
    compileOnly("com.github.EsotericOrganisation:tss-ranks-plugin:0.1.1:dev")

    paperweight.paperDevBundle("1.21-R0.1-SNAPSHOT")
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(21)
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
}

bukkitPluginYaml {
  main = "org.esoteric_organisation.tss_lobby_plugin.TSSLobbyPlugin"
  load = BukkitPluginYaml.PluginLoadOrder.STARTUP
  authors.addAll("Esoteric Organisation", "Esoteric Enderman")
  apiVersion = "1.21"
}
