package cross.mods.portablebeacons.init;

import cross.mods.portablebeacons.PortableBeacons;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class PortableBeaconsTabs {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PortableBeacons.ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PORTABLE_CREATIVE_TAB = TABS.register("portable_beacons_tab",
            () -> CreativeModeTab.builder().icon(PortableItems.BEACON_SHELL::toStack).title(Component.translatable("itemGroup.portablebeacons"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(PortableItems.BEACON_SHELL.get());
                        output.accept(PortableItems.PORTABLE_BEACON_IRON.get());
                        output.accept(PortableItems.PORTABLE_BEACON_IRON_II.get());
                        output.accept(PortableItems.PORTABLE_BEACON_GOLD.get());
                        output.accept(PortableItems.PORTABLE_BEACON_GOLD_II.get());
                        output.accept(PortableItems.PORTABLE_BEACON_EMERALD.get());
                        output.accept(PortableItems.PORTABLE_BEACON_EMERALD_II.get());
                        output.accept(PortableItems.PORTABLE_BEACON_DIAMOND.get());
                        output.accept(PortableItems.PORTABLE_BEACON_DIAMOND_II.get());
                        output.accept(PortableItems.PORTABLE_BEACON_NETHERITE.get());
                        output.accept(PortableItems.PORTABLE_BEACON_NETHERITE_II.get());
                    }).build());

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
