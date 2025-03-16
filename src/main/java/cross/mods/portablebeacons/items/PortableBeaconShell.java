package cross.mods.portablebeacons.items;

import cross.mods.portablebeacons.PortableBeacons;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PortableBeaconShell extends Item {

    public PortableBeaconShell() {
        super(new Properties().stacksTo(1).tab(PortableBeacons.TAB));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltips, TooltipFlag tooltipFlag) {
        tooltips.add(Component.translatable("tooltip.portablebeacons.shell").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, pLevel, tooltips, tooltipFlag);
    }
}
