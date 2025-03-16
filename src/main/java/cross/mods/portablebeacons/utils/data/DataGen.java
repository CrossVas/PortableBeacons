package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.PortableBeacons;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent e) {
        DataGenerator generator = e.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = e.getLookupProvider();
        ExistingFileHelper fileHelper = e.getExistingFileHelper();
        if (e.includeServer()) {
            BlockTagsProvider blockTags = new BlockTagsProvider(packOutput, lookupProvider, PortableBeacons.ID, fileHelper) {
                @Override
                protected void addTags(HolderLookup.Provider provider) {}
            };
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new PortableBeaconsTagsProvider(packOutput, lookupProvider, blockTags, fileHelper));
            generator.addProvider(true, new PortableRecipeProvider(packOutput, lookupProvider));
        }
        if (e.includeClient()) {
            generator.addProvider(true, new PortableItemModelProvider(packOutput, fileHelper));
        }
    }
}
