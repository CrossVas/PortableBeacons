package cross.mods.portablebeacons.utils;

import cross.mods.portablebeacons.PortableBeacons;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.SlotTypeMessage;

public class CuriosLoader {

    private static final String CURIOS_ID = "curios";
    private static final ResourceLocation CURIO_POCKET_ICON = new ResourceLocation(PortableBeacons.ID, "slot/curios/portablebeacons");
    private static final String SLOT_ID = PortableBeacons.ID;

    public static void init() {
        InterModComms.sendTo(CURIOS_ID, "register_type", () -> new SlotTypeMessage.Builder(SLOT_ID)
                .icon(CURIO_POCKET_ICON)
                .size(1)
                .build()
        );
    }

    @Mod.EventBusSubscriber(modid = PortableBeacons.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    private static final class ModEvents {
        private ModEvents() {}

        @SubscribeEvent
        public static void event(TextureStitchEvent.Pre e) {
            e.addSprite(CURIO_POCKET_ICON);
        }
    }
}
