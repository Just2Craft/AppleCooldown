package fr.just2craft.plugins.applecooldown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AppleCooldown extends JavaPlugin {
	
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager().registerEvents(new AppleListener(), this);
		Bukkit.getServer().getLogger().info("[AppleCooldown] Le plugin est activé !");
	}

	public void onDisable() {
		//Il fraudrai vider le HashMap ainsi que les ScheduledTask du plugin avant la désactivation
		Bukkit.getServer().getLogger().info("[AppleCooldown] désactivé !");
	}
}