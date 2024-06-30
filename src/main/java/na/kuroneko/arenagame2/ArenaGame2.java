package na.kuroneko.arenagame2;

import na.kuroneko.arenagame2.command.GameCommand;
import na.kuroneko.arenagame2.mob.MobSpawn;
import na.kuroneko.arenagame2.weapon.basic.BasicEvent;
import na.kuroneko.arenagame2.weapon.reward.RewardEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArenaGame2 extends JavaPlugin {

    public static ArenaGame2 instance;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getCommandMap().register("", new GameCommand("arena"));
        Bukkit.getPluginManager().registerEvents(new BasicEvent(), this);
        Bukkit.getPluginManager().registerEvents(new RewardEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MobSpawn(), this);
    }

    @Override
    public void onDisable() {
    }
}
