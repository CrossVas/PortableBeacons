package cross.mods.portablebeacons;

import cross.mods.portablebeacons.init.PortableBeaconsTabs;
import cross.mods.portablebeacons.init.PortableComponentData;
import cross.mods.portablebeacons.init.PortableItems;
import cross.mods.portablebeacons.utils.ConfigHandler;
import cross.mods.portablebeacons.utils.CuriosLoader;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

@Mod(PortableBeacons.ID)
public class PortableBeacons {

    public static final String ID = "portablebeacons";
    public static final ConfigHandler CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public static final CuriosLoader CURIO_LOADER = new CuriosLoader();
    public static final boolean CURIO = ModList.get().isLoaded("curios");

    static {
        final Pair<ConfigHandler, ModConfigSpec> SPEC_PAIR = new ModConfigSpec.Builder().configure(ConfigHandler::new);
        CONFIG = SPEC_PAIR.getLeft();
        CONFIG_SPEC = SPEC_PAIR.getRight();
    }

    public PortableBeacons(IEventBus eventBus, ModContainer mod) {
        mod.registerConfig(ModConfig.Type.COMMON, CONFIG_SPEC);
        mod.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        PortableBeaconsTabs.register(eventBus);
        PortableComponentData.register(eventBus);
        PortableItems.register(eventBus);
        eventBus.addListener(this::commonLoad);
    }

    public void commonLoad(FMLCommonSetupEvent e) {
        if (CURIO) CURIO_LOADER.preInit();
    }
}
