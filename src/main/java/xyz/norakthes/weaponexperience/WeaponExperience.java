package xyz.norakthes.weaponexperience;

import de.tr7zw.changeme.nbtapi.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public final class WeaponExperience extends JavaPlugin implements Listener{
    ItemStack item = new ItemStack(Material.DIAMOND_SWORD,1);
    ItemMeta itemMeta = item.getItemMeta();
    NBTItem nbti = new NBTItem(item);
    Integer lore;

    public void nbtTags() {

    }




    @Override
    public void onEnable() {
        this.getCommand("nbt").setExecutor(new nbt());
        try {
            NBTFile file = new NBTFile(new File(getDataFolder(), "test.nbt"));
            nbti.setInteger("Experience",0);
            item = nbti.getItem();
            file.save();
            ShapedRecipe testRecipe = new ShapedRecipe(NamespacedKey.minecraft("test"), item);
            ShapelessRecipe debug = new ShapelessRecipe(NamespacedKey.minecraft("yes"), item);
            getServer().getPluginManager().registerEvents(this, this);
            debug.addIngredient(Material.DIAMOND);
            testRecipe.shape(" * ", " * ", " % ");
            testRecipe.setIngredient('*', Material.DIAMOND);
            testRecipe.setIngredient('%', Material.STICK);
            getServer().addRecipe(debug);
            getServer().addRecipe(testRecipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        NBTItem nbtItem = new NBTItem(mainHand);
        Integer experience = nbtItem.getInteger("Experience");
        Bukkit.broadcastMessage(String.valueOf(nbtItem.getInteger("Experience")));
        boolean hasNBT = nbtItem.hasKey("Experience");
        if (hasNBT) {
            experience++;
            nbtItem.setInteger("Experience", experience);
            mainHand = nbtItem.getItem();
            player.getInventory().setItemInMainHand(mainHand);
            Bukkit.broadcastMessage(String.valueOf(experience));
        }
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event){

    }

}
