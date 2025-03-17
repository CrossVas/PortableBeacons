package cross.mods.portablebeacons.utils;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.items.PortableBeaconItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class EffectsHelper {

    public static final int duration = PortableBeacons.CONFIG.costTimer.get() * 20 + 20;

    public static void applyEffects(Player player, PortableBeaconItem.Tiers tier) {
        List<MobEffectInstance> effects = tier.effects();
        for (MobEffectInstance effect : effects) {
            MobEffectInstance freshEffect = new MobEffectInstance(effect.getEffect(), duration, effect.getAmplifier(), false, PortableBeacons.CONFIG.particles.get(), true);
            player.addEffect(freshEffect);
        }
    }

    public static void applyAura(Player player, PortableBeaconItem.Tiers tier) {
        Level level = player.getLevel();
        int range = PortableBeacons.CONFIG.effectRange.get();
        if (range == 0) return;
        if (!level.isClientSide()) {
            List<Player> list = level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(range));
            for (Player players : list)
                applyEffects(players, tier);
        }
    }
}
