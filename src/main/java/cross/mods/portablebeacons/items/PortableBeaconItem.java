package cross.mods.portablebeacons.items;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.init.PortableComponentData;
import cross.mods.portablebeacons.utils.EffectsHelper;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.List;

public class PortableBeaconItem extends Item {

    protected final Tiers TIER;
    private final String TIMER_TAG = "timer";
    private final String ENABLED_TAG = "enabled";

    public PortableBeaconItem(Properties properties, Tiers tier) {
        super(properties);
        this.TIER = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide()) {
            this.toggle(player.getMainHandItem(), player);
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        this.tickItem(stack, level, entity);
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public void tickItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity) {
        if (!level.isClientSide() && entity instanceof Player player) {
            if (!this.isToggledOn(stack) && !PortableBeacons.CONFIG.toggleable.get()) {
                this.toggleOn(stack);
            }

            if (this.isToggledOn(stack)) {
                if (this.getTimer(stack) >= this.getMaxTime() && player.totalExperience >= this.getExperienceCost()) {
                    int range = getRange();
                    if (range > 0) {
                        EffectsHelper.applyAura(player, this.TIER);
                    } else EffectsHelper.applyEffects(player, this.TIER);
                    if (!player.isCreative()) {
                        player.giveExperiencePoints(-this.getExperienceCost());
                    }

                    this.resetTimer(stack);
                } else {
                    this.tickTimer(stack);
                }
            }
        }
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return this.TIER.amp() == 2;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    public void toggle(ItemStack stack, Player player) {
        CompoundTag tag = getNBTData(stack);
        saveStatusData(stack, !tag.getBoolean(ENABLED_TAG));
        boolean active = isToggledOn(stack);
        if (active) {
            player.displayClientMessage(Component.translatable("tooltip.portablebeacons.mode", Component.translatable("tooltip.portablebeacons.mode.on").withStyle(ChatFormatting.GREEN)).withStyle(ChatFormatting.GOLD), false);
        } else {
            player.displayClientMessage(Component.translatable("tooltip.portablebeacons.mode", Component.translatable("tooltip.portablebeacons.mode.off").withStyle(ChatFormatting.RED)).withStyle(ChatFormatting.GOLD), false);
        }
    }

    private void toggleOn(ItemStack stack) {
        saveStatusData(stack, true);
    }

    private boolean isToggledOn(ItemStack stack) {
        CompoundTag tag = getNBTData(stack);
        return tag.getBoolean(ENABLED_TAG);
    }

    private int getRange() {
        return PortableBeacons.CONFIG.effectRange.get();
    }

    private int getExperienceCost() {
        return PortableBeacons.CONFIG.cost.get();
    }

    private int getMaxTime() {
        return PortableBeacons.CONFIG.costTimer.get() * 20;
    }

    private int getTimer(ItemStack stack) {
        CompoundTag tag = getNBTData(stack);
        return tag.getInt(TIMER_TAG);
    }

    private void tickTimer(ItemStack stack) {
        saveTimerData(stack, getTimer(stack) + 1);
    }

    private void resetTimer(ItemStack stack) {
        saveTimerData(stack, 0);
    }

    @Override
    public Component getName(ItemStack stack) {
        return super.getName(stack).copy().withStyle(this.TIER.style());
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag tooltipFlag) {
        boolean active = isToggledOn(stack);
        if (Screen.hasShiftDown()) {
            list.add(Component.translatable("tooltip.portablebeacons.desc.main").withStyle(ChatFormatting.YELLOW));
            if (this.TIER.amp() == 1) {
                list.add(Component.translatable("tooltip.portablebeacons.upgrade").withStyle(ChatFormatting.GREEN));
            }

            if (this.TIER == Tiers.NETHERITE || this.TIER == Tiers.NETHERITE_2) {
                list.add(Component.translatable("tooltip.portablebeacons.immunetofire").withStyle(ChatFormatting.LIGHT_PURPLE));
            }
        } else {
            if (this.getRange() > 0) {
                list.add(Component.translatable("tooltip.portablebeacons.range", Component.literal("" + this.getRange()).withStyle(ChatFormatting.AQUA)).withStyle(ChatFormatting.GOLD));
            }

            if (PortableBeacons.CONFIG.toggleable.get()) {
                if (active) {
                    list.add(Component.translatable("tooltip.portablebeacons.mode", Component.translatable("tooltip.portablebeacons.mode.on").withStyle(ChatFormatting.GREEN)).withStyle(ChatFormatting.GOLD));
                } else {
                    list.add(Component.translatable("tooltip.portablebeacons.mode", Component.translatable("tooltip.portablebeacons.mode.off").withStyle(ChatFormatting.RED)).withStyle(ChatFormatting.GOLD));
                }
                list.add(Component.translatable("tooltip.portablebeacons.toggle").withStyle(ChatFormatting.GRAY));
            }
            list.add(Component.translatable("tooltip.portablebeacons.sneak_info", Component.translatable("tooltip.portablebeacons.sneak_info.desc").withStyle(ChatFormatting.WHITE)).withStyle(ChatFormatting.GRAY));
            list.add(Component.translatable("tooltip.portablebeacons.provides").withStyle(ChatFormatting.YELLOW));

            List<MobEffectInstance> effects = this.TIER.effects();
            for (MobEffectInstance effect : effects) {
                int level = effect.getAmplifier();
                list.add(effect.getEffect().value().getDisplayName().copy().append(" ").append(Component.translatable("enchantment.level." + (level + 1))));
            }
        }
        super.appendHoverText(stack, context, list, tooltipFlag);
    }

    public CompoundTag getNBTData(ItemStack stack) {
        if (stack.has(PortableComponentData.CUSTOM_NBT)) {
            return stack.get(PortableComponentData.CUSTOM_NBT).copyTag();
        }
        return new CompoundTag();
    }

    public void saveTimerData(ItemStack stack, int timer) {
        CompoundTag tag = getNBTData(stack);
        tag.putInt(TIMER_TAG, timer);
        stack.set(PortableComponentData.CUSTOM_NBT, CustomData.of(tag));
    }

    public void saveStatusData(ItemStack stack, boolean status) {
        CompoundTag tag = getNBTData(stack);
        tag.putBoolean(ENABLED_TAG, status);
        stack.set(PortableComponentData.CUSTOM_NBT, CustomData.of(tag));
    }

    public enum Tiers {
        IRON(ChatFormatting.WHITE, 1, MobEffects.REGENERATION, MobEffects.MOVEMENT_SPEED),
        IRON_2(2, IRON),
        GOLD(ChatFormatting.GOLD, 1, IRON, MobEffects.JUMP),
        GOLD_2(2, GOLD),
        EMERALD(ChatFormatting.GREEN, 1, GOLD, MobEffects.DIG_SPEED),
        EMERALD_2(2, EMERALD),
        DIAMOND(ChatFormatting.AQUA, 1, EMERALD, MobEffects.DAMAGE_BOOST),
        DIAMOND_2(2, DIAMOND),
        NETHERITE(ChatFormatting.LIGHT_PURPLE, 1, DIAMOND, MobEffects.FIRE_RESISTANCE, MobEffects.DAMAGE_RESISTANCE),
        NETHERITE_2(2, NETHERITE);

        final List<Holder<MobEffect>> EFFECTS;
        final int AMPLIFICATION;
        final ChatFormatting STYLE;
        final List<MobEffectInstance> EFFECTS_LIST;

        Tiers(int amplification, Tiers baseTier, Holder<MobEffect>... additionalEffects) {
            this(baseTier.style(), amplification, combineEffects(baseTier.EFFECTS, additionalEffects));
        }

        Tiers(ChatFormatting style, int amplification, Tiers baseTier, Holder<MobEffect>... additionalEffects) {
            this(style, amplification, combineEffects(baseTier.EFFECTS, additionalEffects));
        }

        Tiers(ChatFormatting style, int amplification, Holder<MobEffect>... effects) {
            this.EFFECTS = List.of(effects);
            this.AMPLIFICATION = amplification;
            this.STYLE = style;
            this.EFFECTS_LIST = new ObjectArrayList<>();
            for (Holder<MobEffect> effect : effects) {
                MobEffectInstance instance = new MobEffectInstance(effect, 1, amplification - 1, false, true, true);
                this.EFFECTS_LIST.add(instance);
            }
        }

        private static Holder<MobEffect>[] combineEffects(List<Holder<MobEffect>> baseEffects, Holder<MobEffect>[] additionalEffects) {
            LinkedHashSet<Holder<MobEffect>> combined = new LinkedHashSet<>(baseEffects);
            combined.addAll(List.of(additionalEffects));
            return combined.toArray(new Holder[0]);
        }

        public int amp() {
            return this.AMPLIFICATION;
        }

        public List<MobEffectInstance> effects() {
            return this.EFFECTS_LIST;
        }

        public ChatFormatting style() {
            return this.STYLE;
        }
    }
}
