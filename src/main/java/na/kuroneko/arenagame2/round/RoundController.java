package na.kuroneko.arenagame2.round;

import na.kuroneko.arenagame2.ArenaGame2;
import na.kuroneko.arenagame2.mob.MobSpawn;
import na.kuroneko.arenagame2.utils.PlayerData;
import na.kuroneko.arenagame2.weapon.WeaponGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RoundController {
    private final Round round = new Round();
    private MobSpawn mob;
    private WeaponGUI weapon = new WeaponGUI();

    public RoundController() {
        this.mob = new MobSpawn(this); // 여기서 this는 현재 RoundController 인스턴스를 의미합니다.
    }

    public void startRound() {
        mob.spawnMob();
    }

    public void nextRound(Player player) {
        round.nextRound();
        weapon.reward(player);
        Bukkit.getScheduler().runTaskLater(ArenaGame2.instance, this::startRound, 200L);
    }

    public void entRound(Player player) {
        PlayerData data = PlayerData.loadFromJson(player.getName());
        data.setHighestRound(round.getCurrentRound());
        data.saveToJson();
        round.reset();
    }
}
