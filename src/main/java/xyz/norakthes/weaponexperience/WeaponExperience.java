package xyz.norakthes.weaponexperience;

import com.sun.tools.javac.code.Attribute;
import de.tr7zw.changeme.nbtapi.NBTCompoundList;
import de.tr7zw.changeme.nbtapi.NBTFile;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.norakthes.weaponexperience.Commands.exp;
import xyz.norakthes.weaponexperience.Commands.inv;
import xyz.norakthes.weaponexperience.Commands.nbt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class WeaponExperience extends JavaPlugin implements Listener{
    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD,1);
    ItemStack book = new ItemStack(Material.BOOK,1);
    NBTItem swordNBT = new NBTItem(sword);
    NBTItem bookNBT = new NBTItem(book);


    public static List<ItemStack> itemList = new ArrayList<>();
    static {
        itemList.add(new ItemStack(Material.DIAMOND_SWORD));
    }

    public static Inventory myInventory = Bukkit.createInventory(null, 9, "Test Inventory");
    static {
        myInventory.setItem(0, new ItemStack(Material.DIRT,1));
        myInventory.setItem(8, new ItemStack(Material.GOLD_BLOCK, 1));
    }

    @Override
    public void onEnable() {
        try {
            NBTFile file = new NBTFile(new File(getDataFolder(), "test.nbt"));

            //Gives NBT to crafting items
            swordNBT.setInteger("Experience",0);


            //Initializes NBT to crafted items
            sword = swordNBT.getItem();
            book = bookNBT.getItem();


            //Crafting recipes
            ShapedRecipe testRecipe = new ShapedRecipe(NamespacedKey.minecraft("test"), sword);
            ShapelessRecipe debug = new ShapelessRecipe(NamespacedKey.minecraft("yes"), sword);

            //Crafting recipe shapes
            // Sword
            testRecipe.shape(" * ", " * ", " % ");
            testRecipe.setIngredient('*', Material.DIAMOND);
            testRecipe.setIngredient('%', Material.STICK);

            // Debug recipe
            debug.addIngredient(Material.DIAMOND);

            //Add recipes to server
            getServer().addRecipe(debug);
            getServer().addRecipe(testRecipe);

            //Commands
            this.getCommand("nbt").setExecutor(new nbt());
            this.getCommand("inv").setExecutor(new inv());
            this.getCommand("exp").setExecutor(new exp());

            file.save();
            getServer().getPluginManager().registerEvents(this, this);
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
        if (!event.getPlayer().getInventory().getItemInMainHand().equals(new ItemStack(Material.AIR)) && event.getPlayer().getInventory().getItemInMainHand().equals(itemList)){
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            NBTItem nbtItem = new NBTItem(mainHand);
            Integer experience = nbtItem.getInteger("Experience");
            Bukkit.broadcastMessage(String.valueOf(nbtItem.getInteger("Experience")));
            boolean hasNBT = nbtItem.hasKey("Experience");
            if (!hasNBT) {
                nbtItem.setInteger("Experience", 1);
            }
            if (hasNBT) {
                experience++;
                nbtItem.setInteger("Experience", experience);

                Bukkit.broadcastMessage(String.valueOf(experience));
            }
            mainHand = nbtItem.getItem();
            player.getInventory().setItemInMainHand(mainHand);
            NBTCompoundList compoundList = nbtItem.getCompoundList("Attributes");
            Bukkit.broadcastMessage(String.valueOf(compoundList));
        }
    }
}
