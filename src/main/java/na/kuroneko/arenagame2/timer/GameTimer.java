package na.kuroneko.arenagame2.timer;

import na.kuroneko.arenagame2.ArenaGame2;
import na.kuroneko.arenagame2.utils.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameTimer {
    private BukkitTask timer;
    private static int time;

    public void startTimer() {
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                ++time;
            }
        }.runTaskTimer(ArenaGame2.instance, 0, 1L);
    }

    public void endTimer(Player player) {
        if (timer != null && !timer.isCancelled()) {
            timer.cancel();
            timer = null;
        }

        player.sendMessage("총 플레이타임: " + time/20 + "초");

        PlayerData data = PlayerData.loadFromJson(player.getName());
        data.setPlayTime(time/20);
        data.saveToJson();

        time = 0;
    }
}
