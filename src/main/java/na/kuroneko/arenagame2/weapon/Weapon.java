package na.kuroneko.arenagame2.weapon;

import org.bukkit.entity.Player;

public interface Weapon {
    void giveStartItem(Player player);
    void giveReward(Player player);
    void clearInv(Player player);
}
