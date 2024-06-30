package na.kuroneko.arenagame2.arena;

import org.bukkit.*;
import org.bukkit.entity.Player;

public class Arena {
    private final World world = Bukkit.getWorld("Arena");

    public void creatWorld() {
        WorldCreator creator = new WorldCreator("Arena");
        creator.type(WorldType.FLAT);
        creator.generateStructures(false);

        World world = creator.createWorld();
        if (world != null) {
            world.setSpawnFlags(false, false);
            Bukkit.getLogger().warning("평지월드 생성 완료");
        } else {
            Bukkit.getLogger().warning("평지 월드 생성 실패");
        }
    }

    public void creatBorder() {
        if (world != null) {
            world.getWorldBorder().setCenter(0,0);
            world.getWorldBorder().setSize(29);
            world.getWorldBorder().setWarningDistance(0);
        }
    }

    public void resetBorder() {
        if (world != null) {
            world.getWorldBorder().reset();
        }
    }

    public void creatFiled() {
        filed(Material.REINFORCED_DEEPSLATE);
    }

    public void resetFiled() {
        filed(Material.AIR);
    }

    public void playerTP(Player player) {
        Location loc = new Location(world, 0, -49, -11);
        player.teleport(loc);
    }

    public void playerWorldTP(Player player) {
        Location loc = new Location(world, 0, -60,0);
        player.teleport(loc);
    }

    private void filed(Material type) {
        if (world != null) {
            for (int x = -15; x <= 15; x++) {
                for (int z = -15; z <= 15; z++) {
                    Location loc = new Location(world, x, -50, z);
                    loc.getBlock().setType(type);
                }
            }
        }
    }
}
