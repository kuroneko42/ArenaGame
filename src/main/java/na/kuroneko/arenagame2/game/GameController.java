package na.kuroneko.arenagame2.game;

import na.kuroneko.arenagame2.arena.Arena;
import na.kuroneko.arenagame2.round.RoundController;
import na.kuroneko.arenagame2.timer.GameTimer;
import na.kuroneko.arenagame2.utils.PlayerData;
import na.kuroneko.arenagame2.weapon.WeaponGUI;
import org.bukkit.entity.Player;

public class GameController {
    private final Arena arena = new Arena();
    private final RoundController round = new RoundController();
    private final GameTimer timer = new GameTimer();
    private final WeaponGUI weapon = new WeaponGUI();
    private boolean gameStarted = false;

    public void startGame(Player player) {
        PlayerData data = PlayerData.loadFromJson(player.getName());

        player.sendMessage("Player Name: " + data.getPlayerName() +
                "\nHighest Round: " + data.getHighestRound() +
                "\nPlay Time: " + data.getPlayTime() + "초");

        arena.creatBorder();
        arena.creatFiled();
        arena.playerTP(player);
        weapon.basic(player);
        timer.startTimer();
        round.startRound();
    }

    public void endGame(Player player) {
        arena.resetBorder();
        arena.playerWorldTP(player);
        arena.resetFiled();
        timer.endTimer(player);
        round.entRound(player);
    }
}
