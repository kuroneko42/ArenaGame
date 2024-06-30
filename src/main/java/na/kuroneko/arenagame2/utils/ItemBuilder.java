package na.kuroneko.arenagame2.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(@NotNull Material type) {
        item = new ItemStack(type);
        meta = item.getItemMeta();
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setDisplay(@NotNull String display) {
        meta.setDisplayName(display);
        return this;
    }

    public ItemBuilder setLore(@NotNull List<String> lore) {
        meta.setLore(lore);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        meta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        meta.setUnbreakable(unbreakable);
        return this;
    }

    public @NotNull ItemStack build() {
        item.setItemMeta(meta);
        return item.clone();
    }
}
