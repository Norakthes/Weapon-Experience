package xyz.norakthes.weaponexperience.Commands;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class exp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(mainHand);
        Integer experience = Integer.valueOf("Experience");
        player.sendMessage(String.valueOf(experience));
        return true;
    }
}
