package cross.mods.portablebeacons.utils.data;

import cross.mods.portablebeacons.PortableBeacons;
import cross.mods.portablebeacons.init.PortableItems;
import cross.mods.portablebeacons.utils.PortableBeaconsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class PortableBeaconsTagsProvider extends ItemTagsProvider {
    public PortableBeaconsTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                       TagsProvider<Block> blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTagProvider.contentsGetter(), PortableBeacons.ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(PortableBeaconsTags.ARMOR_TRIMS).add(
                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE,
                Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE
        );
        this.tag(PortableBeaconsTags.BEACONS).add(
                PortableItems.PORTABLE_BEACON_IRON.asItem(),
                PortableItems.PORTABLE_BEACON_IRON_II.asItem(),
                PortableItems.PORTABLE_BEACON_GOLD.asItem(),
                PortableItems.PORTABLE_BEACON_GOLD_II.asItem(),
                PortableItems.PORTABLE_BEACON_EMERALD.asItem(),
                PortableItems.PORTABLE_BEACON_EMERALD_II.asItem(),
                PortableItems.PORTABLE_BEACON_DIAMOND.asItem(),
                PortableItems.PORTABLE_BEACON_DIAMOND_II.asItem(),
                PortableItems.PORTABLE_BEACON_NETHERITE.asItem()
        );
        this.tag(PortableBeaconsTags.CURIO_BEACONS).addTag(PortableBeaconsTags.BEACONS);
    }
}
