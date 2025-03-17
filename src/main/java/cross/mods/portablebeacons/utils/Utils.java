package cross.mods.portablebeacons.utils;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.init.PortableComponentData;
import cross.mods.portablebeacons.items.PortableBeaconItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.function.Consumer;

public class Utils {

    public static final int duration = PortableBeacons.CONFIG.costTimer.get() * 20 + 20;

    public static void applyEffects(Player player, PortableBeaconItem.Tiers tier) {
        List<MobEffectInstance> effects = tier.effects();
        for (MobEffectInstance effect : effects) {
            MobEffectInstance freshEffect = new MobEffectInstance(effect.getEffect(), duration, effect.getAmplifier(), false, PortableBeacons.CONFIG.particles.get(), true);
            player.addEffect(freshEffect);
        }
    }

    public static void applyInRadius(Player player, PortableBeaconItem.Tiers tier) {
        Level level = player.level();
        int range = PortableBeacons.CONFIG.effectRange.get();
        if (range == 0) return;
        if (!level.isClientSide()) {
            List<Player> list = level.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(range));
            for (Player players : list)
                applyEffects(players, tier);
        }
    }

    public static CompoundTag getNBTData(ItemStack stack) {
        return stack.has(PortableComponentData.CUSTOM_NBT)
                ? stack.get(PortableComponentData.CUSTOM_NBT).copyTag()
                : new CompoundTag();
    }

    public static void updateNBTData(ItemStack stack, Consumer<CompoundTag> updater) {
        CompoundTag tag = getNBTData(stack);
        updater.accept(tag);
        stack.set(PortableComponentData.CUSTOM_NBT, CustomData.of(tag));
    }
}
