package na.kuroneko.arenagame2.mob;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import na.kuroneko.arenagame2.round.Round;
import na.kuroneko.arenagame2.round.RoundController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobSpawn implements Listener {
    // 월드를 생성. 이 월드에서 몬스터를 소환
    private final World world = Bukkit.getWorld("Arena");
    // 라운드를 관리하는 Round 객체를 생성
    private final Round round = new Round();
    private RoundController roundController;
    private Player player;

    // 몬스터를 소환하는 메서드
    public void spawnMob() {
        // "spade-soldier"라는 이름의 MythicMob을 가져온다.
        MythicMob mm = MythicBukkit.inst().getMobManager().getMythicMob("spade-soldier").orElse(null);

        // 가져온 몬스터가 null이 아닌 경우에만 실행합니다.
        if (mm != null) {
            // 몬스터를 소환할 위치를 설정
            Location loc = new Location(world, 0, 49, -11);
            // 몬스터를 소환 소환된 몬스터는 ActiveMob 객체로 반환
            ActiveMob spade = mm.spawn(BukkitAdapter.adapt(loc),1 );

            // ActiveMob 객체에서 LivingEntity 객체를 가져온다.
            LivingEntity entity = (LivingEntity) spade.getEntity().getBukkitEntity();

            // LivingEntity 객체에서 체력 속성을 가져온다.
            AttributeInstance healthAttribute = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            // 체력 속성이 null이 아닌 경우에만 실행
            if (healthAttribute != null) {
                // 기본 체력을 가져온다.
                double baseHealth = healthAttribute.getBaseValue();
//                double additionalHealth = getAdditionalHealthForRound(round.getCurrentRound(), "spade-soldier");
                // 라운드에 따른 추가 체력을 계산
                double addHealth = entity.getHealth() + round.getCurrentRound() * 10;
                //  기본 체력에 추가 체력을 더해 계산
                double newHealth = baseHealth + addHealth;
                // 새로운 체력을 설정
                healthAttribute.setBaseValue(newHealth);
                entity.setHealth(newHealth);
            }

            // LivingEntity 객체에서 공격력 속성을 가져온다.
            AttributeInstance damage = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
            // 공격력 속성이 null이 아닌 경우에만 실행
            if (damage != null) {
                // 기본 공격력을 가져옵니다.
                double baseDamage = damage.getBaseValue();
                // 라운드에 따른 새로운 공격력을 계산한다.
                double newDamage = getAdjustedDamageForRound(baseDamage, round.getCurrentRound());
                // 새로운 공격력을 설정한다.
                damage.setBaseValue(newDamage);
            }
        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == player) {
            roundController.nextRound();
        }
    }

    private double getAdjustedDamageForRound(double baseDamage, int round) {
        int multiplier = Math.floorDiv(round - 1, 5);
        return baseDamage * Math.pow(2, multiplier);
    }

//    private double getAdditionalHealthForRound(int round, String mobType) {
//        return switch (mobType) {
//            case "spadesoldier" -> round * 5;
//            case "heartsoldier" -> round * 10;
//            default -> 0.0;
//        };
//    }
}
