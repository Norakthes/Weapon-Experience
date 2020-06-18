package xyz.norakthes.weaponexperience;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class nbt implements CommandExecutor {
    //  Debug
    //  Gives item in main hand nbt
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equals("nbt")){
            Player player = (Player) sender;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            NBTItem nbtItem = new NBTItem(itemStack);
            nbtItem.setInteger("Experience",0);
            return true;
        }
        return false;
    }
}
