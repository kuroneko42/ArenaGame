package na.kuroneko.arenagame2.weapon.reward;

import na.kuroneko.arenagame2.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RewardEvent implements Listener {

    private final Map<ItemStack, Double> weapon = new HashMap<>();
    private final Map<ItemStack, Double> food = new HashMap<>();
    private final Map<ItemStack, Double> armor = new HashMap<>();
    private final Random random = new Random();

    public RewardEvent() {
        weapon.put(new ItemBuilder(Material.IRON_SWORD).build(), 0.2);
        weapon.put(new ItemBuilder(Material.IRON_AXE).build(), 0.2);
        weapon.put(new ItemBuilder(Material.IRON_PICKAXE).build(), 0.2);
        weapon.put(new ItemBuilder(Material.DIAMOND_SWORD).build(), 0.1);
        weapon.put(new ItemBuilder(Material.DIAMOND_AXE).build(), 0.1);
        weapon.put(new ItemBuilder(Material.DIAMOND_PICKAXE).build(), 0.1);
        weapon.put(new ItemBuilder(Material.NETHERITE_SWORD).build(), 0.05);
        weapon.put(new ItemBuilder(Material.NETHERITE_AXE).build(), 0.05);

        food.put(new ItemBuilder(Material.MELON_SLICE).setAmount(10).build(), 0.24);
        food.put(new ItemBuilder(Material.COOKED_BEEF).setAmount(5).build(), 0.24);
        food.put(new ItemBuilder(Material.CHORUS_FRUIT).setAmount(8).build(), 0.24);
        food.put(new ItemBuilder(Material.GOLDEN_CARROT).setAmount(8).build(), 0.24);
        food.put(new ItemBuilder(Material.GOLDEN_APPLE).setAmount(8).build(), 0.04);

        armor.put(new ItemBuilder(Material.IRON_HELMET).build(), 0.15);
        armor.put(new ItemBuilder(Material.IRON_CHESTPLATE).build(), 0.15);
        armor.put(new ItemBuilder(Material.IRON_LEGGINGS).build(), 0.15);
        armor.put(new ItemBuilder(Material.IRON_BOOTS).build(), 0.15);
        armor.put(new ItemBuilder(Material.DIAMOND_HELMET).build(), 0.075);
        armor.put(new ItemBuilder(Material.DIAMOND_CHESTPLATE).build(), 0.075);
        armor.put(new ItemBuilder(Material.DIAMOND_LEGGINGS).build(), 0.075);
        armor.put(new ItemBuilder(Material.DIAMOND_BOOTS).build(), 0.075);
        armor.put(new ItemBuilder(Material.NETHERITE_HELMET).build(), 0.025);
        armor.put(new ItemBuilder(Material.NETHERITE_CHESTPLATE).build(), 0.025);
        armor.put(new ItemBuilder(Material.NETHERITE_LEGGINGS).build(), 0.025);
        armor.put(new ItemBuilder(Material.NETHERITE_BOOTS).build(), 0.025);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("보상")) {
            event.setCancelled(true);

            if (event.getCurrentItem() == null || event.getClickedInventory() == null) return;

            Material type = event.getCurrentItem().getType();
            Player player = (Player) event.getWhoClicked();

            switch (type) {
                case DIAMOND_SWORD -> {
                    giveRandomItems(player, weapon);
                    player.closeInventory();
                }

                case COOKED_BEEF -> {
                    giveRandomItems(player, food);
                    player.closeInventory();
                }

                case DIAMOND_HELMET -> {
                    giveRandomItems(player, armor);
                    player.closeInventory();
                }
            }
        }
    }

    public void giveRandomItems(Player player, Map<ItemStack, Double> items) {
        double p = random.nextDouble();
        double probability = 0.0;
        for (Map.Entry<ItemStack, Double> entry : items.entrySet()) {
            probability += entry.getValue();
            if (p <= probability) {
                player.getInventory().addItem(entry.getKey());
                break;
            }
        }
    }
}
