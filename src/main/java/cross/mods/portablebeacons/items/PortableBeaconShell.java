package cross.mods.portablebeacons.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class PortableBeaconShell extends Item {

    public PortableBeaconShell() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltips, TooltipFlag tooltipFlag) {
        tooltips.add(Component.translatable("tooltip.portablebeacons.shell").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltips, tooltipFlag);
    }
}
