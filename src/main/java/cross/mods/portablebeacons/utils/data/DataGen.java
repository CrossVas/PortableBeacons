package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.PortableBeacons;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        ExistingFileHelper fileHelper = e.getExistingFileHelper();
        if (e.includeServer()) {
            BlockTagsProvider blockTags = new BlockTagsProvider(generator, PortableBeacons.ID, fileHelper);
            generator.addProvider(true, new PortableBeaconsTagsProvider(generator, blockTags, fileHelper));
            generator.addProvider(true, new PortableRecipeProvider(generator));
        }
        if (e.includeClient()) {
            generator.addProvider(true, new PortableItemModelProvider(generator, fileHelper));
        }
    }
}
