package xyz.norakthes.weaponexperience;

import de.tr7zw.changeme.nbtapi.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public final class WeaponExperience extends JavaPlugin implements Listener {
    int xp = 0;
    ItemStack item = new ItemStack(Material.DIAMOND_SWORD,1);
    ItemMeta itemMeta = item.getItemMeta();
    NBTItem nbti = new NBTItem(item);
    Integer lore;

    public void nbtTags() {

    }




    @Override
    public void onEnable() {
        try {
            NBTFile file = new NBTFile(new File(getDataFolder(), "test.nbt"));
            NBTList attribute = nbti.getCompoundList("AttributeModifiers");
            item = nbti.getItem();
            file.save();
            ShapedRecipe testRecipe = new ShapedRecipe(NamespacedKey.minecraft("test"), item);
            getServer().getPluginManager().registerEvents(this, this);
            testRecipe.shape(" * ", " * ", " % ");
            testRecipe.setIngredient('*', Material.DIAMOND);
            testRecipe.setIngredient('%', Material.STICK);
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
        item = event.getPlayer().getItemInHand();
        nbti.setInteger("Experience",xp);
        lore = nbti.getInteger("Experience");
        xp = lore;
        item.setLore(Collections.singletonList("Experience: " + lore));
        xp++;
        item = nbti.getItem();
        //String nbt = nbtItem.getString("Test");

        Bukkit.broadcastMessage(item + " | ");

    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event){
        item = event.getCurrentItem();
        if (item.getType() == Material.DIAMOND_SWORD){
            nbti.setInteger("Experience",xp);
            lore = nbti.getInteger("Experience");
            item.setLore(Collections.singletonList("Experience: " + lore));
            item = nbti.getItem();
        }

    }

}
