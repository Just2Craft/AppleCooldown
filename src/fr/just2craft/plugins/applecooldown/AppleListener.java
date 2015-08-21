package fr.just2craft.plugins.applecooldown;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

public class AppleListener implements Listener {
	
	public static int tacheapple;

	Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("AppleCooldown");
	FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("AppleCooldown").getConfig();

	HashMap<String,Long> cooldownList = new HashMap<String,Long>();

	@EventHandler
	public void onConsume(final PlayerItemConsumeEvent e) {
		if (e.getItem().getType() == Material.GOLDEN_APPLE) {
			if (cooldownList.containsKey(e.getPlayer().getName())) {
				Integer timeToLeave = safeLongToInt(((cooldownList.get(e.getPlayer().getName()) + (config.getInt("cooldown") * 1000L)) - System.currentTimeMillis()) / 1000);
				e.setCancelled(true);
				e.getPlayer().sendMessage(config.getString("messages.onCooldown").replace("%t%", Integer.toString(timeToLeave)));
			}
			else {
				cooldownList.put(e.getPlayer().getName(), System.currentTimeMillis());
				e.getPlayer().sendMessage(config.getString("messages.cooldown").replace("%t%",Integer.toString(config.getInt("cooldown"))));
				tacheapple = Bukkit.getScheduler().scheduleSyncDelayedTask(AppleListener.this.plugin, new Runnable() {
					public void run() {
						cooldownList.remove(e.getPlayer().getName());
						//On vérifie que le joueur ne s'est pas déconnecté entre temps.
						if(e.getPlayer().isOnline()){
							e.getPlayer().sendMessage(config.getString("messages.endCooldown"));
						}
					}
				}, config.getInt("cooldown") * 20L);
			}
		}
	}

	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException(l + " Impossible de caster le Long en Integer.");
		}
		return (int) l;
	}
}