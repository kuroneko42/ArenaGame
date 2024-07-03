package na.kuroneko.arenagame2;

import na.kuroneko.arenagame2.command.GameCommand;
import na.kuroneko.arenagame2.mob.MobSpawn;
import na.kuroneko.arenagame2.round.RoundController;
import na.kuroneko.arenagame2.weapon.basic.BasicEvent;
import na.kuroneko.arenagame2.weapon.reward.RewardEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArenaGame2 extends JavaPlugin {

    public static ArenaGame2 instance;

    private RoundController roundController = new RoundController();

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getCommandMap().register("", new GameCommand("arena"));
        Bukkit.getPluginManager().registerEvents(new BasicEvent(), this);
        Bukkit.getPluginManager().registerEvents(new RewardEvent(), this);
        Bukkit.getPluginManager().registerEvents(new MobSpawn(roundController), this);
    }

    @Override
    public void onDisable() {
    }
}
