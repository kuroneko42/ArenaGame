package na.kuroneko.arenagame2.command;

import com.google.common.collect.Lists;
import na.kuroneko.arenagame2.arena.Arena;
import na.kuroneko.arenagame2.game.GameController;
import na.kuroneko.arenagame2.timer.GameTimer;
import na.kuroneko.arenagame2.weapon.WeaponGUI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GameCommand extends BukkitCommand {

    public GameCommand(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("플레이어만 사용 가능");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage("/arena world | border | remove");
        }

        Arena arena = new Arena();
        GameController game = new GameController();

        String args0 = args[0];
        switch (args0) {
            case "tp" -> {
                arena.playerWorldTP(player);
            }

            case "world" -> {
                arena.creatWorld();
            }

            case "start" -> game.startGame(player);

            case "stop" -> game.endGame(player);
        }

        return false;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            return Lists.newArrayList("tp", "world", "start", "stop");
        }

        return super.tabComplete(sender, alias, args);
    }
}
