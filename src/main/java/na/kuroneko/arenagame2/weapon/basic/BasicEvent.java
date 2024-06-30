package na.kuroneko.arenagame2.weapon.basic;

import na.kuroneko.arenagame2.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BasicEvent implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("기본 무기")) {
            event.setCancelled(true);
        }

        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null || event.getClickedInventory() == null) return;

        Material type = event.getCurrentItem().getType();

        switch (type) {
            case WOODEN_SWORD -> {
                player.getInventory().addItem(new ItemBuilder(Material.WOODEN_SWORD)
                        .setDisplay("기본 검").setUnbreakable(true).build());
                player.closeInventory();
            }

            case WOODEN_AXE -> {
                player.getInventory().addItem(new ItemBuilder(Material.WOODEN_AXE)
                        .setDisplay("기본 도끼").setUnbreakable(true).build());
                player.closeInventory();
            }

            case WOODEN_PICKAXE -> {
                player.getInventory().addItem(new ItemBuilder(Material.WOODEN_PICKAXE)
                        .setDisplay("기본 곡괭이").setUnbreakable(true).build());
                player.closeInventory();
            }
        }
    }
}
