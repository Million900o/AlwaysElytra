package alwaysElytra;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import alwaysElytra.commands.ToggleCommand;
import alwaysElytra.listeners.Listeners;

public class Main extends JavaPlugin {
	public static ArrayList<String> immunePlayers = new ArrayList<String>();
	
	@Override
	public void onEnable() {				
		//Register the listeners
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Listeners(), this);
		
		getCommand("toggle").setExecutor(new ToggleCommand());
		
		new Timer().scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				getServer().getOnlinePlayers().forEach(e -> {
					if(!e.hasPermission("elytra.propel")) return;
					if(!e.isGliding()) return;
					if(e.isSneaking() == false) return;
					double pitch = ((e.getLocation().getPitch() + 90) * Math.PI) / 180;
					double yaw = ((e.getLocation().getYaw() + 90) * Math.PI) / 180;
					double x = Math.sin(pitch) * Math.cos(yaw);
					double y = Math.sin(pitch) * Math.sin(yaw);
					double z = Math.cos(pitch);
					Vector vector = new Vector(x, z, y).multiply(1.2);
					e.setVelocity(vector);
				});
			}
			
		}, 50, 50);;
	}
}
