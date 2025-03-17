package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.init.PortableItems;
import cross.mods.portablebeacons.utils.PortableBeaconsTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class PortableBeaconsTagsProvider extends ItemTagsProvider {


    public PortableBeaconsTagsProvider(DataGenerator dataGenerator, BlockTagsProvider tagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, tagsProvider, PortableBeacons.ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(PortableBeaconsTags.BEACONS).add(
                PortableItems.PORTABLE_BEACON_IRON.get(),
                PortableItems.PORTABLE_BEACON_IRON_II.get(),
                PortableItems.PORTABLE_BEACON_GOLD.get(),
                PortableItems.PORTABLE_BEACON_GOLD_II.get(),
                PortableItems.PORTABLE_BEACON_EMERALD.get(),
                PortableItems.PORTABLE_BEACON_EMERALD_II.get(),
                PortableItems.PORTABLE_BEACON_DIAMOND.get(),
                PortableItems.PORTABLE_BEACON_DIAMOND_II.get(),
                PortableItems.PORTABLE_BEACON_NETHERITE.get()
        );
        this.tag(PortableBeaconsTags.CURIO_BEACONS).addTag(PortableBeaconsTags.BEACONS);
    }
}
