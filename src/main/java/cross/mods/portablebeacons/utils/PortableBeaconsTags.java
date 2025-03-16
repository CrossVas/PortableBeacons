package cross.mods.portablebeacons.utils;

import cross.mods.portablebeacons.PortableBeacons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PortableBeaconsTags {

    public static TagKey<Item> CURIO_BEACON = ItemTags.create(new ResourceLocation(PortableBeacons.ID, "beacons"));
    public static TagKey<Item> CURIO_RING = ItemTags.create(new ResourceLocation("curios", "ring"));
}
