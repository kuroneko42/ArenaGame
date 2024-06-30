package na.kuroneko.arenagame2.weapon;

import na.kuroneko.arenagame2.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class WeaponGUI {

    public void basic(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5, "기본 무기");

        inv.setItem(20, new ItemBuilder(Material.WOODEN_SWORD).setDisplay("기본 검").setUnbreakable(true).build());
        inv.setItem(22, new ItemBuilder(Material.WOODEN_AXE).setDisplay("기본 도끼").setUnbreakable(true).build());
        inv.setItem(24, new ItemBuilder(Material.WOODEN_PICKAXE).setDisplay("기본 곡괭이").setUnbreakable(true).build());

        player.openInventory(inv);
    }

    public void reward(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5, "보상");

        inv.setItem(20, new ItemBuilder(Material.DIAMOND_SWORD).setDisplay("무기 뽑기").build());
        inv.setItem(22, new ItemBuilder(Material.COOKED_BEEF).setDisplay("음식 뽑기").build());
        inv.setItem(24, new ItemBuilder(Material.DIAMOND_HELMET).setDisplay("갑옷 뽑기").build());

        player.openInventory(inv);
    }
}
