package fr.just2craft.plugins.applecooldown;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AppleCooldown extends JavaPlugin {

	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getServer().getPluginManager()
				.registerEvents(new AppleListener(), this);
		Bukkit.getServer().getLogger()
				.info("[AppleCooldownV2] Le plugin est activ� !");
	}

	public void onDisable() {
		Bukkit.getServer().getLogger().info("AppleCooldownV2 d�sactiv� !");
		
		// Arr�t de la t�che scheduler
		Bukkit.getScheduler().cancelTask(AppleListener.tacheapple);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		Player p = (Player) sender;

		if (p.hasPermission("applecooldown.reload")) {
			// On recharge la configuration
			reloadConfig();

			// Puis on charge le plugin pour actualiser les variables
			onEnable();
		} else {
			// Message d'erreur personnalisable dans la configuration
			sender.sendMessage("Erreur : Vous n'avez pas la permission de red�marrer ce plugin !");
		}

		return true;
	}

}