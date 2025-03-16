package cross.mods.portablebeacons.utils;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.items.PortableBeaconItem;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class EffectsHelper {

    public static final int duration = PortableBeacons.CONFIG.costTimer.get() * 20 + 20;

    public static void applyEffects(Player player, PortableBeaconItem.Tiers tier) {
        int amp = tier.amp() - 1;
        List<Holder<MobEffect>> effects = tier.effects();
        for (Holder<MobEffect> effect : effects) {
            player.addEffect(new MobEffectInstance(effect, duration, amp, false, true, true));
        }
    }

    public static void applyAura(Player player, PortableBeaconItem.Tiers tier) {
        Level level = player.level();
        int range = PortableBeacons.CONFIG.effectRange.get();
        if (range == 0) return;
        if (!level.isClientSide()) {
            List<Player> list = level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(range));
            for (Player players : list)
                applyEffects(players, tier);
        }
    }
}
