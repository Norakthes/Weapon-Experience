package xyz.norakthes.weaponexperience.Commands;

import de.tr7zw.changeme.nbtapi.NBTFile;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;

import java.io.File;
import java.io.IOException;

import static xyz.norakthes.weaponexperience.WeaponExperience.myInventory;

public class nbt implements CommandExecutor{
    //  Debug
    //  Gives item in main hand nbt
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("nbt")) {
            Player player = (Player) sender;
            ItemStack mainHandItem = player.getInventory().getItemInMainHand();
            NBTItem nbtItem = new NBTItem(mainHandItem);
            nbtItem.setInteger("Experience", 0);
            player.getInventory().remove(mainHandItem);
            mainHandItem = nbtItem.getItem();
            player.getInventory().addItem(mainHandItem);

            player.sendMessage("Yes");
            return true;
        }
        if (label.equalsIgnoreCase("info")){
            Player player = (Player) sender;
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            NBTItem nbti = new NBTItem(mainHand);
            Integer experience = nbti.getInteger("Experience");
            player.sendMessage(String.valueOf(experience));
            return true;
        }
        return false;
    }
}
