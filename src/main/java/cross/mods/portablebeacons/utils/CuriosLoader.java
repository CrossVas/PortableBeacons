package cross.mods.portablebeacons.utils;

import cross.mods.portablebeacons.PortableBeacons;
import net.neoforged.fml.InterModComms;
import top.theillusivec4.curios.api.SlotTypePreset;

public class CuriosLoader {

    public void preInit() {
        InterModComms.sendTo(PortableBeacons.ID, "curios", "register_type", () -> SlotTypePreset.CHARM.getMessageBuilder().build());
    }
}
