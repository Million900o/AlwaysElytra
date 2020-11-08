package alwaysElytra.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import alwaysElytra.Main;


public class ToggleCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(Main.immunePlayers.contains(player.getName())) {
				Main.immunePlayers.remove(Main.immunePlayers.indexOf(player.getName()));
				player.setGliding(true);
				sender.sendMessage(ChatColor.GREEN + "You will now be stuck in elytra mode.");
			} else {
				Main.immunePlayers.add(player.getName());
				player.setGliding(false);
				sender.sendMessage(ChatColor.GREEN + "You are no longer stuck in elytra mode.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "This command is player only.");
		}
		
		return true;
	}

}
