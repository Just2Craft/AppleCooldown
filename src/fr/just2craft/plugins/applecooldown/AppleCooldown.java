package fr.just2craft.plugins.applecooldown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AppleCooldown extends JavaPlugin {
	
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(new AppleListener(), this);
		Bukkit.getServer().getLogger().info("[AppleCooldownV2] Le plugin est activ� !");
	}

	public void onDisable() {
		Bukkit.getServer().getLogger().info("AppleCooldownV2 d�sactiv� !");
	}
}