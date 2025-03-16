package cross.mods.portablebeacons;

import cross.mods.portablebeacons.init.PortableItems;
import cross.mods.portablebeacons.utils.ConfigHandler;
import cross.mods.portablebeacons.utils.CuriosLoader;
import cross.mods.portablebeacons.utils.data.DataGen;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

@Mod(PortableBeacons.ID)
public class PortableBeacons {

    public static final String ID = "portablebeacons";
    public static final ConfigHandler CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    static {
        final Pair<ConfigHandler, ForgeConfigSpec> SPEC_PAIR = new ForgeConfigSpec.Builder().configure(ConfigHandler::new);
        CONFIG = SPEC_PAIR.getLeft();
        CONFIG_SPEC = SPEC_PAIR.getRight();
    }

    public static final CreativeModeTab TAB = new CreativeModeTab(ID) {
        @Override
        public ItemStack makeIcon() {
            return PortableItems.BEACON_SHELL.get().getDefaultInstance();
        }
    };

    public static final CuriosLoader CURIO_LOADER = new CuriosLoader();
    public static final boolean CURIO = ModList.get().isLoaded("curios");

    public PortableBeacons() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CONFIG_SPEC, ID + ".toml");
        PortableItems.register(bus);
        bus.addListener(this::commonLoad);
        bus.addListener(DataGen::gatherData);
    }

    public void commonLoad(FMLCommonSetupEvent e) {
        if (CURIO) CURIO_LOADER.preInit();
    }
}
