package alwaysElytra.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import alwaysElytra.Main;

public class Listeners implements Listener {
	
	@EventHandler
	public void isGliding(EntityToggleGlideEvent event) {
		if(Main.immunePlayers.contains(event.getEntity().getName())) return;
		event.setCancelled(true);
	}
	
	@EventHandler
	public void playerJoin(PlayerJoinEvent event) {
		if(Main.immunePlayers.contains(event.getPlayer().getName())) return;
		if(!event.getPlayer().hasPermission("elytra.toggleinair"))
			event.getPlayer().setGliding(true);
		event.getPlayer().setAllowFlight(true);
	}
	
//	@EventHandler
//	public void playerCrouch(PlayerToggleSneakEvent event) {
//		Player player = event.getPlayer();
//		if(!player.hasPermission("elytra.propel")) return;
//		if(!player.isGliding()) return;
////		event.setCancelled(true);
//	}
	
	@EventHandler
	public void playerToggleFly(PlayerToggleFlightEvent event) {
		if(Main.immunePlayers.contains(event.getPlayer().getName())) return;
		if(!event.getPlayer().hasPermission("elytra.toggleinair")) return;
		event.getPlayer().setGliding(!event.getPlayer().isGliding());
		event.setCancelled(true);
	}
	
}