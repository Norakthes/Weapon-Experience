package xyz.norakthes.weaponexperience.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static xyz.norakthes.weaponexperience.WeaponExperience.myInventory;

public class inv implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.openInventory(myInventory);
        return true;
    }
}
